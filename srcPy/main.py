import glob
import os.path
import os
import re
import time
import shutil
from tika import parser
from PyPDF2 import PdfFileReader

path = '../Corpus_2022/'


print("                       _______                                                                                                     ")
print("_________   _...._     \  ___ `'.                       _________   _...._                                  __.....__              ")
print("\        |.'      '-.   ' |--.\  \       _.._           \        |.'      '-.                           .-''         '.            ")
print(" \        .'```'.    '. | |    \  '    .' .._|           \        .'```'.    '.          .-,.--.       /     .-''\"'-.  `. .-,.--.  ")
print("  \      |       \     \| |     |  '   | '                \      |       \     \   __    |  .-. |     /     /________\   \|  .-. | ")
print("   |     |        |    || |     |  | __| |__               |     |        |    |.:--.'.  | |  | |  _  |                  || |  | | ")
print("   |      \      /    . | |     ' .'|__   __|              |      \      /    ./ |   \ | | |  | |.' | \    .-------------'| |  | | ")
print("   |     |\`'-.-'   .'  | |___.' /'    | |                 |     |\`'-.-'   .' `\" __ | | | |  '-.   | /\    '-.____...---.| |  '-  ")
print("   |     | '-....-'`   /_______.'/     | |                 |     | '-....-'`    .'.''| | | |  .'.'| |// `.             .' | |      ")
print("  .'     '.            \_______|/      | |                .'     '.            / /   | |_| |.'.'.-'  /    `''-...... -'   | |      ")
print("'-----------'                          | |              '-----------'          \ \._,\ '/|_|.'   \_.'                     |_|      ")
print("                                       |_|                                      `--'  `\"                                           ")



def listdirectory(file_path):
    fichier = []
    l = glob.glob(file_path + '//*')
    for i in l:
        if os.path.isdir(i):
            fichier.extend(listdirectory(i))
        else:
            new_name = i.replace(" ", "")
            os.rename(i, new_name)
            fichier.append(i)
    return fichier

all_PDF_Files = listdirectory(path)
txtFiles = []
txtFilesTika = []
pdfFiles = []

globalpath = os.getcwd()

# TEST LINUX

print(os.getcwd())
test = os.getcwd()
path = globalpath + "/Corpus_2022/"
pathTxt = globalpath + '/Corpus_2022_txt/'
pathTxtTika = globalpath + '/Corpus_2022_txt_tika/'
all_PDF_Files = os.listdir(path)

if not os.path.exists(pathTxt):
    print("CREATING Corpus_2022_txt folder...")
    os.mkdir(pathTxt)
if not os.path.exists(pathTxtTika):
    print("CREATING Corpus_2022_txt_tika folder...")
    os.mkdir(pathTxtTika)

for x in all_PDF_Files:
    regex = re.search(".pdf$", x)
    filenamePDF2TXT = x.split(".")[0] + ".txt"
    filenameTIKA = x.split(".")[0] + "_tika.txt"
    if regex:
        pdfFiles.append(x)
        txtFiles.append((pathTxt + filenamePDF2TXT))
        txtFilesTika.append((pathTxtTika + filenameTIKA))

    regexTxt = re.search(".txt$", x)
    if regexTxt:
        shutil.copyfile((path + x), (pathTxt + filenamePDF2TXT))
        shutil.copyfile((path + x), (pathTxtTika + filenameTIKA))


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
