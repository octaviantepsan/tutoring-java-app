
package App_2;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author stefa
 */
public class FereastraInregistrare extends JFrame{
     private JTextField tNume, tPrenume, tCnp, tMaterie;
     private JButton bInregistrare, bInapoi;
     private JRadioButton[] tipPersoana;
     private JFrame f;
     
     public FereastraInregistrare(){
        super("Creare cont utilizator");
         
        var content = new JPanel();
        add(content);
        content.setLayout(new GridLayout(6,2,10,10));
        content.setBorder(new EmptyBorder(10, 10, 10, 10)); 
        
        tNume = new JTextField(30);
        tPrenume = new JTextField(30);
        tCnp = new JTextField(10);
        tMaterie = new JTextField(10);
        
        bInregistrare = new JButton("Creeaza cont");
        bInapoi = new JButton("Inapoi");
        
        content.add(new JLabel("Nume"));
        content.add(tNume);
        
        content.add(new JLabel("Prenume"));
        content.add(tPrenume);
        
        content.add(new JLabel("CNP"));        
        content.add(tCnp);
        
        content.add(new JLabel("Materie"));
        content.add(tMaterie);
        
        ButtonGroup bg = new ButtonGroup();
        
        tipPersoana = new JRadioButton[2];
        tipPersoana[0] = new JRadioButton("Cursant", false);
        tipPersoana[1] = new JRadioButton("Profesor", false);        
    
        bg.add(tipPersoana[0]);
        bg.add(tipPersoana[1]);
        
        content.add(tipPersoana[0]);
        content.add(tipPersoana[1]);
        
        content.add(bInregistrare);
        content.add(bInapoi);
        
         bInregistrare.addActionListener(e -> {
            if((tipPersoana[0].isSelected()) && !(tNume.getText().equals("")) && !(tPrenume.getText().equals("")) && !(tCnp.getText().equals(""))){
                ColectieCursanti.getInstance().adaugaCursant(tNume.getText(),tPrenume.getText(), tCnp.getText(), tMaterie.getText());
                ColectieCursanti.getInstance().salveaza();
                
                JOptionPane.showMessageDialog(null, "Contul de cursant a fost creat cu succes");
                
                this.dispose();
                f = new FereastraLogin();
                f.setVisible(true);               
            }
            else if((tipPersoana[1].isSelected()) && !(tNume.getText().equals("")) && !(tPrenume.getText().equals("")) && !(tCnp.getText().equals(""))){
                ColectieProfesori.getInstance().adaugaProfesor(tNume.getText(),tPrenume.getText(), tCnp.getText(), tMaterie.getText());
                ColectieProfesori.getInstance().salveaza();
                
                JOptionPane.showMessageDialog(null, "Contul de profesor a fost creat cu succes");
                
                this.dispose();
                f = new FereastraLogin();
                f.setVisible(true);             
            }
            else
                JOptionPane.showMessageDialog(null, "Selecteaza cursant/profesor");
        });
         
        bInapoi.addActionListener(e -> {
            this.dispose();
            f = new FereastraLogin();
            f.setVisible(true);
        });
         
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     }
}
