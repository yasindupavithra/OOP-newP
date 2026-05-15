<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Book | LibSystem</title>
    <link rel="stylesheet" href="/css/style.css">
    <script>
        function toggleFields() {
            const type = document.getElementById('type').value;
            const printedFields = document.getElementById('printed-fields');
            const ebookFields = document.getElementById('ebook-fields');
            
            if (type === 'PRINTED') {
                printedFields.style.display = 'block';
                ebookFields.style.display = 'none';
            } else {
                printedFields.style.display = 'none';
                ebookFields.style.display = 'block';
            }
        }
    </script>
</head>
<body>
    <nav>
        <div class="container">LibSystem</div>
    </nav>

    <div class="container" style="max-width: 600px;">
        <div class="card">
            <h2>Add New Book</h2>
            <form action="/books/add" method="post">
                <div class="form-group">
                    <label>Title</label>
                    <input type="text" name="title" required>
                </div>
                <div class="form-group">
                    <label>Author</label>
                    <input type="text" name="author" required>
                </div>
                <div class="form-group">
                    <label>Genre</label>
                    <input type="text" name="genre">
                </div>
                <div class="form-group">
                    <label>ISBN</label>
                    <input type="text" name="isbn">
                </div>
                <div class="form-group">
                    <label>Book Type</label>
                    <select name="type" id="type" onchange="toggleFields()">
                        <option value="PRINTED">Printed Book</option>
                        <option value="EBOOK">E-Book</option>
                    </select>
                </div>

                <div id="printed-fields">
                    <div class="form-group">
                        <label>Rack Location</label>
                        <input type="text" name="rackLocation">
                    </div>
                    <div class="form-group">
                        <label>Pages</label>
                        <input type="number" name="pages">
                    </div>
                </div>

                <div id="ebook-fields" style="display: none;">
                    <div class="form-group">
                        <label>Download URL</label>
                        <input type="text" name="downloadUrl">
                    </div>
                    <div class="form-group">
                        <label>File Size (MB)</label>
                        <input type="number" step="0.01" name="fileSize">
                    </div>
                </div>

                <div style="margin-top: 2rem;">
                    <button type="submit" class="btn btn-primary">Save Book</button>
                    <a href="/books" class="btn btn-secondary">Cancel</a>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
