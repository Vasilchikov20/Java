<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${title}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
</head>
<body>
    <h1 align="center">${title}</h1>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
            <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                <div class="navbar-nav">
                    <a class="nav-link" href="/Home">Home</a>
                    <a class="nav-link ${index[0]}" href="/Student">Student</a>
                    <a class="nav-link ${index[1]}" href="/Group">Group</a>
                </div>
            </div>
        </div>
    </nav>
    <c:if test="${error == true}">
        <div class="alert alert-danger" role="alert">
            <p>Input data error.</p>
            <p>Please check your input and try again.</p>
            <c:if test="${not empty message}" >
                <p>Message: ${message}</p>
            </c:if>
        </div>
    </c:if>
    <center>
        <div>
            <p>Для облегчения работы преподавателя необходимо создать приложение для учета сданных задач по предмету «Информатика». 
			<p>Приложение хранит информацию о группах студентов и о составе каждой группы. 
			<p>Каждый студент должен сдать 3 задачи, данные хранятся в виде «сдал/не сдал». 
			<p>Приложение должно выполнять следующие функции: 
			<br/>
			<p>Создание и удаление групп студентов
			<br/>
			<p>Создание и удаление студентов в составе групп
        </div>
    </center>
</body>
</html>
