package hello.dao;

import hello.model.Course;

import java.util.List;

public interface CourseDAO {
    public List<Course> getAllCourses();
    public List<Course> getUserCourses(int user_id);
    public void saveCourse(Course course);
    public void deleteCourse(int course_id);
}
