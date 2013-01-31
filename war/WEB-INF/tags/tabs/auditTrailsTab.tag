<%@include file="/WEB-INF/jspf/taglibs.jspf"%>
<%@tag pageEncoding="utf-8"%>
<script type="text/javascript" src="/static/js/tabs/auditTrailsTab.js"></script>
<script type="text/template" id="auditTemplate">
<@ _.each(audits, function(audit) { @>
	<div class="row-fluid comment">
		<span class="commentUser"><@= audit.time @><span>
		<span class="offset1 span11 auditContent">
			<@= audit.userEmail @>
			<@ if (audit.activityType == 'Create') { @>
				<spring:message code="tab.audit.verb.create" />
			<@ } else if (audit.activityType == 'Update') { @>
				<spring:message code="tab.audit.verb.update" />
			<@ } else if (audit.activityType == 'Comment') { @>
				<spring:message code="tab.audit.verb.comment" />
			<@ } else if (audit.activityType == 'Delete') { @>
				<spring:message code="tab.audit.verb.delete" />
			<@ } else if (audit.activityType == 'Burndown') { @>
				<spring:message code="tab.audit.verb.burndown" />
			<@ } else if (audit.activityType == 'Reopen') { @>
				<spring:message code="tab.audit.verb.reopen" />
			<@ } else if (audit.activityType == 'Resolve') { @>
				<spring:message code="tab.audit.verb.resolve" />
			<@ } @>
		</spans>
	</div>
<@ }) @>
</script>