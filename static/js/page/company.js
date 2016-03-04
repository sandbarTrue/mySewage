require(['../modeules/sequence'],['../modules/cards'],['../modeules/load']function(sort,cards,load){
	
	var company = {
		init: function(){
			sequence();
			cards();
			load();
		}
	}

	company.init();

})