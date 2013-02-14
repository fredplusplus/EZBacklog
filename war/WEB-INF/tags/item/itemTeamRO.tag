<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<%@tag pageEncoding="utf-8"%>

<@ if(typeof(teamId) !='undefined' && typeof(teamName) != 'undefined') { @>
<dd data-teamid="<@=teamId@>"><@=teamName@></dd>
<@ } else {@>
<dd data-teamid="">
	<spring:message code="itemDetail.team.private" />
</dd>
<@ } @>
