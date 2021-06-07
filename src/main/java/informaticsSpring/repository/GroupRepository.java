package informaticsSpring.repository;

import informaticsSpring.entity.LearningGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unused")
@Repository
public interface GroupRepository extends CrudRepository<LearningGroup, Integer> {
}
