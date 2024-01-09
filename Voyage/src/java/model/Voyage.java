/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author USER
 */
public class Voyage {
    @JsonProperty("idbouquet")
    String idbouquet;
    @JsonProperty("duree")
    double duree;
    @JsonProperty("idcatelieu")
    String idcatelieu;
    @JsonProperty("prix")
    double prix;
    BouquetActivite ba;

    public void setIdBouquet(String idBouquet) {
        this.idbouquet = idBouquet;
    }

    public void setDureejour(double dureejour) {
        this.duree = dureejour;
    }

    public void setIdCateLieu(String idCateLieu) {
        this.idcatelieu = idCateLieu;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public BouquetActivite getBa() {
        return ba;
    }

    public void setBa(BouquetActivite ba) {
        this.ba = ba;
    }
    

    public String getIdBouquet() {
        return idbouquet;
    }

    public double getDureejour() {
        return duree;
    }

    public String getIdCateLieu() {
        return idcatelieu;
    }

    public double getPrix() {
        return prix;
    }

    public Voyage() {
    }

    public Voyage(String idbouquet, double duree, String idcatelieu, double prix) {
        this.idbouquet = idbouquet;
        this.duree = duree;
        this.idcatelieu = idcatelieu;
        this.prix = prix;
    }
    
    public Voyage(String idbouquet, double duree, String idcatelieu, double prix,BouquetActivite bouquetAct) {
        this.idbouquet = idbouquet;
        this.duree = duree;
        this.idcatelieu = idcatelieu;
        this.prix = prix;
        this.ba=bouquetAct;
        
    }
    
    
    
    public LocalTime getDureejours(){
        long hoursPart=(long) this.getDureejour();
        long minutesPart=Math.round((this.getDureejour()-hoursPart)*60);
        
        LocalTime basetime= LocalTime.of(0,0);
        LocalTime resultTime= basetime.plusHours(hoursPart).plusMinutes(minutesPart);
        return resultTime;
    }
    
    public void Insert (Connection connexion) throws SQLException{
        System.out.print("Insert into voyage (idBouquet,dureejours,idcategorie,prix) values ("+ this.getIdBouquet()+","+ this.getDureejour()+","+this.getIdCateLieu()+","+this.getPrix()+")");
        String requete ="Insert into voyage (idbouquet,dureejours,idcategorie,prix) values ("+ this.getIdBouquet()+","+ this.getDureejour()+","+this.getIdCateLieu()+","+this.getPrix()+")";
        PreparedStatement preparedStatement = null;
        preparedStatement = connexion.prepareStatement(requete);
        int lignesAffectees = preparedStatement.executeUpdate();
        if (lignesAffectees > 0) {
            System.out.println("Données insérées avec succès !");
        } else {
            System.out.println("Aucune donnée insérée.");
        }
    }  
    
    public List<Voyage> getByIdActivite(Connection connexion,String idActivite )throws Exception{
        String requete="select * from voyage";
        PreparedStatement prepstat=null;
        prepstat=connexion.prepareStatement(requete);
        ResultSet results= prepstat.executeQuery();
        BouquetActivite ba = null;
        List<Voyage> la = new ArrayList<>();
        Voyage b = null; 
        while(results.next()){
            BouquetActivite baqa= new BouquetActivite().GetByIdBouquet(connexion, results.getString(2));
             Iterator<Activite> iterator = baqa.getActivitels().iterator();
    
    while (iterator.hasNext()) {
        Activite activite = iterator.next();
        
        if (!activite.getId().equals(idActivite)) {
            iterator.remove(); // Remove the current element safely
        }
    }

            la.add(new Voyage(results.getString(2), results.getDouble(3),results.getString(4),results.getDouble(5),baqa));
        }
        return la;
    }
    
    
    public static List<Voyage> GetByPrix(double prixMin , double prixMax , Connection connexion) throws Exception{
        String query="select * from v_vraiPrixVoyage where sum<="+prixMax+" and sum>="+prixMin+";";
        PreparedStatement prepstat=null;
        prepstat=connexion.prepareStatement(query);
        ResultSet results= prepstat.executeQuery();
        BouquetActivite ba = null;
        List<Voyage> la = new ArrayList<>();
        Voyage b = null; 
        while(results.next()){
            BouquetActivite baqa= new BouquetActivite().GetByIdBouquet(connexion, results.getString(2));
             Iterator<Activite> iterator = baqa.getActivitels().iterator();
            la.add(new Voyage(results.getString(2), results.getDouble(3),results.getString(4),results.getDouble(5),baqa));
        }
        return la;
        
    }
}
