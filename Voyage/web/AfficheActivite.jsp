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
    form {
        background-color: #fff;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 0 10px #ccc;
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        margin: 0;
        padding: 0;
        display: flex;
        flex-direction: column; /* Modifier la direction de la disposition en colonne */
        align-items: center;
        justify-content: center;
        height: 100vh;
    }

    label {
        /*display: block;*/
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

    table {
        margin-top: 20px; /* Ajout de marge en haut pour le tableau */
        border-collapse: collapse;
        width: 100%;
    }

    th, td {
        border: 1px solid #ddd;
        padding: 8px;
        text-align: left;
    }

    th {
        background-color: #4caf50;
        color: white;
    }
</style>

</head>
<body>
  <%@ include file="header.jsp" %>
  <div class="contenus">
<form action="AfficheActiviteServlet" method="post">
    <h1>VOIR LES ACTIVITES DANS UN BOUQUET  </h1>
     <label for="bouquet">Nom bouquet�</label>
            <select name="bouquet" id="bouquet">
                <%for(int i=0; i<bouquet.size(); i++){%>
                    <option id="bouquet" name="bouquet" value="<%=bouquet.get(i).getIdBouquet()%>"><%=bouquet.get(i).getNom()%></option>
                <%}%>
            </select>
    <button type="submit">Afficher les activités</button>
    <br>
    <table>
        <thead>
            <tr>
                <th>NOM</th>
                <th>NOMBRE D'ACTIVITES</th>
            </tr>
        </thead>
        <tbody>
            <%if(activite!=null){%>
            <%--<%=activite.size()%>--%>
                <%for(int i=0; i<activite.size(); i++){%>
                    <tr>

                    <td><%=activite.get(i).getNom()%></td>
                    <td><%=activite.get(i).getNbactivite()%></td>
                    </tr>

            <%}%>
      
        <%}%>
        </tbody>
    </table>
    <ul>
       
    </ul>
</form>
  </div>
</body>
</html>
