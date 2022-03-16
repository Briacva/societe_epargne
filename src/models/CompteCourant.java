/**
 * 
 */
package models;

/**
 * @author marvin
 *
 */
public class CompteCourant extends Compte{
	private float soldeMinimum;
	private float fraisTransfert;
	
	public CompteCourant(int id, int numCompte, float solde, float soldeInitial, boolean cloture, boolean typeCompte, float soldeMinimum, float fraisTransfert) {
		super(id, numCompte, solde, soldeInitial, cloture, typeCompte);
		this.soldeMinimum = soldeMinimum;
		this.fraisTransfert = fraisTransfert;
	}

	public float getSoldeMinimum() {
		return soldeMinimum;
	}

	public void setSoldeMinimum(float soldeMinimum) {
		this.soldeMinimum = soldeMinimum;
	}

	public float getFraisTransfert() {
		return fraisTransfert;
	}

	public void setFraisTransfert(float fraisTransfert) {
		this.fraisTransfert = fraisTransfert;
	}
}
