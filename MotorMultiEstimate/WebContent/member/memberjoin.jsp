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
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>

<script type="text/javascript">
var util = new YeoUtil(); 

var idAjaxCheck = false;
var aliasAjaxCheck = false;
var sellerAjaxCheck = false;

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
			util.getHttp("post", "MemberUtil", "opt=2&user_alias="+useralias, "text", function (data){
				if(data == "true")
				{
					alert("이미 있는 닉네임 입니다.");
					$("#user_alias").val("");
				}
				else
				{
					alert("사용 가능한 닉네임 입니다.");
					aliasAjaxCheck = true;
				}
			});
		}	
	});
	
	$("#seller_num_overlap").on("click",function()
	{
		var sellernum = $("#seller_num").val();
		if(sellernum.length > 0)
		{
			util.getHttp("post","MemberUtil", "opt=3&seller_num="+sellernum, "text", function (data){
				if(data == "true")
				{
					alert("이미 있는 사업자 번호 입니다.");
					$("#user_alias").val("");
				}
				else
				{
					alert("사용 가능한 사업자 번호 입니다.");
					sellerAjaxCheck = true;
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
		<td style="text-align:right;width:180px;">아이디&nbsp;:&nbsp;&nbsp;</td><td><input type="text" name="user_id" id="user_id" value="" />&nbsp;<input type="button" id="idoverlap" value="중복체크" /></td>
	</tr>
	<tr>
		<td style="text-align:right;width:180px;">암호&nbsp;:&nbsp;&nbsp;</td><td><input type="password" name="user_pw" id="user_pw" value="" /></td>
	</tr>
	<tr>
		<td style="text-align:right;width:180px;">암호 확인&nbsp;:&nbsp;&nbsp;</td><td><input type="password" id="user_pw_check" value="" /></td>
	</tr>
	<tr>
		<td style="text-align:right;width:180px;">이름&nbsp;:&nbsp;&nbsp;</td><td><input type="text" name="user_name" id="user_name" value="" /></td>
	</tr>
	<tr>
		<td style="text-align:right;width:180px;">닉네임&nbsp;:&nbsp;&nbsp;</td><td><input type="text" id="user_alias" name="user_alias" value="" />&nbsp;<input type="button" id="aliasoverlap" value="중복체크" /></td>
	</tr>
	<tr>
		<td style="text-align:right;width:180px;">전화번호&nbsp;:&nbsp;&nbsp;</td>
		<td>
			<input type="text" name="user_mobile1" id="user_mobile1" value="" size="3" maxlength="3" />
			-<input type="text" name="user_mobile2" id="user_mobile2" value="" size="5" maxlength="4" />
			-<input type="text" name="user_mobile3" id="user_mobile3" value="" size="5" maxlength="4" />
		</td>
	</tr>
	
	<tr>
		<td style="text-align:right;width:180px;">주거지역&nbsp;:&nbsp;&nbsp;</td>
		<td>
			<select name="user_address" id="user_address"></select>
			
			<script type="text/javascript">
				util.setEle( '${area_info}',  "user_address");
			</script>
		</td>
	</tr>
	
	<tr>
		<td style="text-align:right;width:180px;">선호브랜드&nbsp;:&nbsp;&nbsp;</td>
		<td>
			<select name="user_brand" id="user_brand"></select>
			
			<script type="text/javascript">
				util.setEle( '${brand_info}',  "user_brand");
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
						&nbsp;<input type="button" onclick="util.getPost('seller_post', 'seller_address1')" id="" value="우편번호" />
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
							util.setEle( '${product_info}',  "seller_product_type");
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
