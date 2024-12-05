# projet_web_atrio

Ceci est un projet Maven/Spring

## Lancer le projet
Pour lancer le projet vous avez 2 façons : 

- A partir d'un IDE lancer le main de la classe web_atrio/src/main/java/app/WebAtrioApplication.java
- Avec une console : mvn spring-boot:run

## Tester les cas d'utilisation

Une fois le projet lancé, vous pouvez le tester sur le port 8080 (vous pouvez changer le port dans parameters.properties) avec un outils de tests d'API comme Postman avec les params suivants :

dans l'onglet Headers ajouter une ligne :
Key : Content-Type
Value : application/json

### Ajouter un personne :
- Cas OK (Création d'une personne avec id = 1, les ids sont assignés automatiquement de façon incrémentale):


URL : http://localhost:8080/api/personnes

Methode : POST


Body : 
{
  "nom": "Bruce",
  "prenom": "Wayne",
  "dateNaissance": "1939-05-01"
}

- Cas KO :
{
  "nom": "Kent",
  "prenom": "Clark",
  "dateNaissance": "1839-05-01"
}

### Ajouter un emploi à une personne :
- Cas OK avec emploi passé  (id = 1)

URL : http://localhost:8080/api/emplois/assign?personneId=1

Methode : POST

Body :
{
  "nomEntreprise": "Wayne Tech",
  "poste": "Vice Président",
  "dateDebut": "2023-01-01",
  "dateFin": "2023-12-31"
}


- Cas OK avec emploi présent  (id = 1)

URL : http://localhost:8080/api/emplois/assign?personneId=1

Methode : POST

Body :
{
  "nomEntreprise": "Wayne Tech",
  "poste": "PDG",
  "dateDebut": "2024-01-01"
}

- Cas KO sans date de début (id = 1)

URL : http://localhost:8080/api/emplois/assign?personneId=1

Methode : POST

Body :
{
  "nomEntreprise": "Wayne Tech",
  "poste": "PDG",
  "dateFin": "2023-12-31"
}

### Récuperer la liste des personnes ayant un emplois :

URL : http://localhost:8080/api/personnes/with-jobs

Methode : GET

Résultat attendu : Liste de toutes le spersonnes crées précédemment 

### Récuperer tout les employés d'une entreprise (avec le nom de l'entreprise)

URL : http://localhost:8080/api/personnes/by-entreprise?entreprise=Wayne Tech

Methode : GET

Résultat attendu : Liste des employés de l'entreprise

### Récuperer tout les emplois d'une personne entre 2 dates

Cas pour la personne avec (id=1)

URL : http://localhost:8080/api/emplois/between-dates?personneId=1&startDate=2019-01-01&endDate=2024-01-01

Résultat attendu : listes des emplois de la personne avec id = 1 pour la periode 2019-01-01 et 2024-01-01
