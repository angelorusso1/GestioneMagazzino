package entity;

import database.*;
import entity.Operatore;
import entity.Responsabile;
import entity.Ruolo;

import java.util.List;

public class GestioneUtenti {

	private GestorePersistenza gestorePersistenza;

	/**
	 * 
	 * @param nome
	 * @param cognome
	 * @param email
	 * @param ruolo
	 */
	public boolean registraDati(String nome, String cognome, String email, String ruolo) {
		if ("Responsabile".equalsIgnoreCase(ruolo)) {
			return registraResponsabile(nome, cognome, email);
		} else if ("Operatore".equalsIgnoreCase(ruolo)) {
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
		return gp.salva(responsabile);
	}

	private boolean registraOperatore(String nome, String cognome, String email) {
		Operatore operatore = new Operatore();
		operatore.setNome(nome);
		operatore.setCognome(cognome);
		operatore.setEmail(email);

		GestorePersistenza gp = new GestorePersistenza();
		return gp.salva(operatore);
	}

	public Utente verificaCredenziali(String nome, String cognome, String email) {

		GestorePersistenza gp = new GestorePersistenza();
		return gp.cercaUtentePerLogin(nome, cognome, email);
	}

	/**
	 * Verifica se esiste già un utente con la stessa email.
	 * Restituisce true se l'email è già presente, false altrimenti.
	 */
	public boolean emailGiaRegistrata(String email) {
		GestorePersistenza gp = new GestorePersistenza();

		// Usiamo il tuo metodo generico del gestore persistenza
		List<Utente> risultati = gp.cercaPerCampo(Utente.class, "Email", email);

		// Se la lista non è vuota, significa che l'email esiste già
		return risultati != null && !risultati.isEmpty();
	}
}
