package beans;

import utils.DButils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ForCoursesofwebsiteBean {
    private ArrayList<ForCourseofwebsiteBean> courses = new ArrayList<ForCourseofwebsiteBean>();

    public ForCoursesofwebsiteBean() throws SQLException {
        try {
            ResultSet resultSet = DButils.executeQeuryAsSelect("SELECT courseId, courseName FROM courses_of_website");
            if (resultSet != null) {
                while (resultSet.next()) {
                    courses.add(new ForCourseofwebsiteBean(resultSet.getInt("courseId"), resultSet.getString("courseName")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ForCourseofwebsiteBean> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<ForCourseofwebsiteBean> courses) {
        this.courses = courses;
    }
}
