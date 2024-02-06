/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USER
 */
public class ResteStock {
    String idactivite;
    double stockactuel;

    public String getIdactivite() {
        return idactivite;
    }

    public void setIdactivite(String idactivite) {
        this.idactivite = idactivite;
    }

    public double getStockactuel() {
        return stockactuel;
    }

    public void setStockactuel(double stockactuel) throws Exception{
        if(stockactuel<0){
            throw new Exception("Stock actuel négatif");
        }
        this.stockactuel = stockactuel;
    }

    public ResteStock() {
    }

    public ResteStock(String idactivite, double stockactuel) throws Exception {
        this.idactivite = idactivite;
        this.setStockactuel(stockactuel);
    }
    
    public static ResteStock GetByIdActivite(Connection c,String idactivite) throws Exception{
        String query="select * from v_resteenstock where idactivite="+idactivite;
        System.out.println(query);
        PreparedStatement prepstat=null;
        prepstat=c.prepareStatement(query);
        ResultSet results= prepstat.executeQuery();
        ResteStock b = null; 
        while(results.next()){
            b=new ResteStock(results.getString(2),results.getDouble(1));
        }
        return b;
    }
    
    public static double checkReste(Connection c , String idactivite) throws Exception{
        ResteStock rs=ResteStock.GetByIdActivite(c, idactivite);
        double reste= rs.getStockactuel();
        return reste;
    }
    
    public static double checkDisponibiliteDouble(double initiale, double nb) throws Exception{
        double reste= initiale-nb; 
        if(reste<0){
            throw new Exception("TSY AMPY NY AO ANATY STOCK");
        }
        return reste;
    }
    
    public static List<ResteStock> GetAllByIdActivite(Connection c , String idActivite) throws Exception{
        String requete;
        if(idActivite.equals("0")){
            requete = "select * from v_resteenstock";
        }
        else{
            requete = "select * from v_resteenstock where idactivite="+idActivite;
        }
        PreparedStatement prepstat=null;
        prepstat=c.prepareStatement(requete);
        ResultSet results=prepstat.executeQuery();
        List<ResteStock> ls=new ArrayList<>();
        while(results.next()){
            ResteStock b = new ResteStock(results.getString(2),results.getDouble(1));
            ls.add(b);
        }
        prepstat.close();
//        connexion.close();
        return ls;
    }
}
    

