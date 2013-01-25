<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<script type="text/javascript" src="/static/js/breadcrumb.js" ></script>
<script type="text/template" id="breadCrumbTemplate">
<div class="row-fluid">
	<strong class="ez-breadcrumb">
		<@ if (typeof(grandParentId) =='undefined' && typeof(parentId) =='undefined' && typeof(id) !='undefined') { @>
			<spring:message code="breadcrumb.all" />&nbsp;>>&nbsp;<@=id@>
		<@ } else if (typeof(grandParentId) =='undefined' && typeof(parentId) !='undefined' && typeof(id) =='undefined') { @>
			<a href="/f/items"><spring:message code="breadcrumb.all" /></a>&nbsp;>>&nbsp;
			<@=parentId@>
		<@ } else if (typeof(grandParentId) =='undefined' && typeof(parentId) !='undefined' && typeof(id) !='undefined') { @>
			<a href="/f/items/<@=parentId@>"><@=parentId@></a>&nbsp;>>&nbsp;
			<@=id@>
		<@ } else if (typeof(grandParentId) !='undefined' && typeof(parentId) !='undefined' && typeof(id) =='undefined') { @>
			<a href="/f/items/<@=grandParentId@>"><@=grandParentId@></a>&nbsp;>>&nbsp;
			<@=parentId@>
		<@ } else { @>
			&nbsp;
		<@ } @>
	</strong>
</div>
</script>
<script type="text/javascript" >
	var breadCrumbModel = new ItemAggregationModel(${ItemAggregation});
	var breadCrumbView = new BreadCrumbView({ model : breadCrumbModel});
</script>