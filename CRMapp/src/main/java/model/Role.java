package model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
@Entity
public class Role {
	   @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String name;
	    @ManyToMany(mappedBy = "roles")
	    private Set<AppUser> users = new HashSet<>();
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Set<AppUser> getUsers() {
			return users;
		}
		public void setUsers(Set<AppUser> users) {
			this.users = users;
		}
		public Role(Long id, String name, Set<AppUser> users) {
			super();
			this.id = id;
			this.name = name;
			this.users = users;
		}
		public Role(String name) {
			super();
			this.name = name;
		}
		public Role() {
			super();
		}
	    
}
