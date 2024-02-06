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
    @JsonProperty("idvoyage")
    String idvoyage;
    @JsonProperty("taille")
    int taille;

    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public String getIdvoyage() {
        return idvoyage;
    }

    public void setIdvoyage(String idvoyage) {
        this.idvoyage = idvoyage;
    }
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

    public Voyage(String idvoyage,String idbouquet, double duree, String idcatelieu, double prix) {
        this.setIdvoyage(idvoyage);
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
    public Voyage(String idbouquet, double duree, String idcatelieu, double prix,BouquetActivite bouquetAct,int taille) {
        this.idbouquet = idbouquet;
        this.duree = duree;
        this.idcatelieu = idcatelieu;
        this.prix = prix;
        this.ba=bouquetAct;
        this.taille=taille;
        
    }
    
    
    
    public LocalTime getDureejours(){
        long hoursPart=(long) this.getDureejour();
        long minutesPart=Math.round((this.getDureejour()-hoursPart)*60);
        
        LocalTime basetime= LocalTime.of(0,0);
        LocalTime resultTime= basetime.plusHours(hoursPart).plusMinutes(minutesPart);
        return resultTime;
    }
    
    public static List<Voyage> GetAllVoyage(Connection connexion) throws Exception{
        String query="select * from Voyage;";
        PreparedStatement prepstat=null;
        prepstat=connexion.prepareStatement(query);
        ResultSet results= prepstat.executeQuery();
        BouquetActivite ba = null;
        List<Voyage> la = new ArrayList<>();
        Voyage b = null; 
        while(results.next()){
            BouquetActivite baqa= new BouquetActivite().GetByIdBouquet(connexion, results.getString(2));

             Iterator<Activite> iterator = baqa.getActivitels().iterator();
            b=new Voyage(results.getString(2), results.getDouble(3),results.getString(4),results.getDouble(5),baqa);
            b.setIdvoyage(results.getString(1)); 
            b.setTaille(results.getInt(6));
            la.add(b);
        }
        return la;
    }
    
    public void Insert (Connection connexion) throws SQLException{
        System.out.print("Insert into voyage (idBouquet,dureejours,idcategorie,prix) values ("+ this.getIdBouquet()+","+ this.getDureejour()+","+this.getIdCateLieu()+","+this.getPrix()+")");
        String requete ="Insert into voyage (idbouquet,dureejours,idcategorie,prix,taille) values ("+ this.getIdBouquet()+","+ this.getDureejour()+","+this.getIdCateLieu()+","+this.getPrix()+","+this.getTaille()+")";
        PreparedStatement preparedStatement = null;
        preparedStatement = connexion.prepareStatement(requete);
        int lignesAffectees = preparedStatement.executeUpdate();
        if (lignesAffectees > 0) {
            System.out.println("Données insérées avec succès !");
        } else {
            System.out.println("Aucune donnée insérée.");
        }
    }  
    
    public static Voyage GetByIdvoyage(Connection connexion , String idvoyage) throws Exception{
        String requete="select * from voyage where idvoyage="+idvoyage;
        PreparedStatement prepstat=null;
        prepstat=connexion.prepareStatement(requete);
        ResultSet results= prepstat.executeQuery();
        Voyage b = null;

        if(results.next()){
            BouquetActivite baqa= new BouquetActivite().GetByIdBouquet(connexion, results.getString(2));
             Iterator<Activite> iterator = baqa.getActivitels().iterator();
            b = new Voyage(results.getString(2), results.getDouble(3),results.getString(4),results.getDouble(5),baqa);
            b.setIdvoyage(results.getString(1));
        }
        return b;
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
    
    public List<BouquetActivite> GetAllActivite(Connection connexion) throws Exception{
        String query="select * from v_voyagebouquetact where idvoyage="+this.getIdvoyage();
        PreparedStatement prepstat=null;
        prepstat=connexion.prepareStatement(query);
        ResultSet results= prepstat.executeQuery();
        List<BouquetActivite> ba = new ArrayList<>();
        while(results.next()){
            BouquetActivite baqa= new BouquetActivite().GetByIdBouquet(connexion, results.getString(2));
            ba.add(baqa);
        }
        return ba;
    }
    
    public double GetKaramaMpiasa(int idvoyage) throws Exception{
        Connexion connexion = new Connexion();
        String requete = "select*from karamampiasa where idvoyage = "+idvoyage;
        PreparedStatement prepstat=null;
        prepstat=connexion.GetConnection().prepareStatement(requete);
        ResultSet results= prepstat.executeQuery();
        double karama = 0;
        if(results.next()){
            karama = results.getDouble(1);
        }
        return karama;
    }
    
    public double GetPrixActivite(int idvoyage) throws Exception{
        Connexion c=new Connexion();
        double prix = 0;
            String query="select * from prixactiviteparentree where idvoyage="+idvoyage;
            PreparedStatement prepstat=null;
            prepstat=c.GetConnection().prepareStatement(query);
            ResultSet results= prepstat.executeQuery();
            if(results.next()){
                prix=results.getDouble(2);
            }

        return prix;
    }
    public double PrixDeRevient(int idvoyage) throws Exception{
        double karamaMpiasa=this.GetKaramaMpiasa(idvoyage);
        double prixActivite=this.GetPrixActivite(idvoyage);
        return karamaMpiasa+prixActivite;
    }
    
    public double GetBenefice(String idvoyage) throws Exception{
        Connexion c=new Connexion();
        Voyage v=Voyage.GetByIdvoyage(c.GetConnection(), idvoyage);
        double prixRevient=this.PrixDeRevient(Integer.parseInt(idvoyage));
        double benefice=v.getPrix()-prixRevient;
        return benefice;
    }
    
    public List<Voyage> GetBeneficeInInterval(Connection connexion, double prixMin , double prixMax) throws Exception{
        List<Voyage> lsVoyage=Voyage.GetAllVoyage(connexion);
        List<Voyage> res=new ArrayList<>();
        for(int i=0 ; i<lsVoyage.size() ; i++){
            double benefice=this.GetBenefice(lsVoyage.get(i).getIdvoyage());
            if(benefice>=prixMin && benefice<=prixMax){
                res.add(lsVoyage.get(i));
            }
        }
        return res;
    }
    
    public String getTailleString(){
        if(this.getTaille()==1){
            return "court";
        }
        return "long";
    }
}
