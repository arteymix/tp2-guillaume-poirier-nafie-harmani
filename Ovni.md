# Introduction #
Classe pour les ovnis.

# Variables #

### ENNEMI\_X & ENNEMI\_X\_POINTS ###
Pas de jeux de tests, il s'agit d'identifiants uniques.


### ID ###
| Valeurs prévues (constantes prédéfinies) |L'ovni dont l'identificateur est prévu, apparaît.|
|:-----------------------------------------|:------------------------------------------------|
| Valeurs imprévues                        | Un message qui dit :"Veuillez entre une identification valide (id) dans le constructuer de l'objet" apparaît.|

### PROBABILITE\_APPARITION\_OVNIS ###
| Positif | Apparition d'un ovni selon la fréquence de la probabilité, |
|:--------|:-----------------------------------------------------------|
| 0       |Un message d'erreur apparaît qui dit :Exception in thread "AWT-EventQueue-0" java.lang.IllegalArgumentException: n must be positive" |
| Negatif |Un message d'erreur apparaît qui dit :Exception in thread "AWT-EventQueue-0" java.lang.IllegalArgumentException: n must be positive" |

### VIE\_INIT ###
| Positif |L'ovni est qualifié de la valeur de vie initiale. |
|:--------|:-------------------------------------------------|
| 0       |La bar de vie de l'ovni se dessine depuis l'axe des y. |
| Negatif |La bar de vie de l'ovni se dessine a côté de lui,pas sur lui. |

### boss1Killed ###
| true | On passe directement au boss 2 |
|:-----|:-------------------------------|
| false | On doit battre le boss 1       |

### boss2Killed ###
| true | On passe directement au boss 3 si boss1Killed == true |
|:-----|:------------------------------------------------------|
| false | On doit battre le boss 2 si boss1Killed == true       |

### boss3Killed ###
| true | si (boss1Killed && boss2Killed) == true, la partie est terminée |
|:-----|:----------------------------------------------------------------|
| false | le boss 3 doit être battu si le boss 1 et le boss 2 ont déjà été battu |

### isBoss ###
| true | Aucun ovni est généré.|
|:-----|:----------------------|
| false |Les ovnis sont généré normalement. |

### isOr ###
| true |Les ovnis générés sont tous en or. |
|:-----|:----------------------------------|
| false |Aucun ovnis en or est généré.      |

### position ###
Objet [Vecteur](Vecteur.md).

### random ###
Pas de jeux de tests, il s'agit d'un objet Random.

### rectangle ###

### shootRate ###
| Positif | La probabilité d'apparition du projectile ennemi est attribué.|
|:--------|:--------------------------------------------------------------|
| 0       | Un message d'erreur apparaît qui dit :Exception in thread "AWT-EventQueue-0" java.lang.IllegalArgumentException: n must be positive"|
| Négatif |Un message d'erreur apparaît qui dit :Exception in thread "AWT-EventQueue-0" java.lang.IllegalArgumentException: n must be positive" |

### vie ###
| Positif |Les point de vies de l'ovni est attribué. |
|:--------|:-----------------------------------------|
| 0       |Les ovnis meurent à leurs apparitions.    |
| Négatif |Les ovnis meurent quelque soit la collision. |

### vitesseX ###
| Positif |La vitesse de l'ovni est attribué. Ils bougent vers le côté désirer. |
|:--------|:--------------------------------------------------------------------|
| 0       | L'ovni reste immobile.                                              |
| Négatif |Les ovnis bougent vers la direction opposée à la normale.            |

### xDirection ###
| Positif |Le boss se déplace vers la droite. |
|:--------|:----------------------------------|
| 0       |Le boss est immobile.              |
| Négatif | Le boss se déplace vers la gauche. |

### yDirection ###
| Positif |Le boss se déplace vers la bas. |
|:--------|:-------------------------------|
| 0       |Le boss est immobile.           |
| Négatif |Le boss se déplace vers la haut. |