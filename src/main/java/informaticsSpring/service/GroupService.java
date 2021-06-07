package informaticsSpring.service;

import informaticsSpring.entity.LearningGroup;

import java.util.List;

@SuppressWarnings("unused")
public interface GroupService {
    LearningGroup createGroup(LearningGroup LearningGroup);
    LearningGroup getGroupById(Integer id);
    List<LearningGroup> getGroups();
    void updateGroup(LearningGroup LearningGroup);
    void deleteGroup(Integer id);
}
