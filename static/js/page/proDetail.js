/**
 * 需求详情
 * @Author : Mars
 */

/*标书状态刷新*/
var current_fs, next_fs, previous_fs;
	var left, opacity, scale;
	var animating;
	$.get("#",(function (data) {
	    if (animating)
	        return false;
	    animating = true;
	    current_fs = $(this).parent();
	    next_fs = $(this).parent().next();
	    $('#progressbar li').eq($('fieldset').index(next_fs)).addClass('active');
	    next_fs.show();
	    current_fs.animate({ opacity: 0 }, {
	        step: function (now, mx) {
	            scale = 1 - (1 - now) * 0.2;
	            left = now * 50 + '%';
	            opacity = 1 - now;
	            current_fs.css({ 'transform': 'scale(' + scale + ')' });
	            next_fs.css({
	                'left': left,
	                'opacity': opacity
	            });
	        },
	        duration: 800,
	        complete: function () {
	            current_fs.hide();
	            animating = false;
	        },
	        easing: 'easeInOutBack'
	    });
	});
	);

/*已交标书章节渲染*/
var loadBids = (function() {
  var bidSource = "#";
  $.getJSON( bidSource, {
    tags: "mount rainier",
    tagmode: "any",
    format: "json"
  })
    .done(function( data ) {
      $.each( data.items, function( i, item ) {
        $( "<img>" ).attr( "src", item.media.m ).appendTo( "#badge" );
        item.attr.Name.html("<h5>");
        item.attr.Detail.appendTo("#bidDetail");
        item.attr.Date.appendTo("#subDate");
        item.attr.Num.appendTo("#bidNum");
        if ( i === 4 ) {
          return false;
        }
      });
    });
})();