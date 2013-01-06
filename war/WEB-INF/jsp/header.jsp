<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<%@page contentType="text/html" pageEncoding="utf-8"%>
<div class="navbar navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container-fluid">
			<div class="brand">
				<spring:message code="nav.appName" />
			</div>
			<div class="nav-collapse">
				<ul class="nav">
					<li class="active"><a href="#"><spring:message code="nav.btn.home" /></a></li>
					<li><a href="#about"><spring:message code="nav.btn.about" /></a></li>
				</ul>
				<p class="navbar-text pull-right">
					<spring:message code="nav.login" arguments="usertest" />
				</p>
			</div>
		</div>
	</div>
</div>