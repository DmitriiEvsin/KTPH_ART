package hello.model;

import hello.dao.AppRoleDAO;
import hello.dao.AppUserDAO;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "APP_USER")
public class AppUser {
    @Id
    @Column(name="user_id")
    private int user_id;

    @Column(name="user_name")
    private String user_name;

    @Column(name = "encryted_password")
    private String encrypted_password;

    @Column(name = "enabled")
    private boolean enabled;

    @ManyToMany
    @JoinTable (name="USER_ROLE",
            joinColumns=@JoinColumn (name="user_id"),
            inverseJoinColumns=@JoinColumn(name="role_id"))
    private List<AppRole> roles;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int userId) {
        this.user_id = userId;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String userName) {
        this.user_name = userName;
    }

    public String getEncrypted_password() {
        return encrypted_password;
    }

    public void setEncrypted_password(String encryptedPassword) {
        this.encrypted_password = encryptedPassword;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getRole_names()
    {
        StringBuilder sb = new StringBuilder();
        for (AppRole role : this.roles){
            sb.append(role.getRole_name());
            sb.append(", ");
        }
        return sb.toString();
    }

    public List<AppRole> getRoles()
    {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        AppRoleDAO appRoleDAO = context.getBean(AppRoleDAO.class);
        return appRoleDAO.getUserRoles(this.user_id);
    }

    public void setRoles(ArrayList<AppRole> roles)
    {
        this.roles = roles;
    }

    public void addRole(AppRole appRole)
    {
        this.roles.add(appRole);
    }

    @Override
    public String toString(){
        return "id="+user_id+", имя пользователя="+user_name;
    }


}