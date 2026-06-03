package boundary;

import controller.MagazzinoController;
import entity.Ruolo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SchermataLogin {
    private JPanel mainPanel;
    private JLabel lblNome;
    private JLabel lblCognome;
    private JLabel lblEmail;
    private JTextField txtCognome;
    private JTextField txtEmail;
    private JTextField txtNome;
    private JComboBox<Ruolo> cmbRuolo;
    private JButton btnAccedi;

    private MagazzinoController controller;

    public SchermataLogin() {
        this.controller = new MagazzinoController(this);

        btnAccedi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = txtNome.getText().trim();
                String cognome = txtCognome.getText().trim();
                String email = txtEmail.getText().trim();

                // Validazione base
                if (nome.isEmpty() || cognome.isEmpty() || email.isEmpty()) {
                    messaggioErrore("Tutti i campi sono obbligatori!");
                    return;
                }

                // Chiamata al controller
                controller.richiediAccesso(nome, cognome, email);
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void messaggioConferma(String messaggio) {
        JOptionPane.showMessageDialog(mainPanel, messaggio, "Accesso Consentito", JOptionPane.INFORMATION_MESSAGE);
    }

    public void messaggioErrore(String messaggio) {
        JOptionPane.showMessageDialog(mainPanel, messaggio, "Errore di Accesso", JOptionPane.ERROR_MESSAGE);
    }

    public void chiudiFinestra() {
        JFrame finestraCorrente = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
        if (finestraCorrente != null) {
            finestraCorrente.dispose();
        }
    }
}



