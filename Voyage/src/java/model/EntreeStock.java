/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

/**
 *
 * @author USER
 */
public class EntreeStock {
    String idEntreeStock;
    int nb;
    String idactivite;
    Date dateEntreeStock;
    double prix;

    public String getIdEntreeStock() {
        return idEntreeStock;
    }

    public void setIdEntreeStock(String idEntreeStock) {
        this.idEntreeStock = idEntreeStock;
    }

    public int getNb() {
        return nb;
    }

    public void setNb(int nb) throws Exception{
        if(nb>=0){
            
            this.nb = nb;
        }
        else{
            throw new Exception("Nombre de billets négatif");
        }
    }

    public String getIdactivite() {
        return idactivite;
    }

    public void setIdactivite(String idactivite) {
        this.idactivite = idactivite;
    }

    public Date getDateEntreeStock() {
        return dateEntreeStock;
    }

    public void setDateEntreeStock(Date dateEntreeStock) {
        this.dateEntreeStock = dateEntreeStock;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) throws Exception{
        if(prix>=0){
            
            this.prix = prix;
        }
        else{
            throw new Exception("Prix négatif");
        }
    }

    public EntreeStock(String idEntreeStock, int nb, String idactivite, Date dateEntreeStock, double prix) throws Exception{
        this.idEntreeStock = idEntreeStock;
        this.setNb(nb);
        this.idactivite = idactivite;
        this.dateEntreeStock = dateEntreeStock;
        this.setPrix(prix);
    }
    
    public EntreeStock(int nb, String idactivite, Date dateEntreeStock, double prix) throws Exception{
        this.setNb(nb);
        this.idactivite = idactivite;
        this.dateEntreeStock = dateEntreeStock;
        this.setPrix(prix);
    }

    public EntreeStock() {
    }
    
    public void Insert(Connection connexion) throws Exception{
        String query="insert into EntreeStock (nb,idactivite,dateentreestock,prix) values ("+this.getNb()+","+this.getIdactivite()+",'"+this.getDateEntreeStock()+"',"+this.getPrix()+")";
        PreparedStatement preparedStatement = null;
        preparedStatement = connexion.prepareStatement(query);
        int lignesAffectees = preparedStatement.executeUpdate();
        if (lignesAffectees > 0) {
            System.out.println("Données insérées avec succès !");
        } else {
            System.out.println("Aucune donnée insérée.");
        }
    }
    
    
            
}
