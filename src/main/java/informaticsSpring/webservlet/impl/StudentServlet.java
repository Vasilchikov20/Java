package informaticsSpring.webservlet.impl;

import informaticsSpring.entity.Student;
import informaticsSpring.entity.LearningGroup;
import informaticsSpring.service.impl.StudentServiceImpl;
import informaticsSpring.service.impl.GroupServiceImpl;
import informaticsSpring.webservlet.GenericServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SuppressWarnings("OptionalGetWithoutIsPresent")
@WebServlet("/Student")
public class StudentServlet extends GenericServlet {
    private final StudentServiceImpl StudentService;
    private final GroupServiceImpl GroupService;

    public StudentServlet(StudentServiceImpl StudentService, GroupServiceImpl GroupService) {
        this.StudentService = StudentService;
        this.GroupService = GroupService;
    }

    @Override
    protected String getUrl() {
        return "Student";
    }

    @Override
    protected String[] getIndex() {
        return new String[]{"active", "", ""};
    }

    @Override
    protected String[] getLabels() {
        return new String[]{
                "GroupCourse", "GroupNumber", "FirstName", "SecondName", "FirstTask", "SecondTask", "ThirdTask"
        };
    }

    private Stream<LearningGroup> getRelatedGroups(List<LearningGroup> learningGroups, Student rc) {
        return learningGroups.stream().filter(y -> Objects.equals(rc.getLearningGroup().getId(), y.getId()));
    }

    private Stream<LearningGroup> getGroupByCourse(List<LearningGroup> learningGroups, int course) {
        return learningGroups.stream().filter(y -> course == y.getCourseValue());
    }

    private Stream<LearningGroup> getGroupByNumber(List<LearningGroup> learningGroups, int number) {
        return learningGroups.stream().filter(y -> number == y.getNumberValue());
    }

    private Stream<LearningGroup> getGroupByCourseAndNumber(List<LearningGroup> learningGroups, int course, int number) {
        return learningGroups.stream().filter(y -> course == y.getCourseValue() && number == y.getNumberValue());
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Override
    protected String[][] fetchData(HttpServletRequest request) {
        var data = StudentService.getStudents();
        if (request.getParameter("id") != null && !request.getParameter("id").equals("")) {
            data = data.stream().filter(x -> x.getId() == Integer.parseInt(request.getParameter("id"))).
                    collect(Collectors.toList());
        }
        if (request.getParameter("1") != null && !request.getParameter("1").equals("")) {
            var Groups = getGroupByCourse(GroupService.getGroups(),
                    Integer.parseInt(request.getParameter("1"))).
                    collect(Collectors.toList());
            data = data.stream().filter(x -> Groups.
                    stream().anyMatch(y -> Objects.equals(x.getLearningGroup().getId(), y.getId()))).
                    collect(Collectors.toList());
        }
        if (request.getParameter("2") != null && !request.getParameter("2").equals("")) {
            var Groups = getGroupByNumber(GroupService.getGroups(),
                    Integer.parseInt(request.getParameter("2"))).
                    collect(Collectors.toList());
            data = data.stream().filter(x -> Groups.
                    stream().anyMatch(y -> Objects.equals(x.getLearningGroup().getId(), y.getId()))).
                    collect(Collectors.toList());
        }
        if (request.getParameter("3") != null && !request.getParameter("3").equals("")) {
            data = data.stream().filter(x -> x.getFirstName().equals(request.getParameter("3"))).
                    collect(Collectors.toList());
        }
        if (request.getParameter("4") != null && !request.getParameter("4").equals("")) {
            data = data.stream().filter(x -> x.getSecondName().equals(request.getParameter("4"))).
                    collect(Collectors.toList());
        }
        if (request.getParameter("5") != null && !request.getParameter("5").equals("")) {
            data = data.stream().filter(x -> x.getFirstTask() == Integer.parseInt(request.getParameter("5"))).
                    collect(Collectors.toList());
        }
        if (request.getParameter("6") != null && !request.getParameter("6").equals("")) {
            data = data.stream().filter(x -> x.getSecondTask() == Integer.parseInt(request.getParameter("6"))).
                    collect(Collectors.toList());
        }
        if (request.getParameter("7") != null && !request.getParameter("7").equals("")) {
            data = data.stream().filter(x -> x.getThirdTask() == Integer.parseInt(request.getParameter("7"))).
                    collect(Collectors.toList());
        }
        var Groups = GroupService.getGroups();
        return data.stream().map(x -> new String[]{
                String.valueOf(x.getId()),
                String.valueOf(getRelatedGroups(Groups, x).findFirst().get().getCourseValue()),
                String.valueOf(getRelatedGroups(Groups, x).findFirst().get().getNumberValue()),
                x.getFirstName(),
                x.getSecondName(),
                String.valueOf(x.getFirstTask()),
                String.valueOf(x.getSecondTask()),
                String.valueOf(x.getThirdTask()),
        }).toArray(String[][]::new);
    }

    @Override
    protected void insert(HttpServletRequest request) {
        var Student = new Student();
        Student.setLearningGroup(
                getGroupByCourseAndNumber(GroupService.getGroups(),
                        Integer.parseInt(request.getParameter("1")),
                        Integer.parseInt(request.getParameter("2"))).findFirst().get());
        Student.setFirstName(request.getParameter("3"));
        Student.setSecondName(request.getParameter("4"));
        Student.setFirstTask(Integer.parseInt(request.getParameter("5")));
        Student.setSecondTask(Integer.parseInt(request.getParameter("6")));
        Student.setThirdTask(Integer.parseInt(request.getParameter("7")));
        StudentService.createStudent(Student);
    }

    @Override
    protected void update(HttpServletRequest request) {
        var Student = StudentService.getStudentById(Integer.parseInt(request.getParameter("id")));
        if (Student == null) {
            throw new NoSuchElementException();
        }
        Student.setLearningGroup(
                getGroupByCourseAndNumber(GroupService.getGroups(),
                        Integer.parseInt(request.getParameter("1")),
                        Integer.parseInt(request.getParameter("2"))).findFirst().get());
        Student.setFirstName(request.getParameter("3"));
        Student.setSecondName(request.getParameter("4"));
        Student.setFirstTask(Integer.parseInt(request.getParameter("5")));
        Student.setSecondTask(Integer.parseInt(request.getParameter("6")));
        Student.setThirdTask(Integer.parseInt(request.getParameter("7")));
        StudentService.updateStudent(Student);
    }

    @Override
    protected void delete(HttpServletRequest request) {
        var Student = StudentService.getStudentById(Integer.parseInt(request.getParameter("id")));
        if (Student == null) {
            throw new NoSuchElementException();
        }
        StudentService.deleteStudent(Student.getId());
    }
}
