/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;

/**
 *
 * @author USER
 */
public class SortieStock {
    String idSortieStock;
    int nb;
    String idvoyage;
    Date dateSortieStock;

    public String getIdSortieStock() {
        return idSortieStock;
    }

    public void setIdSortieStock(String idSortieStock) {
        this.idSortieStock = idSortieStock;
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

    public String getIdvoyage() {
        return idvoyage;
    }

    public void setIdvoyage(String idvoyage) {
        this.idvoyage = idvoyage;
    }

    public Date getDateSortieStock() {
        return dateSortieStock;
    }

    public void setDateSortieStock(Date dateSortieStock) {
        this.dateSortieStock = dateSortieStock;
    }

    public SortieStock() {
    }

    public SortieStock(String idSortieStock, int nb, String idvoyage, Date dateSortieStock) throws Exception{
        this.idSortieStock = idSortieStock;
        this.setNb(nb);
        this.idvoyage = idvoyage;
        this.dateSortieStock = dateSortieStock;
    }
    public SortieStock(int nb, String idvoyage, Date dateSortieStock) throws Exception{
        this.setNb(nb);
        this.idvoyage = idvoyage;
        this.dateSortieStock = dateSortieStock;
    }
    
    public void Insert(Connection c) throws Exception{
        List<BouquetActivite> ba=new Voyage().GetAllActivite(c);
//        insertion fabrication
        for(int i=0 ; i<ba.size() ; i++){
            String query="insert into SortieStock (nb,idactivite,dateentreestock) values ("+this.getNb()+","+ba.get(i).getIdactivite()+",'"+this.getDateSortieStock()+"')";
            PreparedStatement preparedStatement = null;
            preparedStatement = c.prepareStatement(query);
            int lignesAffectees = preparedStatement.executeUpdate();
            if (lignesAffectees > 0) {
                System.out.println("Données insérées avec succès !");
            } else {
                System.out.println("Aucune donnée insérée.");
            }
        }
    }
    
    public void checkDisponibilite(Connection c) throws Exception{
        Voyage v=Voyage.GetByIdvoyage(c,this.getIdvoyage());
        List<BouquetActivite> lBA=v.GetAllActivite(c);
        for(int j=0; j<this.getNb(); j++){
            for(int i=0 ; i<lBA.size();i++){
                double nbre = lBA.get(i).getNbactivite();
                ResteStock.checkDisponibilite(c, lBA.get(i).getIdactivite(), nbre);
            }
            this.Insert(c);
            
        }
    }
}
