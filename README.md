# L'algorithme d'Ariane
Cet algorithme d'Ariane a été développé dans le cadre de la [SAÉ2.01 "Développement d’une application"](http://www.iut-fbleau.fr/sitebp/pt21/21_2022/STCL6EZD72IDTX73.php) lors de notre première année (2022) de BUT Informatique à l'IUT de Fontainebleau.

## Lancement du programme
### Compilation
Utiliser la commande suivante pour compiler le programme :
```bash
make
```

### Lancement
#### Méthode n°1 (recommandée) : Compiler et lancer le programme avec Make
Utiliser la commande suivante pour compiler (si ce n'est pas déjà fait) puis lancer le programme :
```bash
make run
```

#### Méthode n°2 : Compiler puis lancer le programme
Une fois la [compilation du programme](#compilation) faite, exécuter le programme à l'aide de la commande suivante :
```bash
java -cp out/ Main
```

## Documentation
La documentation du programme est généré à l'aide de JavaDoc. Elle est disponible dans le dossier `doc/` et peut être consultée en ouvrant le fichier `index.html` dans un navigateur web.
Utilisez la commande suivante pour générer la documentation :
```bash
make doc
```

## Crédits
-   Code :
    - Amir Daouadi (@daouadi)
    - Lyanis Souidi (@souidi)