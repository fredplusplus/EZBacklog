<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<%@ attribute name="status" required="true" %>
<@ var ${status}displayed=false @>
<@ _.each(items, function(item) { @>
	<@ if (item.status == '${status}') { @>
	<@ ${status}displayed = true @>
	<div class="row-fluid itemBox <@if (item.selected) {print(' selected') }@>" 
		 data-id="<@=item.id@>" data-level="<@=item.itemLevel@>"
		 id="itemBox_<@=item.id@>">
		<div class="span2 itemBoxId">
			<h5><@=item.id@></h5>
		</div>
		<div class="span9">
			<div class="shortDesc row-fluid">
				<@= item.shortDescription @>
			</div>
			<div class="notes row-fluid">
				<div class="span1 topPadding">
					<small class="label label-info"><@=item.rank@></small>
				</div>
				<div class="span2 topPadding">
					<@if (item.status == 'Open') {@>
						<small class="label label-warning"><spring:message code="dashboard.openproject"/></small>
					<@} else if (item.status =='Resolved'){ @>
						<small class="label label-success"><spring:message code="dashboard.resolvedproject"/></small>
					<@} else if (item.status =='Deleted'){ @>
						<small class="label "><spring:message code="dashboard.deletedproject"/></small>
					<@}@>
				</div>
				<div class="span3 topPadding">
					<small class="label">
					<@ if(typeof(item.teamName) != 'undefined') { @>
						<@=item.teamName@>
					<@ } else {@>
						<spring:message code="itemDetail.team.private" />
					<@ } @>
					</small>
				</div>
			</div>
		</div>
	</div>
	<@ } @>
<@ }); @>
<@ if (${status}displayed) { @>
<hr />
<@ } @>