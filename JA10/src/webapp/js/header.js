$(document).ready(function() {
	$('.leftmenutrigger').on('click', function(e) {
		$('.side-nav').toggleClass('open');
		e.preventDefault();
	});
});

$(document).ready(function() {
	$.get("accessType", function(data) {
		if (data !== '') {
			accessType = data;
		}
	}).done(function() {
		switch (accessType) {
		case 'ADMIN':
			$('li.subscribes').hide();
			break;
		case 'USER':
			$('li.create-magazine').hide();
			break;
		}		
	});
}); 

function logout() {
	$.get('loggingOut', function(data) {
		if (typeof (data) != '') {
			var customUrl = '';
			var urlContent = window.location.href.split('/');

			for (var i = 0; i < urlContent.length - 1; i++) {
				customUrl += urlContent[i] + '/';
			}
			window.location = customUrl + data;
		}
	});
}