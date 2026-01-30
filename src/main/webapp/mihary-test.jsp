<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mihary Test</title>
</head>
<body>
    <h1>Mihary Test</h1>

    <%
        String[] tst = (String[]) request.getAttribute("tst");
    %>

    <% for (String str : tst) { %>
        <li><%= str %></li>
    <% } %>
</body>
</html>