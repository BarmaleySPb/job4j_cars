<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="scripts.js"></script>
    <title>CARS</title>
</head>

<body>

<div class="container">

    <c:if test="${user.name == null}">
        <a style="float: left" class="nav-link" href="<%=request.getContextPath()%>/login.jsp">Login</a>
    </c:if>
    <c:if test="${user.name != null}">
        <a style="float: left" class="nav-link" href="<%=request.getContextPath()%>/advert.jsp">Add advert</a>
    </c:if>
    <a style="float: right" class="nav-link" href="<%=request.getContextPath()%>/logout.do"> <c:out value="${user.name}"/>  | Log out</a>

</div>

<br>

<c:if test="${user.name != null}">
<div class="container pt-3">
    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header">
                Adverts
            </div>
            <div class="card-body">
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">Car</th>
                        <th scope="col">Type engine</th>
                        <th scope="col">Description</th>
                        <th scope="col">Status</th>
                        <th scope="col">Phone number</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${adverts}" var="advert">
                        <tr>
                            <td>
                                <c:out value="${advert.car.name}"/>
                            </td>
                            <td>
                                <c:out value="${advert.car.engine.name}"/>
                            </td>
                            <td>
                                <c:out value="${advert.description}"/>
                            </td>
                            <td>
                                <c:if test="${advert.active == true}">
                                    <c:out value="продается"/>
                                </c:if>
                                <c:if test="${advert.active != true}">
                                    <c:out value="продана"/>
                                </c:if>
                            </td>
                            <td>
                                <c:out value="${advert.ownerPhoneNumber}"/>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</c:if>

</body>
</html>