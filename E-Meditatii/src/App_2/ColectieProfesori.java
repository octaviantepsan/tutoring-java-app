
package App_2;

import java.io.*;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author Octavian
 */
public class ColectieProfesori {
    private ArrayList<Profesor> profesori;
    private static ColectieProfesori instanta;
    
    private ColectieProfesori(){
        profesori = new ArrayList<>();
        File f = new File("conturiProfesori.txt");
        
        if(f.exists()){
            String linie;
            String[] tablou;
            Profesor p;
            
            try{
                var br = new BufferedReader(new FileReader(f));
                
                while(br.ready()){
                    linie = br.readLine();
                    tablou = linie.split("_");
                    p = new Profesor(tablou[0], tablou[1], tablou[2], tablou[3], tablou[4], Boolean.parseBoolean(tablou[5]));
                    profesori.add(p);
                }
                br.close();
            }catch(IOException err){
                err.printStackTrace();
            }
        }
    }
    
    public void adaugaProfesor(String nume, String prenume, String cnp, String materie){
        var p = new Profesor(nume, prenume, cnp, "default.jpg", materie, false);
        profesori.add(p);
    }
    
    public Profesor getProfesorByCnp(String cnp){
        for(Profesor profesor: profesori)
            if(cnp.equals(profesor.getCnp()))
                return profesor;
        return null;
    }
    
    public Profesor getProfesorByName(String nume){ //pentru recenzie intrucar cursantul nu poate stii cnp-ul profesorului
        for(Profesor profesor: profesori)           //dar avem nevoie de obiectul Profesor pt a-i adauga recenzia
            if(nume.equals(profesor.getNume()))
                return profesor;
        return null;
    }
    
    public Profesor getProfesorByPrenume(String prenume){ //pentru recenzie intrucar cursantul nu poate stii cnp-ul profesorului
        for(Profesor profesor: profesori)           //dar avem nevoie de obiectul Profesor pt a-i adauga recenzia
            if(prenume.equals(profesor.getPrenume()))
                return profesor;
        return null;
    }
    
    public void stergeProfesor(String cnp){
        for(Profesor profesor: profesori)
            if(cnp.equals(profesor.getCnp()))
                profesori.remove(profesor);
    }
    
    public void salveaza(){
        try{
            var bw = new PrintWriter(new FileWriter("conturiProfesori.txt"));
            
            for(Profesor profesor: profesori){
                bw.println(profesor.toString());
            }
            
            bw.close();
        }catch(IOException err){
            err.printStackTrace();
        }
    }
    
    public static ColectieProfesori getInstance(){
        if(instanta == null)
            instanta = new ColectieProfesori();
        
        return instanta;
    }
}
