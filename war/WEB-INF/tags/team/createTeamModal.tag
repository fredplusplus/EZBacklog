<%@include file="/WEB-INF/jspf/taglibs.jspf"%>
<%@tag pageEncoding="utf-8"
	description="modal box for creating new team"%>
<script type="text/javascript" src="/static/js/globalteam.js"></script>

<div id="createTeamModalContainer"></div>
<script type="text/template" id="createTeamModalTemplate">
<div id="createTeamModal" data-keyboard="false" class="createTeamModal modal hide fade" tabindex="-1" role="dialog" data-backdrop="static" aria-labelledby="$createTeamModalLabel" aria-hidden="true">
	<div class="modal-header">
		<h3 id="createTeamModal">
			<spring:message code="team.btn.creatateam" />
		</h3>
	</div>
	<div class="modal-body">
		<div class="form-horizontal">
			<fc:textfieldControl labelKey="team.label.name" id="name" />
		</div>
	</div>
	<div class="modal-footer">
		<button id="cancelUpdate" class="btn" data-dismiss="modal" aria-hidden="true"><spring:message code="modal.btn.close" /></button>
		<button id="submitUpdate" class="btn btn-primary" data-dismiss="modal" aria-hidden="true"><spring:message code="modal.btn.ok" /></button>
	</div>
</div>
</script>
<script type="text/javascript">
	var createTeamModel = new CreateTeamModel();
	var createTeamView = new CreateTeamView({
		model : createTeamModel,
		el : $('#createTeamModalContainer'),
	});
</script>