# projet_AW L2 MIAGE 2022-2023

Binôme:
- TAHI Ezan
- DIENG Yama Dior

Travail réalisé:

1. Déplacement du curseur

On a créé un tableau d'entiers de deux valeurs pour conserver la position du curseur au cours du jeu.
Les valeurs de ce tableau sont redéfinies en fonction de la touche appuyée par l'utilisateur pour pouvoir faire boufger le curseur.
On a utilisé les attributs Config.longueurCarteXCases et Config.longueurCarteYCases pour arrêter le déplacement du curseur aux limites de la carte.

2. Affichage des images de chaque case

Pour cela on a transformé la variable carteString qui était dans le constructeur de la classe main.Jeu en attribut d'objet afin de l'utiliser dans la fonction display() pour afficher l'image correspondant au terrain associé à chaque case.
Nous avons créé la classe Terrain dans le dossier entities afin de gérer les terrains, leurs types et leurs images.

