<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Dashboard | EduReg</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<%@ include file="/WEB-INF/jsp/common/nav.jsp" %>

<div class="container">
    <div class="card hero-card">
        <h1 class="hero-title">Academic Dashboard</h1>
        <p class="hero-sub">Welcome to the Student Course Registration System.</p>

        <div class="stats-grid">
            <div class="stat-card" style="border-top-color: var(--primary);">
                <div class="stat-label">Students</div>
                <div class="stat-value">${totalStudents}</div>
            </div>
            <div class="stat-card" style="border-top-color: #10b981;">
                <div class="stat-label">Courses</div>
                <div class="stat-value">${totalCourses}</div>
            </div>
            <div class="stat-card" style="border-top-color: #f59e0b;">
                <div class="stat-label">Active Enrollments</div>
                <div class="stat-value">${totalRegistrations}</div>
            </div>
            <div class="stat-card" style="border-top-color: #ef4444;">
                <div class="stat-label">Total Revenue (LKR)</div>
                <div class="stat-value" style="font-size:1.4rem;">${totalFees}</div>
            </div>
            <div class="stat-card" style="border-top-color: #8b5cf6;">
                <div class="stat-label">Grades Assigned</div>
                <div class="stat-value">${totalGrades}</div>
            </div>
        </div>

        <div style="display:flex;gap:1rem;justify-content:center;margin-top:1.5rem;">
            <a href="/registrations/register" class="btn btn-primary">+ New Enrollment</a>
            <a href="/courses" class="btn btn-outline">Course Catalog</a>
        </div>
    </div>

    <div class="quick-grid">
        <div class="card">
            <h3>📚 Course Management</h3>
            <p>Add, search, and categorize courses. Set capacity and assign instructors.</p>
            <a href="/courses" class="link-primary">Manage Courses &rarr;</a>
        </div>
        <div class="card">
            <h3>👥 User Directory</h3>
            <p>Manage students, admins, and instructors. View role descriptions.</p>
            <a href="/users" class="link-primary">Manage Users &rarr;</a>
        </div>
        <div class="card">
            <h3>📝 Registrations</h3>
            <p>Enroll students in courses. Drop registrations and view history.</p>
            <a href="/registrations" class="link-primary">View Registrations &rarr;</a>
        </div>
        <div class="card">
            <h3>🎓 Grade Book</h3>
            <p>Assign numeric grades to enrolled students. View letter grades.</p>
            <a href="/grades" class="link-primary">Manage Grades &rarr;</a>
        </div>
    </div>
</div>
</body>
</html>
