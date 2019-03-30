package hello.model;

import javax.persistence.*;

@Entity
@Table(name = "USER_ROLE", //
        uniqueConstraints = { //
                @UniqueConstraint(name = "USER_ROLE_UK", columnNames = { "User_Id", "Role_Id" }) })
public class UserRoleUpd {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Integer user_id;

    @Column(name = "role_id", nullable = false)
    private Integer role_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

   public void setUser_id(Integer user_id)
   {
       this.user_id = user_id;
   }

   public void setRole_id(Integer role_id)
   {
       this.role_id = role_id;
   }
}
