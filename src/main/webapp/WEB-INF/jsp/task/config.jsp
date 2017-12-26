<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- 引入jstl --%>
<%@include file="../common/tag.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="../common/head.jsp" %>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>任务配置</title>
</head>
<div class="container">
    <div class="row">
        <ul class="nav nav-tabs">
            <li class="active"><a href="#scriptInfo" data-toggle="tab">脚本</a></li>
            <li><a href="#configInfo" data-toggle="tab">配置</a></li>
            <li class="pull-middle" style="margin-top: 11px;margin-left: 34%;">${taskInfo.taskName}</li>
        </ul>

    </div>
    <form action="/config/updateConfig" method="post" role="form">
        <div id="myTabContent" class="tab-content">
            <div class="tab-pane fade in active" id="scriptInfo">
                    <textarea style="width: 100%;resize:none;" rows="33" name="script">
                        ${taskConfig.script}
                    </textarea>
            </div>
            <div class="tab-pane fade" id="configInfo">
                <div class="container">
                    <div class="row">
                        <div class="form-group">
                            <label for="exampleInputEmail1">种子链接</label>
                            <input type="text" name="seedUrl" class="form-control" id="exampleInputEmail1"
                                   value="<c:out value="${taskConfig.seedUrl}"/>"/>
                        </div>
                        <div class="form-group">
                            <label for="threadNum">线程数</label>
                            <input type="text" name="threadNum" class="form-control" id="threadNum" value="${taskConfig.threadNum}"/>
                        </div>
                        <div class="form-group">
                            <label for="httpAcceptCode">HttpAcceptCode</label>
                            <input type="text" name="acceptCode" class="form-control" id="httpAcceptCode" value="${taskConfig.acceptCode}"/>
                        </div>
                        <div class="form-group">
                            <label for="dataTableName">数据表名</label>
                            <input type="text" name="dataTableName" class="form-control" id="dataTableName" value="${taskConfig.dataTableName}"/>
                        </div>
                        <div class="form-group">
                            <label for="userAgent">UA规则</label>
                            <div class="radio">
                                <label><input type="radio" name="userAgent" value="0" <c:if test="${taskConfig.userAgent}==0">checked</c:if>>PC通用</label>
                            </div>
                            <div class="radio">
                                <label><input type="radio" name="userAgent" value="1" <c:if test="${taskConfig.userAgent}==1">checked</c:if>>Mobile通用</label>
                            </div>
                        </div>
                        <input type="hidden" name="taskId" value="${taskInfo.taskId}"/>
                        <button type="submit" class="btn btn-default">保存</button>
                        <button type="button" id="execute" class="btn btn-primary">运行</button>
                        <script type="text/javascript">
                            $("#execute").click(function execute() {
                                $.post('/config/'+ ${taskId} +'/run' , {} , function (result) {
                                    if(result && result['success']) {
                                        console.log(result);
                                        alert("执行成功");
                                    } else {
                                        alert("执行失败!");
                                    }
                                });
                            });
                        </script>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
</body>
</html>
