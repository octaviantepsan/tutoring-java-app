package App_2;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 *
 * @author stefa
 */
public class FereastraMeniu extends JFrame {
    private JButton bProfil, bSedinta, bRecenzie, bDeconectare, bMaterii;
    private JFrame f;
    private JPanel p;
    private ColectieRecenzii colectieRecenzii = ColectieRecenzii.getInstance();
    private LoggedUserCredentials user = LoggedUserCredentials.getInstance();
    private boolean existaRecenzie;
    
    public FereastraMeniu(){
        super("Meniu");
        
        var recenzii = colectieRecenzii.getRecenziiArray();
        
        p = new JPanel();
        p.setLayout(new GridLayout(5, 1, 5, 5));
        p.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(p);
        
        bProfil = new JButton("Profil");
        bProfil.setPreferredSize(new Dimension(100, 10));
        bSedinta = new JButton("Sedinta");
        bSedinta.setPreferredSize(new Dimension(100, 10));
        bRecenzie = new JButton("Recenzie");
        bRecenzie.setPreferredSize(new Dimension(100, 10));
        bDeconectare = new JButton("Deconectare");
        bDeconectare.setPreferredSize(new Dimension(100, 10));
        bMaterii = new JButton("Materii");
        bMaterii.setPreferredSize(new Dimension(100, 10));
        
        p.add(bProfil, BorderLayout.CENTER);
        p.add(bSedinta, BorderLayout.CENTER);
        p.add(bRecenzie, BorderLayout.CENTER);
        p.add(bMaterii, BorderLayout.CENTER);
        p.add(bDeconectare, BorderLayout.CENTER);
        
        bProfil.addActionListener(e -> {
            this.dispose();
            f = new FereastraProfil();
            f.setVisible(true);
        }); 
        
        bSedinta.addActionListener(e -> {
            this.dispose();
            f = new FereastraSedinte();
            f.setVisible(true);
        }); 
        
        bRecenzie.addActionListener(e -> {
            if(user.getTipPersoana().equals(false)){  //daca user-ul este cursant
                this.dispose();
                f = new FereastraCreareRecenzie();
                f.setVisible(true);
            }
            else if(user.getTipPersoana().equals(true)){ //daca user-ul este profesors
                for(Recenzie recenzie: recenzii){
                if(recenzie.getNumeProfesor().equals(user.getNume()))
                    existaRecenzie = true;
                }
            
                if(existaRecenzie == true){
                    this.dispose();
                    f = new FereastraRecenziiProfesor();
                    f.setVisible(true);
                }
                else
                    JOptionPane.showMessageDialog(null, "Nu exista recenzii");
            }
        }); 
        
        bDeconectare.addActionListener(e -> {
            this.dispose();
            f = new FereastraLogin();
            f.setVisible(true);
        });
        
        bMaterii.addActionListener(e -> {
            this.dispose();
            f = new FereastraMaterii();
            f.setVisible(true);
        }); 
        
        setSize(300, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
