# ProjetWeb: Blog

##1) Présentation du projet:

Le projet est un blog sur le thème du cinéma et des séries. 
Il permet de lire des articles qui sont classés dans différentes catégories :
- Film
- Nouveauté
- Critique
- Série
- Festival

En mode déconnecté, les utilisateurs peuvent accéder aux articles qui sont postés.
Une fois inscrit et connecté, les utilisateurs peuvent laisser des commentaires, postés des articles et parler dans le t'chat qui est sur le site.
Il existe des utilisateurs avec un rôle spécial: "rédacteur", se sont les utilisateurs qui peuvent créer des articles.


##2) Fonctionnalités:

#####Bouton Accueil
Sur la page d'accueil, on peut voir afficher sur la liste déroulante les informations sur le site et son contenu.
Il est aussi affiché:
- Les 5 articles qui ont recu le plus de vote et le nombre de vote
- Les utilisateurs les plus actifs, avec le nombre de commentaire qu'ils ont laissés
- Les 5 derniers articles qui ont été postés ainsi que leur catégorie.

#####Bouton Article
Affichage des articles qui sont présents sur le site trié du plus récent au plus ancien.
Les boutons permettent de trier les articles:  
- Affichage de tous les articles (aucun filtre): Bouton "Tous les articles"
- Trier les articles par ordres chonologiques ou antéchronologique: Bouton "Les plus récents" et "Les plus anciens"
- Afficher les articles pour une catégorie (Film, Nouveauté, Série, Critique et Festival)

Il est possible de cliquer sur un article pour l'afficher, il apparait alors les informations sur l'article:
- Titre
- Image
- Date
- Auteur
- Contenu
- Bouton "j'aime" lorsque l'utilisateur est connecté
- Les commantaires sur l'article
- La possibilité de laisser un commentaire si l'utilisateur est connecté

###### Bouton Afficher les articles aimés
Une fois connecté, un utilisateur peut afficher la liste des articles qu'il a aimé

###### Bouton Afficher mes articles
Une fois connecté, si l'utilisateur a le role de "rédacteur", il peut afficher les articles qu'il a rédigé en cliquant sur ce bouton

###### Bouton Ajouter un article
Un utilisateur qui a un rôle de "rédacteur", peut ajouter un article, il devra alors:
- Remplir le titre
- Choisir une catégorie à laquelle se rattache l'article
- Saisir le contenu de l'article
- Ajouter l'image associé à l'article

#####Bouton Inscription:
En étant déconnecté, il est possible de s'inscrire sur le site afin de pouvoir:
- Participer au discussion dans le t'chat 
- Laisser des commentaires sur les articles
- Voter pour un article lorsque l'utilisateur l'a apprécié.

Par défaut, un utilisateur est crée avec comme rôle "user", ce qui signifie qu'il peut poster des commentaires, aimer un article et parler dans le t'chat mais qu'il ne peut pas écrire et créer un article.

#####Bouton Profil
Le bouton apparaît une fois connecté, il permet de:
- Voir les informations sur l'utilisateur 
  * Pseudo
  * Date de création du compte
  * Mot de passe
  * Nombre d'articles postés
  * Nombre de commentaires laissés
- Modifier le pseudonyme
- Modifier le mot de passe
- Supprimer son compte

#####Barre de recherche
La barre de recherche permet de chercher un article et/ou un utilisateur plus rapidemment.
Il suffit de tapper le titre de l'article ou un mot clef sui se trouve dant le titre ou le début d'un pseudonyme.
Les articles affichés peuvent être trié par date ou catégorie aussi

#####T'chat
