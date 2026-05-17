<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Enroll | EduReg</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<%@ include file="/WEB-INF/jsp/common/nav.jsp" %>

<div class="container" style="max-width:520px;">
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
                <label>Student Type <small style="color:var(--secondary);">(affects fee)</small></label>
                <select name="studentType">
                    <option value="REGULAR">Regular (LKR 5,000)</option>
                    <option value="INTERNATIONAL">International (LKR 12,500)</option>
                </select>
            </div>
            <div style="margin-top:2rem;">
                <button type="submit" class="btn btn-primary">Complete Enrollment</button>
                <a href="/registrations" class="btn btn-outline">Cancel</a>
            </div>
        </form>
    </div>
</div>
</body>
</html>
