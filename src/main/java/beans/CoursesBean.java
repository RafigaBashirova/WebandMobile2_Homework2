package beans;

import utils.DButils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CoursesBean {
    private ArrayList<CourseBean> courses = new ArrayList<CourseBean>();

    public CoursesBean() throws SQLException {
        try {
            ResultSet resultSet = DButils.query("SELECT courseId, courseName FROM courses_of_website");
            if (resultSet != null) {
                while (resultSet.next()) {
                    courses.add(new CourseBean(resultSet.getInt("courseId"), resultSet.getString("courseName")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<CourseBean> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<CourseBean> courses) {
        this.courses = courses;
    }
}
