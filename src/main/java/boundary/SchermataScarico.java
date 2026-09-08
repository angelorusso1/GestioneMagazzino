package boundary;

import controller.MagazzinoController;

import javax.swing.*;

public class SchermataScarico extends JPanel {
    private JPanel mainPanel;
    private JButton btnConfermaScarico;
    private JLabel lblCodice;
    private JLabel lblNome;
    private JTextField txtNome;
    private JTextField txtCodice;
    private JTextField txtQuantitaDaScaricare;
    private JLabel lblQuantitaDaScaricare;


    private MagazzinoController controller;

    public SchermataScarico(MagazzinoController controller,String codiceProdotto,String nomeProdotto) {
        this.controller = controller;

        txtCodice.setText(codiceProdotto);
        txtCodice.setEditable(false); // Blocca la modifica

        txtNome.setText(nomeProdotto);
        txtNome.setEditable(false);

        btnConfermaScarico.addActionListener(e -> {
            String codice = txtCodice.getText();
            String quantitaString = txtQuantitaDaScaricare.getText().trim();

            try {
                int quantita = Integer.parseInt(quantitaString);

                //controllo di sicurezza sull'inserimento del valore da scaricare
                if (quantita <= 0) {
                    messaggioErrore("La quantità da scaricare deve essere maggiore di zero!");
                    return; // Blocca l'operazione
                }

                controller.richiediScarico(codice, quantita, this);

                txtQuantitaDaScaricare.setText("");

                //chiude la finestra
                ((JFrame) SwingUtilities.getWindowAncestor(mainPanel)).dispose();

            } catch (NumberFormatException ex) {
                messaggioErrore("Inserisci un numero valido nel campo Quantità!");
            }
        });
    }

    public void messaggioErrore(String messaggio) {
        JOptionPane.showMessageDialog(this, messaggio, "Errore", JOptionPane.ERROR_MESSAGE);
    }

    public void messaggioConferma(String messaggio) {
        JOptionPane.showMessageDialog(this, messaggio, "Successo", JOptionPane.INFORMATION_MESSAGE);
    }


    public JPanel getMainPanel() {
        return mainPanel;
    }
}