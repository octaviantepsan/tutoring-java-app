package App_2;

import java.awt.*;
import javax.swing.*;
/**
 *
 * @author stefa
 */
public class FereastraMaterii extends JFrame {
    private JButton bInapoi, bSchimbaMaterie;
    private JFrame f;
    private JLabel materiiCurente;
    private JPanel panouMaterie, panouButoane;
    private ColectieCursanti colectieCursanti = ColectieCursanti.getInstance();
    private ColectieProfesori colectieProfesori = ColectieProfesori.getInstance();
    private LoggedUserCredentials user = LoggedUserCredentials.getInstance();
    
    public FereastraMaterii(){
        super("Materii");
        
        setLayout(new GridLayout(2,1,5,20));
        
        panouMaterie = new JPanel();
        add(panouMaterie);
        
        materiiCurente = new JLabel("Materia curenta este: " + user.getMaterie());
        panouMaterie.add(materiiCurente);
       
        panouButoane = new JPanel();
        add(panouButoane);
        
        bInapoi = new JButton("Inapoi");
        bSchimbaMaterie = new JButton("Schimba materie");
        
        panouButoane.add(bSchimbaMaterie);
        panouButoane.add(bInapoi);
        
        
        bInapoi.addActionListener(e -> {
            this.dispose();
            
            f = new FereastraMeniu();
            f.setVisible(true);
        });
        
        bSchimbaMaterie.addActionListener(e -> {
            if(user.getTipPersoana().equals(false)){  //daca user-ul este cursant
                String materie = JOptionPane.showInputDialog("Schimba materia preferata");
                user.setMaterie(materie);
                
                colectieCursanti.getCursant(user.getCnp()).setMaterie(materie);
                colectieCursanti.salveaza();
                
                this.dispose();
                
                JOptionPane.showMessageDialog(null, "Materia preferata a fost schimbata");
                
                f = new FereastraMeniu();
                f.setVisible(true);
            }
            else if(user.getTipPersoana().equals(true)){ //daca user-ul este profesors
                String materie = JOptionPane.showInputDialog("Schimba materia predata");
                user.setMaterie(materie);
                
                colectieProfesori.getProfesorByCnp(user.getCnp()).setMaterie(materie);
                colectieProfesori.salveaza();
                
                this.dispose();
                
                JOptionPane.showMessageDialog(null, "Materia predata a fost schimbata");
                
                f = new FereastraMeniu();
                f.setVisible(true);
                
                
            }
        });  
        
        setSize(250, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
