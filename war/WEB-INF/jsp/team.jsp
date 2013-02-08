<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<%@page contentType="text/html" pageEncoding="utf-8"%>

<script type="text/javascript" src="/static/js/backlogger.js"></script>
<script type="text/javascript" src="/static/js/team.js"></script>
<script type="text/javascript">
	var backloggerModel = new BackloggerModel(${Backlogger});
</script>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span6" id="teamCollectionContainer">
		</div>
		<div class="span4 offset1">
			<img src="/static/img/preacher-right.png" class="img-rounded" />
		</div>
	</div>
</div>

<script type="text/template" id="teamListTemplate">
	<h4>
		<spring:message code="team.title" />
	</h4>
	<@ if (teams.length > 0) { @>
	<div class="accordion">
		<@ _.each(teams, function(team) { @>
		<div class="accordion-group">
			<div class="accordion-heading">
				<a href="#collapse<@=team.id@>" class="accordion-toggle" data-toggle="collapse">
					<b><@=team.teamName.name @></b>
				</a>
			</div>
			<div id="collapse<@=team.id@>" class="accordion-body in collapse" style="height: auto">
				<div class="accordion-inner">
					<dl class="dl-horizontal">
					<@ var isAdmin = false @>
					<@ _.each(team.members, function(member) { @>
						<dt><@= member.userEmail @></dt>
						<@ if (member.role == 'Admin')  { @>
						<@ if (member.userEmail == backloggerModel.get('email')) { isAdmin = true  }@>
						<dd><spring:message code="team.role.Admin" /></dd>
						<@ } else if (member.role == 'Operator') {@>
						<dd><spring:message code="team.role.Operator" /></dd>
						<@ } @>
						
					<@ }) @>
					</dl>
				</div>
				<@ if (isAdmin) {@>
				<div class="accordion-inner">
					<div class="btn-toolbar">
						<div class="btn-group">
							<a href="#" class="btn" data-teamId="<@= team.teamName.id @>"><spring:message code="team.btn.addmember" /></a>
						</div>
					</div>
				</div>
				<@ } @>
				<@ isAdmin = false @>
			</div>
		</div>
		<@ }) @>
	</div>
	<@ } @>
</script>

<script type="text/javascript">
var teams = new TeamCollection(${teams});
var adminView = new TeamCollectionView({collection: teams, el : $("#teamCollectionContainer") });
</script>