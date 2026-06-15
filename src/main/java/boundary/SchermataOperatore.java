package boundary;

import controller.MagazzinoController;
import entity.Prodotto;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class SchermataOperatore {
    private JPanel mainPanel;
    private JTable tabellaProdotti;
    private JButton btnSelezionaPerScarico;
    private JTextField txtFiltroCodice;
    private JTextField txtFiltroNome;
    private JTextField txtFiltroCategoria;
    private JTextField txtFiltroPosizione;
    private JButton btnResettaFiltri;
    private JLabel lblFiltroCodice;
    private JLabel lblFiltroNome;
    private JLabel lblFiltroCategoria;
    private JLabel lblFiltroPosizione;
    private JLabel lblContatore;

    private List<Prodotto> prodottiVisualizzati; // Ci serve per sapere quale oggetto corrisponde alla riga cliccata
    private MagazzinoController controller;

    // Il Sorter che gestisce il filtraggio visivo della tabella
    private TableRowSorter<DefaultTableModel> sorter;

    public SchermataOperatore(MagazzinoController controller, List<Prodotto> prodotti) {
        this.controller = controller;
        this.prodottiVisualizzati = prodotti;

        // 1. Popoliamo la tabella con i dati dei prodotti ricevuti
        String[] colonne = {"Codice", "Nome", "Giacenza", "Categoria", "Posizione"};
        DefaultTableModel model = new DefaultTableModel(colonne, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (Prodotto p : prodotti) {
            Object[] riga = {
                    p.getCodice(),
                    p.getNome(),
                    p.getQuantitaDisponibile(),
                    p.getCategoria() != null ? p.getCategoria().getNome() : "N/D",
                    p.getPosizione() != null ? (p.getPosizione().getArea() + " - Scaff. " + p.getPosizione().getScaffale()) : "N/D"
            };
            model.addRow(riga);
        }
        tabellaProdotti.setModel(model);

        // 3. 🎯 INIZIALIZZAZIONE DEL SORTER
        sorter = new TableRowSorter<>(model);
        tabellaProdotti.setRowSorter(sorter);

        // 4. ASCOLTO IN TEMPO REALE (Filtra mentre l'utente digita)
        KeyAdapter tastoRilasciatoListener = new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                applicaFiltriInAnd();
            }
        };

        txtFiltroCodice.addKeyListener(tastoRilasciatoListener);
        txtFiltroNome.addKeyListener(tastoRilasciatoListener);
        txtFiltroCategoria.addKeyListener(tastoRilasciatoListener);
        txtFiltroPosizione.addKeyListener(tastoRilasciatoListener);

        // 5. Gestione del pulsante Resetta
        btnResettaFiltri.addActionListener(e -> {
            txtFiltroCodice.setText("");
            txtFiltroNome.setText("");
            txtFiltroCategoria.setText("");
            txtFiltroPosizione.setText("");
            sorter.setRowFilter(null); // Rimuove ogni filtro mostrando tutto
        });

        // 2. Gestiamo il click sul tasto "Seleziona Prodotto"
        btnSelezionaPerScarico.addActionListener(e -> {
            int rigaVisivaSelezionata = tabellaProdotti.getSelectedRow();

            if (rigaVisivaSelezionata == -1) {
                JOptionPane.showMessageDialog(null, "Seleziona prima un prodotto dalla tabella!", "Attenzione", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Recuperiamo l'oggetto Prodotto reale corrispondente alla riga cliccata
            // 🔥 FONDAMENTALE: Converte l'indice della riga filtrata nell'indice reale del database/lista
            int rigaModelloReale = tabellaProdotti.convertRowIndexToModel(rigaVisivaSelezionata);
            Prodotto prodottoScelto = prodottiVisualizzati.get(rigaModelloReale);

            // Chiudiamo questa finestra attuale
            JFrame frameCorrente = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
            if (frameCorrente != null) frameCorrente.dispose();

            // 🎯 PASSAMANO: Diciamo al controller di aprire lo scarico per QUESTO prodotto
            controller.apriSchermataScaricoSelezionato(prodottoScelto);
        });
    }

    private void applicaFiltriInAnd() {
        List<RowFilter<Object, Object>> filtriAttivi = new ArrayList<>();

        // (?i) serve a rendere la ricerca Case-Insensitive (ignora maiuscole/minuscole)
        if (!txtFiltroCodice.getText().trim().isEmpty()) {
            filtriAttivi.add(RowFilter.regexFilter("(?i)" + txtFiltroCodice.getText().trim(), 0)); // Colonna 0: Codice
        }
        if (!txtFiltroNome.getText().trim().isEmpty()) {
            filtriAttivi.add(RowFilter.regexFilter("(?i)" + txtFiltroNome.getText().trim(), 1)); // Colonna 1: Nome
        }
        if (!txtFiltroCategoria.getText().trim().isEmpty()) {
            filtriAttivi.add(RowFilter.regexFilter("(?i)" + txtFiltroCategoria.getText().trim(), 3)); // Colonna 3: Categoria
        }
        if (!txtFiltroPosizione.getText().trim().isEmpty()) {
            filtriAttivi.add(RowFilter.regexFilter("(?i)" + txtFiltroPosizione.getText().trim(), 4)); // Colonna 4: Posizione
        }

        // Se non c'è scritto nulla nei campi, svuota i filtri, altrimenti applicali in AND
        if (filtriAttivi.isEmpty()) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.andFilter(filtriAttivi));
        }

        lblContatore.setText("Prodotti trovati: " + sorter.getViewRowCount());
    }

    public JPanel getMainPanel() { return mainPanel; }
}
