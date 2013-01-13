<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<%@page contentType="text/html" pageEncoding="utf-8"%>
<script type="text/javascript" src="/static/js/dashboard.js"></script>

<div class="container-fluid" id="dashboardContainer"></div>
<script id="dashboardTemplate" type="text/template">
	<div class="row-fluid" >
		<div class="span4">
			<img src="/static/img/bot.png" class="img-rounded" />
		</div>
		<div class="span6 offset1">
			<div class="row-fluid">
				<h2><@=backlogger.email@></h2>
				<dl class="dl-horizontal">
					<dt><spring:message code="dashboard.joinedsince" /></dt><dd><@=backlogger.joinDate@></dd>
				</dl>
			</div>
		</div>
	</div>
</script>
<script type="text/javascript">
	var dashboardData = ${Dashboard};
	var bb_dashboard = new Dashboard({
		el : $("#dashboardContainer")
	});
</script>