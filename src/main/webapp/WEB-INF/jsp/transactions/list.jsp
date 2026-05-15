<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Borrowing Records | LibSystem</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
    <nav>
        <div class="container"> LibSystem </div>
    </nav>

    <div class="container">
        <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 2rem;">
            <h1>Borrowing Records</h1>
            <a href="/transactions/borrow" class="btn btn-primary">+ New Borrowing</a>
        </div>

        <div class="card">
            <table>
                <thead>
                    <tr>
                        <th>User ID</th>
                        <th>Book ID</th>
                        <th>Borrow Date</th>
                        <th>Due Date</th>
                        <th>Return Date</th>
                        <th>Fine</th>
                        <th>Status</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="t" items="${transactions}">
                        <tr>
                            <td>${t.userId}</td>
                            <td>${t.bookId}</td>
                            <td>${t.borrowDate}</td>
                            <td>${t.dueDate}</td>
                            <td>${t.returnDate == null ? '-' : t.returnDate}</td>
                            <td><span style="color: ${t.fineAmount > 0 ? '#ef4444' : 'inherit'}">LKR ${t.fineAmount}</span></td>
                            <td>
                                <span class="badge ${t.completed ? 'badge-success' : 'badge-warning'}">
                                    ${t.completed ? 'Returned' : 'Active'}
                                </span>
                            </td>
                            <td>
                                <c:if test="${!t.completed}">
                                    <a href="/transactions/return/${t.id}" style="color: var(--primary);">Mark as Returned</a>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>
