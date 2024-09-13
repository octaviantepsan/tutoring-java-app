
package App_2;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author stefa
 */
public class FereastraProfil extends JFrame{
    private JTextField tNume, tPrenume, tCnp;
    private JButton bInapoi, bModificare;
    private JFrame f;
    private String poza;
    private LoggedUserCredentials user = LoggedUserCredentials.getInstance();
    
    public FereastraProfil(){
        super("Profil utilizator");
        
        setLayout(new GridLayout(2,2,30,30));
        
        var panouText = new JPanel();
        panouText.setLayout(new GridLayout(3,2,5,5));
        panouText.setBorder(new EmptyBorder(10, 5, 5, 5));
        
        tNume = new JTextField(30);
        tNume.setText(user.getNume());
        
        tPrenume = new JTextField(30);
        tPrenume.setText(user.getPrenume());
        
        tCnp = new JTextField(13);
        tCnp.setText(user.getCnp());
        
        panouText.add(new JLabel("Nume"));
        panouText.add(tNume);
        tNume.setEditable(false);
        
        panouText.add(new JLabel("Prenume"));
        panouText.add(tPrenume);
        tPrenume.setEditable(false);
        
        panouText.add(new JLabel("CNP"));        
        panouText.add(tCnp);
        tCnp.setEditable(false);
        
        add(panouText);
        
        var panouPoza = new JPanel();
        
        if(user.getTipPersoana().equals(false))  //daca user-ul este cursant
            poza = ColectieCursanti.getInstance().getCursant(user.getCnp()).getPoza();
        else if(user.getTipPersoana().equals(true)) //daca user-ul este profesors
            poza = ColectieProfesori.getInstance().getProfesorByCnp(user.getCnp()).getPoza();
        
        panouPoza.add(new JLabel(new ImageIcon(((new ImageIcon(poza)).getImage()).getScaledInstance(175, 150, Image.SCALE_SMOOTH))));
        panouPoza.setBorder(new EmptyBorder(8, 5, 5, 5));
        add(panouPoza);
        
        var panouButonModificare = new JPanel();
        var panouButonInapoi = new JPanel();
        
        panouButonModificare.setBorder(new EmptyBorder(5, 5, 5, 5));
        panouButonInapoi.setBorder(new EmptyBorder(5, 5, 5, 5));
        
        bModificare = new JButton("Modificare profil");
        bModificare.setPreferredSize(new Dimension(175, 50));
        bInapoi = new JButton("Inapoi");
        bInapoi.setPreferredSize(new Dimension(175, 50));
        
        panouButonModificare.add(bModificare, BorderLayout.SOUTH);
        panouButonInapoi.add(bInapoi, BorderLayout.SOUTH);
  
        add(panouButonModificare);
        add(panouButonInapoi);
        
        bModificare.addActionListener(e -> {
            this.dispose();
            
            f = new FereastraModificareDate();
            f.setVisible(true);
        });           
        
        bInapoi.addActionListener(e -> {
            this.dispose();
            
            f = new FereastraMeniu();
            f.setVisible(true);
        });
        
        setSize(450, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
