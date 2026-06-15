package boundary;


import controller.MagazzinoController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SchermataAnalisiMagazzino {
    private JPanel mainPanel;
    private JButton btnGeneraAnalisi;
    private JLabel lblDataInizio;
    private JLabel lblDataFine;
    private JTextField txtDataInizio;
    private JTextField txtDataFine;

    private MagazzinoController magazzinoController;

    public SchermataAnalisiMagazzino(MagazzinoController magazzinoController) {

        this.magazzinoController = magazzinoController;

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

                    //Controllo di coerenza temporale
                    if (dataInizio.isAfter(dataFine)) {
                        JOptionPane.showMessageDialog(null,
                                "La data di inizio non può essere successiva alla data di fine!",
                                "Errore Temporale",
                                JOptionPane.WARNING_MESSAGE);
                        return; // Blocca l'esecuzione ed evita di chiamare il controller
                    }

                    // Richiamiamo il metodo ESATTO che hai nel tuo Controller
                    magazzinoController.richiediAnalisiMagazzino(dataInizio, dataFine);

                } catch (DateTimeParseException ex) {
                    // Se l'utente scrive una stringa invece di una data, mostriamo un errore
                    JOptionPane.showMessageDialog(null,
                            "Formato data non valido! Inserire la data nel formato GG/MM/AAAA",
                            "Errore di Inserimento",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    public JPanel getMainPanel() {
        return mainPanel;
    }

}
