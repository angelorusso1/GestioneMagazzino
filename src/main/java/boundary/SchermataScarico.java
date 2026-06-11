package boundary;

import javax.swing.*;

public class SchermataScarico extends JPanel{
    private JTextField IDTextField;
    private JPanel panel1;
    private JTextField quantitàTextField;
    private JButton eseguiScaricoButton;

    // Dentro la classe SchermataScarico
    public void messaggioErrore(String messaggio) {
        JOptionPane.showMessageDialog(this, messaggio, "Errore", JOptionPane.ERROR_MESSAGE);
    }

    public void messaggioConferma(String messaggio) {
        JOptionPane.showMessageDialog(this, messaggio, "Successo", JOptionPane.INFORMATION_MESSAGE);
    }
}
