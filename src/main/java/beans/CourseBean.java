package beans;

public class CourseBean {
    private int id;
    private String name;

    public CourseBean(){}

    public CourseBean(Integer courseId, String courseName) {
        setId(courseId);
        setName(courseName);
    }

    @Override
    public String toString() {
        return "beans.CourseBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
