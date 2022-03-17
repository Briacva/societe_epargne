/**
 * 
 */
package models;

/**
 * @author marvin
 * Classe CompteCourant héritée de Compte
 */
public class CompteCourant extends Compte{
	// Variables de classe CompteCourant
	private float soldeMinimum;
	private float fraisTransfert;
	
	/**
	 * Constructeur de CompteCourant, hérite des propriétés du parent pour l'instancier avec toutes les informations
	 * @param id
	 * @param numCompte
	 * @param solde
	 * @param soldeInitial
	 * @param cloture
	 * @param typeCompte
	 * @param soldeMinimum
	 * @param fraisTransfert
	 */
	public CompteCourant(int id, int numCompte, float solde, float soldeInitial, boolean cloture, boolean typeCompte, float soldeMinimum, float fraisTransfert) {
		super(/*id, */numCompte, solde, soldeInitial, cloture, typeCompte);
		this.soldeMinimum = soldeMinimum;
		this.fraisTransfert = fraisTransfert;
	}

	/**
	 * Getter Solde minimum
	 * @return
	 */
	public float getSoldeMinimum() {
		return soldeMinimum;
	}

	/**
	 * Setter Solde minimum
	 * @param soldeMinimum
	 */
	public void setSoldeMinimum(float soldeMinimum) {
		this.soldeMinimum = soldeMinimum;
	}

	/**
	 * Getter Frais transfert
	 * @return
	 */
	public float getFraisTransfert() {
		return fraisTransfert;
	}

	/**
	 * Setter Frais transfert
	 * @param fraisTransfert
	 */
	public void setFraisTransfert(float fraisTransfert) {
		this.fraisTransfert = fraisTransfert;
	}
}
