<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<%@tag pageEncoding="utf-8" description="A robot named bender"%>
<script type="text/template" id="benderTemplate">
	<img src="/static/img/bender.png" class="bender"></img>
	<h4>
		<@if (_.size(items) == 0) { @>
		<spring:message code="bender.noitem" />
		<@ } else {@>
		<spring:message code="bender.clickitem" />
		<@ } @>
	</h4>
</script>