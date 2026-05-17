<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<nav>
    <div class="container nav-inner">
        <div class="nav-brand">EduReg System</div>
        <div class="nav-links">
            <a href="/">Home</a>
            <a href="/courses">Courses</a>
            <a href="/users">Users</a>
            <a href="/registrations">Registrations</a>
            <a href="/grades">Grades</a>
        </div>
    </div>
</nav>
<%-- Flash messages (shown on any page after redirect) --%>
<div class="container" style="margin-bottom:0;padding-bottom:0;">
    <c:if test="${not empty success}">
        <div class="alert alert-success">${success}</div>
    </c:if>
    <c:if test="${not empty error}">
        <div class="alert alert-error">${error}</div>
    </c:if>
</div>
