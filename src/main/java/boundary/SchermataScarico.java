package boundary;

import controller.MagazzinoController;
import javax.swing.*;

public class SchermataScarico extends JPanel {
    private JPanel mainPanel;
    private JButton btnConferma;
    private JLabel lblId;
    private JLabel lblQuantita;
    private JTextField txtQuantita;
    private JTextField txtID;

    private MagazzinoController controller;

    public SchermataScarico(MagazzinoController controller) {
        this.controller = controller;

        btnConferma.addActionListener(e -> {
            String codice = txtID.getText();
            String quantitaString = txtQuantita.getText();

            try {
                int quantita = Integer.parseInt(quantitaString);
                controller.richiediScarico(codice, quantita, this);
                txtID.setText("");
                txtQuantita.setText("");

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