# QRcode-Factory 
[![][travis img]][travis] [![][vulnerabilities img]][vulnerabilities]  [![][license img]][license]

**Short link generator distributed by QRcode**

Javadoc available [here](https://maugern.github.io/QRcode-factory/).

Author : [MAUGER Nicolas](https://github.com/maugern)

## Prerequisite
- Java 8
- maven
- The ``application.properties`` file fill with your postgres database information.

## To launch application locally

First clone the project :
```
git clone https://github.com/maugern/QRcode-factory.git
```

Then download dependency :
```
mvn install verify
```

Then launch application on port 8080
```
mvn jetty:run
```
And that's it! Now you can see the result at ```http://localhost:8080/``` .
If you encounter a problem with this apps, do not hesitate to [bring me the problem](https://github.com/maugern/QRcode-factory/issues) .

## Licence
This project is distribute under [MIT license](https://opensource.org/licenses/MIT)

[travis]:https://travis-ci.org/maugern/QRcode-factory
[travis img]:https://travis-ci.org/maugern/QRcode-factory.svg?branch=master
[license]:https://github.com/maugern/QRcode-factory/blob/master/LICENSE
[license img]:https://img.shields.io/github/license/mashape/apistatus.svg?maxAge=2592000
[vulnerabilities]:https://snyk.io/test/github/maugern/qrcode-factory)
[vulnerabilities img]:https://snyk.io/test/github/maugern/qrcode-factory/badge.svg
