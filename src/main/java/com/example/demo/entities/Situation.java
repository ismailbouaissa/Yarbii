package com.example.demo.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
@Entity
@Table(name = "Situation")
public class Situation {
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column
	    private Long id;
	 	@NotBlank
	    @Column
	    private String nomSituation;
	 	@NotBlank
	    @Column
	 	private String TypeStuition;
	 	@NotBlank
	    @Column
	 	private int totaleprix ;
	 	@NotBlank
	    @Column
	 	private String villeSituation ;
	 	@NotBlank
	    @Column
	    private Date expiration ;
	 	
	 	
	 	
		public Situation() {
			
		}
		public Situation(Long id, @NotBlank String nomSituation, @NotBlank String typeStuition,
				@NotBlank int totaleprix, @NotBlank String villeSituation, @NotBlank Date expiration) {
			super();
			this.id = id;
			this.nomSituation = nomSituation;
			TypeStuition = typeStuition;
			this.totaleprix = totaleprix;
			this.villeSituation = villeSituation;
			this.expiration = expiration;
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getNomSituation() {
			return nomSituation;
		}
		public void setNomSituation(String nomSituation) {
			this.nomSituation = nomSituation;
		}
		public String getTypeStuition() {
			return TypeStuition;
		}
		public void setTypeStuition(String typeStuition) {
			TypeStuition = typeStuition;
		}
		public int getTotaleprix() {
			return totaleprix;
		}
		public void setTotaleprix(int totaleprix) {
			this.totaleprix = totaleprix;
		}
		public String getVilleSituation() {
			return villeSituation;
		}
		public void setVilleSituation(String villeSituation) {
			this.villeSituation = villeSituation;
		}
		public Date getExpiration() {
			return expiration;
		}
		public void setExpiration(Date expiration) {
			this.expiration = expiration;
		}
	 	
	 	
	 	
	 	
	 	

}
