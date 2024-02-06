 <%@page import="com.google.gson.Gson"%>
<%@page import="model.StatistiqueVente"%>
<%@page import="model.Voyage"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<% 
    StatistiqueVente toshow=(StatistiqueVente)request.getAttribute("stats"); 

%>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Insertion d'activitÃ©s</title>  
<!--<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>-->
    <script src="Chart.js"></script>


    <style>

    </style>
    <% 
        List<Voyage> ls=(List<Voyage>)request.getAttribute("voyages");
    %>
</head>
  <%@ include file="header.jsp" %>

<body>
    <div class="contenus">
        <form action="FiltreVente" method="POST">
            <label for="voyage">Voyage</label>
                <select name="voyage" id="voyage">
                     <option value="0">Tous</option>
                    <%
                        for(int i=0 ; i<ls.size() ; i++){
                    %>
                        <option value="<%= ls.get(i).getIdvoyage()%>"><%= ls.get(i).getBa().getBouquet().getNom() %></option>
                    <% 
                        }
                    %>
                </select>
                <input type="submit" value="Filtrer">
                           
<%if(request.getAttribute("stats")!=null){%>
        
     <table>
            <thead>
                <tr>
                    <th>
                        NB femmes
                    </th>
                    <th>Nb hommes</th>
                    <th>Produit lafo femme</th>
                    <th>Produit lafo homme</th>

                </tr>
            </thead>
            <tbody>
                <tr>
                <td><%=toshow.getGenre()[1]%></td>
                <td><%=toshow.getGenre()[0]%></td>
                <td><%=toshow.getNbproduit()[1]%></td>
                <td><%=toshow.getNbproduit()[0]%></td>
                <tr>

            </tbody>
        </table>
                <br><br>
                        <canvas id="myChart"></canvas>

  <script>
    // Fonction pour mettre à jour le graphique avec les données du serveur
    function updateChart(genreData, nbpdtData) {
      var data = {
        labels: ['Genre Masculin', 'Genre Féminin'],
        datasets: [{
          label: 'Nombre de produits',
          data: nbpdtData,
          backgroundColor: [
            'rgba(255, 99, 132, 0.2)',
            'rgba(54, 162, 235, 0.2)',
          ],
          borderColor: [
            'rgba(255, 99, 132, 1)',
            'rgba(54, 162, 235, 1)',
          ],
          borderWidth: 1
        }]
      };

      var options = {
        scales: {
          y: {
            beginAtZero: true
          }
        }
      };

      var ctx = document.getElementById('myChart').getContext('2d');
      var myChart = new Chart(ctx, {
        type: 'bar',
        data: data,
        options: options
      });
    }

    // Convertir les données Java en JSON
    var genreData = <%= new Gson().toJson(toshow.getGenre()) %>;
    var nbpdtData = <%= new Gson().toJson(toshow.getNbproduit()) %>;

    // Mettre à jour le graphique avec les données du serveur
    updateChart(genreData, nbpdtData);
  </script>
  
<%}%>
         </form>
     

    </div> 
    </body>
</html>
