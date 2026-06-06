package boundary;

import controller.MagazzinoController;
import entity.Notifica;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SchermataNotifiche {


    private JPanel mainPanel;
    private JTable tableNotifiche;
    private JButton btnChiudi;

    private MagazzinoController magazzinoController;

    public SchermataNotifiche() {
        this.magazzinoController = new MagazzinoController(this);

        // Listener per chiudere la finestra
        btnChiudi.addActionListener(e -> {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
            if (frame != null) frame.dispose();
        });
    }

    public void popolaTabellaNotifiche(List<Notifica> listaNotifiche) {
        // 1. Definiamo i titoli delle colonne richiesti da te
        String[] colonne = {"Data", "Prodotto", "Q.tà Disponibile", "Soglia Minima"};

        // 2. Creiamo il modello della tabella (impedendo la modifica diretta delle celle)
        DefaultTableModel modello = new DefaultTableModel(colonne, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Tabella in sola lettura
            }
        };

        // Formato data leggibile (es: 06/06/2026 12:00)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        // 3. Inseriamo i dati riga per riga navigando l'oggetto Prodotto
        for (Notifica n : listaNotifiche) {
            Object[] riga = new Object[4];
            riga[0] = n.getDataEmissione().format(formatter);
            riga[1] = n.getProdotto().getNome();
            riga[2] = n.getProdotto().getQuantitaDisponibile();
            riga[3] = n.getProdotto().getSogliaMinima();

            modello.addRow(riga);
        }

        // 4. Agganciamo il modello ricco di dati alla JTable grafica
        tableNotifiche.setModel(modello);
    }

    public void mostraNotifiche(List<Notifica> notifiche)
    {
        // TODO - implement SchermataNotifiche.mostraNotifiche
        throw new UnsupportedOperationException();
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
