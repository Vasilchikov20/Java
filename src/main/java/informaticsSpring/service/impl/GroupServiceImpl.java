package informaticsSpring.service.impl;

import informaticsSpring.entity.LearningGroup;
import informaticsSpring.repository.GroupRepository;
import informaticsSpring.service.GroupService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("unused")
@Service
public class GroupServiceImpl implements GroupService {
    private final GroupRepository GroupRepository;

    public GroupServiceImpl(GroupRepository GroupRepository) {
        this.GroupRepository = GroupRepository;
    }

    @Override
    public LearningGroup createGroup(LearningGroup LearningGroup) {
        return this.GroupRepository.save(LearningGroup);
    }

    @Override
    public LearningGroup getGroupById(Integer id) {
        return this.GroupRepository.findById(id).orElse(null);
    }

    @Override
    public List<LearningGroup> getGroups() {
        return (List<LearningGroup>) this.GroupRepository.findAll();
    }

    @Override
    public void updateGroup(LearningGroup LearningGroup) {
        this.GroupRepository.findById(LearningGroup.getId()).ifPresent(GroupToChange -> {
            GroupToChange.setCourseValue(LearningGroup.getCourseValue());
            this.GroupRepository.save(GroupToChange);
        });
    }

    @Override
    public void deleteGroup(Integer id) {
        Optional<LearningGroup> Group = this.GroupRepository.findById(id);
        this.GroupRepository.delete(Group.orElseThrow());
    }
}
