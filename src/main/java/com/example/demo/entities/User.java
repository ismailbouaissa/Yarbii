package com.example.demo.entities;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@SuppressWarnings("serial")
@Entity
@Table(name = "users")
public class User implements UserDetails {
	
	public User() {};
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Size(max = 20, min = 5)
    @NotBlank
    @Column
    private String username;
    @NotBlank
    @Size(max = 80, min = 5)
    @Column
    private String password;
    @NotBlank
    @Size(max = 50)
    @Email
    @Column
    private String email;
    @Column
    private boolean is_enabled ;
    @NotBlank
	@Column(name="NOM")
	private String nom;
    @NotBlank
	@Column(name="PRENOM")
	private String prenom;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<Role>();
    
    
	public User(@Size(max = 20, min = 5) @NotBlank String username, @NotBlank @Size(max = 80, min = 5) String password,
			@NotBlank @Size(max = 50) @Email String email, Set<Role> roles) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles;
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		this.is_enabled = false ;
		this.nom = null ;
		this.prenom = null ;
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}

	@Override
	public boolean isEnabled() {

		return true;
	}

	public boolean isIs_enabled() {
		return is_enabled;
	}

	public void setIs_enabled(boolean is_enabled) {
		this.is_enabled = is_enabled;
	}
	
	
	
}

