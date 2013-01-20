<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<script type="text/template" id="breadCrumbTemplate">
<div class="row-fluid">
	<strong class="ez-breadcrumb">
		<@ if (typeof(parentId) == 'undefined') { @>
		<a href="/f/items"><spring:message code="breadcrumb.all" /></a>
		<@ } else { @>
		<a href="/f/items/<@= parentId @>"><@= parentId @></a>
		<@ } @>
		&nbsp;>>&nbsp;<@= id@>
	</strong>
</div>
</script>