<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Registrations | EduReg</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
    <nav>
        <div class="container">EduReg System</div>
    </nav>

    <div class="container">
        <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 2rem;">
            <h1>Course Registrations</h1>
            <a href="/registrations/register" class="btn btn-primary">+ New Registration</a>
        </div>

        <div class="card">
            <table>
                <thead>
                    <tr>
                        <th>Registration ID</th>
                        <th>Student ID</th>
                        <th>Course ID</th>
                        <th>Date</th>
                        <th>Fee (LKR)</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="r" items="${registrations}">
                        <tr>
                            <td><code>${r.id}</code></td>
                            <td>${r.studentId}</td>
                            <td>${r.courseId}</td>
                            <td>${r.registrationDate}</td>
                            <td><strong>${r.registrationFee}</strong></td>
                            <td>
                                <span class="badge badge-success">${r.status}</span>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>
