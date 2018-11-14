<%@include file="include.jsp" %>
<html>
<head>
    <title>Train information</title>
</head>

<body style="background-image: url('/resources/images/train.jpg')">

 <div class="centerDiv" align="center">
     <h1>Trains</h1>
 </div>

<c:set var="trains" value="${trains}"/>
    <div align="center" style="width: 1024px; margin: 30px auto;">
        <table id="table_id" class="display">
            <thead>
            <tr style="border: 1px solid grey;">
                <th style="border: 2px solid grey;">Number</th>
                <th style="border: 2px solid grey;">Name</th>
                <th style="border: 2px solid grey;">Luggage weight</th>
                <th style="border: 2px solid grey;">Count of passengers</th>
            </tr>
            </thead>
            <tbody id="tBody">
            <c:forEach items="${trains}" var="train">
                <tr>
                    <td align="center" style="border: 1px solid grey;">
                    <a href="${pageContext.request.contextPath}/?command=ShowCarriages&trainID=${train.id}&trainNumber=${train.number}">${train.number}</a>
                    </td>
                    <td align="center" style="border: 1px solid grey;">${train.name}</td>
                    <td align="center" style="border: 1px solid grey;">${train.luggageWeight}</td>
                    <td align="center" style="border: 1px solid grey;">${train.countOfPassengers}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </div>

</body>
</html>
