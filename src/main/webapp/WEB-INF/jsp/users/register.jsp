<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register | EduReg</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
    <nav>
        <div class="container">EduReg System</div>
    </nav>

    <div class="container" style="max-width: 500px;">
        <div class="card">
            <h2>User Registration</h2>
            <form action="/users/register" method="post">
                <div class="form-group">
                    <label>Username</label>
                    <input type="text" name="username" required>
                </div>
                <div class="form-group">
                    <label>Email</label>
                    <input type="email" name="email" required>
                </div>
                <div class="form-group">
                    <label>Password</label>
                    <input type="password" name="password" required>
                </div>
                <div class="form-group">
                    <label>User Type</label>
                    <select name="type" id="type" onchange="document.getElementById('student-group').style.display = this.value === 'STUDENT' ? 'block' : 'none'">
                        <option value="STUDENT">Student</option>
                        <option value="ADMIN">Administrator</option>
                    </select>
                </div>

                <div id="student-group" class="form-group">
                    <label>Degree Program</label>
                    <input type="text" name="program" placeholder="e.g. B.Sc. in Computer Science">
                </div>

                <div style="margin-top: 2rem;">
                    <button type="submit" class="btn btn-primary">Create Account</button>
                    <a href="/users" class="btn btn-secondary">Cancel</a>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
