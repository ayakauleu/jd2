<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Payments filter</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/payments" method="post">
    <label for="datefrom">
        <input type="date" name="datefrom" value = "${requestScope.datefrom}" id="datefrom">
    </label> -
    <label for="datetill">
        <input type="date" value = "${requestScope.datetill}" name="datetill" id="datetill">
    </label>
    <input type="radio" name="type" value="0"> Transfer
    <input type="radio" name="type" value="1" checked> Payment
    <select name="currency" id="currency">
        <c:forEach var="currency" items="${requestScope.currencies}">
            <option value="${currency.value}">${currency.value}</option>
        </c:forEach>
    </select>
    <br>
    <label for="limit"> Page length
        <input type="text" name="limit" id="limit" value="${requestScope.limit}" >
    </label>
    <label for="offset">  Page number
        <input type="text" name="offset" id="offset" value="${requestScope.offset}" >
    </label>
     <input type="submit" value="Display">
</form>

<br><br>
<c:forEach var="payment" items="${requestScope.payments}">
    <a href="${pageContext.request.contextPath}/payment-info?id=${payment.id}">
            ${payment.paymentDate}
            ${payment.paymentType}
            ${payment.currency}
            ${payment.amount}
            ${payment.params}
    </a><br>
</c:forEach>
</body>
</html>