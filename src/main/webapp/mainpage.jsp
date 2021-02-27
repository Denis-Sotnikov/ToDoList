<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="ru.job4j.todolist.store.HbmToDoList" %>
<%@ page import="ru.job4j.todolist.model.User" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>AJAX</title>
</head>
<%
    User userMain = new User();
    HttpSession sc = request.getSession();
    if (sc.getAttribute("user") != null) {
        userMain = (User) sc.getAttribute("user");
    } else {
        response.sendRedirect(request.getContextPath() + "/index.do");
    }
%>
<body>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
    function sendGreeting() {
        for (var i = document.getElementById('table').getElementsByTagName('tr').length -1; i; i--) {
            document.getElementById('table').deleteRow(i);
        }

        $.ajax({
            type: 'GET',
            url: 'http://localhost:8080/todolist/task.do',
            data: {
                'description': $('#description').val(), 'author': $('#author').val()
            },
            dataType: 'json'
        }).done(function (data) {

            var trHTML = '';
            var str = '';
            $.each(data, function (i, item) {
                if (document.getElementById('box').checked) { // Включен
                    if (item.done === true) {
                        trHTML += '<tr><td>' + item.id + '</td><td>' + item.description + '</td><td>' + item.created + '</td><td>' + '<input id="gal" type="checkbox" checked></td><td>' + item.author + '</td></tr>';
                    } else {trHTML += '<tr><td>' + item.id + '</td><td>' + item.description + '</td><td>' + item.created + '</td><td>' + '<input id="gal" type="checkbox"></td><td>' + item.author + '</td></tr>';}
                } else {
                    if (item.done === false) {
                        trHTML += '<tr><td>' + item.id + '</td><td>' + item.description + '</td><td>' + item.created + '</td><td>' + '<input id="gal" type="checkbox"></td><td>' + item.author + '</td></tr>';
                    }
                }
            });
            $('#table').append(trHTML);
        }).fail(function (err) {
            alert(err)
        });
    }
</script>

<div style="width: 50%; padding-left: 30px; padding-top: 30px" ;  >
    <div class="row">
        <ul class="nav">
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/autorization.jsp"> <c:out value="<%=userMain.getName()%>"/> | Выйти</a>
            </li>
        </ul>
    </div>
    <form>
        <div class="form-group">
            <label for="description">Добавить новое задание</label><br>
            <textarea name = "description" class="form-control" id="description"> </textarea>
        </div>
        <div class="form-group">
            <label for="box">Показать все задачи</label>
            <input  type="checkbox" style="padding-left: 10px" name = "box" id="box" value="Показать все задачи">
        </div>
        <div class="form-group">
            <textarea name = "author" class="form-control" id="author" value=<c:out value="<%=userMain.getName()%>"/>><c:out value="<%=userMain.getName()%>"/></textarea>
        </div>
        <button type="button" class="btn btn-primary" onclick="sendGreeting()" id = "bt">Submit</button>
    </form>

    <br>
    <label for="table">Список заданий</label>
    <table class="table" id="table">
        <thead>
        <tr>
            <th>ID</th>
            <th>Task</th>
            <th>Created</th>
            <th>Done</th>
            <th>Author</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>
</body>
</html>