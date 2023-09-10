function loginRegisterSwitch() {
	$('form').animate({	height: 'toggle', opacity: 'toggle'	}, 'slow');
}

function showAlert() {
	$('.alert-success').show();
}

$('.message a').click(function() {
	loginRegisterSwitch();
});

$('button.register').click(function() {
	var firstName = $('.register-form .firstName').val();
	var lastName = $('.register-form .lastName').val();
	var email = $('.register-form .email').val();
	var password = $('.register-form .password').val();
	var confirmPassword = $('.register-form .confirmPassword').val();

	if ($('[name=accessType]')[0].checked) {
    	var accessType = 'user';
    } else if ($('[name=accessType]')[1].checked) {
    	var accessType = 'admin';
    }
	
	if (firstName == '' || lastName == '' || email == '' || password == '' || confirmPassword == '') {
		alert('Fill in all required fields');
	} else if ((password.length) < 6) {
		alert('Password must be at least 6 characters long');
	} else if (!(password).match(confirmPassword)) {
		alert("The entered passwords do not match. Try again");
	} else {
		var regFormUser = {
			firstName: firstName,
			lastName: lastName,
			email: email,
			password: password,
			accessType: accessType		
		};

		$.post('registering', regFormUser, function(data) {
			if (data === 'Success') {
				$('.register-form').trigger('reset');
				$('.login-form').trigger('reset');
				loginRegisterSwitch();
				showAlert();
			}			
		});
	}
});

$('button.login').click(function() {
	var email = $('.login-form .email').val();
	var password = $('.login-form .password').val();

	if (email == '' || password == '') {
		alert('Fill the authorization form');
	} else {
		var loginFormUser = {
			login: email,
			password: password
		};

		$.post('logging', loginFormUser, function(data) {
			if (typeof(data) === 'object') {
				var customUrl = '';
				var urlContent = window.location.href.split('/');
			
				for (var i = 0; i < urlContent.length - 1; i++) {
					customUrl += urlContent[i] + '/';
				}
				window.location = customUrl + data.destinationUrl;
			} else {
				alert(data);
				$('.login-form').trigger('reset');
			}
		});
	}
});