package Utils;

import java.io.Serializable;

public class Car implements Serializable  {
    private String Module;
    private String Matricule;
    private double prix;
    private String action ;
    public Car(String module, String matricule, double prix) {
        Module = module;
        Matricule = matricule;
        this.prix = prix;
    }
    public Car() {
      
    }
    public String getModule() {
        return Module;
    }
    public String getMatricule() {
        return Matricule;
    }
    public double getPrix() {
        return prix;
    }
    public String getAction() {
        return action;
    }
    public void setModule(String module) {
        Module = module;
    }
    public void setMatricule(String matricule) {
        Matricule = matricule;
    }
    public void setPrix(double prix) {
        this.prix = prix;
    }
    public void setAction(String action) {
        this.action = action;
    }  
}
