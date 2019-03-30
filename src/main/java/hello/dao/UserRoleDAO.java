package hello.dao;

import hello.model.UserRole;
import hello.model.UserRoleUpd;

import java.util.List;

public interface UserRoleDAO {
    public List<String> getRoleUser();

    public void saveUserRole(UserRoleUpd userRole);

    public UserRoleUpd getByUserRole(Integer user_id, Integer role_id);

    public void deleteUserRole(Long id);


}
