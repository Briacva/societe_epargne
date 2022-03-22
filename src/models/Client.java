package models;
import java.util.Date;

public class Client {
	private int id;
	private String raisonSocial;
	private String libelleClient;
	private String telephone;
	private String mail;
	private String adresse;
	private String civilite;
	private Date dateDeNaissance;
	private int id_Conseille;
	
	
//	constructeur
	public Client(
			String raisonSocial,
			String libelleClient,
			String telephone,
			String mail,
			String adresse,
			String civilite,
			Date dateDeNaissance,
			int id_Conseille) {
		
		super();
		this.civilite = civilite;
		this.raisonSocial = raisonSocial;
		this.libelleClient = libelleClient;
		this.telephone = telephone;
		this.mail = mail;
		this.adresse = adresse;
		this.dateDeNaissance = dateDeNaissance;
		this.id_Conseille = id_Conseille;
	}
	
	
	
//	Getter et setter.
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRaisonSocial() {
		return raisonSocial;
	}
	public void setRaisonSocial(String raisonSocial) {
		this.raisonSocial = raisonSocial;
	}
	public String getLibelleClient() {
		return libelleClient;
	}
	public void
			setLibelleClient(String libelleClient) {
		this.libelleClient = libelleClient;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
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
	public Date getdateDeNaissance() {
		return dateDeNaissance;
	}
	public void setdateDeNaissance(Date dateDeNaissance) {
		this.dateDeNaissance = dateDeNaissance;
	}
	public int getId_Conseille() {
		return id_Conseille;
	}
	
	public void setId_Conseille(int id_Conseille) {
		this.id_Conseille = id_Conseille;
	}
	
	


}