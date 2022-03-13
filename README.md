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
* Sprint 1 - Du 24/01/2022 au 07/01/2022 : Lancement du projet, mise en place des différents outils. Comparaison des différents PDFParser existants.
* Sprint 2 - Du 07/02/2022 au 28/02/2022 : Transformation des fichiers PDF en txt, récupération des données (NomFichier, Titre, Auteurs, Abstract) pour les stocker dans un fichier txt.
* Sprint 3 - Du 28/02/2022 au 14/03/2022 : Ajout d'une sortie des données au formt XML. Récupération des adresses mails des auteurs ainsi que de la bibliographie.

Suivi de l'avancement (rendus) :
-
* ~~07/02/2022 : Dépôt du Sprint 1~~
* ~~28/02/2022 : Dépôt du Sprint 2~~
* ~~14/03/2022 : Dépôt du Sprint 3~~

Utilisation :
-

Prérequis : 

- Vous devez disposer de python3 et d'une version de JAVA égale ou supérieur à 11.
- La commande pip pour python doit être installée.

Attention, le programme fonctionne correctemment sous Linux, mais le fonctionnement du programme avec WSL sous Windows n'est pas garantit. 

1. Executer depuis un terminal le script bash à l'aide de la commande ```bash scriptExecution.sh```. Par défaut, le programme génère les fichiers au format txt et au format XML.

- Pour n'obtenir que des fichiers au format txt utilisez l'option ```-t```.
- Pour n'obtenir que des fichiers au format XML utilisez l'option ```-x```.

2. Les fichiers produits par le programme sont disponibles dans 2 dossiers générés, un par type de fichier : ```FinalProduction``` pour les fichiers txt et ```FinalProductionXML``` pour les fichiers XML.

