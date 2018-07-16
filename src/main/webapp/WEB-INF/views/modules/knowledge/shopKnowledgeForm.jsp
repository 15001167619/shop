<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>常识管理</title>
	<meta name="decorator" content="default"/>

	<script src="${ctxStatic}/ueditor/ueditor.config.js" type="text/javascript"></script>
	<script src="${ctxStatic}/ueditor/ueditor.all.js" type="text/javascript"></script>
	<script src="${ctxStatic}/ueditor/lang/zh-cn/zh-cn.js" type="text/javascript"></script>

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
		<li><a href="${ctx}/knowledge/shopKnowledge/">常识列表</a></li>
		<li class="active"><a href="${ctx}/knowledge/shopKnowledge/form?id=${shopKnowledge.id}">常识<shiro:hasPermission name="knowledge:shopKnowledge:edit">${not empty shopKnowledge.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="knowledge:shopKnowledge:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" name="knowledgeForm" modelAttribute="shopKnowledge" action="${ctx}/knowledge/shopKnowledge/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<input type="hidden" name="listPicUrl" id="listPicUrl" value="${shopKnowledge.listPicUrl}"/>
		<input type="hidden" name="primaryPicUrl" id="primaryPicUrl" value="${shopKnowledge.primaryPicUrl}"/>
		<form:hidden path="knowledgeDesc" id="knowledgeDescValue"/>
		<div class="hide">
			<textarea id="pageInfoValue">${shopKnowledge.knowledgeDesc}</textarea>
		</div>
		<div class="control-group">
			<label class="control-label">标题</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="120" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">常识详情</label>
			<div class="controls">
				<script id="knowledgeDesc" type="text/plain" style="width:1024px;height:500px;"></script>
                <script type="text/javascript">
					var isUEditor = true;
					//实例化编辑器
					var ueTemplate = UE.getEditor('knowledgeDesc', {
						allowDivTransToP: false,
						toolbars: [[
							'fullscreen', 'source', '|', 'undo', 'redo', '|',
							'bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc', '|',
							'rowspacingtop', 'rowspacingbottom', 'lineheight', '|',
							'customstyle', 'paragraph', 'fontfamily', 'fontsize', '|',
							'directionalityltr', 'directionalityrtl', 'indent', '|',
							'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|', 'touppercase', 'tolowercase', '|',
							'link', 'unlink', 'anchor', '|', 'imagenone', 'imageleft', 'imageright', 'imagecenter', '|',
							'simpleupload', 'insertimage', 'emotion',  'attachment', 'map', 'pagebreak', 'background', '|',
							'horizontal', 'date', 'time', 'spechars', 'snapscreen', 'wordimage', '|',
							'inserttable', 'deletetable', 'insertparagraphbeforetable', 'insertrow', 'deleterow', 'insertcol', 'deletecol', 'mergecells', 'mergeright', 'mergedown', 'splittocells', 'splittorows', 'splittocols', 'charts', '|',
							'preview', 'searchreplace',
							'自定义功能'  // 自定义
						]]
					});

					//初始化编辑框内容
					var pageInfoStr = $("#pageInfoValue").val();
					ueTemplate.ready(function() {
						ueTemplate.setContent(pageInfoStr, false);
					});

				</script>

			</div>
		</div>

		<div class="control-group">
			<label class="control-label">常识列表图</label>
			<div class="controls">
				<div id="previewList">
					<c:choose>
						<c:when test="${not empty shopKnowledge.listPicUrl}">
							<img id="imgListName" alt="" src="${picUrl}${shopKnowledge.listPicUrl}" style="width: 250px;height: 150px">
						</c:when>
						<c:otherwise>
							<img id="imgListName" alt="" src="${ctxStatic}/common/imgs/img.png" style="width: 250px;height: 150px">
						</c:otherwise>
					</c:choose>
				</div>
				<input type="file" onchange="previewList(this)" id="listUrlPic" />
			</div>
		</div>


		<div class="control-group">
			<label class="control-label">常识主图</label>
			<div class="controls">
				<div id="previewPrimary">
					<c:choose>
						<c:when test="${not empty shopKnowledge.primaryPicUrl}">
							<img id="imgPrimaryName" alt="" src="${picUrl}${shopKnowledge.primaryPicUrl}" style="width: 250px;height: 150px">
						</c:when>
						<c:otherwise>
							<img id="imgPrimaryName" alt="" src="${ctxStatic}/common/imgs/img.png" style="width: 250px;height: 150px">
						</c:otherwise>
					</c:choose>
				</div>
				<input type="file" onchange="previewPrimary(this)" id="primaryUrlPic" />
			</div>
		</div>

		<div class="form-actions">
			<shiro:hasPermission name="knowledge:shopKnowledge:edit">
				<input id="btnSubmit" onclick="saveKnowledge()" class="btn btn-primary" type="button" value="保存"/>
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>

	<script type="text/javascript">
		function saveKnowledge(){
            $("#knowledgeDescValue").val(UE.getEditor('knowledgeDesc').getContent());
            document.knowledgeForm.action = "<c:url value='/a/knowledge/shopKnowledge/save'/>";
            document.knowledgeForm.submit();
		}


        function previewList(file) {
            var formData = new FormData();
            var listCoverPic = $("#listUrlPic").val();
            formData.append("file", $("#listUrlPic")[0].files[0]);
            formData.append("headerPic", listCoverPic);
            var listPicPath;
            $.ajax({
                url : "<c:url value='/a/picture/listUrlPicUpload'/>",
                type : 'POST',
                data : formData,
                async : false,
                cache : false,
                contentType : false,
                processData : false,
                success : function(returndata) {
                    debugger;
                    $("#imgListName").attr("src", "${picUrl}" + returndata);
                    listPicPath =  returndata;
                    $("#listPicUrl").val(listPicPath);
                },
                error : function(returndata) {
                    alert(returndata);
                }
            });
        }

        function previewPrimary(file) {
            var formData = new FormData();
            var primaryCoverPic = $("#primaryUrlPic").val();
            formData.append("file", $("#primaryUrlPic")[0].files[0]);
            formData.append("headerPic", primaryCoverPic);
            var primaryPicPath;
            $.ajax({
                url : "<c:url value='/a/picture/primaryUrlUpload'/>",
                type : 'POST',
                data : formData,
                async : false,
                cache : false,
                contentType : false,
                processData : false,
                success : function(returndata) {
                    debugger;
                    $("#imgPrimaryName").attr("src", "${picUrl}" + returndata);
                    primaryPicPath =  returndata;
                    $("#primaryPicUrl").val(primaryPicPath);
                },
                error : function(returndata) {
                    alert(returndata);
                }
            });
        }

	</script>

</body>
</html>