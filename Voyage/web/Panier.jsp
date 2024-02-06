<%@page import="model.Voyage"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    List<Voyage> ls=(List<Voyage>)request.getAttribute("voyages");
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insertion de vente</title>
</head>
<body>
<%@ include file="header.jsp" %>
<div class="contenus">
    <div class="form-container">
        <div id="entriesContainer">
            <form id="mainForm" action='PanierServlet' method='POST'>
                <label for="matricule">Matricule</label>
                <input type="number" id="matricule" name="matricule">
                <label for="voyage">Voyage</label>
                <select name="voyage" id="voyage">
                    <%
                        for(int i=0 ; i<ls.size() ; i++){
                    %>
                        <option value="<%= ls.get(i).getIdvoyage()%>"><%= ls.get(i).getBa().getBouquet().getNom() %></option>
                    <% 
                        }
                    %>
                </select>
                <label for="nb">Nombre</label>
                <input type="number" id="nb" name="nb">

                <label for="dtachat">Date d'achat: </label>
                <input type="date" id="dtachat" name="dtachat">
                <button type="button" onclick="ajouterChamp()">Ajouter</button>

                <table id="entriesTable">
                    <thead>
                        <tr>
                            <th>Voyage</th>
                            <th>Nombre</th>
                            <th>Date d'achat</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody id="entriesBody"></tbody>
                </table>

                <button type="submit" onclick="validerFormulaire()">Valider l'achat</button>
            </form>
        </div>
    </div>
</div>
<script>
    function ajouterChamp() {
        var uniqueId = Date.now(); // Utiliser une timestamp pour créer un ID unique

        var tableBody = document.getElementById('entriesBody');

        var newRow = tableBody.insertRow();
        newRow.id = 'row_' + uniqueId;

        var voyageCell = newRow.insertCell(0);
        var nombreCell = newRow.insertCell(1);
        var dtachatCell = newRow.insertCell(2);
        var actionCell = newRow.insertCell(3);

        var voyage = document.getElementById('voyage').value;
        var nombre = document.getElementById('nb').value;
        var dtachat = document.getElementById('dtachat').value;

        voyageCell.innerHTML = voyage;
        nombreCell.innerHTML = nombre;
        dtachatCell.innerHTML = dtachat;
        actionCell.innerHTML = '<button onclick="supprimerLigne(' + uniqueId + ')">Supprimer</button>';
    }

    function supprimerLigne(uniqueId) {
        var row = document.getElementById('row_' + uniqueId);
        row.parentNode.removeChild(row);
    }

    function validerFormulaire() {
        var data = [];
        var matriculeValue = document.getElementById("matricule").value;

        // Récupérer toutes les entrées du tableau
        var tableRows = document.querySelectorAll('#entriesBody tr');
        tableRows.forEach(function (row, index) {
            var cells = row.cells;
            var voyage = cells[0].innerHTML;
            var nombre = cells[1].innerHTML;
            var dtachat = cells[2].innerHTML;

            data.push({
                idvoyage : voyage,
                nbProduitLafo : nombre,
                idclient : matriculeValue,
                dtachat: dtachat
        });
        });

        // Ajouter le reste des données du formulaire
//        data.push({
//            matricule: matriculeValue
            // Ajouter d'autres champs du formulaire si nécessaire
//        });

        // Effectuer la requête fetch ou soumettre le formulaire avec les données
        fetch('PanierServlet', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        }).then(response => {
            // Handle the server response if necessary
            console.log('Server response:', response);
        }).catch(error => {
            // Handle request errors if necessary
            console.error('Request error:', error);
        });

        // Réinitialiser le tableau et le formulaire
        document.getElementById('entriesBody').innerHTML = '';
        document.getElementById('mainForm').reset();
    }
</script>

</body>
</html>
