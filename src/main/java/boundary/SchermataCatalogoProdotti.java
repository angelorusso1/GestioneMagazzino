package boundary;

import controller.MagazzinoController;
import entity.*;

import java.util.List;
import java.util.Scanner;

public class SchermataCatalogoProdotti {
    private MagazzinoController magazzinoController;
    private Scanner scanner;
    public void creaNuovoProdotto() {
        // TODO - implement SchermataCatalogoProdotti.creaNuovoProdotto
        System.out.println("SCHERMATA: CREAZIONE NUOVO PRODOTTO");
        inserisciDati();
    }

    public void inserisciDati() {
        // TODO - implement SchermataCatalogoProdotti.inserisciDati
        System.out.println("COMPILAZIONE MODULO PRODOTTO: ");

        System.out.print("Codice identificativo univoco: ");
        String codice = scanner.nextLine();

        System.out.print("Nome prodotto: ");
        String nome = scanner.nextLine();

        System.out.print("Descrizione: ");
        String descrizione = scanner.nextLine();

        System.out.print("Categoria: ");
        String nomeCategoria = scanner.nextLine();
        Categoria categoria = new Categoria(nomeCategoria);

        System.out.print("Soglia minima di disponibilità: ");
        int soglia = Integer.parseInt(scanner.nextLine());

        System.out.print("Posizione (scaffale/area): ");
        String infoPosizione = scanner.nextLine();
        int scaffale = scanner.nextInt();
        // Nota: Qui passiamo infoPosizione sia come scaffale che come area per semplicità di input da console
        Posizione posizione = new Posizione(scaffale, infoPosizione);


        System.out.println("\n[INTERFACCIA] Invio dei dati al Controller...");

        this.magazzinoController.richiediCreazioneProdotto(codice, nome, descrizione, categoria, soglia, posizione, this);
    }

    public void messaggioConferma(String messaggio) {
        // TODO - implement SchermataCatalogoProdotti.messaggioConferma
        System.out.println("[NOTIFICA INTERFACCIA]: " + messaggio);
    }
}

