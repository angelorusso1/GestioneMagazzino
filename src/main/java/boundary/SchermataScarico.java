package boundary;

import controller.MagazzinoController;
import javax.swing.*;

public class SchermataScarico extends JPanel {
    private JTextField IDTextField;
    private JPanel panel1;
    private JTextField quantitàTextField;
    private JButton eseguiScaricoButton;

    private MagazzinoController controller;

    public SchermataScarico(MagazzinoController controller) {
        this.controller = controller;

        eseguiScaricoButton.addActionListener(e -> {
            String codice = IDTextField.getText();
            String quantitaString = quantitàTextField.getText();

            try {
                int quantita = Integer.parseInt(quantitaString);
                controller.richiediScarico(codice, quantita, this);
                IDTextField.setText("");
                quantitàTextField.setText("");

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
        return panel1;
    }
}