<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Grades | EduReg</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<%@ include file="/WEB-INF/jsp/common/nav.jsp" %>

<div class="container">
    <div class="page-header">
        <h1>Grade Book</h1>
        <a href="/grades/assign" class="btn btn-primary">+ Assign Grade</a>
    </div>

    <div class="card">
        <table>
            <thead>
                <tr>
                    <th>Student</th>
                    <th>Course</th>
                    <th>Score</th>
                    <th>Letter Grade</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="g" items="${grades}">
                    <tr>
                        <td><strong>${g.studentName}</strong></td>
                        <td>${g.courseTitle}</td>
                        <td>${g.gradeValue}%</td>
                        <td>
                            <span class="badge
                                ${g.letterGrade == 'A' ? 'badge-success' :
                                  g.letterGrade == 'B' ? 'badge-info'    :
                                  g.letterGrade == 'C' ? 'badge-warning' : 'badge-danger'}">
                                ${g.letterGrade}
                            </span>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty grades}">
                    <tr><td colspan="4" style="text-align:center;padding:2rem;">No grades assigned yet.</td></tr>
                </c:if>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
