/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author ROTSY
 */
public class Mpiasa {
    int id;
    String nom;
    Date dtn;
    int idCategorie;
    double salaireHoraire;
    Date dtembauche;
    public Date getDtembauche() {
        return dtembauche;
    }

    public void setDtembauche(Date dtembauche) throws Exception{
        Calendar calendar=Calendar.getInstance();
        java.util.Date currendDateUtil = calendar.getTime();
        Date currentDateSQL=new Date(currendDateUtil.getTime());
        if(dtembauche.after(currentDateSQL)){
            throw new Exception("Date invalide !!");
        }
        this.dtembauche = dtembauche;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public double getSalaireHoraire() {
        return salaireHoraire;
    }

    public void setSalaireHoraire(double salaireHoraire) {
        this.salaireHoraire = salaireHoraire;
    }

    public Mpiasa() {
    }

    public Mpiasa(int id, String nom, Date dtn, int idCategorie, double salaireHoraire,Date dtembauche) throws Exception{
        this.id = id;
        this.nom = nom;
        this.dtn = dtn;
        this.idCategorie = idCategorie;
        this.salaireHoraire = salaireHoraire;
                this.dtembauche = dtembauche;

    }
    public Mpiasa(String nom, Date dtn, int idCategorie, double salaireHoraire,Date dtembauche) throws Exception {
        this.nom = nom;
        this.dtn = dtn;
        this.idCategorie = idCategorie;
        this.salaireHoraire = salaireHoraire;
                this.setDtembauche(dtembauche);

    }
    
    
    
    public void Insert (Connection connexion) throws SQLException{
        String requete = "Insert into mpiasa (nom,dtn,idcategorie,salairehoraire,dtembauche) values ('"+ this.getNom()+"','"+this.getDtn()+"',"+this.getIdCategorie()+","+this.getSalaireHoraire()+",'"+this.getDtembauche()+"')";
        System.out.println(requete);
        PreparedStatement preparedStatement = null;
        preparedStatement = connexion.prepareStatement(requete);
        int lignesAffectees = preparedStatement.executeUpdate();
        if (lignesAffectees > 0) {
            System.out.println("Données insérées avec succès !");
        } else {
            System.out.println("Aucune donnée insérée.");
        }
    }  
    
    public static List<Mpiasa> GetAll() throws Exception{
        Connexion connexion = new Connexion();
        String requete = "Select*from mpiasa";
        PreparedStatement prepareStatement=null;
        prepareStatement=connexion.GetConnection().prepareStatement(requete);
        ResultSet results=prepareStatement.executeQuery();
        List<Mpiasa> ls=new ArrayList<>();
        while(results.next()){
            ls.add(new Mpiasa(results.getInt(1),results.getString(2), results.getDate(3),results.getInt(4),results.getDouble(5),results.getDate(6)));
        }
        prepareStatement.close();
        connexion.GetConnection().close();
        return ls;
    }
    
    public Grade GetGrade() throws Exception{
        Connexion connexion = new Connexion();
        String requete = "Select * from v_checkgrade where id="+this.getId();
        PreparedStatement prepareStatement=null;
        prepareStatement=connexion.GetConnection().prepareStatement(requete);
        ResultSet results=prepareStatement.executeQuery();
        if(results.next()){
            return new Grade(results.getString("grade"),results.getInt("anciennete"), results.getDouble("salairehoraire"));
        }
        prepareStatement.close();
        connexion.GetConnection().close();
        return null;
    }
}
