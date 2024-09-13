
package App_2;

import java.io.*;
import java.text.*;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author Octavian
 */
public class ColectieSedinte {
    private ArrayList<Sedinta> sedinte;
    private static ColectieSedinte instanta;
    SimpleDateFormat formatter = new SimpleDateFormat("HH:mm dd MM yyyy", Locale.ENGLISH);
    
    private ColectieSedinte(){
        sedinte = new ArrayList<>();
        File f = new File("sedinte.txt");
        
        if(f.exists()){
            String linie;
            String[] tablou;
            Sedinta s;
            
            try{
                var br = new BufferedReader(new FileReader(f));
                
                while(br.ready()){
                    linie = br.readLine();
                    tablou = linie.split("_");
                    s = new Sedinta(Integer.parseInt(tablou[0]), Boolean.parseBoolean(tablou[1]), tablou[2], tablou[3], tablou[4], tablou[5], tablou[6], tablou[7], tablou[8], tablou[9]);
                    sedinte.add(s);
                }
                br.close();
            }catch(IOException err){
                err.printStackTrace();
            }
        }
    }
    
    public ArrayList<Sedinta> getSedinteArray(){
        return sedinte;
    }
    
    public void adaugaSedinta(int idSedinta, String data, String loc, String pret, String materie, String cNume, String cPrenume, String pNume, String pPrenume){
        var s = new Sedinta(idSedinta, false, data, loc, pret, materie, cNume, cPrenume, pNume, pPrenume); 
        sedinte.add(s);
        salveaza();
    }
    
    
    public void anuleazaSedinta(Sedinta s){    //se va face din interfata, cautand id-ul prin colectieSedinte, intrucat cursantul cat si profesorul isi vor putea vizualiza sedintele tot din interfata
            if(sedinte.contains(s)){
                sedinte.remove(s);
                
                JOptionPane.showMessageDialog(null, "Sedinta a fost anulata");
            }
        salveaza();
    }
    
    public void salveaza(){
        try{
            var bw = new PrintWriter(new FileWriter("sedinte.txt"));
            
            for(Sedinta sedinta: sedinte){
                bw.println(sedinta.toString());
            }
            bw.close();
        }catch(IOException err){
            err.printStackTrace();
        }
    }
    
    public static ColectieSedinte getInstance(){
        if(instanta == null)
            instanta = new ColectieSedinte();
        
        return instanta;
    }
}
