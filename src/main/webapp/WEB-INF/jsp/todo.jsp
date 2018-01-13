<html>
<head>
    <title>First web app</title>
    <link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
          rel="stylesheet">
</head>
<body>
<div class="container">
    <form method="post">
        <fieldset class="form-group">
            <label>Description</label>
            <input name="desc" class="form-control" required="required" type="text"/>
        </fieldset>
        <button type= "submit" class="btn btn-success">Add Todo</button>
    </form>
    <script src="webjars/jquery/1.9.1/jquery.min.js"></script>
    <script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</div>
</body>
</html>