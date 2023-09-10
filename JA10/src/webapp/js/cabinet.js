var magazines = null;

$.get('magazines', function(data) {
	if (data !== '') {
		magazines = data;
	}
}).done(function() {
			var cardsContent = '';
			
			jQuery.each(magazines, function(i, value) {
				var publishDateFormatted = ('0' + value.publishDate.day).slice(-2) + "." + ('0' + value.publishDate.month).slice(-2) + "." + value.publishDate.year;
				
				cardsContent += "<div class='col-3'>"
					    + "<div class='card'>"
						+ "<div class='card-body'>"
						+ "<h5 class='card-title'>"	+ value.title + "</h5>"
						+ "<h6 class='card-subtitle mb-2 text-muted'>" + value.subPrice / 100 + " $" + "</h6>"
						+ "<p class='card-text'>" + value.description + "</p>"
						+ "<p class='card-text'>" + "Publish date: <br>" + publishDateFormatted + " г." + "</p>"
						+ "<a href='magazine?id=" + value.id + "' class='card-link'>Open</a>"
						+ "</div>"
						+ "</div>"
						+ "</div>"						
			});

			$('.magazineCards').html(cardsContent);
		}).done(function() {
			$.get("accessType", function(data) {
				if (data !== '') {
					accessType = data;
				}
			}).done(function() {
				if(accessType === 'ADMIN') {
					$('a.card-link').hide();
				}
			});
		});