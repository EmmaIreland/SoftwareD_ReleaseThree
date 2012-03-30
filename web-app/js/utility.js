/* 
 * Place miscellaneous, general-purpose functions here.
 * Import this file when needed.
 */

function arrMax(array) {
	var max = -Infinity;
	$.each(array, function(index, ele) {
		if (ele > max) max = ele;
	});
	return max;
}