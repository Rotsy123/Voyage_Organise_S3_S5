package model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategorieLieu {
    String id;
    String nom;
    
    public String getId(){
        return this.id;
    }
    public void setId(String id){
        this.id= id;
    }
    public String getNom(){
        return this.nom;
    }
    public void setNom(String nom){
        this.nom = nom;
    }
    
    public CategorieLieu (){}
    public CategorieLieu(String id, String nom){
        setId(id);
        setNom(nom);
    }
    public CategorieLieu(String nom){
        setNom(nom);
    }
    public void Insert (Connection connexion) throws SQLException{
        System.out.print("Insert into CategorieLieu (nom) values '"+ this.getNom()+"'");
        String requete = "Insert into CategorieLieu (nom) values ('"+ this.getNom()+"')";
        PreparedStatement preparedStatement = null;
        preparedStatement = connexion.prepareStatement(requete);
        int lignesAffectees = preparedStatement.executeUpdate();
        if (lignesAffectees > 0) {
            System.out.println("Données insérées avec succès !");
        } else {
            System.out.println("Aucune donnée insérée.");
        }
    }    
    public List<CategorieLieu> GetAllCategorieLieu(Connection connexion) throws SQLException{
        String requete = "Select * from CategorieLieu";
        System.out.println(requete);
        PreparedStatement prepstat=null;
        prepstat=connexion.prepareStatement(requete);
        ResultSet results=prepstat.executeQuery();
        List<CategorieLieu> ls=new ArrayList<>();
        while(results.next()){
            CategorieLieu b = new CategorieLieu(results.getString(1), results.getString(2));
            ls.add(b);
        }
        prepstat.close();
//        connexion.close();
        return ls;
    }
}
