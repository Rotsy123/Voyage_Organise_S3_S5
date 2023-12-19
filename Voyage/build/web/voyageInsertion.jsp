<%@page import="model.CategorieLieu"%>
<%@page import="model.Bouquet"%>
<%@page import="java.util.List"%>
<%List<Bouquet> bouquet = (List<Bouquet>) request.getAttribute("bouquet");%>
<%List<CategorieLieu> cateLieu = (List<CategorieLieu>) request.getAttribute("cateLieu");%>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Insertion de bouquet</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
        }

        #activite-form {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px #ccc;
        }

        .entry {
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
                        <%=bouquet.get(0).getIdBouquet()%>

<div id="bouquet-form">
    <form id="bouquetForm">
        <div class="entry">
            <label for="bouquet">Nom bouquet©</label>
            <select name="bouquet" id="bouquet">
                <%for(int i=0; i<bouquet.size(); i++){%>
                
                    <option id="bouquet" name="bouquet" value="<%=bouquet.get(i).getIdBouquet()%>"><%=bouquet.get(i).getNom()%></option>
                <%}%>
            </select>
            <label for="activite">Nom CateLieu©</label>
            <select name="activite" id="activite">
                <%for(int i=0; i<cateLieu.size(); i++){%>
                    <option id="activite" name="activite" value="<%=cateLieu.get(i).getId()%>"><%=cateLieu.get(i).getNom()%></option>
                <%}%>
            </select>
            <label for="duree">Duree</label>
            <input type="number" step="0.01" id="duree" name="duree" required>
            <label for="prix">Prix</label>
            <input type="number" step="0.01" id="prix" name="prix" required>
        </div>

        <div id="entriesContainer"></div>

        <button type="button" onclick="ajouterChamp()">Ajouter</button>
        <button type="submit" onclick="validerFormulaire()">Valider</button>
    </form>
</div>

<script>
    function ajouterChamp() { 
    var newEntry = document.createElement('div');
    newEntry.className = 'entry';
    var uniqueId = Date.now(); 
 
    newEntry.innerHTML = `
        <label for="bouquet">Nom bouquet©</label>
            <select name="bouquet" id="bouquet">

    <%for(int i=0; i<bouquet.size(); i++){%>
                    <option id="bouquet" name="bouquet" value="<%=bouquet.get(i).getIdBouquet()%>"><%=bouquet.get(i).getNom()%></option>
                <%}%>
            </select>
        <label for="activite_${uniqueId}">Nom Activite©</label>
        <select name="activite_${uniqueId}" id="activite_${uniqueId}">
            <%for(int i=0; i<cateLieu.size(); i++){%>
                <option name="activite_${uniqueId}" id="activite_${uniqueId}" value="<%=cateLieu.get(i).getId()%>"><%=cateLieu.get(i).getNom()%></option>
            <%}%>
        </select>
        <label for="duree_${uniqueId}">Duree</label>
        <input type="number" step="0.01" id="duree_${uniqueId}" name="duree_${uniqueId}" required>
        <label for="prix_${uniqueId}">Prix</label>
        <input type="number" step="0.01" id="prix_${uniqueId}" name="prix_${uniqueId}" required>
    `; 
    document.getElementById('entriesContainer').appendChild(newEntry);
}
 
    function validerFormulaire() {
        var data= [];
        // RÃ©cupÃ©rer toutes les entrÃ©es du formulaire
         var entries = document.querySelectorAll('.entry');
        entries.forEach(function (entry, index) {
        var nomBouquet = entry.querySelector('select[name^="bouquet"]').value;
        var nomActivite = entry.querySelector('select[name^="activite"]').value;
        var duree=entry.querySelector('input[name^="duree"]').value;
        var prix=entry.querySelector('input[name^="prix"]').value;

        data.push({
            idcatelieu : nomActivite,
            idbouquet : nomBouquet,
            duree : duree,
            prix : prix
        });

    });
 
        fetch('VoyageInsertion', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            }).then(response => {
                // Gï¿½rer la rï¿½ponse du serveur si nï¿½cessaire
                console.log('Rï¿½ponse du serveur :', response);
            }).catch(error => {
                // Gï¿½rer les erreurs de requï¿½te si nï¿½cessaire
                console.error('Erreur de requï¿½te :', error);
            });

            // Retourner false pour empï¿½cher la soumission normale du formulaire
            return false;
            }
</script>

</body>
</html>
