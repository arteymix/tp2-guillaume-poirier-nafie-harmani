# Introduction #
Fichier de classe pour un projectile ennemi.

# Variables #

### ID ###
| Valeurs prévues par les constantes | Le projectile dont l'identificateur est prévu, apparaît.|
|:-----------------------------------|:--------------------------------------------------------|
| Valeurs imprévues                  |Un message qui dit :"Veuillez entre une identification valide (id) dans le constructuer de l'objet" apparaît. |

### POINTS ###
| Positif | Rajoute du pointage au pointage total. |
|:--------|:---------------------------------------|
| 0       | Ne rajoute aucun pointage au pointage total. |
| Négatif | Enlève du pointage au pointage total.  |


### dommage ###
| Positif |Fais du dommage sur le canon dont il rentre en collision avec. |
|:--------|:--------------------------------------------------------------|
| 0       |Ne fais pas de dommage sur le canon dont il rentre en collision avec. |
| Négatif | Ajoute des vies au canon dont il rentre en collision avec.    |

### isInvincible ###
| true |Le projectile ennemi est invincible. |
|:-----|:------------------------------------|
| false |Le projectile ennemi est destructible. |

### position ###
Objet de type [Vecteur](Vecteur.md).

### rectangle ###