<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<%@tag pageEncoding="utf-8"%>

<dt>
	<spring:message code="itemDetail.team" />
</dt>
<@ if (typeof(id) == 'undefined' && typeof(parentId) == 'undefined' && typeof(teamId) =='undefined' && typeof(teamName) == 'undefined') {@>
<dd>
	<div class="dropdown" id="teamDropdown">
		<a class="dropdown-toggle" data-toggle="dropdown" href="#" id="teamSelector"><spring:message code="itemDetail.team.private" /></a>
		<ul class="dropdown-menu" role="menu" aria-labelledby="dLabel" id="teamDropdownContainer">
		</ul>
	</div>
</dd>
<@ } else {@>
<item:itemTeamRO />
<@ } @>
