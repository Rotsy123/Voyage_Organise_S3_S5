/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.PreparedStatement;

/**
 *
 * @author USER
 */
public class FabricationVoyage {
    String idvoyage;
    String idmpiasa;
    double horaire;

    
    public void Inserer()throws Exception{
        Connexion connexion = new Connexion();
        String requete = "insert into fabricationvoyage (idvoyage, idmpiasa, horaire) values ("+this.getIdvoyage()+","+this.getIdmpiasa()+","+this.getHoraire()+")";
        PreparedStatement preparedStatement = null;
        preparedStatement = connexion.GetConnection().prepareStatement(requete);
        int lignesAffectees = preparedStatement.executeUpdate();
        if (lignesAffectees > 0) {
            System.out.println("Données insérées avec succès !");
        } else {
            System.out.println("Aucune donnée insérée.");
        }
    }
    public FabricationVoyage(String idvoyage, String idmpiasa, double horaire) {
        this.idvoyage = idvoyage;
        this.idmpiasa = idmpiasa;
        this.horaire = horaire;
    }

    public String getIdvoyage() {
        return idvoyage;
    }

    public void setIdvoyage(String idvoyage) {
        this.idvoyage = idvoyage;
    }

    public String getIdmpiasa() {
        return idmpiasa;
    }

    public void setIdmpiasa(String idmpiasa) {
        this.idmpiasa = idmpiasa;
    }

    public double getHoraire() {
        return horaire;
    }

    public void setHoraire(double horaire) {
        this.horaire = horaire;
    }

    public FabricationVoyage() {
    }
    
    
    
    
}
