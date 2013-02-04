<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<%@page contentType="text/html" pageEncoding="utf-8"%>
<link href="/static/css/impress.css" rel="stylesheet">
<script type="text/javascript" src="/static/js/lib/impress/impress.js"></script>
<div id="impressContainer" class="container impress-not-supported impressContainer">
	<div id="impress">
		<div class="step slide" data-x="0" data-y="0" data-rotate="0">
			<div class="row-fluid">
				<h4>It's an Easy Backlog</h4>
				<div class="span4 center">
					<img src="/static/img/bot.png" class="img-rounded" />
					<a href="/f/dashboard"><spring:message code="about.login" /></a>
				</div>
				<div class="span7">
					<ul>
						<li>You only find what you absolutely need</li>
						<li>Buttons are at where you need them</li>
						<li>No more fancy features that you never use</li>
					</ul>
				</div>
			</div>
		</div>
		<div class="step slide" data-x="1000" data-y="800" data-rotate="0">
			<div class="row-fluid">
				<h4>It's a Personal Backlog</h4>
				<div class="span4 center">
					<img src="/static/img/calculon.png" class="img-rounded" />
					<a href="/f/dashboard"><spring:message code="about.login" /></a>
				</div>
				<div class="span7">
					<ul>
						<li>Create your personal project and track it</li>
						<li>Rank importance of each work item</li>
						<li>Realize what you need to work on immediately</li>
					</ul>
				</div>
			</div>
		</div>
		<div class="step slide" data-x="0" data-y="1600" data-rotate="0">
			<div class="row-fluid">
				<h4>It's a Free Style Backlog</h4>
				<div class="span4 center">
					<img src="/static/img/bender-left.png" class="img-rounded" />
					<a href="/f/dashboard"><spring:message code="about.login" /></a>
				</div>
				<div class="span7">
					<ul>
						<li>Prioritize work and work on them, however long it takes</li>
						<li>No limit on how long each task should take</li>
						<li>No emphasize on time tracking</li>
						<li>No emphasize on point tracking</li>
					</ul>
				</div>
			</div>
		</div>
		
		<div class="step slide" data-x="-1000" data-y="800" data-rotate="0">
			<div class="row-fluid">
				<h4>It's a Free Backlog</h4>
				<div class="span4 center">
					<img src="/static/img/preacher.png" class="img-rounded" />
					<a href="/f/dashboard"><spring:message code="about.login" /></a>
				</div>
				<div class="span7">
					<ul>
						<li>It's FREE</li>
						<li>Support our work by clicking the advertisements</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
impress().init();
</script>