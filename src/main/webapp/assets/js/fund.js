var count=0;
var ids=0;
var category_id;
function load_fund(category){
	alert('12345jhgug')
	alert(category);
	if(category_id==1){
	category_id=$("#step1").attr("value")
	alert(category_id);
    }
	if(category_id==2){
		category_id=$("#step2").attr("value")
		alert(category_id);
		
	}
	if(category_id==3){
		category_id=$("#step3").attr("value")
		alert(category_id);
		
	}
	


$.ajax({
	type : "GET",
	url : "fundview.do?id="+category_id+"&category="+category,
	error : function(){
		alert('통신실패!!');
	},
	dataType: "json",
	success : function(data){	
		//category_id++;
		test(data,id) ;	
	}
});
}






$('#step2li').click(function(){

	alert('test')

})


function test(data,id){



	var test2=data[1].category2;
	if(count==0){

		$("#step2>li").remove();

		for(var i in data){
			if(i==0){
				alert("i==0")
				$('ul#step2').append('<li class="img_button"  onclick="load_fund("'+data[i].category2+'")">'+data[i].category2+'</li>')
			}
			else if(i!=0&&data[i-1].category2!=data[i].category2){
				alert("i!=0")
				$('ul#step2').append('<li class="img_button" onclick="load_fund("'+data[i].category2+'")">'+data[i].category2+'</li>')
			}
		}

	}
}