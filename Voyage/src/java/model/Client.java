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
public class Client {
    int idClient;
    String nom;
    Date dtn;
    int genre;

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDtn() {
        return dtn;
    }

    public void setDtn(Date dtn) {
        this.dtn = dtn;
    }

    public int getGenre() {
        return genre;
    }

    public void setGenre(int genre) {
        this.genre = genre;
    }

    public Client() {
    }

    public Client(int idClient, String nom, Date dtn, int genre) {
        this.idClient = idClient;
        this.nom = nom;
        this.dtn = dtn;
        this.genre = genre;
    }
    
    public Client(String nom, Date dtn, int genre) {
        this.nom = nom;
        this.dtn = dtn;
        this.genre = genre;
    }
    
    public void Inserer() throws Exception{
        Connexion conn = new Connexion();
        String requete = "Insert into client (nom, dtn, genre) values ('"+this.getNom()+"','"+this.getDtn()+"',"+this.getGenre()+")";
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
