<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Assign Grade | EduReg</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<%@ include file="/WEB-INF/jsp/common/nav.jsp" %>

<div class="container" style="max-width:520px;">
    <div class="card">
        <h2>Assign Grade</h2>
        <c:choose>
            <c:when test="${empty registrations}">
                <p style="color:var(--secondary);">All enrolled students already have grades assigned,
                   or there are no enrollments yet.</p>
                <a href="/registrations" class="btn btn-outline">View Registrations</a>
            </c:when>
            <c:otherwise>
                <form action="/grades/assign" method="post">
                    <div class="form-group">
                        <label>Select Enrolled Student / Course</label>
                        <select name="registrationId" required>
                            <c:forEach var="r" items="${registrations}">
                                <option value="${r.id}">${r.studentName} — ${r.courseTitle}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>Grade (0 – 100)</label>
                        <input type="number" name="gradeValue" min="0" max="100" step="0.5" required
                               placeholder="e.g. 85">
                        <small style="color:var(--secondary);">A≥90 &nbsp; B≥80 &nbsp; C≥70 &nbsp; D≥60 &nbsp; F&lt;60</small>
                    </div>
                    <div style="margin-top:2rem;">
                        <button type="submit" class="btn btn-primary">Submit Grade</button>
                        <a href="/grades" class="btn btn-outline">Cancel</a>
                    </div>
                </form>
            </c:otherwise>
        </c:choose>
    </div>
</div>
</body>
</html>
