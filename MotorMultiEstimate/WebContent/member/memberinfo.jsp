<%@page import="jdk.nashorn.internal.parser.JSONParser"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Map"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<style>  

.userjoininfo
{
	border-collapse:collapse; 
}
.userinfo th, .userinfo td { border:0px solid black; }
</style>

<script type="text/javascript" src="js/jquery-3.4.1.js"></script>
<script type="text/javascript" src="js/YeoUtil.js"></script>

<script type="text/javascript">

var idAjaxCheck = false;
var aliasAjaxCheck = false;
var sellerAjaxCheck = false;

var util = new Util();


$(document).ready(function(){
	
	$("#idoverlap").on("click",function()
	{
		var userid = $("#user_id").val();
		if(userid.length > 0)
		{
			
			util.getHttp("post","MemberUtil", "opt=1&user_id="+userid, "text", function (data){
				if(data == "true")
				{
					alert("이미 있는 아이디 입니다.");
					$("#user_id").val("");
				}
				else
				{
					alert("사용 가능한 아이디 입니다.");
					idAjaxCheck = true;
				}
			});
				
		}
		
	});
	
	$("#aliasoverlap").on("click",function()
	{
		var useralias = $("#user_alias").val();
		if(useralias.length > 0)
		{
			$.ajax({
				method:"post",
				url:"MemberUtil",
				data:{opt:2, user_alias:useralias},
				dataType:"text", 
				success:function(_data, _status, _xhr)
				{
					if(_data == "true")
					{
						alert("이미 있는 닉네임 입니다.");
						$("#user_alias").val("");
					}
					else
					{
						alert("사용 가능한 닉네임 입니다.");
						aliasAjaxCheck = true;
					}
				},
				error:function(_xhr, _status, _error){
					console.log("ERROR : "+_status);
					console.log("ERROR : "+_error);
				}
			});
		}
	});
	
	$("#seller_num_overlap").on("click",function()
	{
		var sellernum = $("#seller_num").val();
		if(sellernum.length > 0)
		{
			$.ajax({
				method:"post",
				url:"MemberUtil",
				data:{opt:3, seller_num:sellernum},
				dataType:"text", 
				success:function(_data, _status, _xhr)
				{
					if(_data == "true")
					{
						alert("이미 있는 사업자 번호 입니다.");
						$("#user_alias").val("");
					}
					else
					{
						alert("사용 가능한 사업자 번호 입니다.");
						sellerAjaxCheck = true;
					}
				},
				error:function(_xhr, _status, _error){
					console.log("ERROR : "+_status);
					console.log("ERROR : "+_error);
				}
			});
		}
	});
	
	
	$("#user_mobile1").on("keyup", function(){checkNum(this);});
	
	$("#user_mobile2").on("keyup", function(){checkNum(this);});

	$("#user_mobile3").on("keyup", function(){checkNum(this);});
	
	$("#seller_check_seller").on("click", function(){
		$("#sellerMenu").show();
	});
	
	$("#user_check_seller").on("click", function(){
		$("#sellerMenu").hide();
	});
	
	$("#formreset").click(function(){
		$("#sellerMenu").hide();
	});
	
	$("#sellerMenu").hide();
	
});	
	

	function checkNum(_id)
	{
		$(_id).val( $(_id).val().replace(/[^0-9]/gi,"") );
	}
	
	function user_submit()
	{
		
		var user_id = $("#user_id").val();
	
		var user_pw =  $("#user_pw").val();
		var user_pw_check = $("#user_pw_check").val();
		var user_name = $("#user_name").val();
		var user_alias = $("#user_alias").val();
		
		var user_mobile1 =  $("#user_mobile1").val();
		var user_mobile2 =  $("#user_mobile2").val();
		var user_mobile3 =  $("#user_mobile3").val();
        var user_address =  $("#user_address").val();
        var user_brand =  $("#user_brand").val();
        
        var seller_check =  $("#seller_check").val();
        
        var seller_num =  $("#seller_num").val();
        var seller_name =  $("#seller_name").val();
        var seller_post =  $("#seller_post").val();
        var seller_address1 =  $("#seller_address1").val();
        var seller_address2 =  $("#seller_address2").val();
        var seller_product_type =  $("#seller_product_type").val();
		
        var sendCheck = false;
        
        if(seller_check == "1") // seller
       	{
        	if(user_id.length > 0)
           	{
            	if((user_pw.length > 0) && ( user_pw == user_pw_check))
               	{
            		if(user_mobile1.length > 0 && user_mobile2.length > 0 && user_mobile3.length > 0)
                   	{
            			if(user_name.length > 0 )
                       	{
                       		if(user_alias.length > 0 )
                   			{
                       			if(user_address.length > 0 )
                       			{
                       					if(seller_num.length > 0)
                        				{
                            				if(seller_name.length > 0)
                            				{
                            					if(seller_post.length > 0)
                                				{
                            						if(seller_address2.length > 0)
                                    				{
                            							sendCheck = true;
                                    				}else{alert("사업장 주소지를 입력 하세요");}	
                                				}else{alert("사업장 주소지를 입력 하세요");}	
                            				}else{alert("사업장 이름을 입력 하세요");}	
                        				}else{alert("사업자 번호를 입력 하세요");}	
                            		
                       			}
                   			}else{alert("사용자 닉네임을 입력 하세요!");}	
                       	}else{alert("사용자 이름을 입력 하세요!");}	
                   	}else{alert("핸드폰 번호가 입력 하세요");}	
               	}else{alert("입력 하신 패스워드를 체크해 주세요!");}	
           	}else{alert("아이디를 입력 하세요!");}	
       	}
        else //user 
       	{
        	if(user_id.length > 0)
           	{
            	if((user_pw.length > 0) && ( user_pw == user_pw_check))
               	{
            		if(user_mobile1.length > 0 && user_mobile2.length > 0 && user_mobile3.length > 0)
                   	{
            			if(user_name.length > 0 )
                       	{
                       		if(user_alias.length > 0 )
                   			{
                       			if(user_address.length > 0 )
                       			{
                       				sendCheck = true;
                            	}
                   			}else{alert("사용자 닉네임을 입력 하세요!");}	
                       	}else{alert("사용자 이름을 입력 하세요!");}	
                   	}else{alert("핸드폰 번호가 입력 하세요");}	
               	}else{alert("입력 하신 패스워드를 체크해 주세요!");}	
           	}else{alert("아이디를 입력 하세요!");}	
       	}
        
        if(!idAjaxCheck)
       	{
        	alert("아이디 중복 체크를 해 주세요!");
        	sendCheck = false;
       	}
        
        if(!aliasAjaxCheck)
       	{
        	alert("닉네임 중복 체크를 해 주세요!");
        	sendCheck = false;
       	}
        
       	if(!sellerAjaxCheck && seller_check == "1")
       	{
         	alert("사업자 번호 중복 체크를 해 주세요!");
         	sendCheck = false;
       	}
       	
    	if(sendCheck)
		{
			$("#userinfoform").attr("action", "MemberJoin");
			$("#userinfoform").attr("method", "post");
			$("#userinfoform").submit();
		}
	}
	
	function user_reset()
	{
		idAjaxCheck = false;
		aliasAjaxCheck = false;
		sellerAjaxCheck = false;
		
		$("#sellerMenu").hide();
		$("#userinfoform")[0].reset();
	}
	
