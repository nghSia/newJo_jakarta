
# TPI JAKARTA - Jeux olympiques - projet newJpo

## entities a creer en premier :
    Stade -> Epreuve -> Billet
    User
    Discount
                                -> Commande


## Relations entre entites
    - Un stade peut avoir 0 ou plusieurs epreuve mais pas en meme temps
    - Un epreuve peut se derouler dans un et un seul stade
    - Un billet concerne 1 epreuve mais 1 epreuve peut avoir 0 ou plusieurs billet (niveau : normal, superieur, VIP)
    - Un user peut passer 0 ou plusieurs commandes
    - Une commande est passe par un seul user
    - une discount peut etre applique a 0 ou plusieurs commande mais une comamnde n'a qu'un seul discount a la fois.


#Fonctionnalites 

    - CRUD de Stade
    - CRUD d'epreuve + ajout de stade dans lequel se deroule epreuve
    - CRUD de Billet + ajout d'epreuve concerne par le billet
    - CRUD User
    - CRUD discount
    - CRUD commande
    - Authentification
    - pas de gestion des roles avec securite
