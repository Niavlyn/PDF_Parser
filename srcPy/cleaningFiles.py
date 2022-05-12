import glob
import os
import re

globalpath = os.getcwd()
pathTxt = globalpath + '/Corpus_2022_txt/'
pathTxtTika = globalpath + '/Corpus_2022_txt_tika/'




def listFilesAndStoreThem(file_path):
    global path
    fichier = []
    l = glob.glob(file_path + '//*')
    for i in l:
        i = str(i)

        if os.path.isdir(i):
            fichier.extend(listFilesAndStoreThem(i))
        elif re.search(".txt$", i):
            new_name = i.replace(" ", "")
            os.rename(i, new_name)
            fichier.append(i)
    return fichier

txtFiles = listFilesAndStoreThem(pathTxt)
txtFilesTika = listFilesAndStoreThem(pathTxtTika)

trCommand = 'tr -cd \'\\11\\12\\15\\40-\\176\''

for i in range(len(txtFiles)):
    nameOfFile = txtFiles[i].split("/")
    splitName = nameOfFile
    if(len(nameOfFile) > 0):
        splitName = nameOfFile[len(nameOfFile)-1]

    # print(trCommand + " <" + txtFiles[i] + "> " + splitName)
    os.system(trCommand + " <" + txtFiles[i] + "> " + txtFiles[i] + "modif")
    os.system("rm " + txtFiles[i])
    os.system("mv " + txtFiles[i] + "modif " + txtFiles[i])

for i in range(len(txtFilesTika)):
    nameOfFile = txtFilesTika[i].split("/")
    splitName = nameOfFile
    if (len(nameOfFile) > 0):
        splitName = nameOfFile[len(nameOfFile) - 1]
    # print(trCommand + " <" + txtFilesTika[i] + "> " + splitName)
    os.system(trCommand + " <" + txtFilesTika[i] + "> " + txtFilesTika[i] + "modif")
    os.system("rm " + txtFilesTika[i])
    os.system("mv " + txtFilesTika[i] + "modif " + txtFilesTika[i])