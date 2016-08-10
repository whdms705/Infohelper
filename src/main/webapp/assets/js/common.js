$(function(){
	$("").hide();
	$("").mouseenter(function(){
		$("").show();
	})
})

 $(function(){
  var sBtn = $("p.rssinfo_category > span");    //  ul > li 이를 sBtn으로 칭한다. (클릭이벤트는 li에 적용 된다.)
  sBtn.click(function(){   // sBtn에 속해 있는  a 찾아 클릭 하면.
   sBtn.removeClass("cateselect");     // sBtn 속에 (active) 클래스를 삭제 한다.
   $(this).parent().addClass("cateselect"); // 클릭한 a에 (active)클래스를 넣는다.
  })
 })