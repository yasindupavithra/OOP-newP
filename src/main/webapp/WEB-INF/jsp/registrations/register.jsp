<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Course Registration | EduReg</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
    <nav>
        <div class="container">EduReg System</div>
    </nav>

    <div class="container" style="max-width: 500px;">
        <div class="card">
            <h2>Enroll in a Course</h2>
            <form action="/registrations/register" method="post">
                <div class="form-group">
                    <label>Select Student</label>
                    <select name="studentId" required>
                        <c:forEach var="student" items="${students}">
                            <option value="${student.id}">${student.username} (${student.id})</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label>Select Course</label>
                    <select name="courseId" required>
                        <c:forEach var="course" items="${courses}">
                            <option value="${course.id}">${course.title} [${course.courseCode}]</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label>Student Type (For Fee Calculation)</label>
                    <select name="studentType">
                        <option value="REGULAR">Regular Student</option>
                        <option value="INTERNATIONAL">International Student</option>
                    </select>
                </div>

                <div style="margin-top: 2rem;">
                    <button type="submit" class="btn btn-primary">Complete Registration</button>
                    <a href="/registrations" class="btn btn-secondary">Cancel</a>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
