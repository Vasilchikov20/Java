package informaticsSpring.controller;

import informaticsSpring.entity.Student;
import informaticsSpring.service.impl.StudentServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings("unused")
@RestController
public class StudentController {
    private final StudentServiceImpl StudentService;

    public StudentController(StudentServiceImpl StudentService) {
        this.StudentService = StudentService;
    }

    @PostMapping("/Student/Create")
    public Student createStudent(@RequestBody Student Student) {
        return this.StudentService.createStudent(Student);
    }

    @GetMapping("/Student/ReadBy/{id}")
    public Student getStudentById(@PathVariable Integer id) {
        return this.StudentService.getStudentById(id);
    }

    @GetMapping("/Student/Read")
    public List<Student> getStudents() {
        return this.StudentService.getStudents();
    }

    @PutMapping("/Student/Update")
    public String updateStudent(@RequestBody Student Student) {
        this.StudentService.updateStudent(Student);
        return "[]";
    }

    @DeleteMapping("/Student/DeleteBy/{id}")
    public String deleteStudent(@PathVariable Integer id) {
        this.StudentService.deleteStudent(id);
        return "[]";
    }
}
