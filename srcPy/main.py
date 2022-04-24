import glob
import os.path
import os
import re
import time
import shutil
from tika import parser
from PyPDF2 import PdfFileReader

path = ""
cpt = 0

def print_title():
    print(
        "                       _______                                                                                                     ")
    print(
        "_________   _...._     \  ___ `'.                       _________   _...._                                  __.....__              ")
    print(
        "\        |.'      '-.   ' |--.\  \       _.._           \        |.'      '-.                           .-''         '.            ")
    print(
        " \        .'```'.    '. | |    \  '    .' .._|           \        .'```'.    '.          .-,.--.       /     .-''\"'-.  `. .-,.--.  ")
    print(
        "  \      |       \     \| |     |  '   | '                \      |       \     \   __    |  .-. |     /     /________\   \|  .-. | ")
    print(
        "   |     |        |    || |     |  | __| |__               |     |        |    |.:--.'.  | |  | |  _  |                  || |  | | ")
    print(
        "   |      \      /    . | |     ' .'|__   __|              |      \      /    ./ |   \ | | |  | |.' | \    .-------------'| |  | | ")
    print(
        "   |     |\`'-.-'   .'  | |___.' /'    | |                 |     |\`'-.-'   .' `\" __ | | | |  '-.   | /\    '-.____...---.| |  '-  ")
    print(
        "   |     | '-....-'`   /_______.'/     | |                 |     | '-....-'`    .'.''| | | |  .'.'| |// `.             .' | |      ")
    print(
        "  .'     '.            \_______|/      | |                .'     '.            / /   | |_| |.'.'.-'  /    `''-...... -'   | |      ")
    print(
        "'-----------'                          | |              '-----------'          \ \._,\ '/|_|.'   \_.'                     |_|      ")
    print(
        "                                       |_|                                      `--'  `\"                                           ")
    print("\n\n###########################################################################################\n\n")


print_title()


def list_directory(file_path):
    global cpt
    l = glob.glob(file_path + '//*')
    for k in l:
        if os.path.isdir(k):
            list_directory(k)
        elif re.search(".pdf$", k) or re.search(".txt$", k):
            print(cpt, " " + k)
            cpt = cpt + 1


def get_file_in_directory(file_path, index):
    l = glob.glob(file_path + '//*')
    cmp = 0
    for k in l:
        if os.path.isdir(k):
            list_directory(k)
        elif cmp == index:
            return k
        cmp = cmp + 1


text = input("Voulez-vous utiliser le répertoire par défaut \"PDF_Parser/Corpus_2022\" ? | O/n : ")
default_directory = False
globalpath = os.getcwd()
if text != 'n':
    default_directory = True
    path = globalpath + "/Corpus_2022/"
else:
    path = input("\nEntrez le path vers votre dossier contenant le corpus : ")

print("PATH", path)

# Liste les fichiers d'un dossier passé en parametre
def listFilesAndStoreThem(file_path):
    fichier = []
    l = glob.glob(file_path + '//*')
    for i in l:
        print("I : ", i)
        i = str(i)

        if os.path.isdir(i):
            fichier.extend(listFilesAndStoreThem(i))
        elif re.search(".pdf$", i) or re.search(".txt$", i):
            new_name = i.replace(" ", "")
            os.rename(i, new_name)
            fichier.append(i)
    return fichier


numbers = []
all_PDF_Files = listFilesAndStoreThem(path)


# Demande à l'utilisateur le n° des fichiers à sélectionner
def ask_for_files():
    print("Sélectionnez les pdf à convertir en tapant le n° du PDF et en le séparant du suivant par ENTREE.")
    print("Pour stoper la sasie, utilisez le symbole #")

    user_input = input()
    while user_input != "#":
        if user_input.isdigit() and cpt > int(user_input) >= 0:
            numbers.append(user_input)
        else:
            print("Merci d'utiliser un nombre présent dans la liste")
        user_input = input()


txtFiles = []
txtFilesTika = []
pdfFiles = []

pathTxt = globalpath + '/Corpus_2022_txt/'
pathTxtTika = globalpath + '/Corpus_2022_txt_tika/'

if not os.path.exists(pathTxt):
    print("CREATING Corpus_2022_txt folder...")
    os.mkdir(pathTxt)
if not os.path.exists(pathTxtTika):
    print("CREATING Corpus_2022_txt_tika folder...")
    os.mkdir(pathTxtTika)


def default_behavior():

    for x in all_PDF_Files:
        regex = re.search(".pdf$", x)
        filenamePDF2TXT = x.split(".")[0] + ".txt"
        filenameTIKA = x.split(".")[0] + ".txt"
        if regex:
            pdfFiles.append(x)
            txtFiles.append((pathTxt + filenamePDF2TXT))
            txtFilesTika.append((pathTxtTika + filenameTIKA))

        regexTxt = re.search(".txt$", x)
        if regexTxt:
            shutil.copyfile((path + x), (pathTxt + filenamePDF2TXT))
            shutil.copyfile((path + x), (pathTxtTika + filenameTIKA))

