package boundary;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class OutputSchermataAnalisi extends JFrame {

    private JPanel mainPanel;
    private JTable tabellaSottoScorta;
    private JTable tabellaClassifica;
    private JTable tabellaMovimenti;
    private JLabel lblProdottiSottoScorta;
    private JLabel lblPiuMovimentati;
    private JLabel lblStoricoMovimenti;

    public OutputSchermataAnalisi(Object[][] datiMovimenti, Object[][] datiClassifica, Object[][] datiSottoScorta) {
        setContentPane(mainPanel);
        setTitle("Risultati Analisi Magazzino");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //Chiude questa finestra


        popolaTabellaMovimenti(datiMovimenti);
        popolaTabellaClassifica(datiClassifica);
        popolaTabellaSottoScorta(datiSottoScorta);
    }

    private void popolaTabellaSottoScorta(Object[][] dati) {
        //Creiamo la struttura della tabella con le colonne
        String[] colonne = {"Codice/ID", "Nome Prodotto", "Giacenza", "Soglia Minima"};
        DefaultTableModel model = new DefaultTableModel(dati, colonne);


        //Diciamo alla JTable grafica di usare questo modello di dati
        tabellaSottoScorta.setModel(model);
    }

    private void popolaTabellaMovimenti(Object[][] dati) {
        String[] colonne = {"Data", "Prodotto", "Quantità Movimentata"};
        DefaultTableModel model = new DefaultTableModel(dati, colonne);
        tabellaMovimenti.setModel(model);
    }

    private void popolaTabellaClassifica(Object[][] dati) {
        String[] colonne = {"Posizione", "ID Prodotto", "Nome Prodotto"};
        DefaultTableModel model = new DefaultTableModel(dati, colonne);
        tabellaClassifica.setModel(model);
    }
}

