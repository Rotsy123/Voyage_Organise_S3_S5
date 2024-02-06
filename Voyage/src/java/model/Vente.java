/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;
import java.sql.PreparedStatement;

/**
 *
 * @author ROTSY
 */
public class Vente {
    int idvente;

    public int getIdvente() {
        return idvente;
    }

    public void setIdvente(int idvente) {
        this.idvente = idvente;
    }
    int idvoyage;
    int nbProduitLafo;
    int idclient;
    Date dtachat;

    public int getIdvoyage() {
        return idvoyage;
    }

    public void setIdvoyage(int idvoyage) {
        this.idvoyage = idvoyage;
    }

    public int getNbProduitLafo() {
        return nbProduitLafo;
    }

    public void setNbProduitLafo(int nbProduitLafo) throws Exception {
        if(nbProduitLafo<0){
            throw new Exception("VALEUR NEGATIVE");
        }
        this.nbProduitLafo = nbProduitLafo;
    }

    public int getIdclient() {
        return idclient;
    }

    public void setIdclient(int idclient) {
        this.idclient = idclient;
    }

    public Date getDtachat() {
        return dtachat;
    }

    public void setDtachat(Date dtachat) {
        this.dtachat = dtachat;
    }

    public Vente() {
    }

    public Vente(int idvente,int idvoyage, int nbProduitLafo, int idclient, Date dtachat) throws Exception {
    this.idvente=idvente;    
    this.idvoyage = idvoyage;
//        this.nbProduitLafo = nbProduitLafo;
        setNbProduitLafo(nbProduitLafo);
        this.idclient = idclient;
        this.dtachat = dtachat;
    }
    
    public Vente(int idvoyage, int nbProduitLafo, int idclient, Date dtachat) {
        this.idvoyage = idvoyage;
        this.nbProduitLafo = nbProduitLafo;
        this.idclient = idclient;
        this.dtachat = dtachat;
    
    }
    public Vente(int idvoyage, int nbProduitLafo, int idclient, String dtachat) throws Exception {
        this.idvoyage = idvoyage;
        this.nbProduitLafo = nbProduitLafo;
                setNbProduitLafo(nbProduitLafo);

        this.idclient = idclient;
        this.dtachat= Date.valueOf(dtachat);
    
    }
    
    
     public void Inserer() throws Exception{
        Connexion conn = new Connexion(); 
        Fabrication fabrication = new Fabrication(this.getIdvoyage()+"", this.getNbProduitLafo(), this.getDtachat());
        int check = fabrication.checkDisponibiliteParIdvoyage(conn.GetConnection());
        if(check == 1){
            String requete = "Insert into vente (idvoyage,nbpdtlafo, idclient, dateachat) values ("+this.getIdvoyage()+","+this.getNbProduitLafo()+","+this.getIdclient()+",'"+this.getDtachat()+"')";
            PreparedStatement preparedStatement = null;
            preparedStatement = conn.GetConnection().prepareStatement(requete);
            int lignesAffectees = preparedStatement.executeUpdate();
            if (lignesAffectees > 0) {
                System.out.println("Données insérées avec succès !");
            } else {
                System.out.println("Aucune donnée insérée.");
            }
        }
        
    }
     
    
}
