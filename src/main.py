# import PyPDF2
#
# FILE_PATH = '../Corpus_2021/Das_Martins.pdf'
# with open(FILE_PATH, mode='rb') as f:
#     reader = PyPDF2.PdfFileReader(f)
#     page = reader.getPage(0) #accessing to first page in file
#     print(page.extractText())

from io import StringIO

from pdfminer.converter import TextConverter
from pdfminer.layout import LAParams
from pdfminer.pdfdocument import PDFDocument
from pdfminer.pdfinterp import PDFResourceManager, PDFPageInterpreter
from pdfminer.pdfpage import PDFPage
from pdfminer.pdfparser import PDFParser
import glob
import os.path
import os
import re

path = '../Corpus_2021/'


def listdirectory(path):
    fichier = []
    l = glob.glob(path + '\\*')
    for i in l:
        if os.path.isdir(i):
            fichier.extend(listdirectory(i))
        else:
            fichier.append(i)
    return fichier


pdfFiles = []
pdfFiles = listdirectory(path)
txtName = []
for x in pdfFiles:
    regex = re.search(".pdf$", x)
    if (regex):
        print(x)
        txtName.append(".." + x.split(".")[2] + ".txt")

print("\n")
for y in txtName:
    print(y)


#gs -o "output.pdf" -sDEVICE=pdfwrite input.pdf
def convertPdfToTxt(path):
    command = 'gs -o "' + path + "\" -sDEVICE=pdfwrite " + path
    print(command + "bbbbbbbbbbbbbbbbbbbbbbbb")
    os.system(command)
    output_string = StringIO()
    with open(path, 'rb') as in_file:
        parser = PDFParser(in_file)
        doc = PDFDocument(parser)
        rsrcmgr = PDFResourceManager()
        device = TextConverter(rsrcmgr, output_string, laparams=LAParams())
        interpreter = PDFPageInterpreter(rsrcmgr, device)
        for page in PDFPage.create_pages(doc):
            interpreter.process_page(page)
    return output_string



for i in range(len(pdfFiles)) :
     with open(txtName[i], 'w', encoding='utf-8') as f:
         print(pdfFiles[i] + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
         # print(convertPdfToTxt(pdfFiles[i]).getvalue())

         f.write(convertPdfToTxt(pdfFiles[i]).getvalue())


# print(convertPdfToTxt(pdfFiles[0]).getvalue())
