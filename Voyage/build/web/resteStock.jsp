<%-- 
    Document   : resteStock
    Created on : Jan 15, 2024, 9:37:23 PM
    Author     : USER
--%>
<%@page import="java.sql.Connection"%>
<%@page import="model.Connexion"%>
<%@page import="model.Activite"%>
<%@page import="model.ResteStock"%>
<%@page import="java.util.List"%>
<%
    List<ResteStock> restes=(List<ResteStock>)request.getAttribute("restestock");
    List<Activite> activite=(List<Activite>)request.getAttribute("activites");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
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
      <%@ include file="header.jsp" %>
      <div class="contenus">
    <body>
        <form action="ResteStockServlet" method="post">
            <label for="activite">SÃ©lectionnez une activitÃ© :</label>
            <select id="activite" name="activite">
                <option value="0">Tout</option>
                <%
                    for(int i=0 ; i<activite.size() ; i++){
                %>
                        <option value="<%=activite.get(i).getId()%>"><%=activite.get(i).getNom()%></option>
                <%
                    }
                %>
                <!-- Ajoutez d'autres options au besoin -->
            </select>
                <input type="submit" value="Filtrer">
            <table>
            <thead>
                <tr>
                    <th>Nom Activite</th>
                    <th>Stock Actuel</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    Connexion conn=new Connexion();
                    Connection c=conn.GetConnection();
                    for (int i = 0; i < restes.size(); i++) { 
                    Activite act=Activite.GetActiviteById(c,restes.get(i).getIdactivite()).get(0);    
                    %>
                    <tr>
                        <td><%= act.getNom()%></td>
                        <td><%= restes.get(i).getStockactuel() %></td>
                    </tr>
                <% }  c.close();%>
            </tbody>
            </table>
        </form>
      </div>
    </body>
</html>
