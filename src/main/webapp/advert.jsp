<%@ page import="cars.store.HbmStore" %>
<%@ page import="cars.models.Engine" %>
<%@ page import="cars.models.CarBody" %>
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

    <a style="float: right" class="nav-link" href="<%=request.getContextPath()%>/logout.do"> <c:out value="${user.name}"/>  | Log out</a>

    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header">
                Add advert
            </div>
            <div class="card-body">
                <form action="<%=request.getContextPath()%>/addadvert" method="post">
                    <div class="form-group">
                        <label>Car Model</label>
                        <input type="text" class="form-control" name="carModel" id="inputCarModel"
                               placeholder="Enter model of your car">
                    </div>
                    <div class="form-group">
                        <label>Select engine type</label>
                        <select class="form-control" name="carEngine" id="carEngine">

                            <% for (Engine engine : HbmStore.instOf().findAll(Engine.class)) { %>
                            <option value="<%=engine.getId()%>"><%=engine.getName()%></option>
                            <% } %>

                        </select>
                    </div>
                    <div class="form-group">
                        <label>Select body type</label>
                        <select class="form-control" name="carBody" id="carBody">

                            <% for (CarBody body : HbmStore.instOf().findAll(CarBody.class)) { %>
                            <option value="<%=body.getId()%>"><%=body.getName()%></option>
                            <% } %>

                        </select>
                    </div>
                    <div class="form-group">
                        <label>Description</label>
                        <input type="text" class="form-control" name="carDescription" id="inputCarDescription"
                               placeholder="Enter description">
                    </div>
                    <button type="submit" class="btn btn-primary" onclick="return validateAdvert();">Confirm</button>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>