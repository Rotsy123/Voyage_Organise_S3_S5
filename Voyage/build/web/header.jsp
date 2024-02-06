<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <style>

    .navbar {
      height:max; 
      width: 200px;
      position: fixed;
      background-color: #f4f4f4;
      padding-top: 100px;
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
     

form{
    
        background-color: #fff;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 0 10px #ccc;
        font-family: Arial, sans-serif;
        background-color: white;
        margin: 0;
        padding: 0;
        display: flex;
/**/        flex-direction: column; /* Modifier la direction de la disposition en colonne */
        align-items: center;
        justify-content: center;
        height: 100vh;
}
.entry {
    margin-bottom: 10px;
    width: 100%;
}

label {
    display: block;
    margin-bottom: 5px;
    margin-left: 5px;
}
 
      select,
        input {
            width: 100%;
            padding: 12px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
            margin-bottom: 10px;
                margin-left: 5px;

        }
.dropbtn{
     background-color: #f4f4f6;
    color: black;
    padding: 10px 15px;
    border: none; 
    cursor: pointer;
    margin-bottom: 50px;
    height:60px;
    width:200px;
}
button {
    background-color: #4caf50;
    color: white;
    padding: 10px 15px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    margin-bottom: 10px;
}

button:hover {
    background-color: #45a049;
}
.dropbtn:hover{
    background-color: #ccccff;
}


    .dropdown-content {
      display: none;
      position: absolute;
      background-color: #f9f9f9;
      min-width: 160px;
      box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
      z-index: 1;
    }

    .dropdown-content a {
      float: none;
      color: black;
      padding: 12px 16px;
      text-decoration: none;
      display: block;
      text-align: left;
    }

    .dropdown-content a:hover {
      background-color: #ccccff;
    }

    .dropdown:hover .dropdown-content {
      display: block;
    }
    

 table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
            color:black;
        }
        tr:hover{
            background-color: #99ffcc;
        }

        th {
            background-color: #f2f2f2;
  </style>
</head>
<body>

    <div class="navbar">
        <div class="dropdown">
            <button class="dropbtn"><h2>VOYAGE</h2></button>
            <div class="dropdown-content">
              <a href="ServletTest">Inserer une nouvelle Activite</a>
              <a href="BouquetServlet">Inserer un nouveau Bouquet</a>
              <a href="BouquetActiviteServlets">Inserer un Bouquet-Activite</a>
              <a href="AfficheActiviteServlet">Voir les Activites dans un bouquet</a>
              <a href="VoyageInsertion">Inserer un nouveau voyage</a>
              <a href="FabricationServlet">Conception de nouveau voyage</a>
            </div>
        </div>
          
      <div class="dropdown">
          <button class="dropbtn"><h2>FILTRE</h2></button>
            <div class="dropdown-content">
              <a href="AfficheVoyage">Filtre par activite</a>
              <a href="filtrePrix.jsp">Filtre par prix</a>
              <a href="filtreBenefice.jsp">Filtre par rapport au benefice</a>
            </div>
      </div>
          
      <div class="dropdown">
          <button class="dropbtn"><h2>STOCK</h2></button>
          <div class="dropdown-content">
            <a href="EntreeStockServlet">Entree en stock</a>
            <a href="SortieStockServlet">Sortie de stock</a>
            <a href="ResteStockServlet">Reste en stock</a>
         </div>
      </div>
          
      <div class="dropdown">
          <button class="dropbtn"><h2>EMPLOYE</h2></button>
          <div class="dropdown-content">
            <a href="categoriempiasa.jsp">Nouvelle Categorie d'employe</a>
            <a href="insertionGrade.jsp">Insertion de grade</a>
            <a href="MpiasaInsertionServlet">Nouvel employe</a>
            <a href="ListeMpiasaServlet">Liste employe</a>
            <a href ="EmployeServlet">Liste des employes </a>
        </div>
        </div>
    <div class="dropdown">
        <button class="dropbtn"><h2>VENTE</h2></button>
          <div class="dropdown-content">
            <a href ="insertionClient.jsp">Insertion de client</a>
            <a href ="VenteInsertion">Insertion de vente</a>
            <a href ="FiltreVente">Statistiques vente</a>
            <a href ="PanierServlet">Ajouter au panier</a>

          </div>
  </div>
      </div>
  <div class="contenus"> 
  </div>
</body>
</html>
