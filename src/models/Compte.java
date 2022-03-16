/**
 * 
 */
package models;

/**
 * @author marvin
 *
 */
public class Compte {
	protected int id;
	protected int numCompte;
	protected float solde;
	protected float soldeInitial;
	protected boolean cloture;
	protected boolean typeCompte;
	
	public Compte(int id, int numCompte, float solde, float soldeInitial, boolean cloture, boolean typeCompte) {
		this.id = id;
		this.numCompte = numCompte;
		this.solde = solde;
		this.soldeInitial = soldeInitial;
		this.cloture = cloture;
		this.typeCompte = typeCompte;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumCompte() {
		return numCompte;
	}

	public void setNumCompte(int numCompte) {
		this.numCompte = numCompte;
	}

	public float getSolde() {
		return solde;
	}

	public void setSolde(float solde) {
		this.solde = solde;
	}

	public float getSoldeInitial() {
		return soldeInitial;
	}

	public void setSoldeInitial(float soldeInitial) {
		this.soldeInitial = soldeInitial;
	}

	public boolean isCloture() {
		return cloture;
	}

	public void setCloture(boolean cloture) {
		this.cloture = cloture;
	}

	public boolean isTypeCompte() {
		return typeCompte;
	}

	public void setTypeCompte(boolean typeCompte) {
		this.typeCompte = typeCompte;
	}
}
