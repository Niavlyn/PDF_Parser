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
python3 srcPy/main.py
ls
cd srcJava
javac -d ../ *.java
cd ../

echo Choisir format de sortie : 1=texte, 2=XML, 3=texte+xml
read varname
if [ "$varname" -eq "1" ]; then
   echo "format texte";
   exit;
elif [ "$varname" -eq "2" ]; then
   echo "format XML";
   exit;
elif [ "$varname" -eq "2" ]; then
  echo "format texte + XML";
  exit;
fi

echo It\'s nice to meet you $varname
java Main $ARG1
rm *.class

