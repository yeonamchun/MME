
YeoUtil = function()
{
	this.ajaxRequest = new XMLHttpRequest();
	this.interval = 1000;
	this.count = 0;
	this.result = "";
	this.type = "text";	
	this.callFunction = "";
	this.method = "post";
}

/*Json 타입의 key value 를 option 화*/
YeoUtil.prototype.setEle = function(_ele, _target )
{
	var This = this;
	
	var orgArea = $.parseJSON(_ele) ;
	var mesgArea = "";
	
	$(document).ready(function() {
		$.each(orgArea,function(key,value){
			mesgArea += "<option value='"+ key+"'>"+ value+"</option>";
	   	});
		$("#"+_target).html(mesgArea);
	});
}

/*숫자에 컴마*/
YeoUtil.prototype.comma = function(_num )
{
	var This = this;
	
	if(util.checkint(_num)){
		return Number(String(_num).replace(/\..*|[^\d]/g,"")).toLocaleString().slice(0,-3);
	}else{
		return false;
	}
}

/*객체의 위치값 및 사이즈*/
YeoUtil.prototype.getPoint = function(_eId )
{
	var This = this;
	try{
		_ele = document.getElementById(_eId);
		var el = _ele; 
		var left = 0;
		var top = 0;
		do{
			left += el.offsetLeft || 0;
			top += el.offsetTop || 0;
			el = el.offsetParent;
		}while (el);	
		return {'x': left, 'y': top, 'w': _ele.offsetWidth, 'h': _ele.offsetHeight};
	}catch(err){
		return false;
	}
}

/*function(_data, _status, _xhr)*/
YeoUtil.prototype.getHttp = function(_method,  _url, _param, _type , _callFunction)
{
	var This = this;
	
	This.type =_type;
	This.method = _method ? _method : "get";
	This.callFunction = _callFunction ? _callFunction : "alert";
	
	var checkBool = "false";
	try
	{
		var da = This.callFunction.substring(0,8);
		if(da == "function")
		{
			checkBool = "true";
		}
	}
	catch(exe)
	{
		checkBool = "true";
	}
	
	if(_method.toLowerCase() == "port")
	{
		This.ajaxRequest.open(_method.toLowerCase(),_url, true);
		This.ajaxRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=Utf-8;");
		This.ajaxRequest.onreadystatechange = callback;
		This.ajaxRequest.send(_param);
	}
	else
	{
		This.ajaxRequest.open(_method.toLowerCase(),_url+"?"+_param, true);
		This.ajaxRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=Utf-8;");
		This.ajaxRequest.onreadystatechange = callback;
		This.ajaxRequest.send(null);
	}	
	
	function callback()
	{
		if( This.ajaxRequest.readyState == 4 )
		{ 
			if( This.ajaxRequest.status == 200 )
			{ 
				try
				{
					if(checkBool == "true")
					{
						if(This.type == "text")
						{
							This.result = String(This.ajaxRequest.responseText);
							_callFunction(This.result);
						}
						else
						{
							This.result = This.ajaxRequest.responseXML;
							_callFunction(This.result);
						}
					}
					else
					{
						if(This.type == "text")
						{
							This.result = String(This.ajaxRequest.responseText);
							if(This.callFunction != null){
								setTimeout( This.callFunction+"("+This.result+")",0 );
							}
						}
						else
						{
							This.result = This.ajaxRequest.responseXML;
							if(This.callFunction != null){
								setTimeout( This.callFunction+"("+This.result+")",0 );
							}
						}
					}
				}
				catch(Err)
				{
					alert("AJAX Call Err");
				}
			}
		}
	}
}

/*
 * 다음 주소 검색
 */ 
YeoUtil.prototype.getPost = function(_targetPost, _targetAddress )
{
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
            document.getElementById(_targetPost).value = data.zonecode; //5자리 새우편번호 사용
            document.getElementById(_targetAddress).value = fullRoadAddr;
            
        }
    }).open();
}