</script>

</head>
<body>
<form name="userinfoform" id="userinfoform" action="" method="">
<table class="userjoininfo">
	<tr>
		<td style="text-align:right;width:180px;">아이디&nbsp;:&nbsp;&nbsp;</td><td><input type="text" name="user_id" id="user_id" value="${uDTO.user_id}" readonly />&nbsp;<input type="button" id="idoverlap" value="중복체크" /></td>
	</tr>
	<tr>
		<td style="text-align:right;width:180px;">암호&nbsp;:&nbsp;&nbsp;</td><td><input type="password" name="user_pw" id="user_pw" value="" /></td>
	</tr>
	<tr>
		<td style="text-align:right;width:180px;">암호 확인&nbsp;:&nbsp;&nbsp;</td><td><input type="password" id="user_pw_check" value="" /></td>
	</tr>
	<tr>
		<td style="text-align:right;width:180px;">이름&nbsp;:&nbsp;&nbsp;</td><td><input type="text" name="user_name" id="user_name" value="${uDTO.user_name}" /></td>
	</tr>
	<tr>
		<td style="text-align:right;width:180px;">닉네임&nbsp;:&nbsp;&nbsp;</td><td><input type="text" id="user_alias" name="user_alias" value="${uDTO.user_alias}" />&nbsp;<input type="button" id="aliasoverlap" value="중복체크" /></td>
	</tr>
	<tr>
		<td style="text-align:right;width:180px;">전화번호&nbsp;:&nbsp;&nbsp;</td>
		<td>
			<input type="text" name="user_mobile1" id="user_mobile1" value="${uDTO.user_mobile1}" size="3" maxlength="3" />
			-<input type="text" name="user_mobile2" id="user_mobile2" value="${uDTO.user_mobile2}" size="5" maxlength="4" />
			-<input type="text" name="user_mobile3" id="user_mobile3" value="${uDTO.user_mobile3}" size="5" maxlength="4" />
		</td>
	</tr>
	
	<tr>
		<td style="text-align:right;width:180px;">주거지역&nbsp;:&nbsp;&nbsp;</td>
		<td>
			<select name="user_address" id="user_address"></select>
			
			<script type="text/javascript">
				var orgArea = $.parseJSON('${area_info}') ;
				var mesgArea = "";
				
				$(document).ready(function() {
					$.each(orgArea,function(key,value){
						if(${uDTO.user_address} == key)
						{
							mesgArea += "<option value='"+ key+"' selected>"+ value+"</option>";
						}
						else
						{
							mesgArea += "<option value='"+ key+"'>"+ value+"</option>";
						}
						
				   	});
					$("#user_address").html(mesgArea);
				});
			</script>
		</td>
	</tr>
	
	<tr>
		<td style="text-align:right;width:180px;">선호브랜드&nbsp;:&nbsp;&nbsp;</td>
		<td>
			<select name="user_brand" id="user_brand"></select>
			
			<script type="text/javascript">
				var orgBrand = $.parseJSON('${brand_info}') ;
				var mesgBrand = "";
				
				$(document).ready(function() {
					$.each(orgBrand,function(key1,value2){
						if(${uDTO.user_brand} == key1)
						{
							mesgBrand += "<option value='"+ key1+"'>"+ value2+"</option>";
						}
						else
						{
							mesgBrand += "<option value='"+ key1+"'>"+ value2+"</option>";
						}
						
				   	});
					$("#user_brand").html(mesgBrand);
				});
			</script>
		</td>
	</tr>
	
	<tr>
		<td style="text-align:right;width:180px;">사용자 또는 Seller&nbsp;:&nbsp;&nbsp;</td>
		<td>
			사용자<input type="radio" id="user_check_seller" name="seller_check" value="0" checked="checked" />&nbsp;&nbsp;사업자<input type="radio" id="seller_check_seller" name="seller_check" value="1" />
		</td>
	</tr>
	
	<tr >
		<td colspan="2">
			<table class="userjoininfo" id="sellerMenu">
				<tr>
					<td style="text-align:right;width:180px;">사업자 번호&nbsp;:&nbsp;&nbsp;</td>
					<td>
						<input type="text" name="seller_num" id="seller_num" value="" />
						&nbsp;<input type="button" id="seller_num_overlap" value="중복체크" />
					</td>
				</tr>
				<tr>
					<td style="text-align:right;width:180px;">사업자 이름&nbsp;:&nbsp;&nbsp;</td>
					<td>
						<input type="text" name="seller_name" id="seller_name" value="" />
					</td>
				</tr>
				<tr>
					<td style="text-align:right;width:180px;">사업자 소재지&nbsp;:&nbsp;&nbsp;</td>
					
					<td>
						<input type="text" name="seller_post" id="seller_post" value="" size="3" readonly />
						&nbsp;<input type="button" onclick="sample4_execDaumPostcode()" id="" value="우편번호" />
					</td>
				</tr>
				
				<tr>
					<td style="text-align:right;width:180px;"></td>
					<td>
					<input type="text" name="seller_address1" id="seller_address1" value="" size="50" readonly />
					<input type="text" name="seller_address2" id="seller_address2" value="" size="50" />
					</td>
				</tr>
				
				<tr>
					<td style="text-align:right;width:180px;">판매차량 종류&nbsp;:&nbsp;&nbsp;</td>
					<td>
						<select name="seller_product_type" id="seller_product_type"></select>
						
						<script type="text/javascript">
							var orgProduct = $.parseJSON('${product_info}') ;
							var mesgProduct = "";
							
							$(document).ready(function() {
								$.each(orgProduct,function(key1,value2){
									mesgProduct += "<option value='"+ key1+"'>"+ value2+"</option>";
							   	});
								$("#seller_product_type").html(mesgProduct);
							});
						</script>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td style="text-align:right;width:180px;"></td>
		<td>
			<input type="button" onclick="user_submit()" value="보내기" />&nbsp;&nbsp;<input type="button" onclick="user_reset()" value="취소" />
		</td>
	</tr>
	
</table>
</form>

<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
    //본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
    function sample4_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 도로명 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var fullRoadAddr = data.roadAddress; // 도로명 주소 변수
                var extraRoadAddr = ''; // 도로명 조합형 주소 변수

                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraRoadAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraRoadAddr !== ''){
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                }
                // 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
                if(fullRoadAddr !== ''){
                    fullRoadAddr += extraRoadAddr;
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('seller_post').value = data.zonecode; //5자리 새우편번호 사용
                document.getElementById('seller_address1').value = fullRoadAddr;
                //document.getElementById('seller_address2').value = data.jibunAddress;
                document.getElementById('seller_address2').focus();
                //

                // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
                if(data.autoRoadAddress) {
                    //예상되는 도로명 주소에 조합형 주소를 추가한다.
                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                    //document.getElementById('guide').innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';

                } else if(data.autoJibunAddress) {
                    var expJibunAddr = data.autoJibunAddress;
                    //document.getElementById('guide').innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';

                } else {
                    //document.getElementById('guide').innerHTML = '';
                }
            }
        }).open();
    }
</script>
