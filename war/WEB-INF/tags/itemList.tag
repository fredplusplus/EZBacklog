<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<%@ attribute name="status" required="true" %>
<@ var ${status}displayed=false @>
<@ _.each(items, function(item) { @>
	<@ if (item.status == '${status}') { @>
	<@ ${status}displayed = true @>
	<div class="row-fluid itemBox <@if (item.selected) {print(' selected') }@>" data-id="<@=item.id@>" data-level="<@=item.itemLevel@>">
		<div class="span2 itemBoxRank">
			<h6>
				<@=item.id@>
			</h6>
		</div>
		<div class="span9">
			<div class="shortDesc row-fluid">
				<@= item.shortDescription @>
			</div>
			<div class="notes row-fluid">
				<@if (item.status == 'Open') {@>
				<spring:message code="dashboard.openproject"/>
				<@} else if (item.status =='Resolved'){ @>
					<spring:message code="dashboard.resolvedproject"/>
				<@} else if (item.status =='Deleted'){ @>
					<spring:message code="dashboard.deletedproject"/>
				<@}@>
			</div>
		</div>
	</div>
	<@ } @>
<@ }); @>
<@ if (${status}displayed) { @>
<hr />
<@ } @>