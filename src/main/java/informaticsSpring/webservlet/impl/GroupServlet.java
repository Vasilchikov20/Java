package informaticsSpring.webservlet.impl;

import informaticsSpring.entity.LearningGroup;
import informaticsSpring.service.impl.GroupServiceImpl;
import informaticsSpring.webservlet.GenericServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@WebServlet("/Group")
public class GroupServlet extends GenericServlet {
    private final GroupServiceImpl GroupService;

    public GroupServlet(GroupServiceImpl GroupService) {
        this.GroupService = GroupService;
    }

    @Override
    protected String getUrl() {
        return "Group";
    }

    @Override
    protected String[] getIndex() {
        return new String[]{"", "active", ""};
    }

    @Override
    protected String[] getLabels() {
        return new String[]{"course", "number"};
    }

    @Override
    protected String[][] fetchData(HttpServletRequest request) {
        var data = GroupService.getGroups();
        if (request.getParameter("id") != null && !request.getParameter("id").equals("")) {
            data = data.stream().filter(x -> x.getId() == Integer.parseInt(request.getParameter("id"))).
                    collect(Collectors.toList());
        }
        if (request.getParameter("1") != null && !request.getParameter("1").equals("")) {
            data = data.stream().filter(x -> x.getCourseValue() == Integer.parseInt(request.getParameter("1"))).
                    collect(Collectors.toList());
        }
        if (request.getParameter("2") != null && !request.getParameter("2").equals("")) {
            data = data.stream().filter(x -> x.getNumberValue() == Integer.parseInt(request.getParameter("2"))).
                    collect(Collectors.toList());
        }
        return data.stream().map(x -> new String[]{
                String.valueOf(x.getId()),
                String.valueOf(x.getCourseValue()),
                String.valueOf(x.getNumberValue())}).toArray(String[][]::new);
    }

    @Override
    protected void insert(HttpServletRequest request) {
        var Group = new LearningGroup();
        Group.setCourseValue(Integer.parseInt(request.getParameter("1")));
        Group.setNumberValue(Integer.parseInt(request.getParameter("2")));
        GroupService.createGroup(Group);
    }

    @Override
    protected void update(HttpServletRequest request) {
        var Group = GroupService.getGroupById(Integer.parseInt(request.getParameter("id")));
        if (Group == null) {
            throw new NoSuchElementException();
        }
        Group.setCourseValue(Integer.parseInt(request.getParameter("1")));
        Group.setNumberValue(Integer.parseInt(request.getParameter("2")));
        GroupService.updateGroup(Group);
    }

    @Override
    protected void delete(HttpServletRequest request) {
        var Group = GroupService.getGroupById(Integer.parseInt(request.getParameter("id")));
        if (Group == null) {
            throw new NoSuchElementException();
        }
        GroupService.deleteGroup(Group.getId());
    }
}
