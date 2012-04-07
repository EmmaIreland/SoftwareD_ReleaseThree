function toggleCollapse(event) {
	if ( $(this).parent().attr("class") == "collapsibleDiv collapsed" ) {
		$(this).parent().attr("class", "collapsibleDiv expanded");
	} else {
		$(this).parent().attr("class", "collapsibleDiv collapsed");
	}
}

function setupTrinkets() {
	$(".collapsibleDiv .header").bind({ "click": toggleCollapse });
}

$(function() {
	// on page load
	setupTrinkets();
});