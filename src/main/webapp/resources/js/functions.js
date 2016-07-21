$(document).ready(function() {
	$('#driv-exp').slider({
		formatter: function(value) {
			return value + ' years';
		}
	});
});