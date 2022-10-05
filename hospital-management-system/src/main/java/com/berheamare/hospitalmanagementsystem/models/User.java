package com.berheamare.hospitalmanagementsystem.models;



import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table()

// Implements UserDetails to retrieve the user’s authentication and authorization information
public class User implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String username;
	private String password;

	@Enumerated(EnumType.STRING)
	private UserRole userRole;

	private Boolean locked = false;
	private Boolean enabled = false;

	public User() {

	}

	public User(String firstname, 
				String lastname, 
				String email, 
				String username,
				String password, 
				UserRole userRole) {
		
		this.firstName = firstname;
		this.lastName = lastname;
		this.email = email;
		this.username=username;
		this.password = password;
		this.userRole = userRole;
	}

	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public void setEmail(String email) {
		this.email = email;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}
	public String getFirstName() {
		// TODO Auto-generated method stub
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getEmail() {
		// TODO Auto-generated method stub
		return email;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub

		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userRole.name());

		return Collections.singletonList(authority);
	}

	

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return !locked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return enabled;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", username=" + username + ", password=" + password + ", userRole=" + userRole + ", locked=" + locked
				+ ", enabled=" + enabled + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, enabled, firstName, id, lastName, locked, password, userRole, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && Objects.equals(enabled, other.enabled)
				&& Objects.equals(firstName, other.firstName) && Objects.equals(id, other.id)
				&& Objects.equals(lastName, other.lastName) && Objects.equals(locked, other.locked)
				&& Objects.equals(password, other.password) && userRole == other.userRole
				&& Objects.equals(username, other.username);
	}

	
}