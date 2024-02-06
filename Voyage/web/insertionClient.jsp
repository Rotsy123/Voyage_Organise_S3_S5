<%-- 
    Document   : insertionClient
    Created on : 25 janv. 2024, 14:29:48
    Author     : ROTSY
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insertion client</title>
    </head>
    <body>
        <%@ include file="header.jsp" %>
      <div class="contenus">
    <div class="form-container">
        <form action='ClientInsertion' method='GET'>
            <label for="nom">Nom Client: </label>
            <input type="text" id="nom" name="nom">

            <label for="dtn">Date de naissance: </label>
            <input type="date" id="dtn" name="dtn">

            <label for="genre">Genre: </label>
            <select id="genre" name="genre">
                <option value="0">Male</option>
                <option value="1">Femelle</option>
            </select>
            
            <button type="submit">Valider</button>
        </form>
    </div>
      </div>
    </body>
</html>
