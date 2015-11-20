# Introduction #
Classe pour l'objet de highscores. Cet objet hérite d'un dictionnaire String+Integer afin de stocker les scores des joueurs. Il est sérializable et se sérialize automatiquement lorsqu'un score y est rajouté. Le dictionnaire est trié lorsqu'on lui demande son contenu avec la méthode getScores() qui retourne un ArrayList de String. Il est trié en fonction des points.

# Variables #

### xObtained ###

Où x vaut le nom du trophée.