
package App_2;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author stefa
 */
public class FereastraLogin extends JFrame{
    private JTextField tNume, tPrenume, tCnp;
    private JButton bLogin, bInregistrare;
    private JFrame f;
    private ColectieCursanti colectieCursanti;
    private ColectieProfesori colectieProfesori;
    private LoggedUserCredentials user = LoggedUserCredentials.getInstance();
    
    public FereastraLogin(){
        super("Conectare utilizator");
        
        colectieCursanti = ColectieCursanti.getInstance();
        colectieProfesori = ColectieProfesori.getInstance();
        
        var content = new JPanel();
        add(content);
        content.setLayout(new GridLayout(4,2,20,10));
        content.setBorder(new EmptyBorder(10, 10, 10, 10)); 
        
        tNume = new JTextField(30);
        tPrenume = new JTextField(30);
        tCnp = new JTextField(13);
        
        bLogin = new JButton("Conectare");
        bInregistrare = new JButton("Creeaza cont");
        
        content.add(new JLabel("Nume"));
        content.add(tNume);
        
        content.add(new JLabel("Prenume"));
        content.add(tPrenume);
        
        content.add(new JLabel("CNP"));        
        content.add(tCnp);
        
        content.add(bLogin);
        content.add(bInregistrare);
        
        bLogin.addActionListener(e -> {
            if((colectieCursanti.getCursant(tCnp.getText()) != null) && (colectieCursanti.getCursantByNume(tNume.getText()) != null) && (colectieCursanti.getCursantByPrenume(tPrenume.getText()) != null)){
                JOptionPane.showMessageDialog(null, "Te-ai conectat cu succes");
                
                user.setTipPersoana(false); // false = utilizatorul este cursant
                user.setUser(tNume.getText(), tPrenume.getText(), tCnp.getText());
                user.setMaterie(colectieCursanti.getCursant(tCnp.getText()).getMaterie());
                
                if(colectieCursanti.getCursant(tCnp.getText()).getSedintaAnulata() == true){
                    colectieCursanti.getCursant(tCnp.getText()).setSedintaAnulata(false);
                    colectieCursanti.salveaza();
                    
                    this.dispose();
                
                    JOptionPane.showMessageDialog(null, "O sedinta a fost anulata/respinsa de catre profesor");
                    
                    f = new FereastraMeniu(); 
                    f.setVisible(true);
                }
                else if(colectieCursanti.getCursant(tCnp.getText()).getSedintaAnulata() == false){
                    this.dispose();
                
                    f = new FereastraMeniu(); 
                    f.setVisible(true);
                }
            }
            else if((colectieProfesori.getProfesorByCnp(tCnp.getText()) != null) && (colectieProfesori.getProfesorByName(tNume.getText()) != null) && (colectieProfesori.getProfesorByPrenume(tPrenume.getText()) != null)){
                JOptionPane.showMessageDialog(null, "Te-ai conectat cu succes");
                
                user.setTipPersoana(true); //true = utilizatorul este profesor
                user.setUser(tNume.getText(), tPrenume.getText(), tCnp.getText());
                user.setMaterie(colectieProfesori.getProfesorByCnp(tCnp.getText()).getMaterie());
                
                if(colectieProfesori.getProfesorByCnp(tCnp.getText()).getSedintaAnulata() == true){
                    colectieProfesori.getProfesorByCnp(tCnp.getText()).setSedintaAnulata(false);
                    colectieProfesori.salveaza();
                    
                    this.dispose();
                
                    JOptionPane.showMessageDialog(null, "O sedinta a fost anulata/respinsa de catre cursant");
                    
                    f = new FereastraMeniu(); 
                    f.setVisible(true);
                }
                else if(colectieProfesori.getProfesorByCnp(tCnp.getText()).getSedintaAnulata() == false){
                    this.dispose();
                
                    f = new FereastraMeniu(); 
                    f.setVisible(true);
                }
            }
            else
                JOptionPane.showMessageDialog(null, "Contul nu exista");
        });
        
        bInregistrare.addActionListener(e -> {
            this.dispose();
            f = new FereastraInregistrare();
            f.setVisible(true);
        });      
        
        setSize(300,250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
