<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Users | EduReg</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<%@ include file="/WEB-INF/jsp/common/nav.jsp" %>

<div class="container">
    <div class="page-header">
        <h1>User Management</h1>
        <a href="/users/register" class="btn btn-primary">+ Register User</a>
    </div>

    <div class="card">
        <table>
            <thead>
                <tr>
                    <th>Username</th>
                    <th>Email</th>
                    <th>Type</th>
                    <th>Extra Info</th>
                    <th>Role Description</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="user" items="${users}">
                    <tr>
                        <td><strong>${user.username}</strong></td>
                        <td>${user.email}</td>
                        <td>
                            <span class="badge
                                ${user.userType == 'ADMIN' ? 'badge-warning' :
                                  user.userType == 'INSTRUCTOR' ? 'badge-info' : 'badge-success'}">
                                ${user.userType}
                            </span>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${user.userType == 'STUDENT'}">
                                    <small>${user.degreeProgram}</small>
                                </c:when>
                                <c:when test="${user.userType == 'INSTRUCTOR'}">
                                    <small>${user.department}</small>
                                </c:when>
                                <c:otherwise>-</c:otherwise>
                            </c:choose>
                        </td>
                        <td><small>${user.roleDescription}</small></td>
                        <td>
                            <a href="/users/delete/${user.id}" style="color:#ef4444;"
                               onclick="return confirm('Delete this user?')">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty users}">
                    <tr><td colspan="6" style="text-align:center;padding:2rem;">No users registered yet.</td></tr>
                </c:if>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
