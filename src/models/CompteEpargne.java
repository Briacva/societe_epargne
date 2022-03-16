package models;

/**
 * 
 * @author marvin
 *
 */
public class CompteEpargne extends Compte{
	private float plafond;
	private float tauxInteret;
	
	public CompteEpargne(int id, int numCompte, float solde, float soldeInitial, boolean cloture, boolean typeCompte, float plafond, float tauxInteret) {
		super(id, numCompte, solde, soldeInitial, cloture, typeCompte);
		this.plafond = plafond;
		this.tauxInteret = tauxInteret;
	}
	
	public float getPlafond() {
		return plafond;
	}

	public void setPlafond(float plafond) {
		this.plafond = plafond;
	}

	public float getTauxInteret() {
		return tauxInteret;
	}

	public void setTauxInteret(float tauxInteret) {
		this.tauxInteret = tauxInteret;
	}

}
