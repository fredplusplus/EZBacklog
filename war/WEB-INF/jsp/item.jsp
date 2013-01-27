<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<%@page contentType="text/html" pageEncoding="utf-8"%>

<script type="text/javascript" src="/static/js/Item.js" ></script>
<item:itemAggregation />
	
<div class="container-fluid">
	<div class="row-fluid" >
		<div class="span5" id="itemCollectionContainer"></div>
		<div class="span6">
			<div class="row-fluid" id="breadCrumbContainer"></div>
			<div class="row-fluid">
				<div class="span12" id="itemDetailContainer"></div>
			</div>
			<div class="row-fluid">
				<div class="span12 hide" id="itemTabsContainer">
					<tab:itemTabs />
				</div>
			</div>
		</div>
	</div>
</div>
<item:breadcrumb />
<ez:bender />
<script type="text/template" id="itemCollectionTemplate" >
	<item:itemList status="Open"/>
	<item:itemList status="Resolved"/>
	<item:itemList status="Deleted"/>
</script>
<modal:updateProgressModal />
<item:itemDetail/>
<tab:descriptionTab />

<script type="text/javascript">
	var itemCollection = new ItemCollection(${Items});
	var itemCollectionView = new ItemCollectionView({el : $("#itemCollectionContainer"), collection: itemCollection});
	var benderView = new BenderView({collection: itemCollection});
</script>
