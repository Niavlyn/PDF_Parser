#!/bin/bash
mkdir Corpus_2022_meta
mkdir Corpus_2022_txt
mkdir FinalProduction
pip install pdf2txt
python3 srcPy/main.py
cd srcJava
javac -d ../ *.java
cd ../
java Main
rm *.class
