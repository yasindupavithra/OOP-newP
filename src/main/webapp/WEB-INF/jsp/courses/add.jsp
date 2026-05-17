<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Course | EduReg</title>
    <link rel="stylesheet" href="/css/style.css">
    <script>
        function toggleFields() {
            const type = document.getElementById('type').value;
            document.getElementById('onsite-fields').style.display = type === 'ONSITE' ? 'block' : 'none';
            document.getElementById('online-fields').style.display  = type === 'ONLINE'  ? 'block' : 'none';
        }
        function syncInstructor() {
            const sel = document.getElementById('instructorSelect');
            document.getElementById('instructorId').value   = sel.value;
            document.getElementById('instructorName').value = sel.options[sel.selectedIndex].text;
        }
    </script>
</head>
<body>
<%@ include file="/WEB-INF/jsp/common/nav.jsp" %>

<div class="container" style="max-width:640px;">
    <div class="card">
        <h2>Add New Course</h2>
        <form action="/courses/add" method="post">
            <div class="form-group">
                <label>Course Title</label>
                <input type="text" name="title" required>
            </div>
            <div class="form-group">
                <label>Course Code</label>
                <input type="text" name="code" placeholder="e.g. CS101" required>
            </div>
            <div class="form-group">
                <label>Credits</label>
                <input type="number" name="credits" value="3" min="1" max="6">
            </div>
            <div class="form-group">
                <label>Max Capacity (seats)</label>
                <input type="number" name="maxCapacity" value="30" min="1" max="500">
            </div>

            <%-- NEW: Instructor picker from registered instructors --%>
            <div class="form-group">
                <label>Instructor</label>
                <c:choose>
                    <c:when test="${not empty instructors}">
                        <select id="instructorSelect" onchange="syncInstructor()">
                            <c:forEach var="inst" items="${instructors}">
                                <option value="${inst.id}">${inst.username}</option>
                            </c:forEach>
                        </select>
                        <input type="hidden" name="instructorId"   id="instructorId"   value="${instructors[0].id}">
                        <input type="hidden" name="instructorName" id="instructorName" value="${instructors[0].username}">
                    </c:when>
                    <c:otherwise>
                        <input type="text"   name="instructorName" placeholder="Enter instructor name" required>
                        <input type="hidden" name="instructorId"   value="unassigned">
                        <small style="color:var(--secondary);">No instructors registered yet. <a href="/users/register">Register one first.</a></small>
                    </c:otherwise>
                </c:choose>
            </div>

            <div class="form-group">
                <label>Course Type</label>
                <select name="type" id="type" onchange="toggleFields()">
                    <option value="ONSITE">On-Site</option>
                    <option value="ONLINE">Online</option>
                </select>
            </div>

            <div id="onsite-fields">
                <div class="form-group">
                    <label>Room Number</label>
                    <input type="text" name="room">
                </div>
                <div class="form-group">
                    <label>Campus Location</label>
                    <input type="text" name="location">
                </div>
            </div>

            <div id="online-fields" style="display:none;">
                <div class="form-group">
                    <label>Platform (Zoom / Teams)</label>
                    <input type="text" name="platform">
                </div>
                <div class="form-group">
                    <label>Meeting Link</label>
                    <input type="text" name="link">
                </div>
            </div>

            <div style="margin-top:2rem;">
                <button type="submit" class="btn btn-primary">Create Course</button>
                <a href="/courses" class="btn btn-outline">Cancel</a>
            </div>
        </form>
    </div>
</div>
</body>
</html>
