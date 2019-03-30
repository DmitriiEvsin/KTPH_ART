package hello.dao;

import java.util.List;

import hello.model.AppUser;

public interface AppUserDAO {
    public void saveUser(AppUser appUser);
    public AppUser getUserById(int id);
    public AppUser getUserByUsername(String username);
    public List<AppUser> getAllUsers();
    public void deleteAppUser(Integer user_id);
    public void updateUser(AppUser appUser);
}
