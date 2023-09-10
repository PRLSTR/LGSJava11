$('form').on('reset', function() {
	$('input.publishDate').attr('type', 'text')
})

function up(e) {
	if (e.value.indexOf('.') != '-1') {
		e.value = e.value.substring(0, e.value.indexOf('.') + 3);
	}
}

function nullize(e) {
	if (e.value == '') {
		e.value = ''
	} else if (!e.value.includes('.')) {
		e.value += '.00'
	} else if (e.value.split('.')[1].length < 2) {
		e.value += '0'
	}
}

$('button.register').click(function() {
	var title = $('.title').val();
	var description = $('.description').val();
	var publishDate = $('.publishDate').val();
	var subPrice = $('.subPrice').val();
	
	var subPriceCents = subPrice.split('.')[0] + subPrice.split('.')[1]
	
	if (title == '' || description == '' || publishDate == '' || subPrice == '') {
		alert('Fill in all required fields');
	} else {
		var regFormMagazine = {
			title: title,
			description: description,
			publishDate: publishDate,
			subPrice: subPriceCents						
		};

		$.post('magazine', regFormMagazine, function(data) {
			$('form').trigger('reset');
			alert(data)			
		});
	}
});