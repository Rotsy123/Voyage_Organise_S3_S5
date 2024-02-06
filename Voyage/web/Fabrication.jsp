<%@page import="model.Mpiasa"%>
<%@page import="model.Voyage"%>
<%@page import="java.util.List"%>
<%
    List<Voyage> lsVoyage=(List<Voyage>)request.getAttribute("lsVoyage");
    List<Mpiasa> lsMpiasa=(List<Mpiasa>)request.getAttribute("lsMpiasa");
%><!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Formulaire</title>
    <style>
 
    </style>
</head>
<body>
     <%@ include file="header.jsp" %>
      <div class="contenus">
    <div class="form-container">
        <form action="FabricationServlet" method="POST">

            <label for="selectOption">Select Voyage:</label>
            <select id="selectOption" name="voyage">
                <%
                    for(int i=0 ; i<lsVoyage.size() ; i++){ 
                %>
                        <option value="<%=lsVoyage.get(i).getIdvoyage()%>"><%=lsVoyage.get(i).getBa().getBouquet().getNom()%></option>
                <%
                    }
                %>
            </select>
            <label for="selectOption">Select Mpiasa:</label>
            <select id="selectOption" name="mpiasa">
                <%
                    for(int i=0 ; i<lsMpiasa.size() ; i++){ 
                %>
                        <option value="<%=lsMpiasa.get(i).getId()%>"><%=lsMpiasa.get(i).getNom()%></option>
                <%
                    }
                %>
            </select>

            <label for="inputText2">Horaire:</label>
            <input type="text" id="inputText2" name="horaire">
            <button type="submit">Valider</button>
        </form>
    </div>
      </div>
</body>
</html>
