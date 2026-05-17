<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Courses | EduReg</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<%@ include file="/WEB-INF/jsp/common/nav.jsp" %>

<div class="container">
    <div class="page-header">
        <h1>Course Catalog</h1>
        <div style="display:flex;gap:1rem;align-items:center;">
            <form action="/courses" method="get" style="display:flex;gap:0.5rem;">
                <input type="text" name="search" value="${search}" placeholder="Search code or title...">
                <button type="submit" class="btn btn-primary">Search</button>
                <c:if test="${not empty search}">
                    <a href="/courses" class="btn btn-outline">Clear</a>
                </c:if>
            </form>
            <a href="/courses/add" class="btn btn-primary">+ Add Course</a>
        </div>
    </div>

    <div class="card">
        <table>
            <thead>
                <tr>
                    <th>Code</th>
                    <th>Title</th>
                    <th>Instructor</th>
                    <th>Credits</th>
                    <th>Type</th>
                    <th>Capacity</th>
                    <th>Status</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="course" items="${courses}">
                    <c:set var="enrolled" value="${requestScope['enrolled_'.concat(course.id)]}" />
                    <tr>
                        <td><code>${course.courseCode}</code></td>
                        <td><strong>${course.title}</strong></td>
                        <td>${course.instructorName}</td>
                        <td>${course.credits}</td>
                        <td>
                            <span class="badge ${course.courseType == 'ONLINE' ? 'badge-warning' : 'badge-success'}">
                                ${course.courseType}
                            </span>
                        </td>
                        <%-- NEW: seat capacity display --%>
                        <td>
                            <c:choose>
                                <c:when test="${enrolled >= course.maxCapacity}">
                                    <span style="color:#ef4444;font-weight:600;">${enrolled}/${course.maxCapacity} FULL</span>
                                </c:when>
                                <c:otherwise>
                                    ${enrolled}/${course.maxCapacity}
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${course.openForRegistration}">
                                    <span style="color:#166534;">Open</span>
                                </c:when>
                                <c:otherwise>
                                    <span style="color:#991b1b;">Closed</span>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <a href="/courses/delete/${course.id}" style="color:#ef4444;"
                               onclick="return confirm('Delete this course?')">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty courses}">
                    <tr>
                        <td colspan="8" style="text-align:center;padding:2rem;color:var(--secondary);">
                            No courses found.
                        </td>
                    </tr>
                </c:if>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
