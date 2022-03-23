package models;

import java.util.Date;

public class Client {
	private int id;
	private String raisonSociale;
	private String libelleClient;
	private String numeroTel;
	private String mail;
	private String adresse;
	private String civilite;
	private Date dateNaisssance;
	private int id_Conseiller;
	
	public Client(String raisonSociale, String libelleClient, String numeroTel, String mail, String adresse, String civilite, Date dateNaisssance, int id_Conseiller) {
		this.raisonSociale = raisonSociale;
		this.libelleClient = libelleClient;
		this.numeroTel = numeroTel;
		this.mail = mail;
		this.adresse = adresse;
		this.civilite = civilite;
		this.dateNaisssance = dateNaisssance;
		this.id_Conseiller = id_Conseiller;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRaisonSociale() {
		return raisonSociale;
	}

	public void setRaisonSociale(String raisonSociale) {
		this.raisonSociale = raisonSociale;
	}

	public String getLibelleClient() {
		return libelleClient;
	}

	public void setLibelleClient(String libelleClient) {
		this.libelleClient = libelleClient;
	}

	public String getNumeroTel() {
		return numeroTel;
	}

	public void setNumeroTel(String numeroTel) {
		this.numeroTel = numeroTel;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getCivilite() {
		return civilite;
	}

	public void setCivilite(String civilite) {
		this.civilite = civilite;
	}

	public Date getDateNaisssance() {
		return dateNaisssance;
	}

	public void setDateNaisssance(Date dateNaisssance) {
		this.dateNaisssance = dateNaisssance;
	}

	public int getId_Conseiller() {
		return id_Conseiller;
	}

	public void setId_Conseiller(int id_Conseiller) {
		this.id_Conseiller = id_Conseiller;
	}
}
