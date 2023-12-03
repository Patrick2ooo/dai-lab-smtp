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
Ce fichier permet d'ajouter à notre code le contenu des différents fichier texte que vous avez pu configurer lors de l'installation, ainsi que de contrôler si les mail que vous avez défini sont bel et bien valide.

### PrankConfig.java
Cette partie nous permets de configurer les différents groupe (créé aléatoirement avec les mail que vous avez fourni) pour notre campagne de prank

## MailConfig
se dossier contient toutes les classe permettant de modéliser notre Campagne
- Groupe: contient toutes les information concernant un groupe
- Mail: contient toutes les informations concernant un email
- Message : contient tout le contenu d'un email
- Personne: contient l'email d'une personne

### Prank.java
Ce fichier contient toutes les informations nécessaire à un Prank pour un groupe spécifique, il permet aussi de configurer le Mail (setupMail), donc qui est l'envoyeur, qui sont les destinataire, ainsi que le contenu du message.

## SMTP
### Client.java
Le client contient toute les informations nécessaire à la connexion à un serveur, ainsi que les différentes interaction entre le serveur et le clients. la méthode envoieMail gère toutes ces interaction, l'implémentation à été réalisée à l'aide de [SMTP RFC](https://datatracker.ietf.org/doc/html/rfc5321)

## Main
Le main va s'occuper d'instancier lees différents spam/pranks avec les information fourni par notre classe configuration, on va ensuite se servir de la classe Client pour envoyé tout les prank, configuré par les classes PrankConfig et Prank.

## Exemple



