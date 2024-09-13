
package App_2;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author Octavian
 */
public class Sedinta implements Comparable<Sedinta> {
    private String loc;
    private String pret;
    private String materie;
    private int idSedinta;
    private Cursant cursant;
    private Profesor profesor;
    private Date data;
    private Boolean status = false;
    SimpleDateFormat formatter = new SimpleDateFormat("HH:mm dd MM yyyy", Locale.ENGLISH);
    
    public Sedinta(int idSedinta, Boolean status, String data, String loc, String pret, String materie, String cNume, String cPrenume, String pNume, String pPrenume){
        try{
            this.data = formatter.parse(data);
        }catch(Exception e) {
            e.printStackTrace();
        }
        this.status = status;
        this.loc = loc;
        this.pret = pret;
        this.materie = materie;
        this.cursant = new Cursant(cNume, cPrenume);
        this.profesor = new Profesor(pNume, pPrenume);
        this.idSedinta = idSedinta;
    }
    
    public Date getData(){
        return data;
    }
    
    public String getStringData() {
    	return formatter.format(data);
    }
    
    public String getCursantNume(){
        return cursant.getNume();
    }
    
    public String getCursantPrenume(){
        return cursant.getPrenume();
    }
      
    public String getProfesorNume(){
        return profesor.getNume();
    }
      
    public String getProfesorPrenume(){
        return profesor.getPrenume();
    }
    
    public String getLocatie(){
        return loc;
    }
    
    public String getPret(){
        return pret;
    }
    
    public String getMaterie(){
        return materie;
    }
   
    public void setStatus(Boolean status){
        this.status = status;
    }
    
    public Boolean getStatus(){
        return status;
    }
    
    public int getIdSedinta(){
        return idSedinta;
    }
    
    public void setIdSedinta(){
        this.idSedinta = (int)(Math.random() * 300) + 0;
    }
    
    public void setProfesor(String nume, String prenume){   //Cand profesorul accepta sedinta, acesteia i se creeaza si 
        this.profesor = new Profesor(nume, prenume);        //proprietatea 'profesor', intrucat fiecare sedinta are nevoie sa aiba un cursant si un profesor
    }
    
    @Override
    public int compareTo(Sedinta s) {
    	return this.data.compareTo(s.getData());
    }
    
    @Override
    public String toString(){
        var sb = new StringBuffer();
        
        sb.append(idSedinta);
        sb.append("_");
        sb.append(status);
        sb.append("_");
        sb.append(formatter.format(data));
        sb.append("_");
        sb.append(loc);
        sb.append("_");
        sb.append(pret);
        sb.append("_");
        sb.append(materie);
        sb.append("_");
        sb.append(cursant.getNume());
        sb.append("_");
        sb.append(cursant.getPrenume());
        sb.append("_");
        sb.append(profesor.getNume());
        sb.append("_");
        sb.append(profesor.getPrenume());
        
        return sb.toString();
    }
}
