package entity;

import database.*;
import entity.Operatore;
import entity.Responsabile;
import entity.Ruolo;

public class GestioneUtenti {

	private GestorePersistenza gestorePersistenza;

	/**
	 * 
	 * @param nome
	 * @param cognome
	 * @param email
	 * @param ruolo
	 */
	public boolean registraDati(String nome, String cognome, String email, Ruolo ruolo) {
		if (ruolo == Ruolo.Responsabile) {
			return registraResponsabile(nome, cognome, email);
		} else if (ruolo == Ruolo.Operatore) {
			return registraOperatore(nome, cognome, email);
		}
		return false;
	}
	private boolean registraResponsabile(String nome, String cognome, String email) {
		Responsabile responsabile = new Responsabile();
		responsabile.setNome(nome);
		responsabile.setCognome(cognome);
		responsabile.setEmail(email);

		GestorePersistenza gp = new GestorePersistenza();
		// Il metodo "salva" del tuo prof probabilmente restituisce già un boolean.
		// Se così fosse, basta usare "return" qui davanti:
		return gp.salva(responsabile);
	}

	// 3. Cambia da void a boolean
	private boolean registraOperatore(String nome, String cognome, String email) {
		Operatore operatore = new Operatore();
		operatore.setNome(nome);
		operatore.setCognome(cognome);
		operatore.setEmail(email);

		GestorePersistenza gp = new GestorePersistenza();
		return gp.salva(operatore);
	}

	public Utente verificaCredenziali(String nome, String cognome, String email) {
		// Qui in futuro potresti aggiungere logiche di business
		// (es. controllare se l'utente è stato "sospeso" prima di farlo entrare)

		GestorePersistenza gp = new GestorePersistenza();
		return gp.cercaUtentePerLogin(nome, cognome, email);
	}
}
