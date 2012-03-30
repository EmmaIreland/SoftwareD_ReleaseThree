var draggedSpan = null;
var parentUL = null;

function handleDragStart(e) {
	draggedSpan = this;
	parentUL = $(this).parent().parent();
	
	var dt = e.originalEvent.dataTransfer;

	dt.effectAllowed = 'move';
  	dt.setData('text/html', $(this).parent().html());
}
			
function handleDragOver(e) {
	if (e.preventDefault) {
		e.preventDefault(); // Necessary. Allows us to drop.
	}

 	e.originalEvent.dataTransfer.dropEffect = 'move'; 
	return false;
}

function handleDragEnter(e) {
	this.classList.add('over');
}

function handleDragLeave(e) {
	this.classList.remove('over'); 
}
			
function handleDrop(e) {
	if (e.stopPropagation) {
		e.stopPropagation(); // stops the browser from redirecting.
  	}
  	

  
	if (this != parentUL) {
		$(this).append('<li>' + e.originalEvent.dataTransfer.getData('text/html') + '</li>');		
		var newSpan = $(this).find('span').last();
		newSpan.on({"dragstart": handleDragStart,
			"dragend": handleDragEnd
		});
		
		$(draggedSpan).parent().remove();
		
		var group_id = $(this).attr("id");
		var student_id = $(newSpan).attr("id");
		var oldgroup_id = $(parentUL).attr("id");
 	
		jQuery.post("../team/changeMember/" , {g_id: group_id.substr(1), s_id: student_id.substr(1), o_id: oldgroup_id.substr(1)});
		
		equalizeGroupHeights();
	} 

  return false;
}

function handleDragEnd(e) {
	$("ul.students").removeClass('over');
}

function deleteGroup(event) {
	var groupSpaceToDelete = $(this).parent();
	var members = groupSpaceToDelete.find("li");
	var length = members.length;
	for ( var i = 0; i < length; i++ ) {
		putStudentBack(members[i]);
	}
	
	// deleting group in database, also deletes memberships
	var groupId = groupSpaceToDelete.find("ul").first().attr("id").substr(1);
	jQuery.post("../team/delete/", {id: groupId});
	
	groupSpaceToDelete.remove();
	
	if ($(".group").length < 1) {
		$("#groupArea").html("<h2>No groups have been created for this project.</h2>");
	}
}

function putStudentBack(student_li) {
	// to be used in deleting groups
	// For some reason #g0 is the unassigned student list (JOE BEAVER!!!!!!!)
	$("#g0").append(student_li);
}

function setup() {		
	$("span.student").on({"dragstart": handleDragStart,
		"dragend": handleDragEnd
	});
	$("ul.students").on({"dragenter": handleDragEnter,
		"dragover": handleDragOver,
		"dragleave": handleDragLeave,
		"drop": handleDrop
	});
	$("img.deleteButton").on({"click": deleteGroup});
	
	equalizeGroupHeights();
	equalizeGroupWidths();
}

function equalizeGroupHeights() {
	var maxULHeight = 0;
	var minULHeight = 1000;
	$("div.group ul.students").each(function() {
		if (maxULHeight < $(this).height()) maxULHeight = $(this).height();
	});
	
	$("div.group ul.students").each(function() {
		$(this).parent().height(maxULHeight + 35);
	});
}

function equalizeGroupWidths() {
	var groupWidths = $.makeArray($(".group")).map(getInnerWidth);
	var studentWidths = $.makeArray($(".student")).map(getInnerWidth);
	$(".group").css("width", Math.max(arrMax(groupWidths), arrMax(studentWidths)));
}

function getInnerWidth(x) {
	return $(x).innerWidth();
}

$(function() {
	// on page load
	setup();
});