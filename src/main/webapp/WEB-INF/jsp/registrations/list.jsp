<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Registrations | EduReg</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<%@ include file="/WEB-INF/jsp/common/nav.jsp" %>

<div class="container">
    <div class="page-header">
        <h1>Course Registrations</h1>
        <a href="/registrations/register" class="btn btn-primary">+ New Enrollment</a>
    </div>

    <div class="card">
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <%-- IMPROVEMENT: show names, not raw IDs --%>
                    <th>Student</th>
                    <th>Course</th>
                    <th>Type</th>
                    <th>Date</th>
                    <th>Fee (LKR)</th>
                    <th>Status</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="r" items="${registrations}">
                    <tr>
                        <td><code style="font-size:0.75rem;">${r.id}</code></td>
                        <td><strong>${r.studentName}</strong><br><small style="color:var(--secondary);">${r.studentId}</small></td>
                        <td>${r.courseTitle}<br><small style="color:var(--secondary);">${r.courseId}</small></td>
                        <td><small>${r.studentType}</small></td>
                        <td>${r.registrationDate}</td>
                        <td><strong>${r.registrationFee}</strong></td>
                        <td>
                            <span class="badge ${r.status == 'ENROLLED' ? 'badge-success' : 'badge-dropped'}">
                                ${r.status}
                            </span>
                        </td>
                        <td>
                            <%-- NEW: Drop action (only for enrolled) --%>
                            <c:if test="${r.status == 'ENROLLED'}">
                                <a href="/registrations/drop/${r.id}" style="color:#f59e0b;"
                                   onclick="return confirm('Drop this course registration?')">Drop</a>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty registrations}">
                    <tr><td colspan="8" style="text-align:center;padding:2rem;">No registrations yet.</td></tr>
                </c:if>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
