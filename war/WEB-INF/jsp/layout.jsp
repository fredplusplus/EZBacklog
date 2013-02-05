<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="google-site-verification" content="V1v2h97tTZuMFBout2AgCEtcc5sR6FxZeJOQ-UgG8kw" />
		<meta name="msvalidate.01" content="7F71E3B1567D7FAFA3B08AC802700F87" />
		<meta name="keywords" content="scrum, backlog, kanban, project management, sprint, ezbacklog" />
		<meta name="description" content="An free easy backlog tool to manage your personal projects." />
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<title>
			<c:set var="titleKey">
				<tiles:insertAttribute name="title" ignore="true" />
			</c:set>
			<spring:message code="${titleKey}" />
		</title>
		<style type="text/css">
		body {
			padding-top: 60px;
			min-width: 1200px;
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
		<modal:pleaseWaitModal />
		<ez:loading />
		<tiles:insertAttribute name="sitenav" />
		<tiles:insertAttribute name="body" />
		<tiles:insertAttribute name="footer" />
	</body>
</html>