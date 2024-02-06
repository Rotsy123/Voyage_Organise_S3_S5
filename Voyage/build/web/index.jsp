 <%@page import="model.Activite"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Insertion d'activités</title>
    <style>
    </style>
</head>
  <%@ include file="header.jsp" %>

<body>
    <div class="contenus">
<div id="activite-form">
    <form id="activiteForm">
        <h1>INSERER UNE NOUVELLE ACTIVITE</h1>
            
        <div class="entry">
            <label for="nomActivite">Nom activité</label>
            <input type="text" id="nomActivite">
            <label for="prixActivite">Prix activité</label>
            <input type="text" id="prixActivite">
        </div>

        <div id="entriesContainer"></div>

        <button type="button" onclick="ajouterChamp()">Ajouter</button>
        <button type="submit" onclick="validerFormulaire()">Valider</button>
        <%List<Activite> activite =(List<Activite>) request.getAttribute("activiteall");%>
        <table>
            <thead>
                <tr>
                    <th>NOM</th>
                    <th>PRIX</th>
                </tr>
            </thead>
            <tbody>
                <%for(int i=0; i<activite.size(); i++){%>
                    <tr>
                        <td><%=activite.get(i).getNom()%></td>
                        <td><%=activite.get(i).getPrix()%></td>
                    </tr>
                <%}%>
            </tbody>
        </table>
    </form>
</div>

<script>
    function ajouterChamp() {
    var newEntry = document.createElement('div');
    newEntry.className = 'entry';
    var uniqueId = Date.now(); // Utiliser une timestamp pour cr�er un ID unique
    newEntry.innerHTML = `
       <label for="${uniqueId}">Nom activité</label>
        <input type="text" id="${uniqueId}
        <label for="${uniqueId}">Prix activité</label>
        <input type="text" id="${uniqueId}">">
    `;
    document.getElementById('entriesContainer').appendChild(newEntry);
}


    function validerFormulaire() {
        var data= [];
        var entries = document.querySelectorAll('.entry');
        entries.forEach(function (entry, index) {
            var nomAct= document.getElementById("nomActivite").value;
            var prixAct= document.getElementById("prixActivite").value;
            data.push({
                nom : nomAct,
                prix : prixAct
            });
        });
 
        fetch('ServletTest', {
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
    </div>
</body>
</html>
