package hello.dao;

import hello.model.AppRole;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface AppRoleDAO {
    public List<String> getRoleNames(int userId);

    public List<AppRole> getUserRoles(Integer userId);

    public List<AppRole> getAppRoles();

    public void saveAppRole(AppRole appRole);

    public AppRole getRoleById(int id);

    public void deleteAppRole(Integer role_id);

}