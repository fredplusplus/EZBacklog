<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<%@tag pageEncoding="utf-8" description="Overview for a item type"%>
<script type="text/javascript" src="/static/js/itemAggregation.js" ></script>
<div class="container-fluid itemAggregation">
	<div class="row-fluid" id="itemAggregationContainer"></div>
</div>

<modal:updateItemModal/>

<script id="itemAggregationTemplate" type="text/template" >
	
	<div class="span5">
		<h5>
			<spring:message code="itemAggregation.itemCount"/>
			<@ if (level==1) {@> <spring:message code="itemAggregation.itemType.1" /> <@} @>
			<@ if (level==2) {@> <spring:message code="itemAggregation.itemType.2" /> <@} @>
			<@ if (level==3) {@> <spring:message code="itemAggregation.itemType.3" /> <@} @>
		</h5>
		<small>
			<ul class="inline">
				<li><spring:message code="itemAggregation.openOverview"/></li>
				<li><spring:message code="itemAggregation.resolvedOverview"/></li>
			</ul>
		</small>
	</div>
	<div class="span5 offset1">
		<a href="#" data-toggle="modal" data-target="#updateItemModal" class="btn btn-info">
			<@ if (level==1) {@> <spring:message code="itemAggregation.create.1" /> <@} @>
			<@ if (level==2) {@> <spring:message code="itemAggregation.create.2" /> <@} @>
			<@ if (level==3) {@> <spring:message code="itemAggregation.create.3" /> <@} @>
		</a>
	</div>
</script>

<script type="text/javascript">
  var itemAggregationModel = new ItemAggregationModel(${ItemAggregation});
  var itemAggregationView = new ItemAggregationView({el: $('#itemAggregationContainer'), model: itemAggregationModel});
  var createItemView = new UpdateItemModalView({model:itemAggregationModel});
</script>