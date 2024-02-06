 <%@page import="model.Grade"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Insertion d'activités</title>
    <style>

    </style>
</head>
  <%@ include file="header.jsp" %>

<body>
    <div class="contenus">
<div id="activite-form">
    
    <form action="GradeServlet" method="post">
        <h1>INSERER UNE NOUVELLE GRADE</h1>
            
        <div class="entry">
            <label for="nomgrade">Nom grade</label>
            <input type="text" id="nomgrade" name="nomgrade">
            <label for="min">Anciennete Minimum</label>
            <input type="text" id="min" name="min">
            <label for="max">Anciennete Maximum</label>
            <input type="text" id="max" name="max">
            <label for="salaire">Multiplier le salaire par</label>
            <input type="text" id="salaire" name="salaire">
        </div>

        <button type="submit">Valider</button>
    </form>

        <% List<Grade> all = Grade.GetAll(); %>

        <table>
            <thead>
                <tr>
                    <th>NOM</th>
                    <th>ANCIENNETE MINIMUM</th>
                    <th>ANCIENNETE MAXIMUM</th>
                    <th>MULTIPLIER SALAIRE MINIMUM PAR</th>
                </tr>
            </thead>
            <tbody>
                <% for(int i=0; i<all.size(); i++) { %>
                <form action="GradeServlet" method="get">

                    <tr>
                        <input type="hidden" name="idgrade" value="<%= all.get(i).getId()%>">

                        <td><input type="text" name="nom" id="nom" placeholder="<%= all.get(i).getNom() %>"> </td>
                        <td><input type="text" name="min" id="min" placeholder="<%= all.get(i).getMin() %>"> </td>
                        <td><input type="text" name="max" id="max" placeholder="<%= all.get(i).getMax() %>"> </td>
                        <td><input type="text" name="salaire" id="salaire" placeholder="<%= all.get(i).getSalaire() %>"> </td>
                        <td><button type="submit">UPDATE</button></td>
                    </tr>
                </form>

                <% } %>
            </tbody>
        </table>
                    </form>

</div>

    </div>
</body>
</html>
