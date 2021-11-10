//JS
function checkWrite(){
	document.getElementById("nameDiv").innerText="";
	document.getElementById("idDiv").innerText="";
	document.getElementById("pwdDiv").innerText="";
	document.getElementById("repwdDiv").innerText="";
	
	if(document.writeForm.name.value==""){
		document.getElementById("nameDiv").innerText="이름을 입력하세요.";
	}else if(document.writeForm.id.value==""){
		document.getElementById("idDiv").innerText="아이디를 입력하세요.";
	}else if(document.writeForm.pwd.value==""){
		document.getElementById("pwdDiv").innerText="비밀번호를 입력하세요.";
	}else if(document.writeForm.repwd.value != document.writeForm.pwd.value){
		document.getElementById("repwdDiv").innerText="비밀번호가 다릅니다."
	}else{
		document.writeForm.submit();
	}
}

//jQuery
$(function(){
	$('#checkBtn').click(function(){
		$('#nameDiv').empty();
		$('#idDiv').empty();
		$('#pwdDiv').empty();
		$('#repwdDiv').empty();
		
	/*	if($('#name').val()==''){
			$('#nameDiv').html('이름을 입력하세요.');
		}//id속성 */
		
		if($('input[name="name"]').val() ==''){
			$('#nameDiv').html('이름을 입력하세요.');
		}else if($('input[name="id"]').val() ==''){
			$('#idDiv').html('아이디를 입력하세요.');
		}else if($('input[name="pwd"]').val()==''){
			$('#pwdDiv').html('비밀번호를 입력하세요.');
		}else if($('input[name="pwd"]').val()!=$('input[name="repwd"]').val()){
			$('#repwdDiv').html('비밀번호가 다릅니다.');
		}else{
			$('form[name="writeForm"]').submit();
		}
	});
	
	$('#loginBt').click(function(){
		$('#idDiv').empty();
		$('#pwdDiv').empty();
		
		if($('input[name="id"]').val()==''){
			$('#idDiv').html('아이디를 입력하세요');
		}else if($('input[name="pwd"]').val()==''){
			$('#pwdDiv').html('비밀번호를 입력하세요');
		}else{
			$('form[name=loginForm]').submit();
		}
	});
	
	
});