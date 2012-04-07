function toggleCollapse(event) {
	if ( $(this).attr("class") == "collapsibleDiv collapsed" ) {
		$(this).attr("class", "collapsibleDiv expanded");
	} else {
		$(this).attr("class", "collapsibleDiv collapsed");
	}
}

function setupTrinkets() {
	$(".collapsibleDiv").bind({ "click": toggleCollapse });
}

$(function() {
	// on page load
	setupTrinkets();
});