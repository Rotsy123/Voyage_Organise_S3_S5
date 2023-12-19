 <!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Insertion d'activités</title>
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

label {
    display: block;
    margin-bottom: 5px;
}

select {
    width: 100%;
    padding: 8px;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
    margin-bottom: 10px;
}

button {
    background-color: #4caf50;
    color: white;
    padding: 10px 15px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}

button:hover {
    background-color: #45a049;
}

    </style>
</head>
<body>

<div id="activite-form">
    <form id="activiteForm">
        <div class="entry">
            <label for="nomActivite">Nom activité</label>
            <input type="text" id="nomActivite">
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
    var uniqueId = Date.now(); // Utiliser une timestamp pour cr�er un ID unique
    newEntry.innerHTML = `
       <label for="${uniqueId}">Nom activité</label>
        <input type="text" id="${uniqueId}">
    `;
    document.getElementById('entriesContainer').appendChild(newEntry);
}


    function validerFormulaire() {
        var data= [];
        var entries = document.querySelectorAll('.entry');
        entries.forEach(function (entry, index) {
            var nomAct= entry.querySelector('input[type=text]').value;
            data.push({
                nom : nomAct
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

</body>
</html>
