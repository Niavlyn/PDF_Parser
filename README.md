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
* Sprint 3 - Du 28/02/2022 au 14/03/2022 : Ajout d'une sortie des données au format XML. Récupération des adresses mails des auteurs ainsi que de la bibliographie.
* Sprint 4 - Du 14/03/2022 au 24/04/2022 : Ajout d'un menu textuel et enrichissement des fichiers XML avec les affiliations des auteurs, le corps de l'article, la conclusion, la discussion et le nom du fichier d'origine.
* Sprint 5 - Du 24/04/2022 au 15/05/2022 : Optimisation.

Suivi de l'avancement (rendus) :
-
* ~~07/02/2022 : Dépôt du Sprint 1~~
* ~~28/02/2022 : Dépôt du Sprint 2~~
* ~~14/03/2022 : Dépôt du Sprint 3~~
* ~~24/04/2022 : Dépôt du Sprint 4~~
* ~~15/05/2022 : Dépôt final~~

Utilisation :
-

Prérequis : 

- Vous devez disposer de python3 et d'une version de JAVA égale ou supérieur à 11.
- La commande pip pour python doit être installée.

Attention, le programme fonctionne correctemment sous Linux, mais le fonctionnement du programme avec WSL sous Windows n'est pas garantit. 

1. Executer depuis un terminal le script bash à l'aide de la commande ```bash scriptExecution.sh```. Suivez les instructions du menu textuel pour utiliser le parseur. Vous pouvez choisir les fichiers/dossier à parser et le format de sortie des données (TXT ou XML).

2. Les fichiers produits par le programme sont disponibles dans 2 dossiers générés, un par type de fichier : ```FinalProduction``` pour les fichiers txt et ```FinalProductionXML``` pour les fichiers XML.

Conseils :
-

* Vous pouvez appuyer 2 fois sur ENTREE au lancement du programme pour parser entièrement le répertoire par défaut.
* Lorsque le programme est utilisé pour la première fois sur une session, le convertisseur téléchargera des données depuis un serveur, cette phase peut durer quelques minutes, mais ne sera pas rééfectué aux exécutions suivantes.


