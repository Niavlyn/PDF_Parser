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
pip install pdf2txt
python3 srcPy/main.py
ls
cd srcJava
javac -d ../ *.java
cd ../
java Main $ARG1
rm *.class

