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
cd srcJava
javac -d ../ *.java
cd ../
printf "\n\n###########################################################################################\n\n"

echo Choisir format de sortie : 1=texte, 2=XML, 3=texte+xml
read varname
if [ "$varname" -eq "1" ]; then
   echo "format texte";
elif [ "$varname" -eq "2" ]; then
   echo "format XML";
elif [ "$varname" -eq "3" ]; then
  echo "format texte + XML";
fi

java Main -$ARG1
rm *.class

