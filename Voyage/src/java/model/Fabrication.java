/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author USER
 */
public class Fabrication {
    int idFabrication;
    String idvoyage;
    int nbvoyage;
    Date dateFabrication;

    public int getNbvoyage() {
        return nbvoyage;
    }

    public void setNbvoyage(int nbvoyage) throws Exception{
        if(nbvoyage<=0){
            throw new Exception("Nombre de voyage nulle ou négative");
        }
        this.nbvoyage = nbvoyage;
    }

    public int getIdFabrication() {
        return idFabrication;
    }

    public void setIdFabrication(int idFabrication) {
        this.idFabrication = idFabrication;
    }

    public String getIdvoyage() {
        return idvoyage;
    }

    public void setIdvoyage(String idvoyage) {
        this.idvoyage = idvoyage;
    }

    public Date getDateFabrication() {
        return dateFabrication;
    }

    public void setDateFabrication(Date dateFabrication) {
        this.dateFabrication = dateFabrication;
    }

    public Fabrication() {
    }

    public Fabrication(int idFabrication, String idvoyage, int nbvoyage, Date dateFabrication) throws Exception{
        this.idFabrication = idFabrication;
        this.idvoyage = idvoyage;
        this.setNbvoyage(nbvoyage);
        this.dateFabrication = dateFabrication;
    }
    
    public Fabrication(String idvoyage, int nbvoyage, Date dateFabrication) throws Exception{
        this.idvoyage = idvoyage;
        this.setNbvoyage(nbvoyage);
        this.dateFabrication = dateFabrication;
    }
    
    public void Insert(Connection c) throws Exception{
        int nbAfakaFabriquena=this.checkDisponibilite(c);
        String requete ="Insert into fabrication (idvoyage,datefabrication,nb) values ("+ this.getIdvoyage()+",'"+this.getDateFabrication()+"',"+nbAfakaFabriquena+")";
        PreparedStatement preparedStatement = null;
        preparedStatement = c.prepareStatement(requete);
        int lignesAffectees = preparedStatement.executeUpdate();
        if (lignesAffectees > 0) {
            System.out.println("Données insérées avec succès !");
        } else {
            System.out.println("Aucune donnée insérée.");
        }
        Voyage v=Voyage.GetByIdvoyage(c,this.getIdvoyage());
        BouquetActivite ba=new BouquetActivite().GetByIdBouquet(c,v.getIdBouquet());
        for(int i=0 ; i<nbAfakaFabriquena ; i++){
            for(int j=0 ; j<ba.getActivitels().size() ; j++){
                int nbActiviteIlaina=Collections.frequency(ba.getActivitels(),ba.getActivitels().get(j));
                String idact=ba.getActivitels().get(j).getId();
                SortieStock ins=new SortieStock();
                ins.setIdvoyage(this.getIdvoyage());
                ins.Insert(c,idact,nbActiviteIlaina, this.getDateFabrication());
                if(nbActiviteIlaina>1){
                    j=j+(nbActiviteIlaina-1);
                }
            }
        }
    }
    
    
    
    public int checkDisponibilite(Connection c) throws Exception{
        Voyage v=Voyage.GetByIdvoyage(c,this.getIdvoyage());
        BouquetActivite ba=new BouquetActivite().GetByIdBouquet(c,v.getIdBouquet());
        int nbPossibleInsert=0;
        for(int j=0; j<this.getNbvoyage(); j++){
            for(int i=0 ; i<ba.getActivitels().size() ; i++){
                int nbre=Collections.frequency(ba.getActivitels(),ba.getActivitels().get(i));
                System.out.println(ba.getActivitels().get(i).getId());
                ResteStock.checkDisponibilite(c, ba.getActivitels().get(i).getId(), nbre);
                if(nbre>1){
                    i=i+(nbre-1);
                }
            }
            nbPossibleInsert++;
        }
        return nbPossibleInsert;
    }
}
