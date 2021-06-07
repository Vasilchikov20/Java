package informaticsSpring.entity;

import javax.persistence.*;

@SuppressWarnings("unused")
@Entity
public class LearningGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer courseValue;

    private Integer numberValue;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCourseValue() {
        return courseValue;
    }
    public void setCourseValue(int courseValue) {
        this.courseValue = courseValue;
    }

    public Integer getNumberValue() {
        return numberValue;
    }
    public void setNumberValue(Integer numberValue) {
        this.numberValue = numberValue;
    }
}
