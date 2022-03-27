<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous">

    </script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous">

    </script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous">

    </script>
    <script>

        function validate() {
            const email = $('#inputEmail').val();
            const password = $('#inputPassword').val();
            const name = $('#inputName').val();
            const confirmPassword = $('#inputConfirmPassword').val();
            const phoneNumber = $('#inputPhoneNumber').val();

            if (name === '') {
                alert('Please enter your name.');
                return false;
            }
            if (email === '') {
                alert('Please enter your email.');
                return false;
            }
            if (phoneNumber === '') {
                alert('Please enter your phone number.');
                return false;
            }
            if (password === '') {
                alert('Please enter your password.');
                return false;
            }
            if (confirmPassword === '') {
                alert('Please confirm your password.');
                return false;
            }
            if (password !== confirmPassword) {
                alert('The password and password confirmation do not match.');
                return false;
            }

            return true;
        }

    </script>
    <title>CARS</title>
</head>
<body>
<div class="container pt-3">

    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header">
                Registration
            </div>
            <div class="card-body">
                <form action="<%=request.getContextPath()%>/reg.do" method="post">
                    <div class="form-group">
                        <label>Name</label>
                        <input type="text" class="form-control" name="name" id="inputName"
                               placeholder="Enter your name">
                    </div>
                    <div class="form-group">
                        <label>Email</label>
                        <input type="text" class="form-control" name="email" id="inputEmail"
                               placeholder="Enter your email">
                    </div>
                    <div class="form-group">
                        <label>Enter your phone number</label>
                        <input type="text" class="form-control" name="phoneNumber" id="inputPhoneNumber">
                    </div>
                    <div class="form-group">
                        <label>Password</label>
                        <input type="password" class="form-control" name="password" id="inputPassword"
                               placeholder="Enter your password">
                    </div>
                    <div class="form-group">
                        <label>Confirm password</label>
                        <input type="password" class="form-control" name="password" id="inputConfirmPassword"
                               placeholder="Enter your password">
                    </div>
                    <button type="submit" class="btn btn-primary" onclick="return validate();">Confirm</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
