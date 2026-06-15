package boundary;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SchermataIniziale {
    private JPanel mainPanel;
    private JButton btnRegistrati;
    private JButton btnAccedi;

    public SchermataIniziale() {

        //Listener che gestisce il click sul pulsante "Registrati"
        btnRegistrati.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frameReg = new JFrame("Registrazione Nuovo Utente");
                SchermataRegistrazione schermataReg = new SchermataRegistrazione();

                frameReg.setContentPane(schermataReg.getMainPanel());
                frameReg.setSize(450, 350);
                frameReg.setLocationRelativeTo(null);
                frameReg.setVisible(true);
                // La SchermataIniziale resta aperta sotto, pronta a riprendere il controllo quando frameReg farà dispose()
            }
        });

        //Listener che gestisce il click sul pulsante "Accedi"
        btnAccedi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frameLogin = new JFrame("Accesso al Sistema");
                SchermataLogin schermataLogin = new SchermataLogin();

                frameLogin.setContentPane(schermataLogin.getMainPanel());
                frameLogin.setSize(400, 250);
                frameLogin.setLocationRelativeTo(null);
                frameLogin.setVisible(true);
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}


