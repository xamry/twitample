<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="/KunderaSampleApp/common/../common/v1.css" />
<title>Kundera Sample application</title>
</head>
<body>
	<div>
		<table align="center" border="1">
			<tr>
				<td><s:form method="post" action="save" namespace="/pages">
						<s:textfield label="Email Id" name="emailId" />
						<s:textfield label="Name" name="personName" />
						<s:textfield label="Age" name="age" />
						<s:textfield label="Address Line1" name="street" />
						<s:submit value="Save" name="Save" />
					</s:form></td>
			</tr>
		</table>
	</div>
</body>
</html>