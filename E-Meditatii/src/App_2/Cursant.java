
package App_2;

/**
 *
 * @author Octavian
 */
public class Cursant extends Persoana {
    
    public Cursant(String nume, String prenume, String cnp, String poza, String materie, boolean sedintaAnulata){
        super(nume, prenume, cnp, poza, materie, sedintaAnulata);
    }
    
    public Cursant(String nume, String prenume){    //pentru sedinte, intrucat sedinta nu are nevoie sa aibe si cnp-ul persoanei
        super(nume, prenume);
    }
}
