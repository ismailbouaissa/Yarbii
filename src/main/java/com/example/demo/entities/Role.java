package com.example.demo.entities;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.security.core.GrantedAuthority;

@SuppressWarnings("serial")
@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

	
	public Role() {};
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column
    private RoleType name;
    
    public Role(RoleType name) {
		this.name = name;
	}

	public void setName(RoleType name) {
		this.name = name;
	}
    
	public RoleType getName() {
		return name;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@Override
	public String getAuthority() {
		return name.name();
	}
	public enum RoleType{
        ROLE_COUTISATEUR , ROLE_ADMIN
    }

}