
package App_2;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author stefa
 */
public class FereastraCreareRecenzie extends JFrame {
    private JButton bInapoi, bTrimiteRec;
    private ColectieRecenzii colectieRecenzii = ColectieRecenzii.getInstance();
    private ColectieProfesori colectieProfesori = ColectieProfesori.getInstance();
    private JTextField tDescriere, tMaterie, tRecomandare, tNota, tNumeProfesor, tPrenumeProfesor;
    private JLabel lDescriere, lMaterie, lRecomandare, lNota, lNumeProfesor, lPrenumeProfesor;
    private JFrame f;
    
    public FereastraCreareRecenzie(){
        super("Creeaza o recenzie");
        
        var content = new JPanel();
        add(content);
        content.setLayout(new GridLayout(7,2,20,10));
        content.setBorder(new EmptyBorder(6, 6, 6, 6));
        
        var recenzii = colectieRecenzii.getRecenziiArray();
        
        tDescriere = new JTextField(100);
        tMaterie = new JTextField(30);
        tRecomandare = new JTextField(2);
        tNota = new JTextField(2);
        tNumeProfesor = new JTextField(100);
        tPrenumeProfesor = new JTextField(100);
        
        lDescriere = new JLabel("Descriere");
        lMaterie = new JLabel("Materie");
        lRecomandare = new JLabel("Recomanzi? (Da/Nu) ");
        lNota = new JLabel("Nota");
        lNumeProfesor = new JLabel("Nume Profesor");
        lPrenumeProfesor = new JLabel("Prenume Profesor");
        
        content.add(lDescriere);
        content.add(tDescriere);
        
        content.add(lMaterie);
        content.add(tMaterie);
        
        content.add(lRecomandare);
        content.add(tRecomandare);
        
        content.add(lNota);
        content.add(tNota);
        
        content.add(lNumeProfesor);
        content.add(tNumeProfesor);
        
        content.add(lPrenumeProfesor);
        content.add(tPrenumeProfesor);
        
        bTrimiteRec = new JButton("Trimite recenzia");
        bInapoi = new JButton("Inapoi");
        
        content.add(bTrimiteRec);
        content.add(bInapoi);
        
        bInapoi.addActionListener(e -> {
            this.dispose();
            
            f = new FereastraMeniu();
            f.setVisible(true);
        });
        
        bTrimiteRec.addActionListener(e -> {
           if(!(tDescriere.getText().equals("")) && !(tMaterie.getText().equals("")) && !(tRecomandare.getText().equals("")) && !(tNota.getText().equals("")) && !(tNumeProfesor.getText().equals("")) && !(tPrenumeProfesor.getText().equals("")) && (colectieProfesori.getProfesorByName(tNumeProfesor.getText()) != null) && (colectieProfesori.getProfesorByPrenume(tPrenumeProfesor.getText()) != null)){
                colectieRecenzii.adaugaRecenzie(tDescriere.getText(), tMaterie.getText(), tRecomandare.getText(), tNota.getText(), tNumeProfesor.getText(), tPrenumeProfesor.getText());
                colectieRecenzii.salveaza();
                
                JOptionPane.showMessageDialog(null, "Recenzia a fost creata si trimisa cu succes!");
                
                this.dispose();
                
                f = new FereastraMeniu();
                f.setVisible(true);   
            }
           else{
               if((colectieProfesori.getProfesorByName(tNumeProfesor.getText()) == null) || (colectieProfesori.getProfesorByPrenume(tPrenumeProfesor.getText()) == null))
                    JOptionPane.showMessageDialog(null, "Profesorul nu exista!");
                else
                    JOptionPane.showMessageDialog(null, "Completati toate campurile!");
           }
        });
        
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
