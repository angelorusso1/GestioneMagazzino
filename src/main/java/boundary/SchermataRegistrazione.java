package boundary;

import controller.MagazzinoController;

import entity.Ruolo;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SchermataRegistrazione {
    private JTextField txtNome;
    private JTextField txtCognome;
    private JTextField txtEmail;
    private JComboBox<Ruolo> cmbRuolo;
    private JPanel mainPanel;
    private JLabel lblNome;
    private JLabel lblCognome;
    private JLabel lblEmail;
    private JLabel lblRuolo;
    private JButton btnSalva;

    private MagazzinoController controller;

    public SchermataRegistrazione() {
        this.controller = new MagazzinoController(this);

        cmbRuolo.setModel(new DefaultComboBoxModel<>(Ruolo.values()));

        //compilaDati (quando l'utente preme il bottone Salva)
        btnSalva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Raccogliamo i dati inseriti dall'utente
                String nome = txtNome.getText();
                String cognome = txtCognome.getText();
                String email = txtEmail.getText();

                Ruolo ruoloSelected = (Ruolo) cmbRuolo.getSelectedItem();

                //Chiamo il metodo del controller passandogli le informazioni
                controller.richiediRegistrazione(nome, cognome, email, ruoloSelected);
            }
        });
    }

    /**
     *
     * @param messaggio
     */
    public void messaggioConferma(String messaggio) {
        //Mostra il pop-up con l'icona "Informazione"
        JOptionPane.showMessageDialog(mainPanel, messaggio, "Registrazione Completata", JOptionPane.INFORMATION_MESSAGE);

        //Chiude la finestra e torna al menu principale
        JFrame finestraCorrente = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
        if (finestraCorrente != null) {
            finestraCorrente.dispose();
        }
    }

    /**
     *
     * @param messaggio
     */
    public void messaggioErrore(String messaggio) {
        //Mostra il pop-up con l'icona "Errore" (la X rossa)
        JOptionPane.showMessageDialog(mainPanel, messaggio, "Errore di Registrazione", JOptionPane.ERROR_MESSAGE);

        //Svuota i campi per far riprovare l'utente, ma non chiude la finestra
        txtNome.setText("");
        txtCognome.setText("");
        txtEmail.setText("");
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}