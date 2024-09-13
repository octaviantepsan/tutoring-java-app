
package App_2;

/**
 *
 * @author Octavian
 */
public class LoggedUserCredentials {
    private static LoggedUserCredentials instanta;
    private String nume, prenume, cnp, poza, materie;  //ne vom folosi de cnp-ul de aici pentru a adauga, sau scoate materii preferate/predate ale persoanei, folosind 
    private Boolean tipPersoana;   //ColectieProfesori(Cursanti).getInstance().getProfesor(Cursant) si obtinem obiectul user-ului logat, iar apoi modificam valorile cu metodele 'set'.
    
    public void setUser(String nume, String prenume, String cnp){
        this.nume = nume;
        this.prenume = prenume;
        this.cnp = cnp;
    }

    public void setTipPersoana(Boolean persoana){
        this.tipPersoana = persoana;  //va fi false pt cursant si true pt profesor
    }
    
    public void setMaterie(String materie){
        this.materie = materie;
    }
    
    public Boolean getTipPersoana(){
        return tipPersoana;
    }
    
    public String getMaterie(){
        return materie;
    }
    
    public String getCnp(){
        return cnp;
    }
    
    public String getNume(){
        return nume;
    }
    
    public String getPrenume(){
        return prenume;
    }
    
    public void setNume(String nume){
        this.nume = nume;
    }
    
    public void setPrenume(String prenume){
        this.prenume = prenume;
    }
        
    public static LoggedUserCredentials getInstance(){
        if(instanta == null)
            instanta = new LoggedUserCredentials();
        
        return instanta;
    }
    

}
