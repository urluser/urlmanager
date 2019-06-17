<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>URL Abbreviation</title>
    <link href="/css/index.css" rel="stylesheet">
</head>
<body>
    <script src="/js/index.js"></script>
    <table>
        <tr>
            <th>Abbreviation</th>
            <th>URL</th>
        </tr>
        <c:forEach var="pair" items="${pairs}">
            <tr>
                <td>${pair.abbreviation}</td>
                <td>${pair.url}</td>
                <td><button onclick="remove('${pair.abbreviation}')">Delete</button></td>
            </tr>
        </c:forEach>
        <tr>
            <td><input type="text" id="abbreviation"></td>
            <td><input type="text" id="url"></td>
            <td><button onclick="add()">Add</button></td>
        </tr>
    </table>
</body>
</html>
