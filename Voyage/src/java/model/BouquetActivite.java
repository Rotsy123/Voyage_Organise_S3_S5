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
    double nbactivite;

    public double getNbactivite() {
        return nbactivite;
    }

    public void setNbactivite(double nbactivite) {
        this.nbactivite = nbactivite;
    }
    
    public BouquetActivite(String idbouquet, String idactivite,double nbactivite) {
        this.idbouquet = idbouquet;
        this.idactivite = idactivite;
        this.nbactivite=nbactivite;
    }
    public void Insert (Connection connexion) throws SQLException{
        System.out.println("Insert into bouquetactivite (idbouquet, idactivite) values ("+this.getIdbouquet()+","+ this.idactivite+")");
        String requete = "Insert into bouquetactivite (idbouquet, idactivite,nbactivite) values ("+this.getIdbouquet()+","+ this.getIdactivite()+","+ this.getNbactivite()+")";
        PreparedStatement preparedStatement = null;
        preparedStatement = connexion.prepareStatement(requete);
        int lignesAffectees = preparedStatement.executeUpdate();
        if (lignesAffectees > 0) {
            System.out.println("Données insérées avec succès !");
        } else {
            System.out.println("Aucune donnée insérée.");
        }
    }
    public BouquetActivite GetByIdBouquet(Connection connexion, String idbouquet) throws SQLException, Exception{
        String requete = "Select * from v_bouquetactivite where bouquet_idbouquet="+idbouquet;
        PreparedStatement prepstat=null;
        prepstat=connexion.prepareStatement(requete);
        ResultSet results= prepstat.executeQuery();
        BouquetActivite ba = null;
        List<Activite> la = new ArrayList<>();
        Bouquet b = null; 
        double nbAct=0;
        while(results.next()){
            Activite ins=new Activite(results.getString(2), results.getString(7),results.getDouble(8));
            if(results.getDouble(3)>1){
                double temp=results.getDouble(3);
                while(temp>1){
                    la.add(ins);
                    temp--;
                }
            }
            la.add(ins);
            b = new Bouquet(results.getString(5), results.getString(4));
            nbAct=nbAct+results.getDouble(3);
        }
        ba = new BouquetActivite (b, la);
        ba.setNbactivite(nbAct);
        return ba;
    }
}
