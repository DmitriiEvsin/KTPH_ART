package hello.controller;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import hello.model.AppRole;
import hello.dao.AppRoleDAO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AppRoleController {
    @GetMapping("/role/admin")
    public String admin(Model model)
    {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        AppRoleDAO appRoleDAO = context.getBean(AppRoleDAO.class);

        List<AppRole> roles = appRoleDAO.getAppRoles();

        model.addAttribute("roles", roles);
        model.addAttribute("new_role", new AppRole());
        return "role/admin";
    }

    @RequestMapping("/role/save")
    public String save(@RequestParam(name="role_name") String name)
    {
            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
            AppRoleDAO appRoleDAO = context.getBean(AppRoleDAO.class);

            AppRole appRole = new AppRole();
            appRole.setRole_name(name);

            appRoleDAO.saveAppRole(appRole);

        return "redirect:admin";
    }

    @RequestMapping("/role/delete")
    public String delete(@RequestParam(name="role_id") Integer role_id)
    {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        AppRoleDAO appRoleDAO = context.getBean(AppRoleDAO.class);
        appRoleDAO.deleteAppRole(role_id);
        return "redirect:admin";
    }
}
