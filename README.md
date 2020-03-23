
API rest utilisé pour l'application RandoExpress.

Utilisation :

Authentification :

- POST /RandoExpress_API/ws/rest/auth

Le corp de la requête doit contenir un JSON de ce type :

```JSON
{
	"mail" : "lamblino@hotmail.fr",
	"password" : "azerty"
}
```

Si les identifiants sont bons l'API renvoie un JSON contenant un JSON Web Token permettant d'effectuer les différentes requêtes ainsi que l'ensemble des informations de la personne :

```
{
    "message": "Connexion validé",
    "jwt": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJhdXRoMCJ9.izVguZPRsBQ5Rqw6dhMvcIwy8_9lQnrO3vpxGwPCuzs",
    "name": "Lamblino",
    "firstName": "Sébastien",
    "mail": "lamblino@hotmail.fr",
    "id": "1"
}
```


Le token doit ensuite être sauvegardé et être passé dans l'entête de chaque requête :
Authorization : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJhdXRoMCJ9.izVguZPRsBQ5Rqw6dhMvcIwy8_9lQnrO3vpxGwPCuzs

Sans cela la requête est refusée et retourne le JSON suivant :

```JSON
{
    "message": "Token inexistant"
}
```

Personnes :

- GET /RandoExpress_API/ws/rest/personnes (retourne la liste des personnes de l'application)

```JSON
[
    {
        "firstName": "Sébastien",
        "password": "azerty",
        "mail": "lamblino@hotmail.fr",
        "name": "Lamblino",
        "id": 1
    },
    {
        "firstName": "Vadym",
        "password": "azerty",
        "mail": "vadym@android.fr",
        "name": "Vadym",
        "id": 2
    }
]
```

- GET /RandoExpress_API/ws/rest/personne/id/2 (retourne la personne correspondant à l'id indiqué)
ou
- GET /RandoExpress_API/ws/rest/personne/mail/lamblino@hotmail.fr (retourne la personne correspondant à l'email indiqué)

```JSON
{
     "firstName": "Sébastien",
        "password": "azerty",
        "mail": "lamblino@hotmail.fr",
        "name": "Lamblino",
        "id": 2
}
```

- POST /RandoExpress_API/ws/rest/personne (créer une personne)

- PUT /RandoExpress_API/ws/rest/personne (modifie une personne)

- DELETE /RandoExpress_API/ws/rest/personne/{id} (supprime une personne)

Randonnées :

- GET /RandoExpress_API/ws/rest/randos (retourne la liste des randonnées de l'application qui ne sont encore d'actualité)

```JSON
[
    {
        "owner": {
            "firstName": "Sébastien",
            "name": "Lamblino",
            "id": 1,
            "uuid": "UID"
        },
        "persons": [
            {
                "firstName": "Vadym",
                "name": "Vadym",
                "id": 2,
                "uuid": "UID2"
            }
        ],
        "ville": "Marseille",
        "latitude": "43.232230",
        "name": "Calanque Luminy",
        "description": "Magnifique randonné dans les calanques de Marseille",
        "id": 3,
        "dateDepart": "20/02/2020",
        "longitude": "5.435990"
    },
    {
        "owner": {
            "firstName": "Sébastien",
            "name": "Lamblino",
            "id": 1,
            "uuid": "UID"
        },
        "persons": [],
        "ville": "Marseille",
        "latitude": "43.288593",
        "name": "Randonné cool",
        "description": "Magnifique randonné dans Marseille",
        "id": 4,
        "dateDepart": "20/03/2020",
        "longitude": "5.370514"
    }
]
```

- GET /RandoExpress_API/ws/rest/rando/id/4 (retourne la randonnée correspondant à l'id indiqué)

```JSON
{
    "owner": {
        "firstName": "Sébastien",
        "name": "Lamblino",
        "id": 1,
        "uuid": "UID"
    },
    "persons": [],
    "ville": "Marseille",
    "latitude": "43.288593",
    "name": "Randonné cool",
    "description": "Magnifique randonné dans Marseille",
    "id": 4,
    "dateDepart": "20/03/2020",
    "longitude": "5.370514"
}
```


- GET /RandoExpress_API/ws/rest/randos/person/{id} (retourne les randonnées à venir d'une personne)

- GET /RandoExpress_API/ws/rest/randos/person/old/{id} (retourne les randonnées passées d'une personne)

- GET /RandoExpress_API/ws/rest/randos/ville/{ville} (retourne les randonnées se situant dans la ville indiqué)

- POST /RandoExpress_API/ws/rest/rando/{id}/inscription/{mail} (inscription de la personne correspondant à l'email à la rando indiqué)

- POST /RandoExpress_API/ws/rest/rando/{id}/inscription/{mail} (désinscription de la personne correspondant à l'email à la rando indiqué)

- POST PUT DELETE idem sur /rando


