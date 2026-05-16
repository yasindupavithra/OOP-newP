<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Courses | EduReg</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
    <nav>
        <div class="container">
            <div style="font-size: 1.5rem; font-weight: bold; color: var(--primary);">EduReg System</div>
            <div>
                <a href="/">Home</a>
                <a href="/courses">Courses</a>
                <a href="/users">Users</a>
                <a href="/registrations">Registrations</a>
            </div>
        </div>
    </nav>

    <div class="container">
        <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 2rem;">
            <h1>Course Catalog</h1>
            <a href="/courses/add" class="btn btn-primary">+ Add New Course</a>
        </div>

        <div class="card">
            <table>
                <thead>
                    <tr>
                        <th>Course Code</th>
                        <th>Title</th>
                        <th>Instructor</th>
                        <th>Credits</th>
                        <th>Type</th>
                        <th>Status</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="course" items="${courses}">
                        <tr>
                            <td><code>${course.courseCode}</code></td>
                            <td><strong>${course.title}</strong></td>
                            <td>${course.instructor}</td>
                            <td>${course.credits}</td>
                            <td>
                                <span class="badge ${course.courseType == 'ONLINE' ? 'badge-warning' : 'badge-success'}">
                                    ${course.courseType}
                                </span>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${course.openForRegistration}">
                                        <span style="color: #166534;">Open</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span style="color: #991b1b;">Closed</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <a href="/courses/delete/${course.id}" style="color: #ef4444;" onclick="return confirm('Delete this course?')">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>
