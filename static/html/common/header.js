(function() {
	"use strict"
	var headerCss = document.getElementsByTagName('script'),
		path, cssPath, headTitle = "";

	for(var i = 0; i < headerCss.length; i++){
	    cssPath = headerCss[i].getAttribute('data-css');
		headTitle = headerCss[i].getAttribute('data-title');
		if(cssPath != null && cssPath != undefined){
			cssPath = '<link rel="stylesheet" href="/css/page/' + cssPath + '.css"/>'
			break;	
		}

	}
	var html = '<!DOCTYPE html>'+
				'<html lang="en">'+
				'<head>'+
					'<meta charset="UTF-8">'+
					'<title>'+headTitle+'</title>'+
					'<link rel="stylesheet" href="/css/lib/bootstrap.css"/>'+
					'<link rel="stylesheet" href="/css/global.css"/>'
					+cssPath+
					'<script>'+
						'var MIS = {};'+
						'MIS.STATIC_ROOT = "/js"'+
					'</script>'+
				'</head>'+
				'<body>';
				
    var headerTpl = function(){
		/*
		<div class="common-header">
			<div class="global-center common-header-bar clearfix">
				<ul class="common-header-aside login-or-register">
					<li class="common-header-item register">
						<a href="#">注册</a>
					</li>
					<li class="common-header-item login">
						<a href="#">登录</a>
					</li>
				</ul>
				<ul class="common-header-list">
					<li class="common-header-item index">
						<a href="#">
							<i class="user-home-pic inline-block"></i>
							<span class="index-font">用户主页</span>
						</a>
					</li>
					<li class="common-header-item index">
						<a href="#">
							<i class="my-order-pic inline-block"></i>
							<span class="index-font">我的订单</span>
						</a>
					</li>
					<li class="common-header-item index">
						<a href="#">
							<i class="contact-service-pic inline-block"></i>
							<span class="index-font">联系客服</span>
						</a>
					</li>
				</ul>
			</div>

			<div class="global-center common-header-main clearfix">
				<div class="common-header-logo">
					<img src="">
				</div>
				<div class="common-header-search">
					
				</div>
			</div>
		</div>
		
		*/
	};
	var  header = html + headerTpl.toString().replace(/^[^\/]+\/\*!?/, '').replace(/\*\/[^\/]+$/, '');
	document.write(header);
})();
