<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<%@page contentType="text/html" pageEncoding="utf-8"%>

<script type="text/javascript" src="/static/js/sitenav.js"></script>

<div class="navbar navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container-fluid" id="sitenaveTemplateContainer">
			<script id="sitenavTemplate" type="text/template">
			<div class="brand">
				<spring:message code="nav.appName" />
			</div>			
			<div class="nav-collapse" id="sitenav">
				<ul class="nav" >
					<li <@ if (isDashboard) {@> class="active" <@}@>><a href="/f/dashboard"><spring:message code="nav.btn.dashboard" /></a></li>
					<li <@ if (isProject) {@> class="active" <@}@>><a href="/f/projects"><spring:message code="nav.btn.projects" /></a></li>
					<li <@ if (isAbout) {@> class="active" <@}@>><a href="/f/about"><spring:message code="nav.btn.about" /></a></li>				
				</ul>
				<p class="navbar-text pull-right">
					<@= userName @>
				</p>
			</div>
			</script>
		</div>
	</div>
</div>

<script type="text/javascript">
	var siteNavData = ${SiteNav};
	var bb_sitenav = new SiteNav({el : $("#sitenaveTemplateContainer")});
</script>