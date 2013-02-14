<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<%@tag pageEncoding="utf-8"%>
<c:set var="defaultTeamnName">
	<spring:message code="itemDetail.team.private" />
</c:set>
<@ if(typeof(teamId) !='undefined' && typeof(teamName) != 'undefined') { @>
<dd id="teamSelector" data-teamid="<@=teamId@>" data-teamname="<@=teamName@>"><@=teamName@></dd>
<@ } else {@>
<dd id="teamSelector" data-teamid="" data-teamname="${defaultTeamnName}">${defaultTeamnName}</dd>
<@ } @>
