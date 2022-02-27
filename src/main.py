import glob
import os.path
import os
import re
import shutil
from PyPDF2 import PdfFileReader

command = 'pip install pdf2txt'
os.system(command)

path = '../Corpus_2022/'
pathTxt = '../Corpus_2022_txt/'

def listdirectory(path):
    fichier = []
    l = glob.glob(path + '//*')
    for i in l:
        if os.path.isdir(i):
            fichier.extend(listdirectory(i))
        else:
            new_name = i.replace(" ", "")
            os.rename(i, new_name)
            fichier.append(i)
    return fichier

allFiles = []
allFiles = listdirectory(path)
txtFiles = []
pdfFiles = []

for x in allFiles:
    regex = re.search(".pdf$", x)
    if regex:
        pdfFiles.append(x)
        filename = ".." + x.split(".")[2] + ".txt"
        txtFiles.append((pathTxt + filename.split("/")[2]))

    regexTxt = re.search(".txt$", x)
    if regexTxt:
        shutil.copyfile(x, (pathTxt + x.split("/")[2]))

def convertPdfToTxt(filePdf, fileTxt):
    #si le fichier contient un espace, penser à ajouter un \ avant le caractère

    # pdf2txt.py[-P password] [-o output][-t text | html | xml | tag] [-O output_dir][-c encoding] [-s scale][-R
    # rotation] [-Y normal | loose | exact][-p pagenos] [-m maxpages] [-S][-C][-n][-A][-V] [-M char_margin][-L
    # line_margin] [-W word_margin] [-F boxes_flow][-d] input.pdf...
    command = 'pdf2txt.py -o ' + fileTxt + ' ' + filePdf
    os.system(command)

def metadonnees(path, pathPDF, nomFichier):
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


for i in range(len(txtFiles)) :
     with open(txtFiles[i], 'w', encoding='utf-8') as f:
         convertPdfToTxt(pdfFiles[i], txtFiles[i])
         banana = pdfFiles[i].split(".pdf")[0]
         banana = banana.split("/")[2]
         nomFichier = banana
         banana = "../Corpus_2022_meta/" + banana + "_meta.txt"
         metadonnees(banana,pdfFiles[i], nomFichier)
