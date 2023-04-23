<p align="center">
  <img src="https://user-images.githubusercontent.com/77997318/233859238-0f9d2aa6-d39f-422d-b37b-52e23e8aa072.png" alt="image">
</p>

JavaFX, avec la bibliothèque CalendarFX

- [Introduction](#introduction)
- [Fonctionnalités](#fonctionnalités)
- [Choix d'interface](#Choix-d-interface)
- [Conclusion](#conclusion)


# Introduction

Nous avons développé une application de gestion de calendrier et de suivi des plantes, appelée GreenCal. Cette application permet aux utilisateurs de gérer leurs événements et de suivre l'évolution de leurs plantes, tout en ayant accès aux informations météorologiques pertinentes. L'objectif principal de ce projet était de concevoir une interface utilisateur intuitive et efficace en suivant les heuristiques de Nielsen pour la conception d'interfaces utilisateur. 

Dans ce rapport, nous présentons les fonctionnalités de l'application, puis les choix d'interface effectués et la manière dont nous avons appliqué les heuristiques de Nielsen pour améliorer l'expérience utilisateur.


# Fonctionnalités


<table>
  <tr>
   <td>1
   </td>
   <td>Affichage calendrier
   </td>
   <td><strong>✔</strong>
   </td>
  </tr>
  <tr>
   <td>2
   </td>
   <td>Recherche d'événements dans l’agenda
   </td>
   <td><strong>✔</strong>
   </td>
  </tr>
  <tr>
   <td>3
   </td>
   <td>Ajout/modification/suppression d'événements dans le calendrier
   </td>
   <td><strong>✔</strong>
   </td>
  </tr>
  <tr>
   <td>4
   </td>
   <td>événements ponctuels et récurrents 
   </td>
   <td><strong>✔</strong>
   </td>
  </tr>
  <tr>
   <td>5
   </td>
   <td>Ajouter des plantes
   </td>
   <td><strong>✔</strong>
   </td>
  </tr>
  <tr>
   <td>6
   </td>
   <td>Accéder à la liste des plantes
   </td>
   <td><strong>✔</strong>
   </td>
  </tr>
  <tr>
   <td>7
   </td>
   <td>Accéder à la page des plantes
   </td>
   <td><strong>✔</strong>
   </td>
  </tr>
  <tr>
   <td>8
   </td>
   <td>Entrer des informations de suivi des plantes
   </td>
   <td><strong>✔</strong>
   </td>
  </tr>
  <tr>
   <td>9
   </td>
   <td>Consulter l’historique des informations de suivi des plantes
   </td>
   <td><strong>✔</strong>
   </td>
  </tr>
  <tr>
   <td>10
   </td>
   <td>Ajout/modification/suppression des plantes
   </td>
   <td><strong>✔</strong>
   </td>
  </tr>
  <tr>
   <td>11
   </td>
   <td>Planifier des événements à partir de la page de la plante
   </td>
   <td><strong>✔</strong>
   </td>
  </tr>
  <tr>
   <td>12
   </td>
   <td>Affichage de la saison
   </td>
   <td><strong>✔</strong>
   </td>
  </tr>
  <tr>
   <td>13
   </td>
   <td>Fenêtre d’aide / documentation
   </td>
   <td><strong>✔</strong>
   </td>
  </tr>
  <tr>
   <td colspan="3" ><strong>Bonus </strong>
   </td>
  </tr>
  <tr>
   <td>14
   </td>
   <td>Affichage des informations météo (API)
   </td>
   <td><strong>✔</strong>
   </td>
  </tr>
  <tr>
   <td colspan="2" >Importation Google Agenda
   </td>
   <td><strong>❌</strong>
   </td>
  </tr>
</table>



# Choix d interface
![](https://i.imgur.com/qSC1chA.png)


Au lancement de l’application, la vue du calendrier s’affiche. Ce calendrier est affiché à l’aide de la bibliothèque CalendarFX.

On retrouve l’affichage de l’agenda du jour, et les évènements à venir.

_(On peut changer la vue en vue hebdomadaire, mensuelle ou annuelle)_

Un double-clic sur celui-ci permet l’ajout d’un événement.
![](https://i.imgur.com/WdK5Dsd.png)


L’affichage se divise en 3 parties : 

![](https://i.imgur.com/qzJmEbf.png)




1. Un **menu latéral** qui permet de naviguer rapidement entre les vues:

_(Accueil, Consulter les plantes, Nouvelles Plante, Aide)_

_et d’ajouter rapidement un Nouvel événement_




2. Le **contenu** (qui change selon la vue) occupant la majorité de l’écran.

    _(C’est ici qu’on affiche les calendrier, ou la liste des plantes, ou le formulaire d’ajout ou la page d’une plante)_

3. La **TopBar** qui affiche les informations météo importantes, la date du jour et la saison. (car ce sont des informations clefs d’après le sondage).
* Ajout d’une Plante : 

On accède à la page avec le 4e bouton du menu latéral : 

![](https://i.imgur.com/oRMdOM1.png)

Après le choix d’image, on affiche l’image sélectionnée et son chemin.

Après l’enregistrement, un pop-up s’affiche pour informer l’utilisateur : 
![](https://i.imgur.com/loiqV9j.png)


* Consulter les plantes : 

On accède à la page avec le 3e bouton du menu latéral : 

![](https://i.imgur.com/sheB37t.png)


La liste des plantes est affichée dans un tableau qui permet de voir rapidement les principales informations des plantes. On peut également les trier par non, id, surnom ou date de plantation en cliquant sur la colonne correspondante.

On peut supprimer la plante sélectionnée avec le bouton “Supprimer”:

![](https://i.imgur.com/nCP5Avj.png)


Un double clic sur une plante de la liste permet d’afficher sa page : 



* Page d’une plante : 

![](https://i.imgur.com/KyXtfI1.png)


Cette page permet d’afficher toutes les informations enregistrées concernant la plante (image, dates, suivi des mesures, notes…) : (La page est assez longue donc scrollable)

![](https://i.imgur.com/NYVkfbn.png)


Chaque affichage d’informations permet d’en ajouter avec un bouton + qui ouvre un pop-up et un bouton annuler …

Les mesures de la plante sont affichées dans un graphe afin de mieux les visualiser : 

![](https://i.imgur.com/quKjP8y.png)


Comme pour les autres informations, on peut en ajouter avec le bouton + : 

![](https://i.imgur.com/YRqZKYT.png)


Enfin, les notes sont affichées sous forme de liste.

Depuis la page de la plante comme n’importe où, on peut ajouter un événement avec le menu latéral (fonctionnalité 11).

![](https://i.imgur.com/TX0RCQF.png)


* Pop-up d’aide

La dernière touche du menu latéral affiche un pop-up qui documente l’application (heuristique 10 : _Aide et documentation_) : 

![](https://i.imgur.com/dat4GtQ.png)

# Conclusion

GreenCal est une application bien conçue qui répond aux besoins des utilisateurs en matière de gestion de calendrier et de suivi des plantes. Nous avons veillé à ce que l'interface utilisateur soit intuitive et facile à utiliser en suivant les heuristiques de Nielsen pour la conception d'interfaces utilisateur. Les fonctionnalités offertes par l'application sont complètes et répondent aux attentes des utilisateurs. 

Tous les choix d'interface, tels que l'utilisation d'icônes dans le menu latéral et la disposition des éléments, ont été faits pour améliorer l'expérience utilisateur et faciliter la navigation dans l'application.

Enfin, nous avons veillé à fournir une documentation et une aide adéquates pour aider les utilisateurs à tirer le meilleur parti de l'application.

Nous avons particulièrement apprécié ce projet, car il nous a permis de mettre l'accent sur le développement de l'interface front-end. Cela contraste avec de nombreux autres projets réalisés au cours de notre licence, où nous avons principalement développé des compétences en back-end et consacré moins d'attention à l'interface utilisateur. Ce projet nous a permis de développer nos connaissances en matière de conception d'interfaces utilisateur et d'améliorer l'expérience globale des utilisateurs sur nos prochains projets.
