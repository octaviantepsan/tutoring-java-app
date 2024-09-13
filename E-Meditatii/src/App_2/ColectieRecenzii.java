
package App_2;

import java.io.*;
import java.util.*;

/**
 *
 * @author stefa
 */
public class ColectieRecenzii {
    private ArrayList<Recenzie> recenzii;
    private static ColectieRecenzii instanta;
    
    private ColectieRecenzii(){
        recenzii = new ArrayList<>();
        File f = new File("recenzii.txt");
        
        if(f.exists()){
            String linie;
            String[] tablou;
            Recenzie r;
                        
            try{
                var br = new BufferedReader(new FileReader(f));
                
                while(br.ready()){
                    linie = br.readLine();
                    tablou = linie.split("_");
                    r = new Recenzie(tablou[0], tablou[1], tablou[2], tablou[3], tablou[4], tablou[5]);
                    recenzii.add(r);
                }
                br.close();
            }catch(IOException err){
                err.printStackTrace();
            }
        }
    }
    
        public Recenzie getRecenzie(String descriere, String materie, String recomandare, String nota, String numeProfesor, String prenumeProfesor){
            for(Recenzie recenzie: recenzii)
                return recenzie;
        return null;
    }
        
        public void adaugaRecenzie(String descriere, String materie, String recomandare, String nota, String numeProfesor, String prenumeProfesor){
        var r = new Recenzie(descriere, materie, recomandare, nota, numeProfesor, prenumeProfesor);
        recenzii.add(r);
    }
        
        public void salveaza(){
        try{
            var bw = new PrintWriter(new FileWriter("recenzii.txt"));
            
            for(Recenzie recenzie: recenzii){
                bw.println(recenzie.toString());
            }
            
            bw.close();
        }catch(IOException err){
            err.printStackTrace();
        }
    }
        
        public ArrayList<Recenzie> getRecenziiArray(){
            return recenzii;
        }
        
        public static ColectieRecenzii getInstance(){
        if(instanta == null)
            instanta = new ColectieRecenzii();
        
        return instanta;
    }
}
