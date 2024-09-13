package App_2;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author stefa
 */
public class FereastraModificareDate extends JFrame{
    private JTextField tNume, tPrenume, tCnp;
    private JButton bInapoi, bSalveaza, bPoza, imagineButon;
    private JFrame f;
    private String poza;
    private LoggedUserCredentials user = LoggedUserCredentials.getInstance();
    public String[] imaginiProfil = {
			            "default.jpg",
			            "girl.jpg",
			            "man.jpg",
			            "bunny.jpg",
			            "bubbles.jpg"
				};
    public String[] imaginiCertificate = { 
                                        "certificat.jpg", 
                                        "certificatmaths.jpg", 
                                    };
    
    public FereastraModificareDate(){
        super("Modificare date personale");
        
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
        
        panouText.add(new JLabel("Prenume"));
        panouText.add(tPrenume);
        
        panouText.add(new JLabel("CNP"));        
        panouText.add(tCnp);
        tCnp.setText(user.getCnp());
        tCnp.setEditable(false);
        
         add(panouText);
        
        var panouPoza = new JPanel();
        
        if(user.getTipPersoana().equals(false))  //daca user-ul este cursant
            poza = ColectieCursanti.getInstance().getCursant(user.getCnp()).getPoza();
        else if(user.getTipPersoana().equals(true)) //daca user-ul este profesors
            poza = ColectieProfesori.getInstance().getProfesorByCnp(user.getCnp()).getPoza();
        
        imagineButon = new JButton("", new ImageIcon(((new ImageIcon(poza)).getImage()).getScaledInstance(175, 150, Image.SCALE_SMOOTH)));
        imagineButon.setPreferredSize(new Dimension(175, 150));
        panouPoza.setBorder(new EmptyBorder(8, 5, 5, 5));
        panouPoza.add(imagineButon);
        add(panouPoza);
        
        var panouButonModificare = new JPanel();
        var panouButonInapoi = new JPanel();
        
        panouButonModificare.setBorder(new EmptyBorder(5, 5, 5, 5));
        panouButonInapoi.setBorder(new EmptyBorder(5, 5, 5, 5));
        
        bSalveaza = new JButton("Salveaza");
        bSalveaza.setPreferredSize(new Dimension(175, 50));
        bInapoi = new JButton("Inapoi");
        bInapoi.setPreferredSize(new Dimension(175, 50));
        
        panouButonModificare.add(bSalveaza, BorderLayout.SOUTH);
        panouButonInapoi.add(bInapoi, BorderLayout.SOUTH);
  
        add(panouButonModificare);
        add(panouButonInapoi);
        
        bInapoi.addActionListener(e -> {
            this.dispose();
            
            f = new FereastraProfil();
            f.setVisible(true);
        });     
        
        bSalveaza.addActionListener(e -> {
            if(user.getTipPersoana().equals(false)){  //daca user-ul este cursant
                ColectieCursanti.getInstance().getCursant(user.getCnp()).setNume(tNume.getText());
                ColectieCursanti.getInstance().getCursant(user.getCnp()).setPrenume(tPrenume.getText());
                user.setNume(tNume.getText());
                user.setPrenume(tPrenume.getText());
                
                ColectieCursanti.getInstance().salveaza();
            }
            else if(user.getTipPersoana().equals(true)){ //daca user-ul este profesors
                ColectieProfesori.getInstance().getProfesorByCnp(user.getCnp()).setNume(tNume.getText());
                ColectieProfesori.getInstance().getProfesorByCnp(user.getCnp()).setPrenume(tPrenume.getText());
                user.setNume(tNume.getText());
                user.setPrenume(tPrenume.getText());
                
                ColectieProfesori.getInstance().salveaza();
            }
            
            this.dispose();
            
            JOptionPane.showMessageDialog(null, "Profilul a fost actualizat");
            
            f = new FereastraProfil();
            f.setVisible(true);
        });      
        
        imagineButon.addActionListener(e -> {
            
           
           if(user.getTipPersoana().equals(false)){  //daca user-ul este cursant
                String poza = (String)JOptionPane.showInputDialog(null, "Alege o poza:", "Schimba poza", JOptionPane.QUESTION_MESSAGE, null, imaginiProfil, imaginiProfil[0]);
                ColectieCursanti.getInstance().getCursant(user.getCnp()).setPoza(poza);
           }
           else if(user.getTipPersoana().equals(true)){ //daca user-ul este profesors
                String poza = (String)JOptionPane.showInputDialog(null, "Alege un certificat:", "Schimba certificatul", JOptionPane.QUESTION_MESSAGE, null, imaginiCertificate, imaginiCertificate[0]);
                ColectieProfesori.getInstance().getProfesorByCnp(user.getCnp()).setPoza(poza);
           }
        });
        
        setSize(450, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
    }
}
