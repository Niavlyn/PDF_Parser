#!/bin/bash
ARG1=${1:-xt}
rm -rf Corpus_2022_meta
rm -rf Corpus_2022_txt
rm -rf FinalProduction
rm -rf FinalProductionXML
mkdir Corpus_2022_meta
mkdir Corpus_2022_txt
mkdir FinalProduction
mkdir FinalProductionXML
python3 -m pip install pdf2txt --quiet
python3 -m pip install tika --quiet
python3 srcPy/main.py
python3 srcPy/cleaningFiles.py

cd srcJava
javac -d ../ *.java
cd ../
#printf "\n\n###########################################################################################\n\n"

echo "Choisir format de sortie :"
echo "1 : TXT | 2 : XML | 3 : TXT + XML"
read varname
if [ "$varname" -eq "1" ]; then
   printf "## Sortie TXT selectionnée ##\n";
   ARG1=${1:-t}
elif [ "$varname" -eq "2" ]; then
   printf "## Sortie XML selectionnée ##\n";
   ARG1=${1:-x}
elif [ "$varname" -eq "3" ]; then
  printf "## Sortie TXT + XML selectionnée ##\n";
fi

java Main -$ARG1
rm *.class

