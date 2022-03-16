package models;

/**
 * 
 * @author marvin
 * Classe CompteEpargne héritée de Compte
 */
public class CompteEpargne extends Compte{
	// Variables de classe CompteEpargne
	private float plafond;
	private float tauxInteret;
	
	/**
	 * Constructeur de CompteEpargne, hérite des propriétés du parent pour l'instancier avec toutes les informations
	 * @param id
	 * @param numCompte
	 * @param solde
	 * @param soldeInitial
	 * @param cloture
	 * @param typeCompte
	 * @param plafond
	 * @param tauxInteret
	 */
	public CompteEpargne(int id, int numCompte, float solde, float soldeInitial, boolean cloture, boolean typeCompte, float plafond, float tauxInteret) {
		super(id, numCompte, solde, soldeInitial, cloture, typeCompte);
		this.plafond = plafond;
		this.tauxInteret = tauxInteret;
	}
	
	/**
	 * Getter Plafond
	 * @return
	 */
	public float getPlafond() {
		return plafond;
	}

	/**
	 * Setter Plafond
	 * @param plafond
	 */
	public void setPlafond(float plafond) {
		this.plafond = plafond;
	}

	/**
	 * Getter Taux d'intérêt
	 * @return
	 */
	public float getTauxInteret() {
		return tauxInteret;
	}

	/**
	 * Setter Taux d'intérêt
	 * @param tauxInteret
	 */
	public void setTauxInteret(float tauxInteret) {
		this.tauxInteret = tauxInteret;
	}

}
