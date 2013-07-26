//checkbox
function checkAll() { 
	var code_Values = document.all['code_Value']; 
	if(code_Values.length){
		for(var i=0;i<code_Values.length;i++){ 
			code_Values[i].checked = true; 
		} 
	}else{ 
		code_Values.checked = true; 
	} 
}
function uncheckAll(){ 
	var code_Values = document.all['code_Value']; 
	if(code_Values.length){ 
		for(var i=0;i<code_Values.length;i++){ 
			code_Values[i].checked = false; 
		} 
	}else{ 
		code_Values.checked = false; 
	} 
}

function multipleDelete(){ 
	var num = 0; 
	var code_Values = document.all['code_Value']; 
	if(code_Values.length){ 
		for(var i=0;i<code_Values.length;i++){ 
			if(code_Values[i].checked == true){ 
				num ++; 
			} 
		} 
	}else{ 
		if(code_Values.checked == true){ 
			num ++ ; 
		} 
	}
	if(num == 0){
		alert('Please select delete item');
	}
	if(num >0){
		document.BuCodeSearch.action = '<%=EusUtil.getPage("lookup.generic.bucode.delete.s")%>'; 
		document.BuCodeSearch.submit();
	}
}

//==========================
function checkAll(){
	var code_Values = document.getElementsByTagName("input");
	for(i = 0;i < code_Values.length;i++){
		if(code_Values[i].type == "checkbox"){
			code_Values[i].checked = true;
		}
	}
}
function uncheckAll(){
	var code_Values = document.getElementsByTagName("input");
	for(i = 0;i < code_Values.length;i++){
		if(code_Values[i].type == "checkbox"){
			code_Values[i].checked = false;
		}
	}
}


