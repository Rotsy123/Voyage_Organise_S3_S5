<%@page import="model.Activite"%>
<%@page import="model.Voyage"%>
<%@page import="java.util.List"%>
<%List<Activite> activite = (List<Activite>) request.getAttribute("activite");%>
<%List<Voyage> voyage = (List<Voyage>) request.getAttribute("voyage");%>
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
    width: 400px; /* Adjust the width as needed */
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

table {
    width: 100%;
    border-collapse: collapse;
    margin-top: 20px;
}

th, td {
    border: 1px solid #ccc;
    padding: 10px;
    text-align: left;
}

thead {
    background-color: #4caf50;
    color: white;
}

tbody tr:nth-child(even) {
    background-color: #f2f2f2;
}

    </style>
</head>
<body>

<form action="AfficheVoyage" method="post">
     <label for="activite">Nom activite</label>
            <select name="activite" id="activite">
                <%for(int i=0; i<activite.size(); i++){%>
                    <option id="activite" name="activite" value="<%=activite.get(i).getId()%>"><%=activite.get(i).getNom()%></option>
                <%}%>
            </select>
    <button type="submit">Afficher les voyages</button>
<!--
    <ul>
        <%if(voyage!=null){%>
         Remplacez ces valeurs par les activités réelles récupérées de votre servlet 
            <%for(int i=0; i<voyage.size(); i++){%>
            
            <%for(int k=0; k<voyage.get(i).getBa().getActivitels().size(); k++){%>
                            <li><%=voyage.get(i).getBa().getBouquet().getNom()%> ------- <%=voyage.get(i).getBa().getActivitels().get(k).getNom()%>--------<%=voyage.get(i).getBa().getActivitels().size()%></li>

<%}%>
            <%}%>
      
        <%}%>
        
    </ul>-->
        <% if (voyage != null) { %>
    <table>
        <thead>
            <tr>
                <th>Bouquet</th>
                <th>Duree en jour</th>
                <th>Prix</th>
                <th>Nombre activite</th>
            </tr>
        </thead>
        <tbody>
            <% for (int i = 0; i < voyage.size(); i++) { %>
                <tr>
                    <td><%= voyage.get(i).getBa().getBouquet().getNom() %></td>
                    <td><%= voyage.get(i).getDureejour() %></td>
                    <td><%= voyage.get(i).getPrix() %></td>
                    <td><%= voyage.get(i).getBa().getActivitels().size() %></td>
                </tr>
            <% } %>
        </tbody>
    </table>
<% } %>

            
</form>

</body>
</html>
