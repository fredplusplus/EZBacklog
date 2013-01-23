<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<%@page contentType="text/html" pageEncoding="utf-8"%>

<script type="text/javascript" src="/static/js/Item.js" ></script>
<ez:itemAggregation />
	
<div class="container-fluid">
	<div class="row-fluid" >
		<div class="span5" id="itemCollectionContainer"></div>
		<div class="span6">
			<div class="row-fluid" id="breadCrumbContainer"></div>
			<div class="row-fluid">
				<div class="span12" id="itemDetailContainer"></div>
			</div>
		</div>
	</div>
</div>
<ez:breadcrumb />
<ez:bender />
<script type="text/template" id="itemCollectionTemplate" >
	<ez:itemList status="Open"/>
	<ez:itemList status="Resolved"/>
	<ez:itemList status="Deleted"/>
</script>
<modal:updateProgressModal />
<ez:itemDetail/>
<script type="text/javascript">
	var itemCollection = new ItemCollection(${Items});
	var itemCollectionView = new ItemCollectionView({el : $("#itemCollectionContainer"), collection: itemCollection});
	var benderView = new BenderView({collection: itemCollection});
</script>
