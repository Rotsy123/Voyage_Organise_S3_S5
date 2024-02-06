<%@page import="model.Mpiasa"%>
<%@page import="java.util.List"%>
<%List<Mpiasa> all = (List<Mpiasa>)request.getAttribute("mpiasa");%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
     <%@ include file="header.jsp" %>

<body>
    <div class="contenus">
       <table>
    <thead>
        <tr>
            <th>Nom</th>
            <th>Salaire horaire</th>
            <th>Action</th>
        </tr>
    </thead>
    <tbody>
        <% for(int i = 0; i < all.size(); i++) { %>
            <form action="ListeMpiasaServlet" method="POST">
                <tr>
                    <td>
                        <input type="hidden" name="idmpiasa" id="idmpiasa" value="<%= all.get(i).getId() %>">
                        <input type="text" name="nom" id="nom" value="<%= all.get(i).getNom() %>">
                    </td>
                    <td>
                        <input type="text" name="salaire" id="salaire" placeholder="<%= all.get(i).getSalaireHoraire() %>">
                    </td>
                    <td>
                        <button type="submit">UPDATE</button>
                    </td>
                </tr>
            </form>
        <% } %>
    </tbody>
</table>

    </div>
</body>
</html>
