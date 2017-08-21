//榧犳爣涓婁笅缈绘粴瑙﹀姩浜嬩欢锛屾帶鍒惰繑鍥為椤垫寜閽樉绀�
document.onmousewheel = function mousescroll(){	
	var returntop = document.getElementById("return-top");
	if(document.body.scrollTop > 800){
		returntop.setAttribute("style", "visibility:show;");
	}else{
		returntop.setAttribute("style", "visibility:hidden;");
	}
		
};
//addLoadEvent(mousescroll);

//
var returntop = document.getElementById("return-top");
returntop.onClick = function(){
	//var returntop = document.getElementById("return-top");
	returntop.setAttribute("style", "visibility:hidden;");
};


/**閾炬帴鎻愮ず閮ㄥ垎鐨刯s***/
var currentSelIndex = -1;
        var oldSelIndex = -1;

function selectItem(keyword, event) {
            if (keyword == "") {
                document.getElementById("ulItems").style.display = "none";
                return;
            }
            else {
            	$("#ulItems li").remove();
            	var name= document.getElementById('txtKeyword').value;
        		$.ajax( {
        			url : '../search/meetingTitle',
        			type : 'post',
        			data:{
        				'name':name
        			},
        			dataType : 'json',
        			success : function(data) { //data:服务器端返回给浏览器端的数据
        				for(var i=0;i<data.meetingList.length;i++){
        					var li1 ="";
        					if(data.meetingList[i].zhongwen_name!=null){
        						li1="<li><a href=\"../allInfo/meetingDetail?id="+data.meetingList[i].huiyi_id +"\">" +data.meetingList[i].zhongwen_name + "</a></li>";
        					}else if(data.meetingList[i].yingwen_name!=null){
        						li1="<li><a href=\"../allInfo/meetingDetail?id="+data.meetingList[i].huiyi_id +"\">" +data.meetingList[i].yingwen_name + "</a></li>";
        					}
        					 
        					$("#ulItems").append(li1);
        				}
        				document.getElementById("ulItems").style.display = "block";
        			},
        			error : function(XMLHttpRequest, textStatus, errorThrown) {
        				alert("失败了……" + errorThrown);
        			}
        		});
            
                if ((event.keyCode == 38 || event.keyCode == 40) && document.getElementById("ulItems").style.display != "none") {
                    if (liLength > 0) {
                        oldSelIndex = currentSelIndex;
                        //涓婄Щ
                        if (event.keyCode == 38) {
                            if (currentSelIndex == -1) {
                                currentSelIndex = liLength - 1;
                            }
                            else {
                                currentSelIndex = currentSelIndex - 1;
                                if (currentSelIndex < 0) {
                                    currentSelIndex = liLength - 1;
                                }
                            }
                            if (currentSelIndex != -1) {
                                document.getElementById("li_" + currentSelIndex).style.backgroundColor = "#cbf3fd";
                                document.getElementById("txtKeyword").value = document.getElementById("li_" + currentSelIndex).innerText;
                            }
                            if (oldSelIndex != -1) {
                                document.getElementById("li_" + oldSelIndex).style.backgroundColor = "#ffffff";
                            }
                        }
                        //涓嬬Щ
                        if (event.keyCode == 40) {
                            if (currentSelIndex == liLength - 1) {
                                currentSelIndex = 0;
                            }
                            else {
                                currentSelIndex = currentSelIndex + 1;
                                if (currentSelIndex > liLength - 1) {
                                    currentSelIndex = 0;
                                }
                            }
                            if (currentSelIndex != -1) {
                                document.getElementById("li_" + currentSelIndex).style.backgroundColor = "#cbf3fd";
                                document.getElementById("txtKeyword").value = document.getElementById("li_" + currentSelIndex).innerText;
                            }
                            if (oldSelIndex != -1) {
                                document.getElementById("li_" + oldSelIndex).style.backgroundColor = "#ffffff";
                            }
                        }
                    }
                }
                else if (event.keyCode == 13) {
                    if (document.getElementById("ulItems").style.display != "none" && liLength > 0 && currentSelIndex != -1) {
                        document.getElementById("txtKeyword").value = document.getElementById("li_" + currentSelIndex).innerText;
                        document.getElementById("ulItems").style.display = "none";
                        currentSelIndex = -1;
                        oldSelIndex = -1;
                    }
                }
                else {
                    autoComplete(keyword);
                    document.getElementById("ulItems").style.display = "block";
                    currentSelIndex = -1;
                    oldSelIndex = -1;
                }
    }            
}

function autoComplete(keyword) {

    }

var textinput = document.getElementById("txtKeyword");
textinput.onblur = function(){
    
    window.setTimeout(yingcang, 300);
    window.clearTimeout(t1);//去掉定时器 
};

function yingcang(){
    document.getElementById("ulItems").style.display = "none";
}



