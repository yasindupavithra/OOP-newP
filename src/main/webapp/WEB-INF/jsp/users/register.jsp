<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register User | EduReg</title>
    <link rel="stylesheet" href="/css/style.css">
    <script>
        function onTypeChange() {
            const type = document.getElementById('type').value;
            document.getElementById('student-group').style.display     = type === 'STUDENT'    ? 'block' : 'none';
            document.getElementById('instructor-group').style.display  = type === 'INSTRUCTOR' ? 'block' : 'none';
        }
    </script>
</head>
<body>
<%@ include file="/WEB-INF/jsp/common/nav.jsp" %>

<div class="container" style="max-width:520px;">
    <div class="card">
        <h2>Register User</h2>
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
                <select name="type" id="type" onchange="onTypeChange()">
                    <option value="STUDENT">Student</option>
                    <option value="INSTRUCTOR">Instructor</option>
                    <option value="ADMIN">Administrator</option>
                </select>
            </div>

            <div id="student-group" class="form-group">
                <label>Degree Program</label>
                <input type="text" name="program" placeholder="e.g. B.Sc. Computer Science">
            </div>

            <%-- NEW: Instructor department field --%>
            <div id="instructor-group" class="form-group" style="display:none;">
                <label>Department</label>
                <input type="text" name="department" placeholder="e.g. Faculty of Engineering">
            </div>

            <div style="margin-top:2rem;">
                <button type="submit" class="btn btn-primary">Create Account</button>
                <a href="/users" class="btn btn-outline">Cancel</a>
            </div>
        </form>
    </div>
</div>
</body>
</html>
