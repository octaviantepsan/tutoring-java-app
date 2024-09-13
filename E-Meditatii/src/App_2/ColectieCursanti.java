
package App_2;

import java.io.*;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author Octavian
 */
public class ColectieCursanti {
    private ArrayList<Cursant> cursanti;
    private static ColectieCursanti instanta;
    
    private ColectieCursanti(){
        cursanti = new ArrayList<>();
        File f = new File("conturiCursanti.txt");
        
        if(f.exists()){
            String linie;
            String[] tablou;
            Cursant c;
            
            try{
                var br = new BufferedReader(new FileReader(f));
                
                while(br.ready()){
                    linie = br.readLine();
                    tablou = linie.split("_");
                    c = new Cursant(tablou[0], tablou[1], tablou[2], tablou[3], tablou[4], Boolean.parseBoolean(tablou[5]));
                    cursanti.add(c);
                }
                br.close();
            }catch(IOException err){
                err.printStackTrace();
            }
        }
    }
    
    public void adaugaCursant(String nume, String prenume, String cnp, String materie){
        var c = new Cursant(nume, prenume, cnp, "default.jpg", materie, false);
        cursanti.add(c);
    }
    
    public Cursant getCursant(String cnp){
        for(Cursant cursant: cursanti)
            if(cnp.equals(cursant.getCnp()))
                return cursant;
        return null;
    }
    
    public Cursant getCursantByNume(String nume){
        for(Cursant cursant: cursanti)
            if(nume.equals(cursant.getNume()))
                return cursant;
        return null;
    }
    
    public Cursant getCursantByPrenume(String prenume){
        for(Cursant cursant: cursanti)
            if(prenume.equals(cursant.getPrenume()))
                return cursant;
        return null;
    }
    
    public void stergeCursant(String cnp){
        for(Cursant cursant: cursanti)
            if(cnp.equals(cursant.getCnp()))
                cursanti.remove(cursant);
    }
    
    public void salveaza(){
        try{
            var bw = new PrintWriter(new FileWriter("conturiCursanti.txt"));
            
            for(Cursant cursant: cursanti){
                bw.println(cursant.toString());
            }
            
            bw.close();
        }catch(IOException err){
            err.printStackTrace();
        }
    }
    
    public static ColectieCursanti getInstance(){
        if(instanta == null)
            instanta = new ColectieCursanti();
        
        return instanta;
    }
}
