package hello.model;

import hello.dao.AppUserDAO;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "COURSE")
public class Course {
    @Id
    @Column(name="course_id")
    private int course_id;

    @Column(name="course_name")
    private String course_name;

    @Column(name="course_block")
    private int course_block;

    @Column(name="course_description")
    private String course_description;

    @Column(name = "course_class")
    private int course_class;

    //@Column(name = "master_id")
    //private  int master_id;

    /*@ManyToMany
    @JoinTable(name = "CONF_COURSE_PAPER", joinColumns = @JoinColumn (name = "course_id"), inverseJoinColumns = @JoinColumn(name="paper_id"))
    private ArrayList<Paper> papers;*/

    @ManyToOne
    @JoinColumn(name = "master_id")
    private AppUser master;

    public int getCourse_id() {
        return course_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public int getCourse_block() {
        return course_block;
    }

    public String getCourse_description() {
        return course_description;
    }

    public int getCourse_class() {
        return course_class;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public void setCourse_block(int course_block) {
        this.course_block = course_block;
    }

    public void setCourse_description(String course_description) {
        this.course_description = course_description;
    }

    public void setCourse_class(int course_class) {
        this.course_class = course_class;
    }

    /*public int getMaster_id() {
        return master_id;
    }

    public void setMaster_id(int master_id) {
        this.master_id = master_id;
    }*/

   /* public ArrayList<Paper> getPapers() {
        return papers;
    }

    public void setPapers(ArrayList<Paper> papers) {
        this.papers = papers;
    }*/

    /*public AppUser getMaster() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        AppUserDAO appUserDAO = context.getBean(AppUserDAO.class);
        return appUserDAO.getUserById(this.master_id);
    }

    public void setMaster(AppUser master) {
        this.master = master;
    }*/
}
