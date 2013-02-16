<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<%@page contentType="text/html" pageEncoding="utf-8"%>

<script type="text/javascript" src="/static/js/backlogger.js"></script>
<script type="text/javascript" src="/static/js/team.js"></script>
<script type="text/javascript">
	var backloggerModel = new BackloggerModel(${Backlogger});
</script>
<div class="container-fluid teamheader">
	<div class="row-fluid ">
		<h4 class="span5">
			<spring:message code="team.title" />
		</h4>
		<div class="span5 offset1" style="text-align: right">
			<a href="#" class="btn btn-info"id="createTeam" onclick="return displayCreateTeamModal();">
				<spring:message code="team.btn.creatateam" />
			</a>
		</div>
	</div>
</div>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span6" id="teamCollectionContainer">
		</div>
		<div class="span4 offset1">
			<img src="/static/img/preacher-right.png" class="img-rounded" />
		</div>
	</div>
</div>
<team:TeamModal />
<script type="text/template" id="teamListTemplate">
	<@ if (teams.length > 0) { @>
	<div class="accordion">
		<@ _.each(teams, function(team) { @>
		<div class="accordion-group" id="teamContainer">
			<div class="accordion-heading">
				<a href="#collapse<@=team.id@>" class="accordion-toggle collapsed" data-toggle="collapse">
					<b><@=team.teamName.name @></b>
				</a>
			</div>
			<div id="collapse<@=team.id@>" class="accordion-body collapse" style="height: 0px">
				<div class="accordion-inner">
					<dl class="dl-horizontal">
					<@ var isAdmin = false @>
					<@ _.each(team.members, function(member) { @>
						<dt><@= member.userEmail @></dt>
						<@ if (member.role == 'Admin')  { @>
						<@ if (member.userEmail == backloggerModel.get('email')) { isAdmin = true  }@>
						<dd><spring:message code="team.role.Admin" /></dd>
						<@ } else if (member.role == 'Operator') {@>
						<dd>
							<spring:message code="team.role.Operator" />
							<@ if (member.userEmail == backloggerModel.get("email") || isAdmin) {@>
							<span id="removeMemberBtn" data-memberid="<@=member.id@>" class="icon-remove pointerMouse"></span>
							<@ } @>
						</dd>
						<@ } @>
						
					<@ }) @>
					</dl>
				</div>
				<@ if (isAdmin) {@>
				<div class="accordion-inner">
					<div class="btn-toolbar">
						<div class="btn-group">
							<a href="#" id="addMemberBtn" class="btn" data-teamid="<@= team.teamName.id @>"><spring:message code="team.btn.addmember" /></a>
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
function displayCreateTeamModal() {
	createTeamView.render();
	  $("#createTeamModal").modal({keyboard:false});
}
</script>