define(['../lib/jquery'],['../util/funcTpl'],['../lib/jRate'],function($, funcTpl, jRate){
	var score = {
		init: function(){
			var htmlstr = funcTpl(score.scoreTpl);
			$('.score').append(htmlstr);
			score.showScore();
		},
		scoreTpl: function(){
			/*
			<table>
				<tr>
					<td rowspan="5">
						<img src="../img/test.png" />
					</td>
					<td>
						<h3>星光大道</h3>
					</td>
					<td></td>
					<td rowspan="4">
						<h2 id="score"></h2>
					</td>
				</tr>
				<tr>
					<td>
						<h5>成交额：38387&nbsp;元</h5>
					</td>
					<td></td>
				</tr>
				<tr>
					<td>
						<h5>员工数：3&nbsp;位</h5>
					</td>
					<td></td>
				</tr>
				<tr>
					<td>
						<h5>技术指标：A+</h5>
					</td>
					<td></td>
				</tr>
				<tr>
					<td width="600px" colspan="2">
						<h5>简介：Lorem ipsum dolor sit amet, consectetur adipisicing elit. Voluptatem dolorum molestias reprehenderit rem officiis quis animi magni, a quisquam mollitia fuga eveniet atque delectus vel ducimus! Eveniet, quis, commodi? Velit?</h5>
					</td>
					<td>
						<div id="jRate" class="jRate"></div>

					</td>
				</tr>
				<tr>
					<td>
						<h6>入住时间：2018年11月1日</h6>
					</td>
					<td></td>
					<td></td>
					<td>
						<a href="about.html">详情</a>
					</td>
				</tr>
			</table>
			 */
		},
		showScore: function(){
			var that = this;
			$("#jRate").jRate({	
				rating: $.getJSON("XXX.json",function(data){
					return data.score;
				}),
				endColor: 'yellow',	
				width: 16,
				height: 16,
				shapeGap: '2px',
				max: 10,
				precision: 0.5,
				readOnly: true,
			});
		}
	}

	return score.init;
})