/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author USER
 */
public class Grade {
    String nom;

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
