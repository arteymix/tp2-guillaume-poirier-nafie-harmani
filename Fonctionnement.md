# Introduction #

Ce logiciel fonctionne relativement simplement. Il y a un seul JComponent sur lequel le draw est effectué, c'est le [MainCanvas](MainCanvas.md). Ce canvas contient une méthode paintComponent(Graphics g) dans laquelle une liste d'objets [Dessinable](Dessinable.md) est bouclé en appellant la méthode dessiner(Graphics g) de chacun des éléments de la liste.

Si un élément est présent dans la liste, il est dessiné, autrement il est détruit. Chaque objet [Dessinable](Dessinable.md) contient un booléen isDessinable qui permet cette interprétation.

Les objets que l'on désire dessiner ([Canon](Canon.md), [Projectile](Projectile.md), [Ovni](Ovni.md), ...) héritent tous de la classe abstraite [Dessinable](Dessinable.md), ce qui nous permet d'utiliser la généricité dans la liste.

Pour les collisions, tout objet apte à intervenir dans une collision implémente l'interface [Collisionable](Collisionable.md). Un simple instanceof nous permet de savoir si une collision doit être faite en appelant la méthode collision(Collisionable c) de ces objets.

Vous trouverez évitemment de l'information plus spécifique dans les autres pages.