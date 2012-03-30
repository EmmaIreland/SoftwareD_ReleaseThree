/* validator.js - v0.1
 * 
 * Author: Joe Einertson
 * For: CSci 3601 Spring 12
 * 
 * JavaScript library utilizing jQuery to make form validation easier.
 * 
 * To use, put onsubmit="return validateForm(params);" in a <g:form> tag.
 * This will prevent the form from being submitted if it is invalid.
 * See comments on validateForm() for documentation of params syntax.
 * 
 * TODO: Add the ability to populate an error messages field
 */


/* validateForm(Object params)
 * params must be an object containing a map of conditions (notBlank,
 * email, etc.) to arrays of jQuery-selectable strings.
 * 
 * i.e. { notBlank: ["#name", "#description"], email: ["#email"] }
 * 
 * Possible values in params: notBlank, email, minLength, numeric, custom
 * 
 * minLength syntax: { minLength: { 3: ["#name", "#title"], 6: ["#password"] } }
 * This requires name and title to be at least 3 characters, 
 * and password to be at least 6.
 * 
 * custom syntax: { custom: { "#name": function(x) { return $(x).val() == "a"; } } }
 * This requires the value of the field name to be equal to "a" (an
 * admittedly useless validator, but since it's custom you can do whatever
 * you want)
 */
function validateForm(params, errorField) {
	var valid = true;
	if (!verifyNotBlank(params.notBlank)) valid = false;
	if (!verifyEmail(params.email)) valid = false;
	if (!verifyMinLength(params.minLength)) valid = false;
	if (!verifyNumeric(params.numeric)) valid = false;
	if (!verifyCustom(params.custom)) valid = false;

	return valid;
}


//This regexp courtesy of http://stackoverflow.com/questions/46155/validate-email-address-in-javascript
var EMAIL_REGEXP = /[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+(?:[A-Z]{2}|com|org|net|gov|mil|biz|info|mobi|name|aero|jobs|museum)\b/;


function verifyNotBlank(fields) {
	return verifyMinLength({1: fields});
}


function verifyEmail(fields) {
	return verify(fields, function(x) {
		return $(x).val().match(EMAIL_REGEXP) !== null;
	});
}


function verifyMinLength(lengths) {
	return verify(lengths, function(x, len) {
		return verify(x, function(y) {
			return $(y).val().length >= parseInt(len);
		});
	});
}


function verifyNumeric(fields) {
	return verify(fields, function (x) {
		var val = $(x).val();
		return !isNaN(parseFloat(val)) && isFinite(val);
	});
}


function verifyCustom(fields) {
	return verify(fields, function(func, x) {
		return func(x);
	});
}


function verify(arr, condition) {
	for (index in arr) {
		if (!condition(arr[index], index)) return false;
	}
	return true;
}