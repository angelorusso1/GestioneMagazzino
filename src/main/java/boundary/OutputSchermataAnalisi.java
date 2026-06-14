package boundary;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import entity.DatiReport;
import entity.Movimento;
import entity.Prodotto;


public class OutputSchermataAnalisi extends JFrame {

    private DatiReport datiAnalisi;
    private JPanel mainPanel;
    private JTable tabellaSottoScorta;
    private JTable tabellaClassifica;
    private JTable tabellaMovimenti;
    private JLabel lblProdottiSottoScorta;
    private JLabel lblPiùMovimentati;
    private JLabel lblStoricoMovimenti;

    public OutputSchermataAnalisi(DatiReport reportMagazzino) {
        this.datiAnalisi = reportMagazzino;
        setContentPane(mainPanel);
        setTitle("Risultati Analisi Magazzino");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Chiude solo questa finestra, non l'app


        popolaTabellaMovimenti();
        popolaTabellaClassifica();
        popolaTabellaSottoScorta();

        // ... (chiamate agli altri metodi per le altre tabelle)
    }

    private void popolaTabellaSottoScorta() {
        // Creiamo la struttura della tabella con le colonne
        String[] colonne = {"Codice/ID", "Nome Prodotto", "Giacenza", "Soglia Minima"};
        DefaultTableModel model = new DefaultTableModel(colonne, 0);

        // Riempiamo le righe con i dati del nostro report
        for (Prodotto p : datiAnalisi.getListaProdottiSottoScorta()) {
            Object[] riga = {
                    p.getId(),
                    p.getNome(),
                    p.getQuantitaDisponibile(),
                    p.getSogliaMinima()
            };
            model.addRow(riga);
        }

        // Diciamo alla JTable grafica di usare questo modello di dati
        tabellaSottoScorta.setModel(model);
    }

    private void popolaTabellaMovimenti() {
        // Colonne per lo storico dei movimenti
        String[] colonne = {"Data", "Prodotto", "Quantità Movimentata"};
        DefaultTableModel model = new DefaultTableModel(colonne, 0);

        // Iteriamo sulla lista dei movimenti
        for (Movimento m : datiAnalisi.getListaMovimenti()) {
            Object[] riga = {
                    m.getData(), // Assicurati che il getter si chiami così
                    m.getProdotto().getNome(), // Recuperiamo il nome del prodotto collegato
                    m.getQuantitaProdotto()
            };
            model.addRow(riga);
        }

        tabellaMovimenti.setModel(model); // Assicurati di aver dato questo "field name" nel .form
    }

    private void popolaTabellaClassifica() {
        // Colonne per la classifica dei più movimentati
        String[] colonne = {"Posizione", "ID Prodotto", "Nome Prodotto"};
        DefaultTableModel model = new DefaultTableModel(colonne, 0);

        int posizione = 1;
        // La query ci ha già restituito i prodotti ordinati dal più al meno movimentato
        for (Prodotto p : datiAnalisi.getListaProdottiPiuMovimentati()) {
            Object[] riga = {
                    posizione + "°",
                    p.getId(),
                    p.getNome()
            };
            model.addRow(riga);
            posizione++;
        }

        tabellaClassifica.setModel(model); // Assicurati di aver dato questo "field name" nel .form
    }


    }

