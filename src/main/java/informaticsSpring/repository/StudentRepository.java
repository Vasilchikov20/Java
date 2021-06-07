package informaticsSpring.repository;

import informaticsSpring.entity.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unused")
@Repository
public interface StudentRepository extends CrudRepository<Student, Integer> {
}
