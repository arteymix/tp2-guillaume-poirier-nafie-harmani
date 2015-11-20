# Introduction #

Les canons sont instanciés au début du programme et non à la volée.
Il y a deux canons possibles.

# Variables #
### MOVEMENT\_INCREMENT\_CANON ###
| > 0 | Le canon se déplace normalement |
|:----|:--------------------------------|
| 0   | Le canon ne peut pas se déplacer |
| < 0 | Les contrôles sont opposés et le canon peut sortir de la carte |

### ANGLE\_INCREMENT\_CANON ###

| 0 | La tête de canon ne bouge pas |
|:--|:------------------------------|
| Négatif | La tête de canon bouge de façon inverse |
| Positif | La tête de canon bouge normalement |


### CANON\_1\_ID & CANON\_2\_ID ###

Ces variables sont des constantes d'identification, elles peuvent prendre n'importe quelle valeur entière, du moment qu'elles soient différentes l'une de l'autre.

### FAST\_SHOT\_OBTAINED & POWER\_FAST\_SHOT\_OBTAINED && POWER\_SHOT\_OBTAINED ###

Ces variables n'ont aucune implication dans le jeu, elles ne servent qu'à l'affichage dans le mode de débogage.


### VIE\_INIT\_CANON ###

Voir les jeux de test sur la variable vie, cette valeur y est directement affectée lorsque le constructeur est appelé.

### isCanon2ValidTarget ###

| true | Le canon 2 apparaît en début de partie |
|:-----|:---------------------------------------|
| false | Le jeu fonctionne normalement avec un seul canon en début de partie |

### peutTirer ###
| true | Le canon peut tirer |
|:-----|:--------------------|
| false | Le canon est incapable de tirer |

### position ###

Objet [Vecteur](Vecteur.md).

### positionA ###
Objet [Vecteur](Vecteur.md).

### positionB ###
Objet [Vecteur](Vecteur.md).

### positionC ###
Objet [Vecteur](Vecteur.md).

### positionD ###
Objet [Vecteur](Vecteur.md).

### tetha ###
| 0 | La tête de canon pointe vers le bas, comme voulu |
|:--|:-------------------------------------------------|
| Math.PI | La tête de canon est en bonne position, c'est sa position initiale |
| Math.PI / 2 | La tête de canon est à 90 degrés, comme voulu    |

### vie ###
| Négatif | Le canon apparaît, mais à la moindre touche, la partie est terminée |
|:--------|:--------------------------------------------------------------------|
| 0       | Le canon apparaît, mais la partie se termine à la première collision |
| 0 > x > VIE\_INIT\_CANON | Le canon commence avec des vies en moins                            |
| VIE\_INIT\_CANON | Les vies sont définies comme voulu                                  |
| > VIE\_INIT\_CANON | La barre de vie est dessinés à l'extérieur du jeu                   |