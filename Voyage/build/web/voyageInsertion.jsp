<%@page import="model.Voyage"%>
<%@page import="model.CategorieLieu"%>
<%@page import="model.Bouquet"%>
<%@page import="java.util.List"%>
<%List<Bouquet> bouquet = (List<Bouquet>) request.getAttribute("bouquet");%>
<%List<CategorieLieu> cateLieu = (List<CategorieLieu>) request.getAttribute("cateLieu");%>
<%List<Voyage> all =(List<Voyage>) request.getAttribute("allvoyage");%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Insertion de bouquet</title>
    <style>

        #activite-form {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px #ccc;
                        font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
        }

        .entry {
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
    
      <%@ include file="header.jsp" %>
<div class="contenus">
<div id="bouquet-form">
    <form id="bouquetForm">
        <h1>INSERER UN NOUVEAU VOYAGE</h1>
        <div class="entry"> 

            <label for="bouquet">Nom bouquet�</label>
            <select name="bouquet" id="bouquet">
                <%for(int i=0; i<bouquet.size(); i++){%>
                
                    <option id="bouquet" name="bouquet" value="<%=bouquet.get(i).getIdBouquet()%>"><%=bouquet.get(i).getNom()%></option>
                <%}%>
            </select>
            <label for="activite">Nom CateLieu�</label>
            <select name="activite" id="activite">
                <%for(int i=0; i<cateLieu.size(); i++){%>
                    <option id="activite" name="activite" value="<%=cateLieu.get(i).getId()%>"><%=cateLieu.get(i).getNom()%></option>
                <%}%>
            </select>
            <label for="duree">Duree</label>
            <input type="number" step="0.01" id="duree" name="duree" required>
            <label for="prix">Prix</label>
            <input type="number" step="0.01" id="prix" name="prix" required>
            <label for="taille">Taille</label>
            <select name="taille" id="taille">
                <option value="1">court</option>
                <option value="2">long</option>
            </select>
        </div>

        <div id="entriesContainer"></div>
 
        <button type="submit" onclick="validerFormulaire()">Valider</button>
        <table>
            <thead>
                <tr>
                    <th>Bouquet</th>
                    <th>Duree</th>
                    <th>Taille</th>
                    <th>Prix</th>
                </tr>
            </thead>
            <tbody>
                <%for(int i=0; i<all.size(); i++){%>
                    <tr>
                        <td><%=all.get(i).getBa().getBouquet().getNom()%></td>
                        <td><%=all.get(i).getDureejour()%></td>
                        <td><%=all.get(i).getTailleString()%></td>
                        <td><%=all.get(i).getPrix()%></td>
                    </tr>
                <%}%>
            </tbody>
        </table>
    </form>
</div>

<script>
  
        

    function validerFormulaire() {
        var data= [];
        // Récupérer toutes les entrées du formulaire
         var entries = document.querySelectorAll('.entry');
        entries.forEach(function (entry, index) {
        var nomBouquet = entry.querySelector('select[name^="bouquet"]').value;
        var nomActivite = entry.querySelector('select[name^="activite"]').value;
        var duree=entry.querySelector('input[name^="duree"]').value;
        var prix=entry.querySelector('input[name^="prix"]').value;
        var taille=entry.querySelector('select[name^="taille"]').value;

        data.push({
            idcatelieu : nomActivite,
            idbouquet : nomBouquet,
            duree : duree,
            prix : prix,
            taille : taille
        });

    });
 
        fetch('VoyageInsertion', {
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
