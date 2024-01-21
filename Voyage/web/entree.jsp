<%@page import="model.Activite"%>
<%@page import="java.util.List"%>
<%
    List<Activite> activite=(List<Activite>)request.getAttribute("activites");
%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Entree en stock</title>
    <style>
    form {
        background-color: #fff;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        margin: 0;
        padding: 0;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        flex-direction: column; /* Modifier la direction de la disposition en colonne */
    }

    label {
        display: block;
        margin-bottom: 8px;
    }

    select,
    input {
        width: 100%;
        padding: 8px;
        margin-bottom: 16px;
        box-sizing: border-box;
    }

    input[type="submit"] {
        background-color: #4caf50;
        color: #fff;
        cursor: pointer;
    }

    input[type="submit"]:hover {
        background-color: #45a049;
    }
</style>

</head>
<body>
  <%@ include file="header.jsp" %>
  <div class="contenus">
<form action="EntreeStockServlet" method="post">
    <label for="activite">Sélectionnez une activité :</label>
    <select id="activite" name="activite">
        <%
            for(int i=0 ; i<activite.size() ; i++){
        %>
                <option value="<%=activite.get(i).getId()%>"><%=activite.get(i).getNom()%></option>
        <%
            }
        %>
        <!-- Ajoutez d'autres options au besoin -->
    </select>

    <br>

    <label for="date">Date :</label>
    <input type="date" id="date" name="date">

    <br>

    <label for="nombreBillets">Nombre de billets :</label>
    <input type="number" id="nombreBillets" name="nombreBillets" min="1">

    <br>

    <label for="prix">Prix :</label>
    <input type="text" id="prix" name="prix" placeholder="Entrez le prix">

    <br>

    <input type="submit" value="Soumettre">
</form>
  </div>
</body>
</html>
