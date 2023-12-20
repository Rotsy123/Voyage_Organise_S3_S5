package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BouquetActivite {

    public String getIdbouquet() {
        return idbouquet;
    }

    public void setIdbouquet(String idbouquet) {
        this.idbouquet = idbouquet;
    }

    public String getIdactivite() {
        return idactivite;
    }

    public void setIdactivite(String idactivite) {
        this.idactivite = idactivite;
    }
    public BouquetActivite(){}
    public BouquetActivite(Bouquet bouquet, List<Activite> activitels) {
        this.bouquet = bouquet;
        this.activitels = activitels;
    }

    public Bouquet getBouquet() {
        return bouquet;
    }

    public void setBouquet(Bouquet bouquet) {
        this.bouquet = bouquet;
    }

    public List<Activite> getActivitels() {
        return activitels;
    }

    public void setActivitels(List<Activite> activitels) {
        this.activitels = activitels;
    }
    Bouquet bouquet;
    List<Activite> activitels; 
    String idbouquet;
    String idactivite;
    public BouquetActivite(String idbouquet, String idactivite) {
        this.idbouquet = idbouquet;
        this.idactivite = idactivite;
    }
    public void Insert (Connection connexion) throws SQLException{
        System.out.println("Insert into bouquetactivite (idbouquet, idactivite) values ("+this.getIdbouquet()+","+ this.idactivite+")");
        String requete = "Insert into bouquetactivite (idbouquet, idactivite) values ("+this.getIdbouquet()+","+ this.getIdactivite()+")";
        PreparedStatement preparedStatement = null;
        preparedStatement = connexion.prepareStatement(requete);
        int lignesAffectees = preparedStatement.executeUpdate();
        if (lignesAffectees > 0) {
            System.out.println("Données insérées avec succès !");
        } else {
            System.out.println("Aucune donnée insérée.");
        }
    }
    public BouquetActivite GetByIdBouquet(Connection connexion, String idbouquet) throws SQLException{
        String requete = "Select * from v_bouquetactivite where bouquet_idbouquet="+idbouquet;
        PreparedStatement prepstat=null;
        prepstat=connexion.prepareStatement(requete);
        ResultSet results= prepstat.executeQuery();
        BouquetActivite ba = null;
        List<Activite> la = new ArrayList<>();
        Bouquet b = null; 
        while(results.next()){
            la.add(new Activite(results.getString(2), results.getString(7)));
            b = new Bouquet(results.getString(4), results.getString(5));
        }
        ba = new BouquetActivite (b, la);
        return ba;
    }
}