def for_selected_files():
    for x in all_PDF_Files:
        print("TYPE : ", type(x))
        regex = re.search(".pdf$", x)
        filenamePDF2TXT = x.split(".")[0] + ".txt"
        filenameTIKA = x.split(".")[0] + ".txt"
        filenamePDF2TXT = filenamePDF2TXT.rsplit('/', 1)[1]
        filenameTIKA = filenameTIKA.rsplit('/', 1)[1]
        if regex:
            pdfName = x.rsplit('/', 1)[1]
            pdfFiles.append(pdfName)
            txtFiles.append((pathTxt + filenamePDF2TXT))
            txtFilesTika.append((pathTxtTika + filenameTIKA))

        regexTxt = re.search(".txt$", x)
        if regexTxt:
            filenamePDF2TXT = x.rsplit('/', 1)[1]
            filenameTIKA = filenamePDF2TXT
            shutil.copyfile(x, (pathTxt + filenamePDF2TXT))
            shutil.copyfile(x, (pathTxtTika + filenameTIKA))

print("PATH 2 : ", path)

allCorpus = input("\nVoulez-vous parser tout le contenu du dossier ? | O/n : ")
if allCorpus == 'n':
    selectOrDelete = input("\nVoulez-vous sélectionner les fichiers à parser (S) ou selectionner les fichiers à ne "
                           "pas parser (D) ? ")
    if selectOrDelete != 'D':
        list_directory(path)
        ask_for_files()
        all_PDF_Files.clear()
        for selectedFile in numbers:
            all_PDF_Files.append(get_file_in_directory(path, int(selectedFile)))

    else:
        list_directory(path)
    for_selected_files()
else:
    all_PDF_Files = os.listdir(path)
    if default_directory:
        path = globalpath + "/Corpus_2022/"

    else :
        print("PATH 3 : ", path)
    default_behavior()


def convertPdfToTxt(filePdf, fileTxt):
    # si le fichier contient un espace, penser à ajouter un \ avant le caractère

    # pdf2txt.py[-P password] [-o output][-t text | html | xml | tag] [-O output_dir][-c encoding] [-s scale][-R
    # rotation] [-Y normal | loose | exact][-p pagenos] [-m maxpages] [-S][-C][-n][-A][-V] [-M char_margin][-L
    # line_margin] [-W word_margin] [-F boxes_flow][-d] input.pdf...
    command = 'pdf2txt.py -o ' + fileTxt + ' ' + filePdf
    os.system(command)


def convertTika(filePdf):
    parsed_pdf = parser.from_file(filePdf)
    output = parsed_pdf['content']
    return output


def metadata(path, pathPDF, nomFichier):
    with open(pathPDF, 'rb') as f:
        pdf = PdfFileReader(f)
        info = pdf.getDocumentInfo()

    auteurs = info.author
    if not auteurs:
        auteurs = ''

    titre = info.title
    if not titre:
        titre = ''

    with open(path, 'w') as f:
        f.write(nomFichier + '\n' + titre + '\n' + auteurs)


# Print iterations progress
def printProgressBar(iteration, total, prefix='', suffix='', decimals=1, length=100, fill='█', printEnd="\r"):
    """
    Call in a loop to create terminal progress bar
    @params:
        iteration   - Required  : current iteration (Int)
        total       - Required  : total iterations (Int)
        prefix      - Optional  : prefix string (Str)
        suffix      - Optional  : suffix string (Str)
        decimals    - Optional  : positive number of decimals in percent complete (Int)
        length      - Optional  : character length of bar (Int)
        fill        - Optional  : bar fill character (Str)
        printEnd    - Optional  : end character (e.g. "\r", "\r\n") (Str)
    """
    percent = ("{0:." + str(decimals) + "f}").format(100 * (iteration / float(total)))
    filledLength = int(length * iteration // total)
    bar = fill * filledLength + '-' * (length - filledLength)
    print(f'\r{prefix} |{bar}| {percent}% {suffix}', end=printEnd)
    # Print New Line on Complete
    if iteration == total:
        print()


print("Conversion avec le convertisseur n°1 (PDF2TXT) : ")

printProgressBar(0, len(txtFiles), prefix='Progress:ion', suffix='', length=50)
for i in range(len(txtFiles)):
    with open(txtFiles[i], 'w', encoding='utf-8') as f:
        realPdfPath = path + pdfFiles[i]
        convertPdfToTxt(realPdfPath, txtFiles[i])
        pathMeta = globalpath + "/Corpus_2022_meta/" + pdfFiles[i] + "_meta.txt"
        nomFichier = pdfFiles[i]
        metadata(pathMeta, realPdfPath, nomFichier)
        time.sleep(0.1)
        # Update Progress Bar
        printProgressBar(i + 1, len(txtFiles), prefix='Progression:', suffix='', length=50)

print("Conversion avec le convertisseur n°2 (TIKA) : ")

printProgressBar(0, len(txtFilesTika), prefix='Progress:ion', suffix='', length=50)
for i in range(len(txtFilesTika)):
    realPdfPath = path + pdfFiles[i]
    result = convertTika(realPdfPath)
    result = str(result)
    with open(txtFilesTika[i], "w") as f:
        f.write(result.lstrip())
    time.sleep(0.1)
    # Update Progress Bar
    printProgressBar(i + 1, len(txtFilesTika), prefix='Progression:', suffix='', length=50)
