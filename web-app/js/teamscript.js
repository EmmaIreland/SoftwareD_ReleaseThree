var defaultPosition = {left: 0, right: 0, top: 0, bottom: 0};

function handleDrop(e, ui) {
	var student = ui.draggable;
	var oldgroup_id = ui.draggable.closest("ul").attr("id");
	var group_id = $(this).find("ul").attr("id");
	var student_id = student.attr("id");
	
	if (oldgroup_id != group_id) {
		$(this).find("ul").append(student.parent());
		
		jQuery.post("../team/changeMember/" , {g_id: group_id.substr(1), s_id: student_id.substr(1), o_id: oldgroup_id.substr(1)});
	
		student.css(defaultPosition);
		equalizeGroupHeights();
		equalizeGroupWidths();
	} else {	
		student.animate(defaultPosition);
	}
}

function deleteGroup(event) {
	if (confirm("Are you sure?")) {
		
		var groupSpaceToDelete = $(this).parent();
		var members = groupSpaceToDelete.find("li");
		
		members.each(function(index, ele) {
			putStudentBack(ele);
		});
		
		// deleting group in database, also deletes memberships
		var groupId = groupSpaceToDelete.find("ul").first().attr("id").substr(1);
		jQuery.post("../team/delete/", {id: groupId});
		
		groupSpaceToDelete.remove();
		
		if ($(".group").length < 1) {
			$("#groupArea").html("<h2>No groups have been created for this project.</h2>");
		}
	}
}

function putStudentBack(student_li) {
	// to be used in deleting groups
	// For some reason #g0 is the unassigned student list (JOE BEAVER!!!!!!!)
	$("#g0").append(student_li);
}

function setupTeamManagement() {
	$(".draggable").draggable({
		revert: "invalid"
	});
	$(".droppable").droppable({
		drop: handleDrop
	});

	$("img.deleteButton").bind({ "click": deleteGroup });
	
	equalizeGroupHeights();
	equalizeGroupWidths();
}

function equalizeGroupHeights() {
//	var maxULHeight = -Infinity;
//	var minULHeight = Infinity;
//	$("div.group ul.students").each(function() {
//		if (maxULHeight < $(this).height()) maxULHeight = $(this).height();
//	});
//	
//	$("div.group ul.students").each(function() {
//		$(this).height(maxULHeight);
//	});
}

function equalizeGroupWidths() {
//	var groupWidths = $.makeArray($(".group")).map(getInnerWidth);
//	var studentWidths = $.makeArray($(".student")).map(getInnerWidth);
//	$(".group").css("width", Math.max(arrMax(groupWidths), arrMax(studentWidths)));
}

function getInnerWidth(x) {
	return $(x).innerWidth();
}

$(function() {
	// on page load
	setupTeamManagement();
});