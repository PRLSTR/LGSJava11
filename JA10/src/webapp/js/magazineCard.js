$('button.openModal').click(function() {
	$('#subPeriod').html($('.subPeriod').val());
});

$('button.buy-magazine').click(function() {
	var magazineID = jQuery(this).attr('magazineID');
	var subPeriod = $('.subPeriod').val();
	
	$.post('subscribe', {'magazineID': magazineID, 'subPeriod': subPeriod},
			function(data) {
				if (data == 'Success') {
					$('#buyMagazineModal').hide();
					$('.modal-backdrop').remove();
					alert('Your magazine subscription has been successfully added to your cart');
				}
			});
});
