/**
 * 首页
 * 
 * @author wangxinyu
 */
require.config({
	baseUrl: MIS.STATIC_ROOT
});
require(['lib/jquery', 'util/request', 'modules/nav'], function($, request, nav) {
	
	var index = {
		init: function() {
			nav();
			// this.user();
		},

		user: function() {
			document.onclick = function() {
			
				request.post('/user', {}, function(res) {
		  	    	console.log(res);
		  		});
			};
		}
	};

	index.init();
});