# Introduction #

# Variables #
Classe pour les objets Dessinable comme les nuages et les bancs de poissons.

### PROBABILITE\_APPARITION\_OBJET\_FLOTTANT ###
| Positif | Les objets flottants apparaissent normalement |
|:--------|:----------------------------------------------|
| 0       | Le jeu reste sur une page blanche             |
| Négatif | Le jeu reste sur une page blanche             |

### positionX ###
| Positif | Ils apparaissent décalé de la valeur de l'entier |
|:--------|:-------------------------------------------------|
| 0       | Il disparaissent immédiatement                   |
| Négatif | Ils disparaissent immédiatement                  |

### positionY ###
| > getCanvasSizeY() | Ils disparaissent immédiatement |
|:-------------------|:--------------------------------|
| Positif            | Ils apparaissent normalement    |
| 0                  | Ils apparaissent au point 0 en y |
| Négatif            | Ils apparaissent plus haut que l'origine  |