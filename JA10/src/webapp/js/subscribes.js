function search() {
	var input, filter, table, tr, td, i, txtValue;
	input = document.getElementById('search');
	filter = input.value.toUpperCase();
	table = document.getElementById('table');
	tr = table.getElementsByTagName('tr');
	for (i = 0; i < tr.length; i++) {
		td = tr[i].getElementsByTagName('td')[0];
		if (td) {
			txtValue = td.textContent || td.innerText;
			if (txtValue.toUpperCase().indexOf(filter) > -1) {
				tr[i].style.display = '';
			} else {
				tr[i].style.display = 'none';
			}
		}
	}
}

var buckets = null;
$.get('subscribes', function(data) {
	if (data !== '') {
		buckets = data;
	}
}).done(
		function() {
			var tableContent = "<tr class='header'>"
					+ "<th style='width: 15%;'>name</th>"
					+ "<th style='width: 25%;'>description</th>"
					+ "<th style='width: 10%;'>Publish date</th>"
					+ "<th style='width: 10%;'>Subscription price</th>"
					+ "<th style='width: 10%;'>Subscription period</th>"
					+ "<th style='width: 10%;'>Subscription date</th>"
					+ "<th style='width: 5%;'>Subscription status</th>"
					+ "<th style='width: 15%;'></th>"					
					+ "</tr>";

			jQuery.each(buckets, function(i, value) {
				var publishDateFormatted = ('0' + value.publishDate.day).slice(-2) + "." + ('0' + value.publishDate.month).slice(-2) + "." + value.publishDate.year;
				var subDateFormatted = ('0' + value.subDate.day).slice(-2) + "." + ('0' + value.subDate.month).slice(-2) + "." + value.subDate.year;
				
				var subStatus;
				if (value.subStatus) {
					subStatus = "class='fas fa-check'"							
				} else {
					subStatus = "class='fas fa-times'"
				}
				
				tableContent += "<tr>"
						+ "<td>" + value.title + "</td>"
						+ "<td>" + value.description + "</td>"
						+ "<td>" + publishDateFormatted + " г." + "</td>"
						+ "<td>" + value.subPrice/100 + " $" + "</td>"
						+ "<td>" + value.subPeriod + " months" + "</td>"
						+ "<td>" + subDateFormatted + " years" + "</td>"
						+ "<td " + subStatus + ">" + "</td>"
						+ "<td><button title='Subscribe/unsubscribe' class='fa fa-shopping-cart mr-3' onclick='buyOrder(" + value.id + ")'></button>" 
						+ "<button title='Delete' class='fa fa-trash' onclick='deleteOrder(" + value.id + ")'></button></td>" + "</tr>"
			});
			$('#table').html(tableContent);
		});

function buyOrder(id) {
	var customUrl = '';
	var urlContent = window.location.href.split('/');
	
	for (var i = 0; i < urlContent.length - 1; i++) {
		customUrl += urlContent[i] + '/'
	}
	customUrl += 'subscribe?id=' + id;

	$.ajax({
		url: customUrl,
		type: 'PUT',
		success: function(data) {
			if (data !== '') {
				location.reload();
				if (data) {
					alert('Subscription completed');
				} else {
					alert('Subscription canceled');
				}
			}
		}
	});
}

function deleteOrder(id) {
	var customUrl = '';
	var urlContent = window.location.href.split('/');
	
	for (var i = 0; i < urlContent.length - 1; i++) {
		customUrl += urlContent[i] + '/'
	}
	customUrl += 'subscribe?id=' + id;

	$.ajax({
		url: customUrl,
		type: 'DELETE',
		success: function(data) {
			if (data == 'Success') {
				location.reload();
				alert('Deleted');
			}
		}
	});
}