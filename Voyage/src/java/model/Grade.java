/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USER
 */
public class Grade {
    int id;

    public Grade(int id, String nom, int min, int max, int salaire) {
        this.id = id;
        this.nom = nom;
        this.min = min;
        this.max = max;
        this.salaire = salaire;
    }
    public Grade(String nom, int min, int max, int salaire) {
        this.nom = nom;
        this.min = min;
        this.max = max;
        this.salaire = salaire;
    }
    
      public void Update() throws Exception{
        Connexion conn = new Connexion();
        String requete = "update grade set nom='"+this.getNom()+"', grade = "+this.getSalaire()+", anciennetemin = "+this.getMin()+", anciennetemax = "+this.getMax()+" where idgrade ="+this.getId();
        System.out.println(requete);
        PreparedStatement preparedStatement = null;
        preparedStatement = conn.GetConnection().prepareStatement(requete);
        int lignesAffectees = preparedStatement.executeUpdate();
        if (lignesAffectees > 0) {
            System.out.println("Données insérées avec succès !");
        } else {
            System.out.println("Aucune donnée insérée.");
        }
    }
    
    public void Inserer() throws Exception{
        Connexion conn = new Connexion();
        String requete = "Insert into grade (nom, grade, anciennetemin, anciennetemax) values ('"+this.getNom()+"', "+this.getSalaire()+","+this.getMin()+","+this.getMax()+")";
        PreparedStatement preparedStatement = null;
        preparedStatement = conn.GetConnection().prepareStatement(requete);
        int lignesAffectees = preparedStatement.executeUpdate();
        if (lignesAffectees > 0) {
            System.out.println("Données insérées avec succès !");
        } else {
            System.out.println("Aucune donnée insérée.");
        }
    }
    
    public static List<Grade> GetAll() throws Exception{
        Connexion connexion = new Connexion();
        String requete = "Select * from grade";
        PreparedStatement prepareStatement=null;
        prepareStatement=connexion.GetConnection().prepareStatement(requete);
        ResultSet results=prepareStatement.executeQuery();
        List<Grade> ls=new ArrayList<>();
        while(results.next()){
            ls.add(new Grade(results.getInt("idgrade"), results.getString("nom"), results.getInt("anciennetemin"), results.getInt("anciennetemax"), results.getInt("grade")));
        }
        prepareStatement.close();
        connexion.GetConnection().close();
        return ls;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    String nom;
    int min;
    int max;
    int salaire;

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getSalaire() {
        return salaire;
    }

    public void setSalaire(int salaire) {
        this.salaire = salaire;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getAnciennete() {
        return anciennete;
    }

    public void setAnciennete(int anciennete) {
        this.anciennete = anciennete;
    }

    public double getKaram() {
        return karam;
    }

    public void setKaram(double karam) throws Exception{
        if(karam<=0){
            throw new Exception("Salaire négatif ou égal a 0");
        }
        this.karam = karam;
    }
    int anciennete;
    double karam;

    public Grade() {
    }

    public Grade(String nom, int anciennete, double karam) throws Exception{
        this.nom = nom;
        this.anciennete = anciennete;
        this.karam = karam;
    }
    
    
}
