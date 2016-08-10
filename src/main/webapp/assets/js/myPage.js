function myUpdate(){
	alert("정말 수정하시겠습니까?")
}



$(document).ready(function() {



	$('ul.nav-color li').click(function(){
		var myPageCategory;
		myPageCategory=$(this).val();
		alert(myPageCategory)
		$('ul.nav-color li').removeClass("active")
		$(this).addClass("active"); 
	});
});


