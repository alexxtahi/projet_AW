# projet_AW L2 MIAGE 2022-2023

Binôme:
- TAHI Ezan
- DIENG Yama Dior
- COULIBALY Aminata
Travail réalisé:

1. Déplacement du curseur

On a créé un tableau d'entiers de deux valeurs pour conserver la position du curseur au cours du jeu.
Les valeurs de ce tableau sont redéfinies en fonction de la touche appuyée par l'utilisateur pour pouvoir faire boufger le curseur.
On a utilisé les attributs Config.longueurCarteXCases et Config.longueurCarteYCases pour arrêter le déplacement du curseur aux limites de la carte.

2. Affichage des images de chaque case

Pour cela on a transformé la variable carteString qui était dans le constructeur de la classe main.Jeu en attribut d'objet afin de l'utiliser dans la fonction display() pour afficher l'image correspondante à chaque case.
Nous avons codé la fonction dispacthCaseString() dans laquelle nous avons utilisé la méthode split() des String afin de dispatcher, pour chaque case, le type de terrain (ou propiété et le joueur qui la possède) et l'unité potentiellement présente sur la case.

2.1. Affichage des terrains et propiétés

Nous avons créé les classes Terrain et Propriete dans le dossier /src/cases afin de gérer dynamiquement les terrains, les propriétés, leurs types et leurs images.
Le constructeur de la classe Terrain attribut automatiquement l'image correspondant au type de terrain
Une méthode affichage() à été créée dans cette classe pour faciler l'affichage des terrains sur la fenêtre du jeu.

2.2. Affichage des unités

Chaque unité a eue droit à une classe dans le dossier /src/unites qui héritent toutes de la super classe Unite, contenant toutes les propriétés et méthodes communes aux unités du jeu.
Elle dispose elle aussi d'une méthode affiche() jouant le même rôle que la méthode du même nom dans la classe Terrain.

3. Déplacements des unités
pour cela il faut au préalable avoir la destination et le cout du deplacement pour savoir si l'unité est en mesure de se deplacer nous avons utiliser la méthode getCoutDuDep() pour calculer le coût du déplacement 
si l'unité peut 


4. Touche pour lister les unités encore utilisables (bonus 1)

PointG est une fonction qui prend en paramètre la carte et l’index du joueur. Elle permet à partir du curseur d’identifier toutes les unités disponibles pour le joueur courant.

