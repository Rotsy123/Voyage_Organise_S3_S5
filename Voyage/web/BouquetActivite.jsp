<%@page import="model.Activite"%>
<%@page import="model.Bouquet"%>
<%@page import="java.util.List"%>
<%List<Bouquet> bouquet = (List<Bouquet>) request.getAttribute("bouquet");%>
<%List<Activite> activite = (List<Activite>) request.getAttribute("activite");%>

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
            <label for="bouquet">Nom bouquet�</label>
            <select name="bouquet" id="bouquet">
                <%for(int i=0; i<bouquet.size(); i++){%>
                
                    <option id="bouquet" name="bouquet" value="<%=bouquet.get(i).getIdBouquet()%>"><%=bouquet.get(i).getNom()%></option>
                <%}%>
            </select>
            <label for="activite">Nom Activite�</label>
            <select name="activite" id="activite">
                <%for(int i=0; i<activite.size(); i++){%>
                    <option id="activite" name="activite" value="<%=activite.get(i).getId()%>"><%=activite.get(i).getNom()%></option>
                <%}%>
            </select>
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
        <label for="bouquet">Nom bouquet�</label>
            <select name="bouquet" id="bouquet">

    <%for(int i=0; i<bouquet.size(); i++){%>
                    <option id="bouquet" name="bouquet" value="<%=bouquet.get(i).getIdBouquet()%>"><%=bouquet.get(i).getNom()%></option>
                <%}%>
            </select>
        <label for="activite_${uniqueId}">Nom Activite�</label>
        <select name="activite_${uniqueId}" id="activite_${uniqueId}">
            <%for(int i=0; i<activite.size(); i++){%>
                <option name="activite_${uniqueId}" id="activite_${uniqueId}" value="<%=activite.get(i).getId()%>"><%=activite.get(i).getNom()%></option>
            <%}%>
        </select>
    `; 
    document.getElementById('entriesContainer').appendChild(newEntry);
}
 
    function validerFormulaire() {
        var data= [];
        // Récupérer toutes les entrées du formulaire
         var entries = document.querySelectorAll('.entry');
        entries.forEach(function (entry, index) {
        var nomBouquet = entry.querySelector('select[name^="bouquet"]').value;
        var nomActivite = entry.querySelector('select[name^="activite"]').value;


        data.push({
            idactivite : nomActivite,
            idbouquet : nomBouquet
        });

    });
 
        fetch('BouquetActiviteServlets', {
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
