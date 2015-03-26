package com.gymadmin.persistence.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 	CREATE TABLE user_roles (
	  	user_role_id INT(11) NOT NULL,
	  	user_id VARCHAR(45) NOT NULL,
	  	role VARCHAR(45) NOT NULL,
	  	PRIMARY KEY (user_role_id),
	  	UNIQUE KEY uni_username_role (role, user_id),
	  	CONSTRAINT fk_userid FOREIGN KEY (user_id) REFERENCES users (nick));
 * @author mrodriguez
 *
 */
@Entity
@Table(name = "user_roles")
public class UserRoleEntity implements Serializable  {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "user_role_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator =	"user_role_sequence") 
	@SequenceGenerator(name = "user_role_sequence", sequenceName = "user_role_id_sequence", allocationSize = 1)
    private Integer userRoleId;
	
	@Column(name = "role", nullable = false, length = 45)
    private String role;
	
	@ManyToOne
    @JoinColumn(name="user_id")
    private UserEntity user;

	public Integer getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(Integer userRoleId) {
		this.userRoleId = userRoleId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}
    
   
    
}
