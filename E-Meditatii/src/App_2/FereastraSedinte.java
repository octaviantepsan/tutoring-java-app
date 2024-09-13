package App_2;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 *
 * @author Octavian
 */
public class FereastraSedinte extends JFrame {  //blocat, probleme cu parsarea datei din fisier sau nu stiu de unde
    private JButton bCreareSedinta, bAnuleaza, bAccepta, bInapoi;
    private JPanel panouSedinte, panouButoane, panouSedinta, panouButoaneSedinta, panouLabels, panouDefaultText;
    private JFrame f;
    private JLabel lCursant, lId, lLocatie, lPret, lProfesor, lData, lStatus, defaultText;
    private ColectieCursanti colectieCursanti;
    private ColectieProfesori colectieProfesori;
    private ColectieSedinte colectieSedinte;
    private LoggedUserCredentials user = LoggedUserCredentials.getInstance();
    private boolean existaSedinta;
    
    public FereastraSedinte(){
        super("Sedinte");
        
        colectieCursanti = ColectieCursanti.getInstance();
        colectieProfesori = ColectieProfesori.getInstance();
        colectieSedinte = ColectieSedinte.getInstance();
        var sedinte = colectieSedinte.getSedinteArray();
        
        panouSedinte =  new JPanel();
        add(panouSedinte);
        
        setSize(525, 400);
        if(user.getTipPersoana().equals(false)){  //daca user-ul este cursant
            for(Sedinta sedinta: sedinte){
                if(sedinta.getCursantNume().equals(user.getNume())){
                    existaSedinta = true;
                    
                    panouSedinta = new JPanel();
                    panouSedinta.setBorder(BorderFactory.createLineBorder(Color.black));
                    panouSedinta.setLayout(new GridLayout(0,2,10,10));
                    panouSedinta.setPreferredSize(new Dimension(400, 110));
                    
                    panouLabels = new JPanel();
                    panouLabels.setLayout(new BoxLayout(panouLabels, BoxLayout.Y_AXIS));
                    
                    lId = new JLabel("Id Sedinta: " + sedinta.getIdSedinta());
                    lProfesor = new JLabel("Nume profesor: " + sedinta.getProfesorNume() + " " + sedinta.getProfesorPrenume());
                    lLocatie = new JLabel("Locatie: " + sedinta.getLocatie());
                    lPret = new JLabel("Pret: " + sedinta.getPret());
                    lData = new JLabel("Data: " + String.format("%s", sedinta.getStringData()));
                    
                    if(sedinta.getStatus() == false)
                        lStatus = new JLabel("Status: Neconfirmat");
                    else
                        lStatus = new JLabel("Status: Confirmat");
                    
                    panouLabels.add(lId);
                    panouLabels.add(lData);
                    panouLabels.add(lProfesor);
                    panouLabels.add(lLocatie);
                    panouLabels.add(lPret);
                    panouLabels.add(lStatus);
                    
                    panouLabels.setBorder(new EmptyBorder(5, 5, 5, 5));
                    
                    panouSedinta.add(panouLabels, BorderLayout.WEST);
                    
                    panouButoaneSedinta = new JPanel();
                    panouButoaneSedinta.setLayout(new GridBagLayout());
                    GridBagConstraints gbc = new GridBagConstraints();
                    gbc.insets = new Insets(5,5,5,5);
                    gbc.ipadx = 5;
                    gbc.ipady = 5;
                    gbc.anchor = GridBagConstraints.CENTER;
                    gbc.fill = GridBagConstraints.HORIZONTAL;
                    
                    
                    bAnuleaza = new JButton("Anulare");
                    
                    panouButoaneSedinta.add(bAnuleaza, gbc);
                    
                    panouSedinta.add(panouButoaneSedinta, BorderLayout.EAST);
                    panouSedinte.add(panouSedinta);
                    
                    bAnuleaza.addActionListener(e -> {
                        colectieSedinte.anuleazaSedinta(sedinta);
                        
                        colectieProfesori.getProfesorByName(sedinta.getProfesorNume()).setSedintaAnulata(true);
                        colectieProfesori.salveaza();
                        
                        this.dispose();
                        f = new FereastraSedinte();
                        f.setVisible(true);
                    });
                }
            }
        }
        else if(user.getTipPersoana().equals(true)){ //daca user-ul este profesors
            for(Sedinta sedinta: sedinte){
                if((sedinta.getProfesorNume().equals(user.getNume())) && (sedinta.getProfesorPrenume().equals(user.getPrenume())) && (sedinta.getStatus() == true)){
                    existaSedinta = true;
                    
                    panouSedinta = new JPanel();
                    panouSedinta.setBorder(BorderFactory.createLineBorder(Color.black));
                    panouSedinta.setLayout(new GridLayout(0,2,10,10));
                    panouSedinta.setPreferredSize(new Dimension(400, 110));
                    
                    panouLabels = new JPanel();
                    panouLabels.setLayout(new BoxLayout(panouLabels, BoxLayout.Y_AXIS));
                    
                    lId = new JLabel("Id Sedinta: " + sedinta.getIdSedinta());
                    lCursant = new JLabel("Nume Cursant: " + sedinta.getCursantNume() + " " + sedinta.getCursantPrenume());
                    lLocatie = new JLabel("Locatie: " + sedinta.getLocatie());
                    lPret = new JLabel("Pret: " + sedinta.getPret());
                    lData = new JLabel("Data: " + String.format("%s", sedinta.getStringData()));
                    
                    if(sedinta.getStatus() == false)
                        lStatus = new JLabel("Status: Neconfirmat");
                    else
                        lStatus = new JLabel("Status: Confirmat");
                    
                    panouLabels.add(lId);
                    panouLabels.add(lData);
                    panouLabels.add(lCursant);
                    panouLabels.add(lLocatie);
                    panouLabels.add(lPret);
                    panouLabels.add(lStatus);
                    
                    panouLabels.setBorder(new EmptyBorder(5, 5, 5, 5));
                    
                    panouSedinta.add(panouLabels, BorderLayout.WEST);
                    
                    panouButoaneSedinta = new JPanel();
                    panouButoaneSedinta.setLayout(new GridBagLayout());
                    GridBagConstraints gbc = new GridBagConstraints();
                    gbc.insets = new Insets(5,5,5,5);
                    gbc.ipadx = 5;
                    gbc.ipady = 5;
                    gbc.anchor = GridBagConstraints.CENTER;
                    gbc.fill = GridBagConstraints.HORIZONTAL;
                    
                    
                    bAnuleaza = new JButton("Anulare");
                    
                    panouButoaneSedinta.add(bAnuleaza, gbc);
                    
                    panouSedinta.add(panouButoaneSedinta, BorderLayout.EAST);
                    panouSedinte.add(panouSedinta);
                    
                    bAnuleaza.addActionListener(e -> {
                        colectieSedinte.anuleazaSedinta(sedinta);
                        
                        colectieCursanti.getCursantByNume(sedinta.getCursantNume()).setSedintaAnulata(true);
                        colectieCursanti.salveaza();
                        
                        this.dispose();
                        f = new FereastraSedinte();
                        f.setVisible(true);
                    });
                }
                else if((sedinta.getProfesorNume().equals(user.getNume())) && (sedinta.getProfesorPrenume().equals(user.getPrenume())) && (sedinta.getStatus() == false)){
                    existaSedinta = true;
                    
                    panouSedinta = new JPanel();
                    panouSedinta.setBorder(BorderFactory.createLineBorder(Color.black));
                    panouSedinta.setLayout(new GridLayout(0,2,10,10));
                    panouSedinta.setPreferredSize(new Dimension(400, 110));
                    
                    panouLabels = new JPanel();
                    panouLabels.setLayout(new BoxLayout(panouLabels, BoxLayout.Y_AXIS));
                    
                    lId = new JLabel("Id Sedinta: " + sedinta.getIdSedinta());
                    lCursant = new JLabel("Nume Cursant: " + sedinta.getCursantNume() + " " + sedinta.getCursantPrenume());
                    lLocatie = new JLabel("Locatie: " + sedinta.getLocatie());
                    lPret = new JLabel("Pret: " + sedinta.getPret());
                    lData = new JLabel("Data: " + String.format("%s", sedinta.getStringData()));
                    
                    if(sedinta.getStatus() == false)
                        lStatus = new JLabel("Status: Neconfirmat");
                    else
                        lStatus = new JLabel("Status: Confirmat");
                    
                    panouLabels.add(lId);
                    panouLabels.add(lData);
                    panouLabels.add(lCursant);
                    panouLabels.add(lLocatie);
                    panouLabels.add(lPret);
                    panouLabels.add(lStatus);
                    
                    panouLabels.setBorder(new EmptyBorder(5, 5, 5, 5));
                    
                    panouSedinta.add(panouLabels, BorderLayout.WEST);
                    
                    panouButoaneSedinta = new JPanel();
                    panouButoaneSedinta.setLayout(new GridBagLayout());
                    GridBagConstraints gbc = new GridBagConstraints();
                    gbc.insets = new Insets(5,5,5,5);
                    gbc.ipadx = 5;
                    gbc.ipady = 5;
                    gbc.anchor = GridBagConstraints.CENTER;
                    gbc.fill = GridBagConstraints.HORIZONTAL;
                    
                    bAnuleaza = new JButton("Respinge");
                    bAccepta = new JButton("Accepta");
                    
                    panouButoaneSedinta.add(bAnuleaza, gbc);
                    panouButoaneSedinta.add(bAccepta, gbc);
                    
                    panouSedinta.add(panouButoaneSedinta, BorderLayout.EAST);
                    panouSedinte.add(panouSedinta);
                    
                    bAnuleaza.addActionListener(e -> {
                        colectieSedinte.anuleazaSedinta(sedinta);
                        
                        colectieCursanti.getCursantByNume(sedinta.getCursantNume()).setSedintaAnulata(true);
                        colectieCursanti.salveaza();
                        
                        this.dispose();
                        f = new FereastraSedinte();
                        f.setVisible(true);
                    });
                    
                    bAccepta.addActionListener(e -> {
                        sedinta.setStatus(true);
                        colectieSedinte.salveaza();
                        
                        JOptionPane.showMessageDialog(null, "Sedinta a fost acceptata");
                        this.dispose();
                        f = new FereastraSedinte();
                        f.setVisible(true);
                    }); 
                }
            }
        }
        
        if(existaSedinta == false){
            defaultText = new JLabel("Nu exista sedinte momentan...");
            panouDefaultText = new JPanel();
            
            defaultText.setFont(new Font("Arial", Font.BOLD, 15));
            add(panouDefaultText, BorderLayout.CENTER);
            panouDefaultText.add(defaultText, BorderLayout.CENTER);
            
            this.setSize(300, 150);
        }
        
        panouButoane = new JPanel();
        add(panouButoane, BorderLayout.SOUTH);
        
        bCreareSedinta = new JButton("Creeaza sedinta");
        bInapoi = new JButton("Inapoi");
        
        if(user.getTipPersoana().equals(false)){   //user este cursant
            panouButoane.add(bCreareSedinta);
        }
        
        panouButoane.add(bInapoi);
        
        bCreareSedinta.addActionListener(e -> {
          {
              this.dispose();
              
               f = new FereastraCreareSedinta();
               f.setVisible(true);
           }
        }); 
        
        bInapoi.addActionListener(e -> {
            this.dispose();
            
            f = new FereastraMeniu();
            f.setVisible(true);
        });   
        
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
