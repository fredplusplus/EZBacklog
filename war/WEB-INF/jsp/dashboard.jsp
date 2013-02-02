<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<%@page contentType="text/html" pageEncoding="utf-8"%>
<script type="text/javascript" src="/static/js/dashboard.js"></script>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>

<div class="container-fluid" id="dashboardContainer"></div>
<script id="dashboardTemplate" type="text/template">
	<div class="row-fluid" >
		<div class="span4">
			<img src="/static/img/bot.png" class="img-rounded" />
		</div>
		<div class="span6 offset1">
			<div class="row-fluid" id="userContainer">
				<h2><@=backlogger.email@></h2>
				<dl class="dl-horizontal">
					<dt><spring:message code="dashboard.joinedsince" /></dt><dd><@=backlogger.joinDate@></dd>
				</dl>
			</div>
			<div class="row-fluid" id="projectContainer">
				<h4><spring:message code="dashboard.projectoverview" /> </h4>
				<table class="table table-striped table-bordered">
					<thead>
						<tr>
							<th></th>
							<th><spring:message code="dashboard.projectcount" /></th>
							<th><spring:message code="dashboard.projectpoint" /></th>
						</tr>
					</thead>
					<tbody>
						<tr class="info">
							<th><spring:message code="dashboard.openproject" /></th>
							<td><@= itemAggregation.statusMap.Open@></td>
							<td><@= itemAggregation.pointMap.Open@></td>
						</tr>
						<tr class="success">
							<th><spring:message code="dashboard.resolvedproject" /></th>
							<td><@= itemAggregation.statusMap.Resolved@></td>
							<td><@= itemAggregation.pointMap.Resolved@></td>
						</tr>
						<tr class="error">
							<th><spring:message code="dashboard.deletedproject" /></th>
							<td><@= itemAggregation.statusMap.Deleted@></td>
							<td><@= itemAggregation.pointMap.Deleted@></td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="row-fluid">
				<h4><spring:message code="dashboard.milestone" /></h4>
				<div class="span12" id="milestoneContainer">
				</div>
			</div>
			<div class="row-fluid">
				<div class="span4">
					<a href="/f/items" class="btn btn-success"><spring:message code="dashboard.btn.gotoproject"/></a>
				</div>
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