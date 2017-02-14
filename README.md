# QRcode Factory [![Build Status](https://travis-ci.org/maugern/QRcode-factory.svg?branch=master)](https://travis-ci.org/maugern/QRcode-factory)

**Notre projet Java/JEE de troisième année à l'ESPI Lille.**

Le sujet et la documentation est disponible sur [notre wiki](https://github.com/maugern/QRcode-factory/wiki).

Liste des membres de l'équipe :
- [BOROMBO Erwan](https://github.com/BBorombo)
- [CATTELLE Clément](https://github.com/komanokami)
- [MAUGER Nicolas](https://github.com/maugern)


## Lancer l'application en locale :

Clonez d'abord le projet dans votre workspace :
```sh
git clone https://github.com/maugern/QRcode-factory.git
```

Puis installer l'application via maven :
```sh
mvn install
mvn compile
```

Enfin, lancez ensuite l'application en locale sur le port 8080 :
```sh
mvn jetty:run
```

C'est fini! Vous pouvez à présent vous rendre sur l'adresse localhost:8080 pour voir le résultat.
Si vous rencontrez une difficulté n'hésitez pas à [nous remonter le problème](https://github.com/maugern/QRcode-factory/issues).
