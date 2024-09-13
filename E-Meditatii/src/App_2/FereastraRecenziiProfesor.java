
package App_2;

import java.awt.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author stefa
 */
public class FereastraRecenziiProfesor extends JFrame {
    private JButton bInainte, bPrinteaza, bCancel;
    private ColectieRecenzii colectieRecenzii = ColectieRecenzii.getInstance();
    private LoggedUserCredentials user = LoggedUserCredentials.getInstance();
    private JPanel panouRecenzie, recenzieText;
    private JFrame f;
    
    public FereastraRecenziiProfesor(){
        super("Vizualizare recenzii");
        
        var recenzii = colectieRecenzii.getRecenziiArray();

        this.setLayout(new BorderLayout());
        
        JPanel panouRecenzii = new JPanel(new CardLayout());
        
        boolean contor = true;
        
        if(recenzii.isEmpty()){
            contor = false;
        }
        
        int i = 1;
        if(contor != false){
            for(Recenzie recenzie: recenzii) {
                if(user.getNume().equals(recenzie.getNumeProfesor()) && user.getPrenume().equals(recenzie.getPrenumeProfesor())){
                    var panouRecenzie = new JPanel(new BorderLayout());
                    panouRecenzie.setBorder(new EmptyBorder(10, 10, 10, 10)); 

                    JPanel recenzieText = new JPanel();
                    recenzieText.setLayout(new BoxLayout(recenzieText, BoxLayout.Y_AXIS));

                    recenzieText.add(new JLabel("Numarul " + i++));
                    recenzieText.add(new JLabel("Descriere: " + recenzie.getDescriere()));
                    recenzieText.add(new JLabel("Materie: " + (recenzie.getMaterie())));
                    recenzieText.add(new JLabel("Recomandare: " + recenzie.getRecomandare()));
                    recenzieText.add(new JLabel("Nota: " + recenzie.getNota()));

                    panouRecenzie.add(recenzieText);
                    recenzieText = new JPanel();
                    recenzieText.setBorder(new EmptyBorder(10, 10, 10, 10)); 
                    
                    JButton bPrinteaza = new JButton("Printeaza");
                    recenzieText.add(bPrinteaza);
                    bPrinteaza.addActionListener(e -> {
                        try{
                            var pw = new PrintWriter(new FileWriter("print.txt"));
                            pw.println("Recenzie: \n");
                            pw.println("Descriere: " + recenzie.getDescriere());                                
                            pw.println(("Materie: " + (recenzie.getMaterie())));
                            pw.println("Recomandare: " + recenzie.getRecomandare());
                            pw.println("Nota: " + recenzie.getNota() + "\n");
                            pw.close();
                        }catch(IOException ex){
                            ex.printStackTrace();
                            }

                        JOptionPane.showMessageDialog(null, "Recenzia a fost tiparita in fisierul \"print.txt\".");
                    });

                    bInainte = new JButton("Inainte");
                    recenzieText.add(bInainte);

                    bInainte.addActionListener(e -> {
                        ((CardLayout)(panouRecenzii.getLayout())).next(panouRecenzii);
                    });

                    bCancel = new JButton("Cancel");
                    recenzieText.add(bCancel);

                    bCancel.addActionListener(e -> {
                        this.dispose();
                        f = new FereastraMeniu();
                        f.setVisible(true);
                    });

                    panouRecenzie.add(recenzieText, BorderLayout.SOUTH);
                    panouRecenzii.add(panouRecenzie, "Recenzie");
                }
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Nu exista recenzii");
        }
            add(panouRecenzii);
        
        setSize(350, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
