<%@include file="common/header.jspf" %>
<%@include file="common/navigation.jspf" %>

<div class="container">

    ${message}
    <form method="post">
        name: <input type="text" name="name"/>
        password: <input type="password" name="password"/>
        <input type="submit"/>
    </form>

    My First JSP! ${name} !

</div>

<%@include file="common/footer.jspf" %>