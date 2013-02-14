<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<%@tag pageEncoding="utf-8"%>

<script type="text/template" id="teamDropdownTemplate" >
<li role="presentation">
	<a role="menuitem" tabindex="-1" href="#" data-teamid=""><spring:message code="itemDetail.team.private" /></a>
</li>
<@ if (teams.length >1) { @>
<li role="presentation" class="divider"></li>
<@ } @>
<@ _.each(teams, function(team) { @>
<li role="presentation">
	<a id="teamoption" role="menuitem" tabindex="-1" href="#" data-teamid="<@=team.id@>" data-teamname="<@=team.teamName.name@>"><@=team.teamName.name@></a>
</li>
<@ }) @>
</script>