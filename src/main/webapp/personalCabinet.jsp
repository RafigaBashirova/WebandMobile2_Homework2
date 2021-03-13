<%@ page import="java.sql.ResultSet" %>
<%@ page import="utils.DButils" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="controller_Servlets.AuthenticationServlet" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jstl/sql" %>

<html>
<head>
    <title>Personal Cabinet</title>
    <link rel="stylesheet" type="text/css" href="personalCab.css">
</head>
<body>

<div id="wholeContainer" onMouseOver="this.style.backgroundColor='#a668ed'" onMouseOut="this.style.backgroundColor='#c899d1'">
    <h1>PERSONAL <br> INFORMATION</h1>

    <div id="holdsForms">
        <form method="POST" action="/homework2/personalCabinet">

            <label for="Lfirstname">First Name:</label>
            <input type="text" id="Lfirstname" name="firstname" placeholder="First Name..."><br>

            <label for="Llastname">Last Name:</label>
            <input type="text" id="Llastname" name="lastname" placeholder="Last Name..."><br>

            <label for="Lage">Age:</label>
            <input type="text" id="Lage" name="age" placeholder="Age..."><br>

            <label for="Lcity">City:</label>
            <input type="text" id="Lcity" name="city" placeholder="City..."><br>

            <label for="Lcountry">Country:</label>
            <input type="text" id="Lcountry" name="country" placeholder="Country..."><br>

            <label for="Lgender">Choose your gender:</label>
            <input type="text" id="Lgender" name="gender" placeholder="Male or Female..."><br>
            <button type="submit" value="save">SAVE</button><br>
            <label>Here are List of courses you can choose:</label><br>
        </form>

        <table border="1" cellpadding="5" align="center" width="40%">
            <tr>
                <th>Course ID</th>
                <th>Course Name</th>
                <th>Enrollment</th>
            </tr>
            <%
                try{
                    ResultSet result = DButils.queries("SELECT courseId, courseName FROM courses_of_website");
                    if(result != null){
                        while(result.next()){
            %>
            <form action="/homework2/courses" method="POST">
                <tr>
                    <td><%=result.getInt("courseId")%></td>
                    <td><%=result.getString("courseName")%>
                        <input type='hidden' name='courseName' value='<%=result.getString("courseName")%>' />
                    </td>
                    <td>
                        <button type="submit" value="enroll">enroll</button>
                    </td>
                </tr>
            </form>

            <%
                        }
                    }else{
                        System.out.println("You have connection problems");
                    }
                }catch(SQLException e){
                    e.printStackTrace();
                }
            %>
        </table>
    </div>

</div>
</body>
</html>