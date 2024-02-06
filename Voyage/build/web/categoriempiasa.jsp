<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Formulaire</title>
    <style>
  

    </style>
</head>
<body>
    
<%@ include file="header.jsp" %>
      <div class="contenus">
        <div id="bouquet-form">
            
            <form action='CategorieMpiasaServlet' method='POST'>
                          <h1>INSERER UNE NOUVELLE CATEGORIE D'EMPLOYE</h1>

                <label for="nom">Nom categorie:</label>
                <input type="text" id="nom" name="nom">

            
                <button type="submit">Valider</button>
            </form>
        </div>
      </div>
</body>
</html>
