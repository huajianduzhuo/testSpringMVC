/**
 * @author myj
 */

$(function(){
	/**
	 * 点击查询
	 */
	$("#queryBtn").click(function(){
		var productName = $("#productName").val();
		var catId = $("#cat_id").val();
		$.post("product_query.do", {catId: catId, productName: productName}, function(data){
			$("#proListTableBody").html('');
			if(data.length == 0){
				$("#proListTableBody").html('<tr><td colspan="3">没有符合条件的商品，更换查询条件试一试</td></tr>');
			}else{
				var tbodyhtml = "";
				for(var i=0;i<data.length;i++){
					tbodyhtml += "<tr><td><a target=\"_blank\" href=\"product_view/"+data[i].id+".do\">"+data[i].name+"</a></td><td>"+data[i].description+"</td><td>"+data[i].price+"元</td></tr>";
				}
				$("#proListTableBody").html(tbodyhtml);
			}
		},"json");
	});
});

/**
 * 选择商品类别
 * @param catId
 */
function catSelect(catId, li){
	$("#cat_id").val(catId);
	var catlis = $("#catUl li");
	for(var i=0;i<catlis.length;i++){
		catlis[i].style.backgroundColor = "#fff";
	}
	li.style.backgroundColor = "#87cefa";
	$("#queryBtn").click();
}

/**
 * li鼠标移入触发
 * @param li
 */
function limouseover(li){
	li.style.backgroundColor = "#87cefa";
}

/**
 * li鼠标离开触发
 * @param li
 */
function limouseout(li){
	var catIdHide = $("#cat_id").val();
	if(catIdHide != li.value){
		li.style.backgroundColor = "#fff";
	}
}

