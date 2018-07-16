<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>会员管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/user/shopUser/">会员列表</a></li>
		<li class="active"><a href="${ctx}/user/shopUser/form?id=${shopUser.id}">会员<shiro:hasPermission name="user:shopUser:edit">${not empty shopUser.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="user:shopUser:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" name="shopUserForm" modelAttribute="shopUser" action="${ctx}/user/shopUser/save" method="post" class="form-horizontal">
		<form:hidden path="id" id="shopUserId"/>
		<form:hidden path="status"/>
		<form:hidden path="deleted"/>
		<input type="hidden" name="avatar" id="avatar" value="${shopUser.avatar}"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">用户名</label>
			<div class="controls">
				<form:input path="userName" htmlEscape="false" maxlength="60" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">密码</label>
			<div class="controls">
				<form:input path="password" htmlEscape="false" maxlength="60" class="input-xlarge " type="password" value=""/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label font_weight">性别</label>
			<div class="controls">
				<form:radiobuttons path="gender"  items="${fns:getDictList('sex')}" itemLabel="label"  itemValue="value" htmlEscape="false" class="required" />
				<span class="help-inline"></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">生日</label>
			<div class="controls">
				<input name="birthday" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${shopUser.birthday}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用户类型</label>
			<div class="controls">
				<form:select path="userLevel" class="input-medium">
					<form:options items="${fns:getDictList('userLevel')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">头像</label>
			<div class="controls">
				<div id="previewAvatar">
					<c:choose>
						<c:when test="${not empty shopUser.avatar}">
							<img id="imgAvatarName" alt="" src="${picUrl}${shopUser.avatar}" style="width: 150px;height: 150px">
						</c:when>
						<c:otherwise>
							<img id="imgAvatarName" alt="" src="${ctxStatic}/common/imgs/user.jpg" style="width: 150px;height: 150px">
						</c:otherwise>
					</c:choose>
				</div>
				<input type="file" onchange="previewAvatar(this)" id="avatarUrlPic" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">昵称</label>
			<div class="controls">
				<form:input path="nickName" htmlEscape="false" maxlength="60" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">手机号</label>
			<div class="controls">
				<form:input path="mobile" htmlEscape="false" maxlength="20" class="input-xlarge required" id="mobile"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">weixin_openid</label>
			<div class="controls">
				<form:input path="weixinOpenid" htmlEscape="false" maxlength="50" class="input-xlarge required"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="user:shopUser:edit">
				<input id="btnSubmit" onclick="saveUser()" class="btn btn-primary" type="button" value="保存"/>
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
	<script type="text/javascript">

        var shopUserMobile = $('#mobile').val();

        function saveUser(){
            var shopUserId = $('#shopUserId').val();

            //新增
			if(shopUserId==""){
                if(!isMobileRepeat($('#mobile').val())){
                    document.shopUserForm.action = "<c:url value='/a/user/shopUser/save'/>";
                    document.shopUserForm.submit();
                }else{
                    swal("手机号错误!", "手机号重复,请重新输入", "error");
                }
			}else{//修改
                if (shopUserMobile != $('#mobile').val()) { //修改手机号
                    if(!isMobileRepeat($('#mobile').val())){
                        document.shopUserForm.action = "<c:url value='/a/user/shopUser/save'/>";
                        document.shopUserForm.submit();
                    }else{
                        swal("手机号错误!", "手机号重复,请重新输入", "error");
                    }
				}else{
                    document.shopUserForm.action = "<c:url value='/a/user/shopUser/save'/>";
                    document.shopUserForm.submit();
				}
			}

		}

        function isMobileRepeat(mobile){
            var dataValue = false;
            $.ajax({
                url : "<c:url value='/a/user/shopUser/isMobileRepeat'/>",
                type : "POST",
                async : false,
                data : {
                    mobile : mobile
                },
                success : function(data) {
                    debugger;
                    dataValue = data;
                }
            });
            return dataValue;
		}

        function previewAvatar(file) {
            var formData = new FormData();
            var avatarCoverPic = $("#avatarUrlPic").val();
            formData.append("file", $("#avatarUrlPic")[0].files[0]);
            formData.append("headerPic", avatarCoverPic);
            var avatarPicPath;
            $.ajax({
                url : "<c:url value='/a/picture/avatarUrlPicUpload'/>",
                type : 'POST',
                data : formData,
                async : false,
                cache : false,
                contentType : false,
                processData : false,
                success : function(returndata) {
                    debugger;
                    $("#imgAvatarName").attr("src", "${picUrl}" + returndata);
                    avatarPicPath =  returndata;
                    $("#avatar").val(avatarPicPath);
                },
                error : function(returndata) {
                    alert(returndata);
                }
            });
        }

	</script>
</body>
</html>