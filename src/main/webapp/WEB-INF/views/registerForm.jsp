<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
<title>Hello :: Spring Application - Register form</title>
</head>
<body>
<h1>Form validation example</h1>
<p>Welcome, please fill in this form</p>

<form:form method="POST" commandName="registrationForm">
    <table>
        <tr>
            <td>First Name:</td>
            <td><form:input path="firstName" /></td>
 			<td><form:errors path="firstName" /></td>            
        </tr>
        <tr>
            <td>Last Name:</td>
            <td><form:input path="lastName" /></td>
 			<td><form:errors path="lastName" /></td>            
        </tr>
        <tr>
            <td>Username:</td>
            <td><form:input path="username" /></td>
 			<td><form:errors path="username" /></td>            
        </tr>
        <tr>
            <td>Password:</td>
            <td><form:password path="password" /></td>
 			<td><form:errors path="password" /></td>            
        </tr>
        <tr>
            <td>Skills:</td>
            <td>
            Java: <form:checkbox path="skills" value="Java" /><br/>
            .Net: <form:checkbox path="skills" value=".Net" /><br/>
            JSP: <form:checkbox path="skills" value="JSP" /><br/>
            XML: <form:checkbox path="skills" value="XML" /><br/>
            C++: <form:checkbox path="skills" value="C++" /><br/>
            </td>
 			<td><form:errors path="skills" /></td>            
        </tr>
        <tr>
            <td>Do you agree with the terms?:</td>
            <td>
            Yes <form:radiobutton path="agreedWithTerms" value="true"/>, No <form:radiobutton path="agreedWithTerms" value="false"/><br/>
             (of course, this shouldn't be a radio button in real life :P)
            </td>
 			<td><form:errors path="agreedWithTerms" /></td>            
        </tr>
        <tr>
            <td colspan="3">
                <input type="submit" value="Save Changes" />
            </td>
        </tr>
    </table>
</form:form>
</body>

</html>