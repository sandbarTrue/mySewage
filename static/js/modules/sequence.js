define(['../lib/jquery'],function($){
	var sequence = {
		init: function(){
			sequence.initData();
			$('#complex').on('click',sortInComplex);
			$('#name').on('click',sortByName);
			$('#turnover').on('click',sortByTurnover);
		},
		initData: function(){
			var companies = new Array();
			$.getJSON("XXX.json",function(data){
				data = new Array();
				data.forEach(function(index,id){
					try{
						companies[index] = data[index];
					}
					catch(e){
						if(e === forEach.break)
							return;
						else
							throw e;
					}
				});
			});
		},
		sortInComplex: function(companies){
			var len = companies.length,temp;
			var score = fucntion(a,b){
				var a = companies.obj.turnover;
				var b = companies.obj.score;
				return 0.8*a + 0.2*b;
			}
			for(var i=0;i<len-1;i++){
				for(var j=len-1;j>0;j--){
					if(companies[j].score()<companies[j-1].score()){
						temp = companies[j];
						companies[j] = companies[j-1];
						companies[j-1] = temp;
					}
				}
			}
			return companies;
		},
		sortByName: function(companies){
			companies.sort(function(a,b){
				for(var i=0;i<companies.length-1;i++){
					a = companies[i].comName;
					b = companies[i+1].comName;
					if(a<b)
						return -1;
					if(a>b)
						return 1;
				}
			});
			return companies;
		},
		sortByTurnover: function(companies){
			var len = companies.length,temp;
			for(var i=0;i<len-1;i++){
				for(var j=len-1;j>0;j--){
					if(companies[j].obj.turnover < companies[j-1].obj.turnover){
						temp = companies[j];
						companies[j] = companies[j-1];
						companies[j-1] = temp;
					}
				}
			}
			return companies;
		}
	}

	return sequence.init;

})