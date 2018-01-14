<%@include file="common/header.jspf" %>
<%@include file="common/navigation.jspf" %>

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
                <td><fmt:formatDate value="${todo.targetDate}" pattern="dd/MM/yyyy"/></td>
                <td>${todo.done}</td>
                <td><a href="/update-todo?id=${todo.id}" type="button" class="btn btn-success">Update</a></td>
                <td><a href="/delete-todo?id=${todo.id}" type="button" class="btn btn-warning">Delete</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div>
        <a class="button" href="/add-todo">Add todo</a>
    </div>

<%@include file="common/footer.jspf" %>