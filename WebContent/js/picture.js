var cur = 0;

/**
 * ����ͼƬ��ʾ
 */

function nextPicture(){
	var sum = parseInt($("#sum").html())-1;
	imgIdToHide = "img" + cur;
	cur++;
	if(cur > sum){
		cur = 0;
	}
	imgIdToShow = "img" + cur;
	$("#"+imgIdToHide+"").hide();
	$("#"+imgIdToShow+"").show();
}

function beforePicture(){
	var sum = parseInt($("#sum").html())-1;
	imgIdToHide = "img" + cur;
	cur--;
	if(cur < 0){
		cur = sum;
	}
	imgIdToShow = "img" + cur;
	$("#"+imgIdToHide+"").hide();
	$("#"+imgIdToShow+"").show();
}