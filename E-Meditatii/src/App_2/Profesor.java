
package App_2;

import java.util.*;

/**
 *
 * @author Octavian
 */ //pentru sistemul de printare, vom folosi creearea unei file txt, in care va fi transmisa recenzia 
public class Profesor extends Persoana {
    private ArrayList<String> materiiPredate = new ArrayList<>();
    private ArrayList<Recenzie> recenzii = new ArrayList<>();
    
    public Profesor(String nume, String prenume, String cnp, String poza, String materie, boolean sedintaAnulata){
        super(nume, prenume, cnp, poza, materie, sedintaAnulata);
    }
    
    public Profesor(String nume, String prenume){   //pentru sedinte, intrucat sedinta nu are nevoie sa aibe si cnp-ul persoanei
        super(nume, prenume);
    }
    
    public void adaugaMateriePredata(String materie){
        this.materiiPredate.add(materie);
    }
    
    public void stergeMateriePredata(String materie){
        this.materiiPredate.remove(materie);
    }
        
    public void adaugaRecenzie(Recenzie recenzie){
        recenzii.add(recenzie);
    }
}