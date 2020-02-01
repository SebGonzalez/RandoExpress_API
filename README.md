
API rest utilisé pour l'application RandoExpress.

Utilisation :

Personnes :

- GET /RandoExpress_API/ws/rest/personnes (retourne la liste des personnes de l'application)

[
    {
        "firstName": "Sébastien",
        "name": "Lamblino",
        "id": 1,
        "uuid": "UID"
    },
    {
        "firstName": "Vadym",
        "name": "Vadym",
        "id": 2,
        "uuid": "UID2"
    }
]

- GET /RandoExpress_API/ws/rest/personne/id/2 (retourne la personne correspondant à l'id indiqué)

{
    "firstName": "Vadym",
    "name": "Vadym",
    "id": 2,
    "uuid": "UID2"
}

- POST /RandoExpress_API/ws/rest/personne (créer une personne)

- PUT /RandoExpress_API/ws/rest/personne (modifie une personne)

- DELETE /RandoExpress_API/ws/rest/personne/{id} (supprime une personne)

Randonnées :

- GET /RandoExpress_API/ws/rest/randos (retourne la liste des randonnées de l'application)

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

- GET /RandoExpress_API/ws/rest/rando/id/4 (retourne la randonnée correspondant à l'id indiqué)

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

- GET /RandoExpress_API/ws/rest/rando/ville/{ville} (retourne les randonnées se situant dans la ville indiqué)

- POST PUT DELETE idem sur /rando


