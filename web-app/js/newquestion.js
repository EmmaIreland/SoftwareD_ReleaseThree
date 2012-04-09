$(function() {
	// onLoad
	updateQuestionType($("#typeDropdown"));
	$(document).on("keyup", ".addOnChange", function(x) {
		var ele = $(x.target);
		if (ele.val().length > 0 && ele.next().length == 0) {
			ele.after("<br /><input type='text' name='" + ele.attr('name') + "' class='addOnChange' />");
		}
	});
});

function showQuestionFields() {
	$("#newQuestionFields").show();
}

function addQuestion() {
	if (validateNewQuestionForm()) {
		var ajaxData = $("#newQuestionForm").serialize();
		jQuery.post("../addQuestion/", ajaxData, function(response) {
			$("#newQuestionForm").parent().append("<span id='" + response.id + "'><br> </span>");
			$("#" + response.id).append($(".deleteIcon").first().clone().show());
			$("#" + response.id).append('<a href="../../question/show/' + response.id + '"> ' + response.prompt + '</a>');
		});
		$("#newQuestionFields").hide();
		clearNewQuestionFields();
	}
}

function clearNewQuestionFields() {
	$("#newQuestionFields").find("[type='text']").val("");
	$("#newQuestionFields").find("[type='text']").not("[id*='Prompt'], [id*='choice']").remove();
	$("#newQuestionFields").find("br").remove();
	$(".errors").hide();
}

function updateQuestionType(dropdown) {
	var type = $(dropdown).val();
	$(".typeRow").hide();
	switch(type) {
	case "existing":
		$("#existingRow").show();
		break;
	case "checkbox":
		$(".checkboxRow").show();
		break;
	case "multipleChoice":
		$(".multipleRow").show();
		break;
	case "shortResponse":
		$(".shortRow").show()
		break;
	case "longResponse":
		$(".longRow").show()
		break;
	}
}

function validateNewQuestionForm() {
	var visiblePromptValue = $("[name$='Prompt']").filter(":visible").val();
	if (visiblePromptValue != null && visiblePromptValue.length < 1) {
		$(".errors").html("Please enter a prompt.");
		$(".errors").parent().parent().show();
		return false;
	}

	var visibleChoices = $("[name$='Choices']").filter(":visible");
	var choicesLengths = visibleChoices.map(function() { return $(this).val().length; }).get();
	// .get() returns a "real" JS array that we can use native functions on (e.g. every())
	var allEmpty = choicesLengths.every(function(len) { return len < 1; });
	if (allEmpty && visibleChoices.length > 0) {
		$(".errors").html("Please enter at least one valid choice.").show();
		$(".errors").parent().parent().show();
		return false;
	}
	return true;
}


function deleteQuestion(image) {
	if (confirm("Are you sure you want to remove this question from the survey?")) {
		questionId = $(image).parent().attr("id");
		jQuery.post("../../question/delete/" , {id: questionId});
		$(image).parent().remove();
	}
}