<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<script type="text/template" id="itemCollectionTemplate" >
<@ _.each(items, function(item) { @> 
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
				<@}@>
			</div>
		</div>
	</div>
<@ }); @>
</script>