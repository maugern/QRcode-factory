# QRcode Factory[![Build Status](https://travis-ci.org/maugern/QRcode-factory.svg?branch=master)](https://travis-ci.org/maugern/QRcode-factory)

**Notre projet Java/JEE de troisième année à l'ESPI Lille.**

Le sujet est mise à disposition sur [notre wiki](https://github.com/maugern/QRcode-factory/wiki).
La javadoc est disponible [ici](https://maugern.github.io/QRcode-factory/).

Liste des membres de l'équipe :
- [BOROMBO Erwan](https://github.com/BBorombo)
- [CATTELLE Clément](https://github.com/komanokami)
- [MAUGER Nicolas](https://github.com/maugern)


## Lancer l'application en locale :

Clonez d'abord le projet dans votre workspace :
```sh
git clone https://github.com/maugern/QRcode-factory.git
```

Télécharger les dépendances et lancer les tests unitaires :
```sh
mvn verify
```

Lancez ensuite l'application en locale :
```sh
mvn jetty:run
```

C'est fini! Vous pouvez à présent vous rendre sur l'adresse localhost:8080 pour voir le résultat.
Si vous rencontrez une difficulté n'hésitez pas à [nous remonter le problème](https://github.com/maugern/QRcode-factory/issues).
