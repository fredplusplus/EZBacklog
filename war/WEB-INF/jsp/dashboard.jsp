<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<%@page contentType="text/html" pageEncoding="utf-8"%>
<script type="text/javascript" src="/static/js/dashboard.js"></script>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>

<div class="container-fluid">
	<div class="row-fluid" >
		<div class="span4">
			<div class="row-fluid">
				<img src="/static/img/bot.png" class="img-rounded" />
			</div>
			<div class="row-fliud dashboradAds">
				<ads:square />
			</div>
		</div>
		<div class="span6 offset1" id="dashboardContainer"></div>
	</div>
</div>
<script id="dashboardTemplate" type="text/template">
	<div class="row-fluid" id="userContainer">
		<h2><@=backlogger.email@></h2>
		<dl class="dl-horizontal">
			<dt><spring:message code="dashboard.joinedsince" /></dt><dd><@=backlogger.joinDate@></dd>
		</dl>
	</div>
	<div class="row-fluid" id="projectContainer">
		<h4>
			<@ if (typeof (backlogger.overviewlevel) == 'undefined' || backlogger.overviewlevel == 1) { @>
			<spring:message code="dashboard.overview.1" />
			<@ } else if (backlogger.overviewlevel == 2) {@>
			<spring:message code="dashboard.overview.2" />
			<@ } else if (backlogger.overviewlevel == 3) {@>
			<spring:message code="dashboard.overview.3" />
			<@ } @>
		</h4>
		<table class="table table-striped table-bordered">
			<thead>
				<tr>
					<th></th>
					<th><spring:message code="dashboard.projectcount" /></th>
					<@ if(backlogger.usePoint) { @>
						<th><spring:message code="dashboard.projectpoint" /></th>
					<@ } @>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th><spring:message code="dashboard.openproject" /></th>
					<td><@= itemAggregation.statusMap.Open@></td>
					<@ if(backlogger.usePoint) { @>
						<td><@= itemAggregation.pointMap.Open@></td>
					<@ } @>
				</tr>
				<tr>
					<th><spring:message code="dashboard.resolvedproject" /></th>
					<td><@= itemAggregation.statusMap.Resolved@></td>
					<@ if(backlogger.usePoint) { @>
						<td><@= itemAggregation.pointMap.Resolved@></td>
					<@ } @>
				</tr>
				<tr>
					<th><spring:message code="dashboard.deletedproject" /></th>
					<td><@= itemAggregation.statusMap.Deleted@></td>
					<@ if(backlogger.usePoint) { @>
						<td><@= itemAggregation.pointMap.Deleted@></td>
					<@ } @>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="row-fluid">
		<h4><spring:message code="dashboard.milestone" /></h4>
		<div class="span12" id="milestoneContainer"></div>
	</div>
</script>
<script type="text/javascript">
	var dashboardData = ${Dashboard};
	var bb_dashboard = new Dashboard({
		el : $("#dashboardContainer")
	});

</script>