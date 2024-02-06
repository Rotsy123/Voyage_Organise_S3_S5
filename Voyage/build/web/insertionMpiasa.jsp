<%@page import="model.CategorieMpiasa"%>
<%@page import="java.util.List"%>
<%
    List<CategorieMpiasa> lsC=(List<CategorieMpiasa>)request.getAttribute("lscate");
    if(request.getAttribute("erreur")!=null){
        out.println(request.getAttribute("erreur"));
    }
%>
<!DOCTYPE html>
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
        <form action='MpiasaInsertionServlet' method='POST'>
            <label for="nom">Nom mpiasa:</label>
            <input type="text" id="nom" name="nom">

            <label for="dtn">Date naissance: </label>
            <input type="date" id="dtn" name="dtn">

            <label for="selectOption">Categorie: </label>
            <select id="selectOption" name="categorie">
                <%
                    for(int i=0 ; i<lsC.size() ; i++){ 
                %>
                        <option value="<%=lsC.get(i).getId()%>"><%=lsC.get(i).getNom()%></option>
                <%
                    }
                %>
            </select>
            <label for="salaire">Salaire par heure :</label>
            <input type="text" id="salaire" name="salaire">
            <label for="dtembauche">Date d'embauche :</label>
            <input type="date" id="dtembauche" name="dtembauche">
            
            <button type="submit">Valider</button>
        </form>
    </div>
      </div>
</body>
</html>
