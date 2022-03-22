/**
 * 
 */
package models;

import java.util.Date;

/**
 * @author marvin
 *
 */
public class Transfert {
	private int idCompteDebiteur;
	private int idCompteCredite;
	private float montant;
	private Date dateTransfert;

	public Transfert(int idCompteDebiteur, int idCompteCredite, float montant, Date dateTransfert) {
		this.idCompteDebiteur = idCompteDebiteur;
		this.idCompteCredite = idCompteCredite;
		this.montant = montant;
		this.dateTransfert = dateTransfert;
	}

	public int getIdCompteDebiteur() {
		return idCompteDebiteur;
	}

	public void setIdCompteDebiteur(int idCompteDebiteur) {
		this.idCompteDebiteur = idCompteDebiteur;
	}

	public int getIdCompteCredite() {
		return idCompteCredite;
	}

	public void setIdCompteCredite(int idCompteCredite) {
		this.idCompteCredite = idCompteCredite;
	}

	public float getMontant() {
		return montant;
	}

	public void setMontant(float montant) {
		this.montant = montant;
	}

	public Date getDateTransfert() {
		return dateTransfert;
	}

	public void setDateTransfert(Date dateTransfert) {
		this.dateTransfert = dateTransfert;
	}
}
