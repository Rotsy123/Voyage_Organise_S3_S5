/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
        
        PreparedStatement prepstat=null;
        prepstat=c.prepareStatement(query);
        ResultSet results= prepstat.executeQuery();
        ResteStock b = null; 
        while(results.next()){
            b=new ResteStock(results.getString("2"),results.getDouble("1"));
        }
        return b;
    }
    
    public static void checkDisponibilite(Connection c , String idactivite,double nb) throws Exception{
        ResteStock rs=ResteStock.GetByIdActivite(c, idactivite);
        double reste=rs.getStockactuel()-nb;
        if(reste<0){
            throw new Exception("TSY AMPY NY AO ANATY STOCK");
        }
    }
}
    

