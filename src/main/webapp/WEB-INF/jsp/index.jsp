<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Course System | Home</title>
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
        <div class="card" style="text-align: center; padding: 4rem 2rem;">
            <h1 style="font-size: 2.5rem; margin-bottom: 1rem;">Student Course Registration System</h1>
            <p style="color: var(--secondary); font-size: 1.25rem; margin-bottom: 2rem;">Streamline your academic journey with our modern course management platform.</p>
            <div style="display: flex; gap: 1rem; justify-content: center;">
                <a href="/courses" class="btn btn-primary">Browse Courses</a>
                <a href="/users/register" class="btn" style="background: var(--secondary); color: white;">Get Started</a>
            </div>
        </div>

        <div style="display: grid; grid-template-columns: repeat(auto-fit, minmax(300px, 1fr)); gap: 1.5rem;">
            <div class="card">
                <h3>📚 Course Catalog</h3>
                <p>Explore a wide range of Online and Onsite courses tailored for your career.</p>
                <a href="/courses" style="color: var(--primary); font-weight: 500;">View Courses &rarr;</a>
            </div>
            <div class="card">
                <h3>👥 Student & Admin Hub</h3>
                <p>Manage student profiles and administrative settings with ease.</p>
                <a href="/users" style="color: var(--primary); font-weight: 500;">Manage Users &rarr;</a>
            </div>
            <div class="card">
                <h3>🔄 Enrollment History</h3>
                <p>Track your course registrations, fees, and enrollment status in real-time.</p>
                <a href="/registrations" style="color: var(--primary); font-weight: 500;">View Registrations &rarr;</a>
            </div>
        </div>
    </div>
</body>
</html>
