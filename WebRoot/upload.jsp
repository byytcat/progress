<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'upload.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    <form action="${pageContext.request.contextPath }/servlet/UploadServlet4" target="ceshi" enctype="multipart/form-data" method="post">
    	上传用户：<input type="text" name="username"><br/>
    	上传文件1：<input type="file" name="file1"><br/>
    <!-- 	上传文件2：<input type="file" name="file2"><br/> -->
    	<input type="submit"  onclick="formSubmit()" value="上传">
    </form>
    <iframe name="ceshi" hidden></iframe>
    <div id="progress">
    	 <div id="show" style="background:#0ff;height:26px;width:0%;"></div>  
			    </div>  
		<span id="msg"></span>
    </div>
    <script type="text/javascript" src="${pageContext.request.contextPath}/jquery-1.5.1.js"></script>
    <script type="text/javascript">
	    var progress;  
	    var uploadProcessTimer = null;  
    	function formSubmit(){
    		uploadProcessTimer = window.setInterval("getFileUploadProcess()",50);//每隔100毫秒执行callback  
           // document.forms[0].submit();  
    	}  
	   	 function getFileUploadProcess() {    
	            $.ajax({  
	                   type:"GET",  
	                   url:"<%=request.getContextPath()%>/servlet/UploadServlet5",  
	                   dataType:"text",  
	                   cache:false,  
	                   success:function(data){  
	                       if(data=="100%"){  
	                           window.clearInterval(uploadProcessTimer);  
	                           //$("#progress").html(data);
	                           $("#show").width(data);  
	                             $("#msg").text(data);  
	                       }else{  
	                            progress=data;  
	                          // $("#progress").html(data);
	                            $("#show").width(data);  
	                             $("#msg").text(data);  
	                       }  
	                   }  
	           });  
	        };  
    </script>
    
    
  </body>
</html>
