define(['../lib/jquery'],['../util/funcTpl'],['../lib/jRate'],function($, funcTpl, jRate){
	var load = {
		init: function(){
			var htmlstr = funcTpl(load.loadTpl);
			$('.btn-load').on('click',loadMore);
		},
		loadTpl: function(){
			/**/
		},
		loadMore: function(){
			$('article').append(htmlstr);
		}
	}
	return load.init;
});