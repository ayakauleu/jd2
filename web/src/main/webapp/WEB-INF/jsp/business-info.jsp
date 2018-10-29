<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Business info</title>
</head>
<body>
<div>
    <p>Id: ${requestScope.business.id}</p>
    <p>Name: ${requestScope.business.name}</p>
    <p>UNP: ${requestScope.business.unp}</p>
</div>
</body>
</html>
