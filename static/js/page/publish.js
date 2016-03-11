/**
 * 发布需求
 * @Author : Mars
 */

// require(['lib/jquery'],['lib/jquery.easing.min.js'],function($,easing) {});
var current_fs, next_fs, previous_fs;
	var left, opacity, scale;
	var animating;
	$('.next').click(function () {
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
	$('.previous').click(function () {
	    if (animating)
	        return false;
	    animating = true;
	    current_fs = $(this).parent();
	    previous_fs = $(this).parent().prev();
	    $('#progressbar li').eq($('fieldset').index(current_fs)).removeClass('active');
	    previous_fs.show();
	    current_fs.animate({ opacity: 0 }, {
	        step: function (now, mx) {
	            scale = 0.8 + (1 - now) * 0.2;
	            left = (1 - now) * 50 + '%';
	            opacity = 1 - now;
	            current_fs.css({ 'left': left });
	            previous_fs.css({
	                'transform': 'scale(' + scale + ')',
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
	$('.submit').click(function () {
	    return false;
	});

/*发布需求成功后跳转*/
var num = document.getElementById('second').innerHTML;
//获取显示秒数的元素，通过定时器来更改秒数。
function startCount() {
	document.getElementById('second').innerHTML = num;
	num = num-1;
	setTimeout("startCount()",1000);
	if (num==0){
   		location.assign("需求详情页");
	}
}

/*表单验证*/
$(function (){
    $("#msform").validate(
    {
        //验证规则
        rules :{
            proName:{
                required: true,
                minlength: 6
            },
            proDetail:{
                required: true,
                minlength: 6
            },
            proDeadline:{
            	required: true,
            	minlength: 8,
            	maxlength: 8
            },
            proBudget:{
            	required: true,
            	minlength: 2
            }
        },
        //错误提示信息
        errorPlacement: function (error, element){
            error.appendTo(element.siblings("span").css("color","red"));
        }
    }
    );
	$("[name='proName']").value.appendTo("#1");
	$("[name='briefIntro']").value.appendTo("#2");
	$("[name='proDetail']").value.appendTo("#3");
	$("[name='proDeadline']").value.appendTo("#4");
	$("[name='proBudget']").value.appendTo("#5");  
});