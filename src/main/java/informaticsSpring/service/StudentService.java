package informaticsSpring.service;

import informaticsSpring.entity.Student;

import java.util.List;

@SuppressWarnings("unused")
public interface StudentService {
    Student createStudent(Student Student);
    Student getStudentById(Integer id);
    List<Student> getStudents();
    void updateStudent(Student Student);
    void deleteStudent(Integer id);
}
