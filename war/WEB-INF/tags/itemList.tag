<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<div class="span5 right-bordered">
	<@ _.each(items, function(item) { @> 
		<div class="row-fluid itemBox">
			<div class="span2 itemBoxRank">
				<h6>
					<spring:message code="itemBox.rank"/>
					<@=item.rank@>
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
					<@}@>
				</div>
			</div>
		</div>
	<@ }); @>
</div>