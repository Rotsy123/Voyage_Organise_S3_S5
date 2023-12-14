package model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
 
public class Bouquet {
    String idBouquet;
    String nom;

    ///setters
    public void setIdBouquet(String idBouquet) {
        this.idBouquet = idBouquet;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    ///getters
    public String getIdBouquet() {
        return idBouquet;
    }
    public String getNom() {
        return nom;
    }
    ///construct
    public Bouquet(){}
    public Bouquet(String nom){
        setNom(nom);
    }
    public Bouquet(String id,String nom){
        setNom(nom);
        setIdBouquet(id);
    }
    public void InsererBouquet(Connection connexion) throws SQLException{
        System.out.println("Insert into bouquet (nom) values ('"+ this.getNom()+"')");
        String requete = "Insert into bouquet (nom) values ('"+ this.getNom()+"')";
        PreparedStatement preparedStatement = null;
        preparedStatement = connexion.prepareStatement(requete);
        int lignesAffectees = preparedStatement.executeUpdate();
        if (lignesAffectees > 0) {
            System.out.println("Données insérées avec succès !");
        } else {
            System.out.println("Aucune donnée insérée.");
        }
    }
    public List<Bouquet> GetAllBouquet(Connection connexion) throws SQLException{
        String requete = "Select * from Bouquet";
        PreparedStatement prepstat=null;
        prepstat=connexion.prepareStatement(requete);
        ResultSet results= prepstat.executeQuery();
        List<Bouquet> ls=new ArrayList<>();
        while(results.next()){
            System.out.println("ID"+results.getString(1));
                    
            Bouquet b = new Bouquet(results.getString(1), results.getString(2));
            ls.add(b);
        }
        prepstat.close();
//        connexion.close();
        return ls;
    }
    
}
