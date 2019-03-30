package hello.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "APP_ROLE")
public class AppRole {

    @Id
    @Column(name = "role_id", nullable = false)
    private int role_id;

    @Column(name = "role_name", length = 30, nullable = false)
    private String role_name;

    @ManyToMany
    @JoinTable(name="USER_ROLE",
            joinColumns=@JoinColumn(name="role_id"),
            inverseJoinColumns=@JoinColumn(name="user_id"))
    private List<AppUser> users;

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int roleId) {
        this.role_id = roleId;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String roleName) {
        this.role_name = roleName;
    }

    @Override
    public String toString()
    {
        return this.role_id+" "+this.role_name;
    }

}