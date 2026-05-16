<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Course | EduReg</title>
    <link rel="stylesheet" href="/css/style.css">
    <script>
        function toggleFields() {
            const type = document.getElementById('type').value;
            const onsiteFields = document.getElementById('onsite-fields');
            const onlineFields = document.getElementById('online-fields');
            
            if (type === 'ONSITE') {
                onsiteFields.style.display = 'block';
                onlineFields.style.display = 'none';
            } else {
                onsiteFields.style.display = 'none';
                onlineFields.style.display = 'block';
            }
        }
    </script>
</head>
<body>
    <nav>
        <div class="container">EduReg System</div>
    </nav>

    <div class="container" style="max-width: 600px;">
        <div class="card">
            <h2>Add New Course</h2>
            <form action="/courses/add" method="post">
                <div class="form-group">
                    <label>Course Title</label>
                    <input type="text" name="title" required>
                </div>
                <div class="form-group">
                    <label>Instructor Name</label>
                    <input type="text" name="instructor" required>
                </div>
                <div class="form-group">
                    <label>Course Code</label>
                    <input type="text" name="code" placeholder="e.g. CS101">
                </div>
                <div class="form-group">
                    <label>Credits</label>
                    <input type="number" name="credits" value="3">
                </div>
                <div class="form-group">
                    <label>Course Type</label>
                    <select name="type" id="type" onchange="toggleFields()">
                        <option value="ONSITE">On-Site Course</option>
                        <option value="ONLINE">Online Course</option>
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

                <div id="online-fields" style="display: none;">
                    <div class="form-group">
                        <label>Platform (Zoom/Teams)</label>
                        <input type="text" name="platform">
                    </div>
                    <div class="form-group">
                        <label>Meeting Link</label>
                        <input type="text" name="link">
                    </div>
                </div>

                <div style="margin-top: 2rem;">
                    <button type="submit" class="btn btn-primary">Create Course</button>
                    <a href="/courses" class="btn btn-secondary">Cancel</a>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
