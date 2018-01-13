<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Todos for: ${name}</title>
    <link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
          rel="stylesheet">
</head>
<body>
<div class="container">

<table class="table table-striped">
    <caption>Your todos are</caption>
    <thead>
    <tr>
        <th>Description</th>
        <th>Target Date</th>
        <th>Status</th>
        <th>Update</th>
        <th>Delete</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${todos}" var="todo">
    <tr>
        <td>${todo.desc}</td>
        <td>${todo.targetDate}</td>
        <td>${todo.done}</td>
        <td><a href="/update-todo?id=${todo.id}" type="button" class="btn btn-success">Update</a></td>
        <td><a href="/delete-todo?id=${todo.id}" type="button" class="btn btn-warning">Delete</a> </td>
    </tr>
    </c:forEach>
    </tbody>
</table>

<div>
<a class="button" href="/add-todo">Add todo</a>
</div>

<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</div>
</body>
</html>