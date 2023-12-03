# Introduction
Ce repertoire github contient toute l'implémentation du laboratoire SMTP. L'objectif de ce Laboratoire est de réalisé un client SMTP en Jvae en utilisant le socket API pour communiquer avec un serveur SMTP.

Pour tester le bon fonctionnement de notre client, ce laboratoire nous invite à créer une campagne de spam/prank, pour ce faire l'utilisateur peux choisir à qui il souhaite envoyé ses spam ainsi que le contenu de se dernier, le programme va pars la suite determiner aléatoirement à quelle groupe de victimes. quel email il doit envoyé.

Afin de simplifier les teste du laboratoire nous utilison l'outil MailDev.

# Serveur SMTP
## Besoin technique
- Docker

## MailDev
Vous pouvez utilisé [MailDev]( https://github.com/maildev/maildev) pour effectuer les différents tests de votre clients, pour se faire suivez bien les instruction fourni dans le GitHub de MailDev pour l'installer, La commande à utiliser pour pouvoir lancé votre serveeur est la même que sur le GitHub de MailDev :
```
docker run -d -p 1080:1080 -p 1025:1025 maildev/maildev
```
Cette commande vous fournis une interface Web sur localhost:1080.

# Installation
## Besoin technique
- Java 21 ou plus
- Maven

## Configuration
Vous trouverez dans le dossier config 3 fichier que vous pouvez modifier selon vos besoin :

- **config.txt** vous permet de définir le nombre de groupe de sur qui les mail seront envoyé afin que la campagne se déroule bien
- **message.txt** défini le contenu des mails que vous enverrez, veillez à bien respecter la mise en page des exemples de mail fourni.
- **victims.txt** sera le fichier qui vous permettra de définir quel sont les adresses email qui feront parti de votre campagne de prank.

Afin que votre client fonctionne veuillez réécrire les ligne 14 à 16 du fichier se trouvant dans /code/src/main/java/myApp/Main.java, pour que les chemins des fichier de configuration corresponde celui de votre clone se répertoire.

## Execution
Pour exécuter le projet il vous faudra utilisé Maven et entré les commande suivant :
```
mvn clean package
cd target
java -jar Labo4Prank-1.0.jar 
```
Une fois ces commandes exécuter, vous pourrez voir les différents mail qui ont été envoyé sur MailDev

# Implementation
## Schéma UML

## Config
### Configuration.java
Ce fichier permet d'ajouter les différents contenu que vous avez 


