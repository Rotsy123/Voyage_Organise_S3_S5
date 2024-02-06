<%-- 
    Document   : insertionClient
    Created on : 25 janv. 2024, 14:29:48
    Author     : ROTSY
--%>

<%@page import="model.Voyage"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    
     String erreurMessage = (String) request.getAttribute("erreur");
    List<Voyage> ls=(List<Voyage>)request.getAttribute("voyages");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insertion de vente</title>
    </head>
      <style>
        .erreur-message {
            color: red;
            font-weight: bold;
        }
    </style>
    <body>
        <%@ include file="header.jsp" %>
      <div class="contenus">
          <%if(erreurMessage!=null){%>
           <div class="erreur-message">
               
            <%= erreurMessage %>
        </div>
        <%}%>
    <div class="form-container">
            <form action='VenteInsertion' method='POST'>
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
            
            <button type="submit">Valider</button>
        </form>
    </div>
      </div>
    </body>
</html>
