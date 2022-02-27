# PDF_Parser

Projet de programmation de L3 MIS à l'UBS


Si vous lisez ce fichier sur une version téléchargée, préférez la lecture sur la version en ligne, plus comfortable :
https://github.com/Nefaryos/PDF_Parser

Ce projet consiste à concevoir un logiciel capable de transformer un article scientifique de type PDF en fichier texte offrant une présentation plus lisible du document. Ce projet à réaliser en groupe doit être basé sur la méthode de gestion de projet SCRUM.

Les différents objectifs de ce projet sont de développer des compétences dans les domaines suivants :
* Analyse
* Codage
* Tests
* Gestion de projet

    
Organisation :
-
Pour avancer dans le projet, notre équipe disposera de 3h00 par semaine jusqu'à la fin du semestre 2. Ces différentes séances sont découpées en sprint de 2 à 3 semaines durant lesquelles les différentes étapes du SCRUM seront appliquées.
Ce projet peut bien entendu progresser en dehors des horaires imposé.


Avancement théorique du projet :
-
* Semaine 04 (24/01/2022) : Lancement du projet, mise en place des différents outils
* Semaine 05 (31/01/2022) : Sprint 1 - Comparaison des différents PDFParser existants
* Semaine 06 (07/02/2022) : Sprint 2 - Transformation des fichiers PDF en txt, récupération des données (NomFichier, Titre, Auteurs, Abstract) pour les stocker dans un fichier txt

Suivi de l'avancement (rendus) :
-
* ~~07/02/2022 : Dépôt du Sprint 1~~
* 28/02/2022 : Dépôt du Sprint 2

Procédure d'obtention des fichiers finaux :
-
1. Sous Linux (non fonctionnel sous Linux, le sera lors du prochain sprint), lancer le programme srcPy/main.py. Ce programme va permettre d'effectuer les actions suivantes :
    * Copie des fichiers du dossier Corpus_2022 au format **txt** vers le dossier Corpus_2022_txt
    * Transformation des fichiers du dossier Corpus_2022 au format **PDF** au format txt, dans le dossier Corpus_2022_txt
    * Récupération des métadonnées des fichiers **PDF** du dossier Corpus_2022 pour les stocker dans des fichiers **txt** dans le dossier Corpus_2022_meta
2. Sous Windows (n'a pas été testé sous Linux, le sera lors du prochain sprint), lancer le Main.java pour obtneir les fichiers terminaux au bon format :
    * Nom du fichier sur 1 ligne
    * Titre sur 1 ligne
    * Auteurs sur 1 ligne
    * Abstract sur 1 ligne


