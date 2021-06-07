package informaticsSpring.service.impl;

import informaticsSpring.entity.Student;
import informaticsSpring.repository.StudentRepository;
import informaticsSpring.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("unused")
@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository StudentRepository;

    public StudentServiceImpl(StudentRepository StudentRepository) {
        this.StudentRepository = StudentRepository;
    }

    @Override
    public Student createStudent(Student Student) {
        return this.StudentRepository.save(Student);
    }

    @Override
    public Student getStudentById(Integer id) {
        return this.StudentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Student> getStudents() {
        return (List<Student>) this.StudentRepository.findAll();
    }

    @Override
    public void updateStudent(Student Student) {
        this.StudentRepository.findById(Student.getId()).ifPresent(StudentToChange -> {
            StudentToChange.setFirstName(Student.getFirstName());
            StudentToChange.setSecondName(Student.getSecondName());
            StudentToChange.setLearningGroup(Student.getLearningGroup());
            this.StudentRepository.save(StudentToChange);
        });
    }

    @Override
    public void deleteStudent(Integer id) {
        Optional<Student> Student = this.StudentRepository.findById(id);
        this.StudentRepository.delete(Student.orElseThrow());
    }
}
