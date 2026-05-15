<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Borrow Book | LibSystem</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
    <nav>
        <div class="container">LibSystem</div>
    </nav>

    <div class="container" style="max-width: 500px;">
        <div class="card">
            <h2>Borrow a Book</h2>
            <form action="/transactions/borrow" method="post">
                <div class="form-group">
                    <label>Select User</label>
                    <select name="userId" required>
                        <c:forEach var="user" items="${users}">
                            <option value="${user.id}">${user.username} (${user.id})</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label>Select Book</label>
                    <select name="bookId" required>
                        <c:forEach var="book" items="${books}">
                            <option value="${book.id}">${book.title} [${book.bookType}]</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label>Due Date</label>
                    <input type="date" name="dueDate" required>
                </div>

                <div style="margin-top: 2rem;">
                    <button type="submit" class="btn btn-primary">Process Borrowing</button>
                    <a href="/transactions" class="btn btn-secondary">Cancel</a>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
