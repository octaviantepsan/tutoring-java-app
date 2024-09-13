
package App_2;

import java.awt.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 *
 * @author stefa
 */
public class FereastraCreareSedinta extends JFrame {
    private JTextField tNumeCursant, tPrenumeCursant, tData, tPret, tMaterie, tLocatie, tNumeProfesor, tPrenumeProfesor;
    private JLabel lNumeCursant, lPrenumeCursant, lData, lPret, lMaterie, lLocatie, lNumeProfesor, lPrenumeProfesor;
    private JButton bSalveaza, bInapoi;
    private LoggedUserCredentials user = LoggedUserCredentials.getInstance();
    private ColectieSedinte colectieSedinte = ColectieSedinte.getInstance();
    private ColectieProfesori colectieProfesori = ColectieProfesori.getInstance();
    private JFrame f;
    SimpleDateFormat formatter = new SimpleDateFormat("HH:mm dd MM yyyy", Locale.ENGLISH);
    
    public FereastraCreareSedinta(){
        super("Creaza cerere sedinta");
        
        var content = new JPanel();
        add(content);
        content.setLayout(new GridLayout(9,2,10,10));
        content.setBorder(new EmptyBorder(6, 6, 6, 6));

        lNumeCursant = new JLabel("Nume Cursant");
        content.add(lNumeCursant);
        tNumeCursant = new JTextField(user.getNume());
        content.add(tNumeCursant);
        tNumeCursant.setEditable(false);
        
        lPrenumeCursant = new JLabel("Prenume Cursant");
        content.add(lPrenumeCursant);
        tPrenumeCursant = new JTextField(user.getPrenume());
        content.add(tPrenumeCursant);
        tPrenumeCursant.setEditable(false);
        
        lData = new JLabel("Data (HH:mm dd MM yyyy)");
        content.add(lData);
        tData = new JTextField();
        content.add(tData);
        
        lMaterie = new JLabel("Materie");
        content.add(lMaterie);
        tMaterie = new JTextField(user.getMaterie());
        content.add(tMaterie);
        tMaterie.setEditable(false);
        
        lLocatie = new JLabel("Locatie");
        content.add(lLocatie);
        tLocatie = new JTextField();
        content.add(tLocatie);
        
        lPret = new JLabel("Pret");
        content.add(lPret);
        tPret = new JTextField();
        content.add(tPret);
        
        lNumeProfesor = new JLabel("Nume Profesor");
        content.add(lNumeProfesor);
        tNumeProfesor = new JTextField();
        content.add(tNumeProfesor);
        
        lPrenumeProfesor = new JLabel("Prenume Profesor");
        content.add(lPrenumeProfesor);
        tPrenumeProfesor = new JTextField();
        content.add(tPrenumeProfesor);
        
        bSalveaza = new JButton("Salveaza");
        content.add(bSalveaza);
        
        bInapoi = new JButton("Inapoi");
        content.add(bInapoi);
        
        bSalveaza.addActionListener(e -> {
            if(!(tNumeCursant.getText().equals("")) && !(tPrenumeCursant.getText().equals("")) && !(tData.getText().equals("")) && !(tMaterie.getText().equals("")) && !(tLocatie.getText().equals("")) && !(tPret.getText().equals("")) && !(tNumeProfesor.getText().equals("")) && !(tPrenumeProfesor.getText().equals("")) && (colectieProfesori.getProfesorByName(tNumeProfesor.getText()) != null) && (colectieProfesori.getProfesorByPrenume(tPrenumeProfesor.getText()) != null)){
                try{
                    if(formatter.parse(tData.getText()).after(new Date())){ 
                        int idSedinta = (int)(Math.random() * 300) + 0;
                        colectieSedinte.adaugaSedinta(idSedinta, tData.getText(), tLocatie.getText(), tPret.getText(), tMaterie.getText(), tNumeCursant.getText(), tPrenumeCursant.getText(), tNumeProfesor.getText(), tPrenumeProfesor.getText());
                        colectieSedinte.salveaza();
                        JOptionPane.showMessageDialog(null, "Sedinta a fost creata cu succes.");
			this.dispose();
                        f = new FereastraSedinte();
                        f.setVisible(true);
                    }
                    else {
	            	tData.setText("");
		        JOptionPane.showMessageDialog(null, "Nu poti crea o sedinta in trecut.");
                    }
	        }catch(ParseException ex) {
                    tData.setText("");
                    JOptionPane.showMessageDialog(null, "Data introdusa nu este corecta.\nTe rog respecta formatul HH:mm dd MM yyyy");
	        }
            }
            else{
                if((colectieProfesori.getProfesorByName(tNumeProfesor.getText()) == null) || (colectieProfesori.getProfesorByPrenume(tPrenumeProfesor.getText()) == null))
                    JOptionPane.showMessageDialog(null, "Profesorul nu exista!");
                else
                    JOptionPane.showMessageDialog(null, "Completati toate campurile!");
            }
        });           
        
        bInapoi.addActionListener(e -> {
            this.dispose();
            
            f = new FereastraSedinte();
            f.setVisible(true);
        });      
        
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
