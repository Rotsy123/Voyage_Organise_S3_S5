<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <style>

    .navbar {
      height:95vh; 
      width: 200px;
      position: fixed;
      background-color: #333;
      padding-top: 20px;
            margin: 0;
      font-family: Arial, sans-serif;
      overflow-y: auto; 
    }

    .navbar a {
      padding: 10px;
      text-decoration: none;
      font-size: 18px;
      color: white;
      display: block;
      transition: 0.3s;
    }

    .navbar a:hover {
      background-color: #555;
    }

    .contenus {
      margin-left: 200px;
      padding: 16px;
    }
  </style>
</head>
<body>

  <div class="navbar">
    <a href="index.jsp">Inserer une nouvelle Activite</a>
    <a href="bouquet.jsp">Inserer un nouveau Bouquet</a>
    <a href="BouquetActiviteServlets">Inserer un Bouquet-Activite</a>
    <a href="AfficheActiviteServlet">Voir les Activites dans un bouquet</a>
    <a href="VoyageInsertion">Inserer un nouveau voyage</a>
    <a href="AfficheActiviteServlet">Filtre par activite</a>
    <a href="filtrePrix.jsp">Filtre par prix</a>
    <a href="EntreeStockServlet">Entree en stock</a>
    <a href="SortieStockServlet">Sortie de stock</a>
    <a href="ResteStockServlet">Reste en stock</a>
    <a href="MpiasaInsertionServlet">Nouvel employe</a>
    <a href="categoriempiasa.jsp">Nouvelle Categorie d'employe</a>
    <a href="FabricationServlet">Conception de nouveau voyage</a>
    <a href="filtreBenefice.jsp">Filtre par rapport au benefice</a>
    <a href="EmployeServlet">Liste employés avec ancienneté</a>



  </div>

  <div class="contenus">
  </div>
</body>
</html>
