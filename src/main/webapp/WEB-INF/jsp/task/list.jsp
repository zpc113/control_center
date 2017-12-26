<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- 引入jstl --%>
<%@include file="../common/tag.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="../common/head.jsp" %>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>任务列表</title>
    <style>
        td {
            vertical-align : middle !important;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="panel default-panel">
        <div class="panel-heading text-center">
            <h2>任务列表</h2>
        </div>
        <div class="panel-body">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>任务名</th>
                    <th>创建时间</th>
                    <th>运行时间</th>
                    <th>运行状态</th>
                    <th>操作</th>
                    <button type="button" id="createTask" class="btn btn-summary pull-right" data-toggle="modal" data-target="#myModal" onclick="changeAction()">新建任务</button>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="taskInfo" items="${taskInfoList}">
                    <tr>
                        <td>
                            <a href="/config/${taskInfo.taskId}/config">${taskInfo.taskName}</a>
                        </td>
                        <td>
                            <fmt:formatDate type="both" value="${taskInfo.createTime}"/>
                        </td>
                        <td>
                            <fmt:formatDate type="both" value="${taskInfo.runTime}"/>
                        </td>
                        <td>${taskInfo.runStatus}</td>
                        <td>
                            <button type="button" class="btn btn-link" data-toggle="modal" data-target="#myModal" onclick="change(this)">
                                <input type="hidden" class="taskName" value="${taskInfo.taskName}"/>
                                <input type="hidden" class="taskId" value="${taskInfo.taskId}"/>
                                修改
                            </button>
                            |
                            <a id="delete" class="btn btn-link" href="/taskInfo/delete/${taskInfo.taskId}" onclick="return confirm('确认删除该任务？');">删除</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<%-- 模态框 --%>
<form action="" method="post" class="form">
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModal" aria-hidden="true" style="height: 370px">
        <div class="modal-dialog" style="height: 90%; margin: 200px auto">
            <div class="modal-content" style="height: 90%">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="myModalLabel">修改任务名称</h4>
                </div>
                <div class="modal-body">
                    <input type="text" id="taskName" class="form-control" name="taskName"/>
                </div>
                <div class="model-footer  pull-right">
                    <button type="submit" class="btn btn-primary">保存</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                </div>
            </div>
        </div>
    </div>
</form>
</body>
<script type="text/javascript">
    /**
     * 将taskName填入到对应的模态框中
     * @param button
     */
    function change(button) {
        var taskName = button.childNodes[1].value;
        var taskId = button.childNodes[3].value;
        // 将对应的taskName填充进input
        $("#taskName").attr("value" , taskName);
        // 修改form表单的action的值
        $(".form").attr("action" , "/taskInfo/" + taskId + "/update");
    }

    /**
     * 将form表单中的action值修改为新增的链接
     */
    function changeAction() {
        $(".form").attr("action" , "/taskInfo/createTask");
        $(".modal-title").text("新建任务");
    }
</script>
</html>
