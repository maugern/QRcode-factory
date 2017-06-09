# QRcode-Factory [![][travis img]][travis] [![][license img]][license]

**Notre projet Java/JEE de troisième année à l'ESPI Lille.**

Le sujet est mise à disposition sur [notre wiki](https://github.com/maugern/QRcode-factory/wiki/Sujet-du-projet-Java-JEE).
La javadoc est disponible [ici](https://maugern.github.io/QRcode-factory/).

Liste des membres de l'équipe :
- [ROMBO Erwan](https://github.com/BBorombo)
- [CATTELLE Clément](https://github.com/komanokami)
- [MAUGER Nicolas](https://github.com/maugern)

## Lancer l'application en local :

Clonez d'abord le projet dans votre workspace :
```sh
git clone https://github.com/maugern/QRcode-factory.git
```

Téléchargez les dépendances et lancer les tests unitaires :
```sh
mvn install verify
```

Lancez/deployez ensuite l'application en local :
```sh
mvn jetty:run
```
C'est fini! Vous pouvez à présent vous rendre sur l'adresse ```http://localhost:8080/``` pour voir le résultat.
Si vous rencontrez une difficulté n'hésitez pas à [nous remonter le problème](https://github.com/maugern/QRcode-factory/issues).

## Contribution
Si vous souhaitez contribuez à ce projet:
- Si vous utilisez eclipse/intellij/netbeans/visualCode: vous pouvez le déinstaller
- Téléchargez et ulilisez un vrai IDE (par exemple [![][spacemacs img]][spacemacs] )
- Executez **dans un vrais terminal** la commande ```mvn clean install package```
- Bravo! Vous avez compilé le projet, vous pouvez maintenant contribuer à un projet libre ! Cela fait de vous quelqu'un de formidable !

## Licence
Le projet est distribué sous licence libre [MIT license](https://opensource.org/licenses/MIT)

[travis]:https://travis-ci.org/maugern/QRcode-factory
[travis img]:https://travis-ci.org/maugern/QRcode-factory.svg?branch=master
[license]:https://github.com/maugern/QRcode-factory/blob/master/LICENSE
[license img]:https://img.shields.io/github/license/mashape/apistatus.svg?maxAge=2592000
[spacemacs img]:https://cdn.rawgit.com/syl20bnr/spacemacs/442d025779da2f62fc86c2082703697714db6514/assets/spacemacs-badge.svg
[spacemacs]:http://spacemacs.org
