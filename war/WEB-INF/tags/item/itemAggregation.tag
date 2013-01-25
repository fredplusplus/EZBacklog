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
			<item:itemType key="itemLevel" displayPrefix="itemAggregation.itemType" />
		</h5>
		<small>
			<ul class="inline">
				<li><spring:message code="itemAggregation.openOverview"/></li>
				<li><spring:message code="itemAggregation.resolvedOverview"/></li>
			</ul>
		</small>
	</div>
	<div class="span5 offset1" style="text-align: right">
		<div class="row-fluid">
		<a href="javascript:void(0);" onclick="displayCreateItemModal();" class="btn btn-info">
			<item:itemType key="itemLevel" displayPrefix="itemAggregation.create" />
		</a>
		</div>
		<div class="row-fluid">
			<h4><@if (typeof(parentShortDescription) !='undefined') {print(parentShortDescription);}@></h4>
		</div>
	</div>
</script>

<script type="text/javascript">
  var itemAggregationModel = new ItemAggregationModel(${ItemAggregation});
  var itemAggregationView = new ItemAggregationView({el: $('#itemAggregationContainer'), model: itemAggregationModel});
  var createItemView = new UpdateItemModalView({model:new ItemModel(${ItemAggregation})});
  createItemView.deactivate();
  function displayCreateItemModal() {
	  createItemView.render();
	  $("#updateItemModal").modal({keyboard:false});
	  return false;
  }
</script>