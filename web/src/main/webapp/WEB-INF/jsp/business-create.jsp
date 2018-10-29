<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Create a new business</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/business-create" method="post">
    <label for="name">Название:
        <input type="text" name="name" id="name">
    </label><br>
    <label for="unp">УНП:
        <input type="text" name="unp" id="unp" placeholder="999999999">
    </label><br>
    <input type="submit" value="SAVE">
</form>
</body>
</html>
