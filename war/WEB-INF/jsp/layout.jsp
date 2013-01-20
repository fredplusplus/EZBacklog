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
		}
		</style>
		<link href="/static/css/layout.css" rel="stylesheet">
		<link href="/static/css/bootstrap.min.css" rel="stylesheet">
		<script src="/static/js/lib/jquery/jquery183.js"> </script>
		<script src="/static/js/lib/backbone/json2.js"> </script>
		<script src="/static/js/lib/underscore/underscore-min.js"> </script>
		<script src="/static/js/lib/backbone/backbone.js"> </script>
		<script src="/static/js/lib/bootstrap/bootstrap.min.js"></script>
		<script src="/static/js/sitewide.js"></script>
	</head>
	<body style="background: #fbfbfb">
		<tiles:insertAttribute name="sitenav" />
		<tiles:insertAttribute name="body" />
		<tiles:insertAttribute name="footer" />
	</body>
</html>