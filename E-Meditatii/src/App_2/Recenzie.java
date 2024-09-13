
package App_2;

/**
 *
 * @author Octavian
 */
public class Recenzie {
    private String descriere, materie, recomandare, nota;
    private String numeProfesor, prenumeProfesor;
    
    public Recenzie(String descriere, String materie, String recomandare, String nota, String numeProfesor, String prenumeProfesor){
        this.descriere = descriere; //short text
        this.materie = materie;
        this.recomandare = recomandare; //cursantul va scrie "Da" sau "Nu". in functie de aceasta, recomandre devine true sau false
        this.nota = nota;   // 1 - 10
        this.numeProfesor = numeProfesor;
        this.prenumeProfesor = prenumeProfesor;
    }
    
    public String getNumeProfesor(){
        return numeProfesor;
    }
    
    public String getPrenumeProfesor(){
        return prenumeProfesor;
    }
    
    public String getDescriere(){
        return descriere;
    }
    
    public String getMaterie(){
        return materie;
    }
        
    public String getRecomandare(){
        return recomandare;
    }
        
    public String getNota(){
        return nota;
    }
        
    public String toString(){
        var sb = new StringBuffer();
        
        sb.append(descriere);
        sb.append("_");
        sb.append(materie);
        sb.append("_");
        sb.append(recomandare);
        sb.append("_");
        sb.append(nota);
        sb.append("_");
        sb.append(numeProfesor);
        sb.append("_");
        sb.append(prenumeProfesor);
        
        return sb.toString();
    }
}
