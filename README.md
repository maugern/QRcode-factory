QRcode-Factory
==============

**Short link generator distributed by QRcode**

[![][travis img]][travis] [![][vulnerabilities img]][vulnerabilities] [![][code-climate img]][code-climate] [![][codecov img]][codecov] [![][license img]][license]

## Contribute
### Prerequisite
- Java 8
- maven
- The ``application.properties`` file fill with your postgres database information.

### To launch application locally

First clone the project :
```
git clone https://github.com/maugern/QRcode-factory.git
```

Then download dependency :
```
mvn install verify
```

Then launch application on port 8080 :
```
mvn jetty:run
```
And that's it! Now you can see the result at ```http://localhost:8080/```.
If you encounter a problem with this apps, do not hesitate to [bring me the problem](https://github.com/maugern/QRcode-factory/issues).

### Javadoc
Javadoc is available [here](https://maugern.github.io/QRcode-factory/).

## Licence
This project is distribute under [MIT license](https://opensource.org/licenses/MIT)

## Author
- [MAUGER Nicolas](https://maugern.fr/)

[travis]:https://travis-ci.org/maugern/QRcode-factory
[travis img]:https://travis-ci.org/maugern/QRcode-factory.svg?branch=master
[license]:https://github.com/maugern/QRcode-factory/blob/master/LICENSE
[license img]:https://img.shields.io/github/license/mashape/apistatus.svg?maxAge=2592000
[vulnerabilities]:https://snyk.io/test/github/maugern/qrcode-factory
[vulnerabilities img]:https://snyk.io/test/github/maugern/qrcode-factory/badge.svg
[code-climate]:https://codeclimate.com/github/maugern/QRcode-factory
[code-climate img]:https://codeclimate.com/github/maugern/QRcode-factory/badges/gpa.svg
[codecov]:https://codecov.io/gh/maugern/QRcode-factory
[codecov img]:https://codecov.io/gh/maugern/QRcode-factory/branch/master/graph/badge.svg
