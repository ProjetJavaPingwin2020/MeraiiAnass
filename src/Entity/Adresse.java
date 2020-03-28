/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.ArrayList;
import java.util.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author med
 */
public class Adresse {
    private String gov;
    private String ville;
    private String rue;

    public Adresse() {
    }

    public Adresse(String gov, String ville, String rue) {
        this.gov = gov;
        this.ville = ville;
        this.rue = rue;
    }

    public String getGov() {
        return gov;
    }

    public void setGov(String gov) {
        this.gov = gov;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    @Override
    public String toString() {
        return  "Governorat de "+gov + "," + ville + "," + rue;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); //To change body of generated methods, choose Tools | Templates.
    }
    
    public String getGovBYid(int id){
        String gov="";
        switch (id){
            case 1:  gov = "Ariana";
                     break;
            case 2:  gov = "Beja";
                     break;
            case 3:  gov = "Ben Arous";
                     break;
            case 4:  gov = "Bizert";
                     break;
            case 5:  gov = "Kairouan";
                     break;
            case 6:  gov = "Kef";
                     break;
            case 7:  gov = "Mehdia";
                     break;
            case 8:  gov = "Monastir";
                     break;
            case 9:  gov = "Nabeul";
                     break;
            case 10: gov = "Sfax";
                     break;
            case 11: gov = "Tozeur";
                     break;
            case 12: gov = "Tunis";
                     break;
            default: gov = "Invalid address";
                     break;
            
        }
      return gov;
    }
    public ObservableList<String> getVilleFroGov(int id){
        //ArrayList<String> gov = new ArrayList<>();
        ObservableList<String> gov=FXCollections.observableArrayList();
        

    
        switch (id){
            case 1:  gov.add("Ariana Ville");gov.add("chotrana");
                     break;
            case 2:  gov.add("el ksar");gov.add("beja");gov.add("hsdf");
                     break;
            case 3:  gov.add("boumhel");gov.add("elmourouj");
                     break;
            case 4:  gov.add("bizert");gov.add("mateur");
                     break;
            case 5:  gov.add("kairouan");gov.add("chbika");
                     break;
            case 6:  gov.add("kef");gov.add("el ksour");
                     break;
            case 7:  gov.add("mehdia");gov.add("chebba");
                     break;
            case 8:  gov.add("monastir");gov.add("bembla");
                     break;
            case 9:  gov.add("nabeul");gov.add("kelibia");
                     break;
            case 10: gov.add("sfax");gov.add("jebiniana");
                     break;
            case 11: gov.add("touzeur");gov.add("dagech");
                     break;
            case 12: gov.add("tunis");gov.add("charthage");
                     break;
            default: gov.add("Invalid");
                     break;
    }
        return gov;
    
    
}
}