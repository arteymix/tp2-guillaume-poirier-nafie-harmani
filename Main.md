# Introduction #
Classe principale du programme.

# Variables #

### alienAuSol ###
| Négatif | Le jeu fonctionne normalement... |
|:--------|:---------------------------------|
| 0       | Valeur par défaut, fonctionne normalement |
| 0 > x > 4 | Affiche x aliens dans le haut de l'écran |
| 4       | Affiche x aliens dans le haut de l'écran, mais la partie continue... |
| > 4     | Affiche les aliens et la partie continue |

### canon1 & canon2 ###

Objets de [Canon](Canon.md).

### composantesDessinables ###

Liste de composantes [Dessinable](Dessinable.md).

### highscore ###

Objet de [Highscores](Highscores.md)

### imageBank ###

Objet [ImageBank](ImageBank.md)

### interfaceGraphique ###

Objet [InterfaceGraphique](InterfaceGraphique.md).

### isDebugEnabled ###
| true | Le jeu démarre en mode de débogage |
|:-----|:-----------------------------------|
| false | Le jeu démarre avec mode normal    |

### isGameOver ###
| true | Valeur normale, fonctionne bien |
|:-----|:--------------------------------|
| false | Fonctionne toujours bien, ce booléen prend son sens quand la partie est réellement terminée |

### isPaused ###
| true | Ne change rien au démarrage, mais sa valeur n'est pas affectée dans Main |
|:-----|:-------------------------------------------------------------------------|
| false | Valeur par défaut, le programme fonctionne normalement                   |

### isRunning ###
| true | Valeur normale, tous les threads sont en fonction |
|:-----|:--------------------------------------------------|
| false | Les threads sont tous arrêté, le rendu fige       |

### latency ###
| Négatif | Les canons bougent d'un bord de la carte à un autre instantanément et le jeu plante à la fermeture parce que le temps d'attente d'un Thread est négatif! |
|:--------|:---------------------------------------------------------------------------------------------------------------------------------------------------------|
| 0       | Les canons bougent d'un bord de la carte à un autre instantanément!                                                                                      |
| 15      | Fonctionnement normal                                                                                                                                    |
| >15     | Le jeu fonctionne au ralentit plus cette valeur est grande                                                                                               |

### level ###
| Négatif | Les tirs ennemis donnent des points de vie :P |
|:--------|:----------------------------------------------|
| 0       | Les tirs ennemis font aucuns dommages         |
| Valeurs prévues (1,2,3,4) | Le jeu fonctionne normalement                 |
| > 4     | Les ennemis causent x fois les dommages       |

### paintDone ###
| true | Ne change rien, mais on suppose que le premier frame ne subit pas d'attente |
|:-----|:----------------------------------------------------------------------------|
| false | Valeur par défaut, le programme fonctionne normalement                      |

### points ###
| Négatif | L'utilisateur commence avec des points négatifs |
|:--------|:------------------------------------------------|
| 0       | L'utilisateur commence avec 0 points            |
| Positif | L'utilisateur commence avec les points donnés   |

### rendu ###

Thread pour le rendu graphique, il est de type [InterfaceGraphique](InterfaceGraphique.md)

### showHighscores ###
| true | N'affiche pas les highscores, mais la requête se fait différemment |
|:-----|:-------------------------------------------------------------------|
| false | Fonctionnement normal, les highscores ne sont pas affichés         |

### tempsDuRendu ###
Le jeu fonctionne peu importe la valeur qu'on met ici. Elle affecte le premier frame, mais elle est réinitialisée à chaque frame.

### tentaculesKilled ###
L'utilisateur commence avec la valeur donnée en tentacules tués.

### timerSeconds ###
| Négatif | Le temps avant d'affronter le boss augmente plus la valeur est grande négativement |
|:--------|:-----------------------------------------------------------------------------------|
| 0       | Valeur normale                                                                     |
| 0 > x > 60000 | Le jeu commence à x ms                                                             |
| 60000   | Le boss apparaît                                                                   |
| > 60000 | Le boss apparaît                                                                   |