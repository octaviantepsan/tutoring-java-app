
package App_2;

/**
 *
 * @author Octavian
 */
public class Persoana {
    private String nume, prenume, cnp, poza, materie;
    private boolean sedintaAnulata;
    
    public Persoana(String nume, String prenume, String cnp, String poza, String materie, Boolean sedintaAnulata){
        this.nume = nume;
        this.prenume = prenume;
        this.cnp = cnp;
        this.poza = poza;
        this.materie = materie;
        this.sedintaAnulata = sedintaAnulata;
    }
    
    public Persoana(String nume, String prenume){
        this.nume = nume;
        this.prenume = prenume;
    }
    
    public String getNume(){
        return this.nume;
    }
    
    public String getPrenume(){
        return this.prenume;
    }
    
    public String getCnp(){
        return this.cnp;
    }
    
    public String getPoza(){
        return this.poza;
    }
    
    public String getMaterie(){
        return this.materie;
    }
    
    public void setMaterie(String materie){
        this.materie = materie;
    }
    
    public void setPoza(String poza){
        this.poza = poza;
    }
    
    public void setNume(String nume){
        this.nume = nume;
    }
    
    public void setPrenume(String prenume){
        this.prenume = prenume;
    }
    
    public void setCnp(String cnp){
        this.cnp = cnp;
    }
    
    public void setSedintaAnulata(Boolean schimbare){
        this.sedintaAnulata = schimbare;
    }
    
    public Boolean getSedintaAnulata(){
        return this.sedintaAnulata;
    }
    
    @Override
    public String toString(){
        var sb = new StringBuffer();
        
        sb.append(nume);
        sb.append("_");
        sb.append(prenume);
        sb.append("_");
        sb.append(cnp);
        sb.append("_");
        sb.append(poza);
        sb.append("_");
        sb.append(materie);
        sb.append("_");
        sb.append(sedintaAnulata);
        
        return sb.toString();
    }
}
