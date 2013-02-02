<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<%@page contentType="text/html" pageEncoding="utf-8"%>
<script type="text/javascript" src="/static/js/setting.js"></script>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span4">
			<img src="/static/img/calculon.png" class="img-rounded" />
		</div>
		<div id="settingContainer" class="span6 offset1 settingContainer"></div>
		<script type="text/template" id="settingTemplate">
			<div class="setting-content">
				<h4>status tracking </h4>
		 		<div class="btn-toolbar setting-group">
					<div class="btn-group" data-toggle="buttons-radio">
  						<button id="overviewlevel1" type="button" class="btn">overviewlevel 1</button>
	  					<button id="overviewlevel2" type="button" class="btn">overviewlevel 2</button>
						<button id="overviewlevel3" type="button" class="btn">overviewlevel 3</button>
					</div>
				</div>
				<h4><spring:message code="setting.label.page.display" /></h4>
				<div class="btn-toolbar setting-group">
					<div class="btn-group" data-toggle="buttons-radio">
  						<button id="en" type="button" class="btn" data-value="<@print('en'==displayLocale)@>"><spring:message code="setting.radio.language.english" /></button>
	  					<button id="zh" type="button" class="btn" data-value="<@print('zh'==displayLocale)@>"><spring:message code="setting.radio.language.chinese" /></button>
					</div>
				</div>
				<h4><spring:message code="setting.label.item.display" /></h4>
				<div class="btn-toolbar setting-group">
					<div class="btn-group" data-toggle="buttons-checkbox">
  						<button id="showActive" type="button" class="btn" data-value="<@=showActive@>"><spring:message code="dashboard.openproject" /></button>
	  					<button id="showResolved" type="button" class="btn" data-value="<@=showResolved@>"><spring:message code="dashboard.resolvedproject" /></button>
  						<button id="showDeleted" type="button" class="btn" data-value="<@=showDeleted@>"><spring:message code="dashboard.deletedproject" /></button>
					</div>
				</div>
			</div>
			<div class="row-fluid">
				<button class="btn btn-info" id="saveSetting"><spring:message code="setting.btn.ok" /></button>
			</div>
		</script>
	</div>
</div>

<script type="text/javascript">
	var settingModel =  new SettingModel(${Backlogger});
	var settingView = new SettingView({model: settingModel});
	settingView.init();
</script>