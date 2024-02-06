 <%@page import="model.Bouquet"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Insertion de bouquet</title>
    <style>
 
 
    </style>
</head>
<body>
      <%@ include file="header.jsp" %>

<div class="contenus">
<div id="bouquet-form">
    <form id="bouquetForm">
        <h1>INSERER UN NOUVEAU BOUQUET</h1>
        <div class="entry">
            <label for="nomBouquet">Nom bouquet�</label>
            <input type="text" id="nomBouquet">
        </div>

        <div id="entriesContainer"></div>

        <button type="button" onclick="ajouterChamp()">Ajouter</button>
        <button type="submit" onclick="validerFormulaire()">Valider</button>
        <%List<Bouquet> bouquet =(List<Bouquet>) request.getAttribute("bouquet");%>
        <table>
            <thead>
                <tr>
                    <th>NOM</th>
                </tr>
            </thead>
            <tbody>
                <%for(int i=0; i<bouquet.size(); i++){%>
                    <tr>
                        <td><%=bouquet.get(i).getNom()%></td> 
                    </tr>
                <%}%>
            </tbody>
        </table>
    </form>
            
</div>
</div>
<script>
    function ajouterChamp() { 
    var newEntry = document.createElement('div');
    newEntry.className = 'entry';
    var uniqueId = Date.now(); // Utiliser une timestamp pour cr�er un ID unique
 
    newEntry.innerHTML = `
       <label for="${uniqueId}">Nom activité</label>
        <input type="text" id="${uniqueId}">
    `; 
    document.getElementById('entriesContainer').appendChild(newEntry);
}
 
    function validerFormulaire() {
                    var data= [];

        // Récupérer toutes les entrées du formulaire
         var entries = document.querySelectorAll('.entry');
        entries.forEach(function (entry, index) {
        var nomAct= entry.querySelector('input[type=text]').value;
        

        data.push({
            nom : nomAct
        });

    });
 
        fetch('BouquetServlet', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            }).then(response => {
                // G�rer la r�ponse du serveur si n�cessaire
                console.log('R�ponse du serveur :', response);
            }).catch(error => {
                // G�rer les erreurs de requ�te si n�cessaire
                console.error('Erreur de requ�te :', error);
            });

            // Retourner false pour emp�cher la soumission normale du formulaire
            return false;
            }
</script>

</body>
</html>
