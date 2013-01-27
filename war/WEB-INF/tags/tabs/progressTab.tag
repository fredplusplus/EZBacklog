<%@include file="/WEB-INF/jspf/taglibs.jspf"%>
<%@tag  pageEncoding="utf-8"%>
<script type="text/javascript" src="/static/js/tabs/progressTab.js" ></script>
<script type="text/template" id="progressTemplate" >
<div class="row-fluid">
	<div class="span1 offset1"><strong><spring:message code="tab.progress.label.overall" /></strong></div>
	<div class="span9 offset1">
		<div class="progress">
			<@if (point == 0) { point = 1; } @>
			<@if (resolvedPoint <= point) { @>
				<div class="bar bar-success" style="width: <@= (resolvedPoint * 100) / point@>%" />
			<@ } else { @>
				<div class="bar bar-success" style="width: <@= (point * 100) / resolvedPoint@>%" />
				<div class="bar bar-warning" style="width: <@= ((resolvedPoint - point) * 100) / resolvedPoint@>%" />
			<@ } @>
		</div>
	</div>
</div>
</script>