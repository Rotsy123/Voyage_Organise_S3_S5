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
    <title>Activités par Bouquet</title>
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

        form {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px #ccc;
            width: 400px; /* Ajustez la largeur selon vos besoins */
            text-align: center;
        }

        label {
            display: block;
            margin-bottom: 10px;
        }

        select {
            width: 100%;
            padding: 8px;
            margin-bottom: 20px;
        }

        button {
            background-color: #4caf50;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        ul {
            list-style: none;
            padding: 0;
            text-align: left;
            margin-top: 20px;
        }

        li {
            margin-bottom: 10px;
            border: 1px solid #ccc;
            padding: 10px;
            border-radius: 4px;
            background-color: #fff;
        }
    </style>
</head>
<body>

<form action="AfficheActiviteServlet" method="post">
     <label for="bouquet">Nom bouquet�</label>
            <select name="bouquet" id="bouquet">
                <%for(int i=0; i<bouquet.size(); i++){%>
                    <option id="bouquet" name="bouquet" value="<%=bouquet.get(i).getIdBouquet()%>"><%=bouquet.get(i).getNom()%></option>
                <%}%>
            </select>
    <button type="submit">Afficher les activités</button>

    <ul>
        <%if(activite!=null){%>
        <!-- Remplacez ces valeurs par les activités réelles récupérées de votre servlet -->
            <%for(int i=0; i<activite.size(); i++){%>

                <li><%=activite.get(i).getNom()%></li>
            <%}%>
      
        <%}%>
    </ul>
</form>

</body>
</html>
