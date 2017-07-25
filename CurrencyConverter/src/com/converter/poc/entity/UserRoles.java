package com.converter.poc.entity;

import javax.persistence.*;
import java.util.Set;

	@Entity
	@Table(name = "role")
	public class UserRoles {
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name="ROLE_ID")
	    private Long id;
		
		@Column(name="ROLE_NAME")
	    private String role;
		
		@ManyToMany(mappedBy = "role")
	    private Set<User> users;
		/**
		 * @return the roles
		 */
		public String getRole() {
			return role;
		}

		/**
		 * @param roles the roles to set
		 */
		public void setRole(String role) {
			this.role = role;
		}

		

	 
	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	   

	    public Set<User> getUsers() {
	        return users;
	    }

	    public void setUsers(Set<User> users) {
	        this.users = users;
	    }
}