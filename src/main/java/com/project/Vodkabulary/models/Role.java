package com.project.Vodkabulary.models;

import lombok.*;

import jakarta.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "roles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long role_ID;
    @Column(name="rolename", columnDefinition = "nvarchar(40)  null")
    private String roleName;
    @OneToMany(mappedBy = "roles", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()
    private Collection<User> users;

    public Role() {
        
    }
    public Role(long role_ID, String roleName) {
        this.role_ID = role_ID;
        this.roleName = roleName;
    }
	public long getRole_ID() {
		return role_ID;
	}
	public void setRole_ID(long role_ID) {
		this.role_ID = role_ID;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Collection<User> getUsers() {
		return users;
	}
	public void setUsers(Collection<User> users) {
		this.users = users;
	}
	
	
}
