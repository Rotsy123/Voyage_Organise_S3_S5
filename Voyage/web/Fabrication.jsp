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
        body {
    font-family: Arial, sans-serif;
    background-color: #f4f4f4;
    margin: 0;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
}

.form-container {
    background-color: #fff;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

form {
    display: grid;
    grid-template-columns: 1fr 1fr;
    gap: 15px;
}

label {
    margin-bottom: 8px;
    display: block;
    font-weight: bold;
}

input,
select {
    width: 100%;
    padding: 8px;
    box-sizing: border-box;
    margin-bottom: 15px;
    border: 1px solid #ccc;
    border-radius: 4px;
}

input[type="date"] {
    /* Specific styling for date input */
    padding: 8px;
}

button {
    padding: 10px;
    background-color: #4caf50;
    color: #fff;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}

button:hover {
    background-color: #45a049;
}

    </style>
</head>
<body>
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
</body>
</html>
