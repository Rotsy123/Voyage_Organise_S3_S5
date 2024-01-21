package model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Activite {
    String id;
    String nom;
    double prix;
    double nbactivite;

    public double getNbactivite() {
        return nbactivite;
    }

    public void setNbactivite(double nbactivite) {
        this.nbactivite = nbactivite;
    }
    
    
    public String getId(){
        return this.id;
    }
    public void setId(String id){
        this.id= id;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) throws Exception {
        if(prix>=0){
            this.prix = prix;
        }
        else{
            throw new Exception("Prix négatif");
        }
    }
    
    public String getNom(){
        return this.nom;
    }
    public void setNom(String nom){
        this.nom = nom;
    }
    public Activite(String id, String nom,double prix, double nbactivite) throws Exception{
        setId(id);
        setNom(nom);
        setPrix(prix);
        setNbactivite(nbactivite);
    }
    public Activite (){}
    public Activite(String id, String nom,double prix) throws Exception{
        setId(id);
        setNom(nom);
        setPrix(prix);
    }
    public Activite(String nom){
        setNom(nom);
    }
    public void Insert (Connection connexion) throws SQLException{
        System.out.print("Insert into activite (nom) values '"+ this.getNom()+"'");
        String requete = "Insert into activite (nom,prix) values ('"+ this.getNom()+"',"+ this.getPrix()+")";
        PreparedStatement preparedStatement = null;
        preparedStatement = connexion.prepareStatement(requete);
        int lignesAffectees = preparedStatement.executeUpdate();
        if (lignesAffectees > 0) {
            System.out.println("Données insérées avec succès !");
        } else {
            System.out.println("Aucune donnée insérée.");
        }
    }    
    public List<Activite> GetAllActivite(Connection connexion) throws SQLException, Exception{
        String requete = "Select * from Activite";
        PreparedStatement prepstat=null;
        prepstat=connexion.prepareStatement(requete);
        ResultSet results=prepstat.executeQuery();
        List<Activite> ls=new ArrayList<>();
        while(results.next()){
            Activite b = new Activite(results.getString(1), results.getString(2),results.getDouble(4));;
            ls.add(b);
        }
        prepstat.close();
//        connexion.close();
        return ls;
    }
    
    public static List<Activite> GetActiviteById(Connection connexion,String id) throws SQLException, Exception{
        String requete = "Select * from Activite where idactivite="+id;
        PreparedStatement prepstat=null;
        prepstat=connexion.prepareStatement(requete);
        ResultSet results=prepstat.executeQuery();
        List<Activite> ls=new ArrayList<>();
        while(results.next()){
            Activite b = new Activite(results.getString(1), results.getString(2),results.getDouble(4));;
            ls.add(b);
        }
        prepstat.close();
//        connexion.close();
        return ls;
    }
}
