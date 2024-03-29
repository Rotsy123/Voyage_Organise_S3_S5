package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategorieMpiasa {
    String id;
    String nom;
    
    public static CategorieMpiasa getByIdMpiasa(int idmpiasa) throws Exception{
        String requete = "select*from mpiasa join categoriempiasa on mpiasa.idcategorie=categoriempiasa.id where mpiasa.id = "+idmpiasa;
        Connexion connexion = new Connexion();
        PreparedStatement prepareStatement=null;
        prepareStatement=connexion.GetConnection().prepareStatement(requete);
        ResultSet results=prepareStatement.executeQuery();
        while(results.next()){
            return new CategorieMpiasa(results.getString("idcategorie"), results.getString("categorie"));
        }
        prepareStatement.close();
        connexion.GetConnection().close();
        return null;
    }
    public void Inserer()throws Exception{
        Connexion connexion = new Connexion();
        String requete = "insert into categoriempiasa (categorie) values ('"+this.getNom()+"')";
        PreparedStatement preparedStatement = null;
        preparedStatement = connexion.GetConnection().prepareStatement(requete);
        int lignesAffectees = preparedStatement.executeUpdate();
        if (lignesAffectees > 0) {
            System.out.println("Données insérées avec succès !");
        } else {
            System.out.println("Aucune donnée insérée.");
        }
    }
    public static List<CategorieMpiasa> GetAll() throws Exception{
        Connexion connexion = new Connexion();
        String requete = "Select*from categoriempiasa";
        PreparedStatement prepareStatement=null;
        prepareStatement=connexion.GetConnection().prepareStatement(requete);
        ResultSet results=prepareStatement.executeQuery();
        List<CategorieMpiasa> ls=new ArrayList<>();
        while(results.next()){
            ls.add(new CategorieMpiasa(results.getString(1), results.getString(2)));
        }
        prepareStatement.close();
        connexion.GetConnection().close();
        return ls;
    }
    public CategorieMpiasa(){}
    public CategorieMpiasa(String id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    
}
