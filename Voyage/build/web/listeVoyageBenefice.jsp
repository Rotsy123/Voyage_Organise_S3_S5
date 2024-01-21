<%-- 
    Document   : listeVoyagePrix
    Created on : Jan 9, 2024, 2:59:07 PM
    Author     : USER
--%>
<%@page import="model.Voyage"%>
<%@page import="java.util.List"%>
<% List<Voyage> voyage=(List<Voyage>)request.getAttribute("voyages");%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Liste voyages</title>
    </head>
    <style>

form {
    background-color: #fff;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 0 10px #ccc;
    width: 400px; /* Adjust the width as needed */
    text-align: center;
        font-family: Arial, sans-serif;
    background-color: #f4f4f4;
    margin: 0;
    padding: 0;
    display: flex;
    align-items: center;
    justify-content: center;
    height: 100vh;
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
    
    <body>
      <%@ include file="header.jsp" %>
      <div class="contenus">
        <table>
        <thead>
            <tr>
                <th>Bouquet</th>
                <th>Duree en jour</th>
                <th>Prix de revient</th>
                <th>Benefice</th>
            </tr>
        </thead>
        <tbody>
            <% for (int i = 0; i < voyage.size(); i++) { %>
                <tr>
                    <td><%= voyage.get(i).getBa().getBouquet().getNom() %></td>
                    <td><%= voyage.get(i).getDureejour() %></td>
                    <td><%= voyage.get(i).PrixDeRevient(Integer.parseInt(voyage.get(i).getIdvoyage())) %></td>
                    <td><%= voyage.get(i).GetBenefice(voyage.get(i).getIdvoyage())%></td>
                </tr>
            <% } %>
        </tbody>
    </table>
      </div>
    </body>
</html>
