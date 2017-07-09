<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="QrCode page">
    <meta name="author" content="Nicolas Mauger">

    <title>Create a QR-code</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

<div class="container">

    <c:if test="${pageContext.request.userPrincipal.name != null}">
    <form:form modelAttribute="qrCodeForm" method="POST" class="form-signin">
    <h2 class="form-heading">Create a QR-code</h2>
        <div class="form-group">
        <spring:bind path="url">
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:input id="url" type="text" path="url" placeholder="URL" class="form-control" />
            <form:errors path="url"></form:errors>
        </div>
        </spring:bind>
        <input type="submit" class="btn btn-lg btn-primary btn-block" value="Generate QR-code" />
    </div>
    </form:form>
    </c:if>
    <c:if test="${pageContext.request.userPrincipal.name == null}">
        <h2>Please <a href="${contextPath}/login">log in</a> if you want to use this service</h2>
    </c:if>
</div>
<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
</body>
</html>