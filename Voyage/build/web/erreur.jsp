<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Pop-up d'erreur</title>
  <style>
    /* Styles pour la fenêtre pop-up */
    #popup {
      display: none;
      position: fixed;
      top: 50%;
      left: 50%;
      transform: translate(-50%, -50%);
      padding: 20px;
      background-color: #fff;
      border: 1px solid #ccc;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
      z-index: 1000;
    }

    /* Styles pour le fond obscurci */
    #overlay {
      display: none;
      position: fixed;
      top: 0;
      left: 0;
      width: 100%;
      height: 100%;
      background-color: rgba(0, 0, 0, 0.7);
      z-index: 999;
    }
  </style>
</head>
<body>


<!-- Fenêtre pop-up -->
<div id="popup">
  <h2>Erreur</h2>
  <p id="messageErreur"></p>
  <button onclick="fermerPopup()">Fermer</button>
</div>

<!-- Fond obscurci -->
<div id="overlay"></div>
<script>
  // Fonction pour afficher la fenêtre pop-up avec un message d'erreur
  function afficherErreur() {
    var message = <%= request.getAttribute("erreur")%>; // Remplacez par votre message d'erreur réel
    document.getElementById('messageErreur').innerText = message;
    document.getElementById('popup').style.display = 'block';
    document.getElementById('overlay').style.display = 'block';
  }

  // Fonction pour fermer la fenêtre pop-up
  function fermerPopup() {
    document.getElementById('popup').style.display = 'none';
    document.getElementById('overlay').style.display = 'none';
  }
  afficherErreur();
</script>