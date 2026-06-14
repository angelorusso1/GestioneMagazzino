package boundary;


import controller.MagazzinoController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SchermataAnalisiMagazzino {
    private JPanel panel1;
    private JButton btnGeneraAnalisi;
    private JLabel lblDataInizio;
    private JLabel lblDataFine;
    private JTextField txtDataInizio;
    private JTextField txtDataFine;

    public SchermataAnalisiMagazzino() {



        btnGeneraAnalisi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String stringaInizio = txtDataInizio.getText();
                String stringaFine = txtDataFine.getText();

                try {
                    // Definiamo il formato della data (es. Giorno/Mese/Anno)
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                    // Convertiamo le stringhe in LocalDate
                    LocalDate dataInizio = LocalDate.parse(stringaInizio, formatter);
                    LocalDate dataFine = LocalDate.parse(stringaFine, formatter);

                    // Richiamiamo il metodo ESATTO che hai nel tuo Controller
                    magazzinoController.richiediAnalisiMagazzino(dataInizio, dataFine);

                    // Opzionale: puoi nascondere questa finestra di input dopo aver cliccato
                    // setVisible(false);

                } catch (DateTimeParseException ex) {
                    // Se l'utente scrive "ciao" invece di una data, mostriamo un errore
                    JOptionPane.showMessageDialog(null,
                            "Formato data non valido! Inserire la data nel formato GG/MM/AAAA",
                            "Errore di Inserimento",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }
}
