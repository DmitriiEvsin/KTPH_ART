package hello.dao;

import hello.model.UserRole;

import java.util.List;

public interface UserRoleDAO {
    public List<String> getRoleUser();

    public void saveUserRole(UserRole userRole);

}
