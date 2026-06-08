package boundary;

import controller.MagazzinoController;
import entity.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SchermataCatalogoProdotti {

    private JPanel mainPanel;
    private JTextField txtCodice;
    private JTextField txtNome;
    private JTextField txtDescrizione;
    private JTextField txtCategoria;
    private JTextField txtSoglia;
    private JTextField txtScaffale;
    private JTextField txtArea;
    private JButton btnSalva;

    private MagazzinoController magazzinoController;

    /**
     * Costruttore accoppiato con il controller grafico
     */
    public SchermataCatalogoProdotti(MagazzinoController magazzinoController) {
        this.magazzinoController = magazzinoController;

        //Listener agganciato al pulsante "btnSalva"
        btnSalva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Avvia formalmente il caso d'uso del Sequence Diagram
                creaNuovoProdotto();
            }
        });
    }

    public void creaNuovoProdotto() {
        //Richiamo logico dell'inserimento dati
        inserisciDati();
    }

    public void inserisciDati() {
        try {
            //Estrazione sicura del testo digitato dall'utente nella griglia grafica
            String codice = txtCodice.getText().trim().toUpperCase();
            String nome = txtNome.getText().trim();
            String descrizione = txtDescrizione.getText().trim();

            String nomeCategoria = txtCategoria.getText().trim().toUpperCase();
            Categoria categoria = new Categoria(nomeCategoria);

            int soglia = Integer.parseInt(txtSoglia.getText().trim());

            int scaffale = Integer.parseInt(txtScaffale.getText().trim());
            String area = txtArea.getText().trim().toUpperCase();
            Posizione posizione = new Posizione(scaffale, area);

            //Validazione locale prima dell'invio al sistema
            if (codice.isEmpty() || nome.isEmpty()) {
                messaggioConferma("ATTENZIONE: I campi Codice e Nome sono obbligatori per procedere.");
                return;
            }

            //Inoltro formale dei dati al controller passandogli la schermata corrente (this)
            this.magazzinoController.richiediCreazioneProdotto(codice, nome, descrizione, categoria, soglia, posizione, this);

        } catch (NumberFormatException ex) {
            //Gestione dell'errore di digitazione se l'utente inserisce lettere nei campi numerici
            messaggioConferma("ERRORE: I campi 'Soglia Minima' e 'Numero Scaffale' richiedono un numero intero.");
        } catch (IllegalArgumentException ex) {
            //Intercetta l'eccezione di sogliaMinima negativa Prodotto e mostra il messaggio
            messaggioConferma(ex.getMessage());
        }
    }

    /**
     * Riceve il messaggio di ritorno dal Controller e genera un popup grafico Swing
     */
    public void messaggioConferma(String messaggio) {
        //Mostra un popup nativo centrato sul pannello principale
        JOptionPane.showMessageDialog(mainPanel, messaggio, "Notifica Sistema", JOptionPane.INFORMATION_MESSAGE);

        //Se l'operazione è andata a buon fine, svuotiamo la griglia per un eventuale nuovo inserimento
        if (messaggio.contains("inserito correttamente")) {
            pulisciCampiGrafici();
        }
    }

    /**
     * Svuota tutti i campi di testo della maschera
     */
    private void pulisciCampiGrafici() {
        txtCodice.setText("");
        txtNome.setText("");
        txtDescrizione.setText("");
        txtCategoria.setText("");
        txtSoglia.setText("");
        txtScaffale.setText("");
        txtArea.setText("");
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
