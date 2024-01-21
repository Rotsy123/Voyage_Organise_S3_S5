<%@page import="model.Voyage"%>
<%@page import="java.util.List"%>
<%
    List<Voyage> voyages=(List<Voyage>)request.getAttribute("voyages");
%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Formulaire de Réservation</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        form {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
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

<form action="SortieStockServlet" method="post">
    <label for="Voyage">Sélectionnez un voyage :</label>
    <select id="Voyage" name="voyage">
        <%
            for(int i=0 ; i<voyages.size() ; i++){
        %>
                <option value="<%=voyages.get(i).getIdvoyage()%>"><%=voyages.get(i).getBa().getBouquet().getNom()%></option>
        <%
            }
        %>
        <!-- Ajoutez d'autres options au besoin -->
    </select>

    <br>

    <label for="date">Date :</label>
    <input type="date" id="date" name="date">

    <br>

    <label for="nombreBillets">Nombre de voyage :</label>
    <input type="number" id="nombreBillets" name="nombreBillets" min="1">

    <br>


    <input type="submit" value="Soumettre">
</form>

</body>
</html>
