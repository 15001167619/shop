<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商品管理</title>
	<meta name="decorator" content="default"/>

	<script src="${ctxStatic}/ueditor/ueditor.config.js" type="text/javascript"></script>
	<script src="${ctxStatic}/ueditor/ueditor.all.js" type="text/javascript"></script>
	<script src="${ctxStatic}/ueditor/lang/zh-cn/zh-cn.js" type="text/javascript"></script>

	<script src="${ctxStatic}/picture/jquery.form.js" type="text/javascript"></script>
	<link href="${ctxStatic}/picture/cover.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="${ctxStatic}/picture/mgScroll.js"></script>

	<script type="text/javascript">
		$(document).ready(function() {
            allCheck('allCkb','ckb');
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
		<li><a href="${ctx}/commodity/shopCommodity/">商品列表</a></li>
		<li class="active"><a href="${ctx}/commodity/shopCommodity/form?id=${shopCommodity.id}">商品<shiro:hasPermission name="commodity:shopCommodity:edit">${not empty shopCommodity.id?'编辑':'添加'}</shiro:hasPermission><shiro:lacksPermission name="commodity:shopCommodity:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" name="commodityForm" modelAttribute="shopCommodity" action="${ctx}/commodity/shopCommodity/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>

		<input type="hidden" id="originalPicUrl" name="originalPicUrl" value="${pictureAlbumString}">
		<input type="hidden" name="listPicUrl" id="listPicUrl" value="${shopCommodity.listPicUrl}"/>
		<input type="hidden" name="specifications" id="specificationArray"/>
		<%--规格临时图片放置--%>
		<input type="hidden"  id="specificationPicTemp"/>

		<div class="control-group">
			<label class="control-label">商品名称</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="120" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商品英文名称</label>
			<div class="controls">
				<form:input path="englishName" htmlEscape="false" maxlength="120" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label font_weight">商品简介</label>
			<div class="controls">
				<script id="briefEditor" type="text/plain" style="width:1024px;height:100px;"></script>
                <form:hidden path="brief" id="briefValue"/>
                <script type="text/javascript">
                var briefUE = UE.getEditor('briefEditor', {
                    toolbars: [[
                        'fullscreen', 'source', '|', 'undo', 'redo', '|',
                        'bold','pasteplain'
                    ]]
                });
                var briefHtmlValue = $("#briefValue").val();
                briefUE.ready(function() {
                    briefUE.setContent(briefHtmlValue, false);
                    briefUE.setHeight(100);
                });
				</script>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否推荐</label>
			<div class="controls">
				<form:radiobuttons path="isOnSale"  items="${fns:getDictList('is_on_sale')}" itemLabel="label"  itemValue="value" htmlEscape="false" class="required" />
				<span class="help-inline"></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">专柜价格</label>
			<div class="controls">
				<form:input path="counterPrice" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商品单位</label>
			<div class="controls">
				<form:input path="unit" htmlEscape="false" maxlength="45" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商品规格</label>
			<div class="controls">
				<button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal" onclick="initModal()">添加规格</button>
			</div>
		</div>

		<script>

			function initModal() {
                $("#imgSpecificationName").attr("src","${ctxStatic}/common/imgs/img.png");
                $("#specificationPicTemp").val("");
            }


            function addCondition(tab, row) {
                //获取参数值
                var colorValue= $("#specificationColor").val();
                var sizeValue= $("#specificationSize").val();
                var specificationPic = $("#specificationPicTemp").val();
                swal("添加成功", "请再次输入商品规格", "success");
                //关闭
                $('#myModal').modal('hide');
                var trHtml="<tr align='center'>" +
                    "<td width='10%' style='text-align:center;'><input type='checkbox' name='ckb'/></td>"+
                    "<td style='text-align:center;'>"+colorValue+"</td>" +
                    "<td style='text-align:center;'>"+sizeValue+"</td>" +
                    "<td style='text-align:center;'>"+specificationPic+"</td>" +
                    "<td style='text-align:center;'><input type='button' onclick='delCondition()' value='删除规格' class='btn btn-primary' id='delConditionValue'></td>" +
                    "</tr>";
                addTr(tab, row, trHtml);
                $("#specificationValue").val("");
            }

            function addTr(tab, row, trHtml){
                var $tr=$("#"+tab+" tr").eq(row);
                if($tr.size()==0){
                    alert("指定的table id或行数不存在！");
                    return;
                }
                $tr.after(trHtml);
            }

            function delCondition(){
                delTr('ckb');
            }

            function delTr(ckb){
                var ckbs=$("input[name="+ckb+"]:checked");
                if(ckbs.size()==0){
                    swal("删除失败", "要删除指定行，需选中要删除的行", "error");
                    return;
                }
                ckbs.each(function(){
                    $(this).parent().parent().remove();
                });
            }

            function allCheck(allCkb, items){
                $("#"+allCkb).click(function(){
                    $('[name='+items+']:checkbox').attr("checked", this.checked );
                });
            }

		</script>

		<div class="control-group" id="applyCondition">
			<label class="control-label">规格表单</label>
			<div class="controls">
				<table id="contentTable" class="table table-striped table-bordered table-condensed">
					<thead>
					<tr>
						<td width="10%" style="text-align:center;"><label><input id="allCkb" type="checkbox" onclick="allCheck('allCkb','ckb')"/>全选</label></td>
						<th style="text-align:center;">颜色</th>
						<th style="text-align:center;">尺码</th>
						<th style="text-align:center;">图片路径</th>
						<th style="text-align:center;">操作</th>
					</tr>
					</thead>
					<tbody>
					<c:forEach items="${specificationList}" var="shopSpecification">
						<tr>
							<td width="10%" style="text-align:center;"><input type="checkbox" name="ckb"/></td>
							<td style="text-align:center;">${shopSpecification.color}</td>
							<td style="text-align:center;">${shopSpecification.size}</td>
							<td style="text-align:center;">${shopSpecification.picUrl}</td>
							<td style="text-align:center;"><input type="button" onclick="delCondition()" value="删除规格" class="btn btn-primary" id="delConditionValue"></td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<!-- 模态框（Modal） -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
							&times;
						</button>
						<h4 class="modal-title" id="myModalLabel">
							商品规格
						</h4>
					</div>
					<div class="modal-body">
						<div class="control-group">
							<label class="control-label">颜色</label>
							<div class="controls">
								<select class="input-medium" id="specificationColor">
									<option value="白色">白色</option>
									<option value="黑色">黑色</option>
									<option value="红色">红色</option>
								</select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">尺码</label>
							<div class="controls">
								<select class="input-medium" id="specificationSize">
									<option value="39">39</option>
									<option value="40">40</option>
									<option value="41">41</option>
									<option value="42">42</option>
									<option value="43">43</option>
									<option value="44">44</option>
								</select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">规格封面图</label>
							<div class="controls">
								<div id="specificationPicUrl">
									<img id="imgSpecificationName" alt="" src="${ctxStatic}/common/imgs/img.png" style="width: 250px;height: 150px">
								</div>
								<input type="file" onchange="previewSpecification(this)" id="specificationPic" />
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭
						</button>
						<input   type="button" value="确认添加" class="btn btn-primary" onclick="addCondition('contentTable', -1)"/>
					</div>
				</div>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">零售价格</label>
			<div class="controls">
				<form:input path="retailPrice" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">商品详情</label>
			<div class="controls">
				<script id="descEditor" type="text/plain" style="width:1024px;height:500px;"></script>
                <form:hidden path="description" id="descValue"/>
                <script type="text/javascript">
                //实例化编辑器
                var descUE = UE.getEditor('descEditor', {
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
                var descStr = $("#descValue").val();
                descUE.ready(function() {
                    descUE.setContent(descStr, false);
                });

				</script>

			</div>
		</div>

		<div class="control-group">
			<label class="control-label">商品封面图</label>
			<div class="controls">
				<div id="previewPicUrl">
					<c:choose>
						<c:when test="${not empty shopCommodity.listPicUrl}">
							<img id="imgPicUrlName" alt="" src="${picUrl}${shopCommodity.listPicUrl}" style="width: 250px;height: 150px">
						</c:when>
						<c:otherwise>
							<img id="imgPicUrlName" alt="" src="${ctxStatic}/common/imgs/img.png" style="width: 250px;height: 150px">
						</c:otherwise>
					</c:choose>
				</div>
				<input type="file" onchange="previewPicUrl(this)" id="listPicUrlPic" />
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">商品图片</label>
			<div class="controls">
				<form id="inputForm" method="post" enctype="multipart/form-data">
					<h2 class="chpro_i">选择商品图片</h2>
					<div class="f_choim">
						<ul class="st_tis" style="margin: 0;">
							<c:forEach items="${projectAlbum}" var="album">
								<li style="display: block"><img class="opic" src="${picUrl}${album}" /><a onclick="closeImg(this)" style="cursor: pointer"></a></li>
							</c:forEach>
						</ul>
						<input type="file" class="f_img" id="imgFile" name="imgFile" onchange="uploadPic('inputForm')"><a class="uplo_i"></a>
					</div>
				</form>
			</div>
		</div>






		<script type="text/javascript">


            function closeImg(obj) {
                debugger;
                var imgSrc = $(obj).parent().find("img")[0].src;
                imgSrc = imgSrc.replace("${picUrl}","");
                var originalPicUrl = $("#originalPicUrl").val();
                var originalPicUrls = originalPicUrl.split(";");
                $("#originalPicUrl").val("");
                for (var int = 0; int < originalPicUrls.length; int++) {
                    if(originalPicUrls[int]!=imgSrc){
                        if ($("#originalPicUrl").val() != "") {
                            $("#originalPicUrl").val($("#originalPicUrl").val()	+ ";" + originalPicUrls[int]);
                        } else {
                            $("#originalPicUrl").val(originalPicUrls[int]);

                        }
                    };

                }
                $(obj).parent().hide().remove();
                $('.f_choim input.f_img,.f_choim a.uplo_i').css('left',
                    $('.st_tis').width());
            };


            function uploadPic(formId) {
                $('input.f_file').hover(
                    function() {
                        $(this).next().find('img.halrea').removeClass('shay')
                            .addClass('disn');
                        $(this).next().find('img.f_coimg').removeClass('disn')
                            .addClass('shay');
                    },
                    function() {
                        $(this).next().find('img.halrea').removeClass('disn')
                            .addClass('shay');
                        $(this).next().find('img.f_coimg').removeClass('shay')
                            .addClass('disn');
                    });
                var formObj = $("#" + formId)
                //异步表单提交 先提交图片
                formObj.ajaxSubmit({
                        type : "POST",
                    	url : "<c:url value='/a/picture/commodityUrlPicUpload'/>",
                        dataType : "text",
                        async : false,
                        data : {
                            fileType : "imge"
                        },
                        success : function(data) {
                            debugger;

                            if (formId == "inputForm") {
                                if ($("#originalPicUrl").val() != "") {
                                    $("#originalPicUrl").val($("#originalPicUrl").val()	+ ";" + data);
                                } else {
                                    $("#originalPicUrl").val(data);

                                }
                                $(".st_tis").append('<li style="display:block"><img class="opic" src="${picUrl}'+data+'"/><a onclick="closeImg(this)" style="cursor:pointer"></a></li>');
                                $('.f_choim input.f_img,a.uplo_i').css('left',$(".st_tis").width());

                            }

                        }
                    });
            }


            function previewPicUrl(file) {
                var formData = new FormData();
                var coverPic = $("#listPicUrlPic").val();
                formData.append("file", $("#listPicUrlPic")[0].files[0]);
                formData.append("headerPic", coverPic);
                var coverPicPath;
                $.ajax({
                    url : "<c:url value='/a/picture/commodityCoverUpload'/>",
                    type : 'POST',
                    data : formData,
                    async : false,
                    cache : false,
                    contentType : false,
                    processData : false,
                    success : function(returndata) {
                        debugger;
                        $("#imgPicUrlName").attr("src", "${picUrl}" + returndata);
                        coverPicPath =  returndata;
                        $("#listPicUrl").val(coverPicPath);
                    },
                    error : function(returndata) {
                        alert(returndata);
                    }
                });
            }

            function previewSpecification(file) {
                var formData = new FormData();
                var specificationPic = $("#specificationPic").val();
                formData.append("file", $("#specificationPic")[0].files[0]);
                formData.append("headerPic", specificationPic);
                var specificationPicPath;
                $.ajax({
                    url : "<c:url value='/a/picture/commoditySpecificationUpload'/>",
                    type : 'POST',
                    data : formData,
                    async : false,
                    cache : false,
                    contentType : false,
                    processData : false,
                    success : function(returndata) {
                        debugger;
                        $("#imgSpecificationName").attr("src", "${picUrl}" + returndata);
                        specificationPicPath =  returndata;
                        //先清空
                        $("#specificationPicTemp").val("");
                        $("#specificationPicTemp").val(specificationPicPath);
                    },
                    error : function(returndata) {
                        alert(returndata);
                    }
                });
            }
		</script>

		<div class="form-actions">
			<shiro:hasPermission name="commodity:shopCommodity:edit">
				<input id="btnSubmit" onclick="saveCommodity()" class="btn btn-primary" type="button" value="保存"/>
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>

	<script type="text/javascript">
        function saveCommodity(){
			debugger;
            //商品规格
            var applyConditionArray;
            var conditionArray = [];
            function conditionObj(color,size,picUrl){
                this.color = color;
                this.size = size;
                this.picUrl = picUrl;
            }
            $("#contentTable").find("tr").each(function () {
                var color,size,picUrl;
                color = $(this).children('td:eq(1)').text();
                size = $(this).children('td:eq(2)').text();
                picUrl = $(this).children('td:eq(3)').text();
                if(size!="")conditionArray.push(new conditionObj(color,size,picUrl));
            });
            applyConditionArray = JSON.stringify(conditionArray);

            $("#specificationArray").val(applyConditionArray);

            $("#briefValue").val(UE.getEditor('briefEditor').getContent());
            $("#descValue").val(UE.getEditor('descEditor').getContent());
            document.commodityForm.action = "<c:url value='/a/commodity/shopCommodity/save'/>";
            document.commodityForm.submit();
		}
	</script>

</body>
</html>