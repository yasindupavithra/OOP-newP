<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Library System | Home</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
    <nav>
        <div class="container">
            <div style="font-size: 1.5rem; font-weight: bold; color: var(--primary);">LibSystem</div>
            <div>
                <a href="/">Home</a>
                <a href="/books">Books</a>
                <a href="/users">Users</a>
                <a href="/transactions">Borrowings</a>
            </div>
        </div>
    </nav>

    <div class="container">
        <div class="card" style="text-align: center; padding: 4rem 2rem;">
            <h1 style="font-size: 2.5rem; margin-bottom: 1rem;">Welcome to the Digital Library</h1>
            <p style="color: var(--secondary); font-size: 1.25rem; margin-bottom: 2rem;">Manage your books, users, and borrowing history with ease.</p>
            <div style="display: flex; gap: 1rem; justify-content: center;">
                <a href="/books" class="btn btn-primary">Browse Catalog</a>
                <a href="/users/register" class="btn" style="background: var(--secondary); color: white;">Join Now</a>
            </div>
        </div>

        <div style="display: grid; grid-template-columns: repeat(auto-fit, minmax(300px, 1fr)); gap: 1.5rem;">
            <div class="card">
                <h3>📚 Book Management</h3>
                <p>Add, edit, and categorize books into Printed or E-Book formats.</p>
                <a href="/books" style="color: var(--primary); font-weight: 500;">Go to Books &rarr;</a>
            </div>
            <div class="card">
                <h3>👥 User Management</h3>
                <p>Register new users and manage admin privileges across the system.</p>
                <a href="/users" style="color: var(--primary); font-weight: 500;">Go to Users &rarr;</a>
            </div>
            <div class="card">
                <h3>🔄 Borrowing Records</h3>
                <p>Track book availability, due dates, and calculate late return fines.</p>
                <a href="/transactions" style="color: var(--primary); font-weight: 500;">Go to Records &rarr;</a>
            </div>
        </div>
    </div>
</body>
</html>
