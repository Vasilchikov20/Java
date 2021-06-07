package informaticsSpring.controller;

import informaticsSpring.entity.LearningGroup;
import informaticsSpring.service.impl.GroupServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings("unused")
@RestController
public class GroupController {
    private final GroupServiceImpl GroupService;

    public GroupController(GroupServiceImpl GroupService) {
        this.GroupService = GroupService;
    }

    @PostMapping("/Group/Create")
    public LearningGroup createGroup(@RequestBody LearningGroup LearningGroup) {
        return this.GroupService.createGroup(LearningGroup);
    }

    @GetMapping("/Group/ReadBy/{id}")
    public LearningGroup getGroupById(@PathVariable Integer id) {
        return this.GroupService.getGroupById(id);
    }

    @GetMapping("/Group/Read")
    public List<LearningGroup> getGroups() {
        return this.GroupService.getGroups();
    }

    @PutMapping("/Group/Update")
    public String updateGroup(@RequestBody LearningGroup LearningGroup) {
        this.GroupService.updateGroup(LearningGroup);
        return "[]";
    }

    @DeleteMapping("/Group/DeleteBy/{id}")
    public String deleteGroup(@PathVariable Integer id) {
        this.GroupService.deleteGroup(id);
        return "[]";
    }
}
