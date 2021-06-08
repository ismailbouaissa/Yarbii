package com.example.demo.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class CotisationSituation {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

	public CotisationSituation(Long id, User user, Situation situation, int prix_cotisation) {
		super();
		this.id = id;
		this.user = user;
		this.situation = situation;
		this.prix_cotisation = prix_cotisation;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne
	@MapsId("idcotisateur")
	private User user;

	@ManyToOne
	@MapsId("idsituation")
	private Situation situation;

	private int prix_cotisation;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Situation getSituation() {
		return situation;
	}

	public void setSituation(Situation situation) {
		this.situation = situation;
	}

	public int getPrix_cotisation() {
		return prix_cotisation;
	}

	public void setPrix_cotisation(int prix_cotisation) {
		this.prix_cotisation = prix_cotisation;
	}

	

	public CotisationSituation() {

	}

}
