<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Users | EduReg</title>
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
                        <th>Description</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="user" items="${users}">
                        <tr>
                            <td><strong>${user.username}</strong></td>
                            <td>${user.email}</td>
                            <td>
                                <span class="badge ${user.userType == 'ADMIN' ? 'badge-warning' : 'badge-success'}">
                                    ${user.userType}
                                </span>
                            </td>
                            <td><small>${user.roleDescription}</small></td>
                            <td>
                                <a href="/users/delete/${user.id}" style="color: #ef4444;" onclick="return confirm('Delete this user?')">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>
