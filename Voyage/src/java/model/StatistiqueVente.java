package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StatistiqueVente {
    Voyage voyage;
    int[] nbproduit;
    int[] genre;
    
    
    
    public StatistiqueVente GetAll(int idvoyage) throws Exception{
        String requete = "select*from v_statistique";
        if(idvoyage!=0){
            requete = "select*from v_statistiquevente where idvoyage = "+idvoyage;
        }
        Connexion connexion = new Connexion();
        PreparedStatement prepareStatement=null;
        prepareStatement=connexion.GetConnection().prepareStatement(requete);
        ResultSet results=prepareStatement.executeQuery();
        List<StatistiqueVente> ls=new ArrayList<>();
        if(results.next()){
            int[] genre = new int[2];
            int[] nbpdt = new int[2];
            Voyage voyage= null;    
            if(idvoyage != 0){
                voyage = Voyage.GetByIdvoyage(connexion.GetConnection(), results.getString("idvoyage"));
            }
            genre[0] = results.getInt("genre_masculin");
            genre[1] = results.getInt("genre_feminin");
            nbpdt [0] = results.getInt("nb_produits_masculin");
            nbpdt [1] = results.getInt("nb_produits_feminin");
            System.out.println(requete+"   "+nbpdt[0]+" ------------------------"+nbpdt[1]);
           
            return new StatistiqueVente(voyage, nbpdt, genre);

        }
        prepareStatement.close();
        connexion.GetConnection().close();
        return null;
    }
    
    public StatistiqueVente(Voyage voyage, int[] nbproduit, int[] genre) {
        this.voyage = voyage;
        this.nbproduit = nbproduit;
        this.genre = genre;
    }

    public Voyage getVoyage() {
        return voyage;
    }

    public void setVoyage(Voyage voyage) {
        this.voyage = voyage;
    }

    public int[] getNbproduit() {
        return nbproduit;
    }

    public void setNbproduit(int[] nbproduit) {
        this.nbproduit = nbproduit;
    }

    public int[] getGenre() {
        return genre;
    }

    public void setGenre(int[] genre) {
        this.genre = genre;
    }

    public StatistiqueVente() {
    }
    
    
}
