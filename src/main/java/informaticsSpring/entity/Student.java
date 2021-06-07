package informaticsSpring.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@SuppressWarnings("unused")
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @JsonBackReference(value="LearningGroup")
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, optional = false)
    private LearningGroup LearningGroup;

    @Column(nullable = false, unique = true)
    private String firstName;

    @Column(nullable = false)
    private String secondName;

    private Integer firstTask;

    private Integer secondTask;

    private Integer thirdTask;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }
    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public LearningGroup getLearningGroup() {
        return LearningGroup;
    }
    public void setLearningGroup(LearningGroup LearningGroup) {
        this.LearningGroup = LearningGroup;
    }

    public Integer getFirstTask() {
        return firstTask;
    }

    public void setFirstTask(Integer firstTask) {
        this.firstTask = firstTask;
    }

    public Integer getSecondTask() {
        return secondTask;
    }

    public void setSecondTask(Integer secondTask) {
        this.secondTask = secondTask;
    }

    public Integer getThirdTask() {
        return thirdTask;
    }

    public void setThirdTask(Integer thirdTask) {
        this.thirdTask = thirdTask;
    }
}
