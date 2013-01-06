<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
<%@page contentType="text/html" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
	<head>
		<title>
			<c:set var="titleKey">
				<tiles:insertAttribute name="title" ignore="true" />
			</c:set>
			<spring:message code="${titleKey}" />
		</title>
		<style type="text/css">
		body {
			padding-top: 60px;
			padding-bottom: 40px;
		}
		</style>
		<link href="/static/css/layout.css" rel="stylesheet">
		<link href="/static/css/bootstrap.min.css" rel="stylesheet">
		<script src="http://code.jquery.com/jquery-1.8.3.min.js"> </script>
		<script src="/static/js/bootstrap.min.js"></script>
	</head>
	<body>
		<tiles:insertAttribute name="header" />
		<tiles:insertAttribute name="body" />
		<tiles:insertAttribute name="footer" />
	</body>
</html>