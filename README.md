QRcode-Factory
==============

**Short link generator distributed by QRcode**

[![][travis img]][travis] [![][circleci img]][circleci] [![][vulnerabilities img]][vulnerabilities] [![][code-climate img]][code-climate] [![][codecov img]][codecov] [![][codacy img]][codacy] [![][sonar img]][sonar] [![][license img]][license]

## Deploy the application with docker 
First pull the docker image :
```
docker pull maugern/qrcode-factory
```

Then run it :
```
docker run -p 8080:8080 qrcode-factory
```

And that's it, you can see the result at the address

## Contributing 
### Prerequisite
To build the application localy, you need :
- Java 8
- maven 3
- A PostgreSQL database

First clone the project :
```
git clone https://github.com/maugern/QRcode-factory.git && cd QRcode-factory
```

Then download dependency :
```
mvn install verify
```

Then launch application on port 8080 :
```
mvn jetty:run -Djdbc.url=<url of your psql database> -Djdbc.username=<username> -Djdbc.password=<password>
```

If you dont like passing your postgres database information in command line, you fill your database information in the file ``src/main/resources/application.properties``.

If you encounter a problem with this apps, do not hesitate to [bring me the problem](https://github.com/maugern/QRcode-factory/issues).

### Javadoc
Javadoc is available [here](https://maugern.github.io/QRcode-factory/).

## Licence
This project is distribute under [GNU Affero Public License](https://www.gnu.org/licenses/agpl-3.0.en.html)

### Others licenses used
I list below the dependencies used in this project grouped by licenses. If I forget some, please send me an email/issue or submit a pull request.
- [Apache License 2](http://www.apache.org/licenses/LICENSE-2.0): Spring, Maven, Jetty, common-dhcp, zxing
- [BSD-2-Clause](https://opensource.org/licenses/BSD-2-Clause): Postgres-jdbc, hsqldb
- [Common Development and Distribution License 1.0](https://opensource.org/licenses/CDDL-1.0): javax-el, jsp-api, logback
- [Eclipse Public License](http://www.eclipse.org/org/documents/epl-v10.php): Jetty, JUnit, logback
- [GNU Lesser General Public License](https://www.gnu.org/licenses/lgpl-3.0.en.html): hibernate
- [MIT license](https://opensource.org/licenses/MIT): hashid



## Author
- [MAUGER Nicolas](https://maugern.fr/)

[travis]:https://travis-ci.org/maugern/QRcode-factory
[travis img]:https://travis-ci.org/maugern/QRcode-factory.svg?branch=master
[license]:https://www.gnu.org/licenses/agpl-3.0.en.html
[license img]:https://img.shields.io/badge/License-AGPL%20v3-blue.svg
[vulnerabilities]:https://snyk.io/test/github/maugern/qrcode-factory
[vulnerabilities img]:https://snyk.io/test/github/maugern/qrcode-factory/badge.svg
[code-climate]:https://codeclimate.com/github/maugern/QRcode-factory
[code-climate img]:https://codeclimate.com/github/maugern/QRcode-factory/badges/gpa.svg
[codecov]:https://codecov.io/gh/maugern/QRcode-factory
[codecov img]:https://codecov.io/gh/maugern/QRcode-factory/branch/master/graph/badge.svg
[circleci]:https://circleci.com/gh/maugern/QRcode-factory
[circleci img]:https://circleci.com/gh/maugern/QRcode-factory.svg?style=shield&circle-token=:circle-token
[codacy]:https://www.codacy.com/app/contact_74/QRcode-factory?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=maugern/QRcode-factory&amp;utm_campaign=Badge_Grade
[codacy img]:https://api.codacy.com/project/badge/Grade/e9829ad6908d47b3a0d9acb290c18dad
[sonar]:https://sonarcloud.io/dashboard?id=fr.maugern%3AQRcode-factory
[sonar img]:https://sonarcloud.io/api/project_badges/measure?project=fr.maugern%3AQRcode-factory&metric=alert_status

