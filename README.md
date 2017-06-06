# QRcode-Factory [![][travis img]][travis] [![][license img]][license]

**Notre projet Java/JEE de troisième année à l'ESPI Lille.**

Le sujet est mise à disposition sur [notre wiki](https://github.com/maugern/QRcode-factory/wiki/Sujet-du-projet-Java-JEE).
La javadoc est disponible [ici](https://maugern.github.io/QRcode-factory/).

Liste des membres de l'équipe :
- [ROMBO Erwan](https://github.com/BBorombo)
- [CATTELLE Clément](https://github.com/komanokami)
- [MAUGER Nicolas](https://github.com/maugern)


## Lancer l'application en locale :

Clonez d'abord le projet dans votre workspace :
```sh
git clone https://github.com/maugern/QRcode-factory.git
```

Télécharger les dépendances et lancer les tests unitaires :
```sh
mvn install verify
```

Lancez ensuite l'application en locale :
```sh
mvn tomcat7:run
```

C'est fini! Vous pouvez à présent vous rendre sur l'adresse ```http://localhost:8080``` pour voir le résultat.
Si vous rencontrez une difficulté n'hésitez pas à [nous remonter le problème](https://github.com/maugern/QRcode-factory/issues).

[travis]:https://travis-ci.org/maugern/QRcode-factory
[travis img]:https://travis-ci.org/maugern/QRcode-factory.svg?branch=master
[license]:https://github.com/maugern/jersey-skeleton/blob/master/LICENSE
[license img]:https://img.shields.io/github/license/mashape/apistatus.svg?maxAge=2592000
