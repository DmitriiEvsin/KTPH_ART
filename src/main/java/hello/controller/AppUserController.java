package hello.controller;

import java.util.ArrayList;
import java.util.List;

import hello.dao.AppRoleDAO;
import hello.dao.AppUserDAO;
import hello.dao.UserRoleDAO;
import hello.model.AppRole;
import hello.model.UserRole;
import hello.utils.EncryptedPasswordUtils;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import hello.model.AppUser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.jws.soap.SOAPBinding;

@Controller
public class AppUserController {
    @GetMapping("/user/admin")
    public String  admin(Model model)
    {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        AppUserDAO appUserDAO = context.getBean(AppUserDAO.class);
        AppRoleDAO appRoleDAO = context.getBean(AppRoleDAO.class);


        List<AppUser> users = appUserDAO.getAllUsers();

        List<AppRole> roles = appRoleDAO.getAppRoles();

        model.addAttribute("users", users);
        model.addAttribute("new_user", new AppUser());
        model.addAttribute("all_roles", roles);
        return "user/admin";
    }

    @RequestMapping("/user/view")
    public String view(@RequestParam(name="user_id", required=false, defaultValue="1") Integer id, Model model) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        AppUserDAO appUserDAO = context.getBean(AppUserDAO.class);
        AppUser appUser = appUserDAO.getUserById(id);
        model.addAttribute("user", appUser);
        return "user/view";
    }

    @RequestMapping("/user/save")
    public String save(@RequestParam(name="user_name") String name, @RequestParam(name="encrypted_password") String password, RedirectAttributes redirectAttributes)
    {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        AppUserDAO appUserDAO = context.getBean(AppUserDAO.class);
        EncryptedPasswordUtils encryptedPasswordUtils = new EncryptedPasswordUtils();

        AppRoleDAO appRoleDAO = context.getBean(AppRoleDAO.class);
        UserRoleDAO userRoleDAO = context.getBean(UserRoleDAO.class);

        AppUser appUser = new AppUser();
        appUser.setUser_name(name);
        appUser.setEncrypted_password(encryptedPasswordUtils.encryptePassword(password));
        appUser.setEnabled(true);

        //AppRole appRoleDefault = appRoleDAO.getRoleById(2);
        //appUser.addRole(appRoleDefault);

        appUserDAO.saveUser(appUser);


        //AppRole appRole = appRoleDAO.getRoleById(role);

        //ArrayList<AppRole> roles = new ArrayList<AppRole>();
        //roles.add(appRole);
        //appUser.setRoles(roles);

        //appUserDAO.updateUser(appUser);


        //UserRole userRole = new UserRole();
        //userRole.setAppRole(appRole);
        //userRole.setAppUser(appUser);
        //userRoleDAO.saveUserRole(userRole);
        redirectAttributes.addAttribute("user_id",appUser.getUser_id());
        return "redirect:view";
    }

    @RequestMapping("/user/delete")
    public String delete(@RequestParam(name="user_id") Integer user_id)
    {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        AppUserDAO appUserDAO = context.getBean(AppUserDAO.class);
        appUserDAO.deleteAppUser(user_id);
        return "redirect:admin";
    }
}
