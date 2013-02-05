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
					<@if (authenticated && !isAbout) { @>
						<li <@ if (isDashboard) {@> class="active" <@}@>><a href="/f/dashboard"><spring:message code="nav.btn.dashboard" /></a></li>
						<li <@ if (isProject) {@> class="active" <@}@>><a href="/f/items"><spring:message code="nav.btn.projects" /></a></li>
						<li <@ if (isSetting) {@> class="active" <@}@>><a href="/f/setting"><spring:message code="nav.btn.setting" /></a></li>
						<li><a href="/f/about"><spring:message code="nav.btn.about" /></a></li>
					<@ } @>
				</ul>
				<div class="navbar-text pull-right span4">
					<div class="row-fluid">	
						<div class="share pull-left">
							<div class="g-plusone pull-right" data-annotation="bubble" data-href="<spring:message code="rootUrl" />"></div>
						</div>
						<div class="span6 text-right pull-right" >
							<@ if (authenticated) { @>
								<@= userName @>
							<@ } @>
						</div>
					</div>
				</div>
			</div>
			</script>
		</div>
	</div>
</div>

<script type="text/javascript">
	var siteNavModel = new SiteNavModel(${SiteNav});
	
	var siteNavView = new SiteNavView({el : $("#sitenaveTemplateContainer"), model: siteNavModel});
</script>