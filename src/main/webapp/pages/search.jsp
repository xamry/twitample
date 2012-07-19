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
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<title>Kundera Sample App</title>
<script src="../jscript/jscript.js">		
</script>
</head>
<body>

<s:iterator value="results" var="person">
	<table border="1" align="center" width="90%">
		<tr>
			<td>
			<table>
				<tr><td>&nbsp;</td></tr>
					<tr align="left">
						<td width="30">&nbsp;</td>
						<td width="150"><b> <s:property value="personId" /> </b></td>
						<td width="150"><s:property value="personName}" /> <s:property
							value="emailId" /></td>
						<td width="70"><s:property value="age"/></td>
						<td width="100"><s:property value="%{address.street}" /></td>						
					</tr>
					<tr align="left">
						<td width="30">&nbsp;</td>
						<td width="150"></td>
						<td width="150"></td>
						<td width="150">&nbsp;</td>
						<td width="150">&nbsp;</td>
					</tr>
			</table>
			</td>
		</tr>
	</table>
</s:iterator>

</body>
</html>	</div>
</body>
</html>
