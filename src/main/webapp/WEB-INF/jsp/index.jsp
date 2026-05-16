<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Course System | Dashboard</title>
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
        <div class="card" style="text-align: center; padding: 3rem 2rem; background: linear-gradient(135deg, #ffffff 0%, #f1f5f9 100%);">
            <h1 style="font-size: 2.5rem; margin-bottom: 0.5rem; color: var(--primary);">Academic Dashboard</h1>
            <p style="color: var(--secondary); font-size: 1.1rem; margin-bottom: 2rem;">Welcome to the Student Course Registration System. Monitor system activity at a glance.</p>
            
            <!-- Supiri Statistics Section -->
            <div style="display: grid; grid-template-columns: repeat(auto-fit, minmax(200px, 1fr)); gap: 1.5rem; margin-bottom: 2rem;">
                <div class="card" style="border-top: 4px solid var(--primary);">
                    <div style="font-size: 0.875rem; color: var(--secondary); text-transform: uppercase; font-weight: 600;">Students</div>
                    <div style="font-size: 2rem; font-weight: bold; margin-top: 0.5rem;">${totalStudents}</div>
                </div>
                <div class="card" style="border-top: 4px solid #10b981;">
                    <div style="font-size: 0.875rem; color: var(--secondary); text-transform: uppercase; font-weight: 600;">Courses</div>
                    <div style="font-size: 2rem; font-weight: bold; margin-top: 0.5rem;">${totalCourses}</div>
                </div>
                <div class="card" style="border-top: 4px solid #f59e0b;">
                    <div style="font-size: 0.875rem; color: var(--secondary); text-transform: uppercase; font-weight: 600;">Registrations</div>
                    <div style="font-size: 2rem; font-weight: bold; margin-top: 0.5rem;">${totalRegistrations}</div>
                </div>
                <div class="card" style="border-top: 4px solid #ef4444;">
                    <div style="font-size: 0.875rem; color: var(--secondary); text-transform: uppercase; font-weight: 600;">Total Revenue</div>
                    <div style="font-size: 1.5rem; font-weight: bold; margin-top: 0.5rem; color: #b91c1c;">LKR ${totalFees}</div>
                </div>
            </div>

            <div style="display: flex; gap: 1rem; justify-content: center;">
                <a href="/registrations/register" class="btn btn-primary">New Enrollment</a>
                <a href="/courses" class="btn" style="background: white; color: var(--primary); border: 1px solid var(--primary);">Course Catalog</a>
            </div>
        </div>

        <div style="display: grid; grid-template-columns: repeat(auto-fit, minmax(300px, 1fr)); gap: 1.5rem; margin-top: 2rem;">
            <div class="card">
                <h3>📚 Course Management</h3>
                <p>Add and categorize courses. Track instructor assignments and course locations.</p>
                <a href="/courses" style="color: var(--primary); font-weight: 500;">Manage &rarr;</a>
            </div>
            <div class="card">
                <h3>👥 User Directory</h3>
                <p>Access student and administrator profiles. Control system access levels.</p>
                <a href="/users" style="color: var(--primary); font-weight: 500;">Manage &rarr;</a>
            </div>
        </div>
    </div>
</body>
</html>
