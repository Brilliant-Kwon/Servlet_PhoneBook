<%--
  Created by IntelliJ IDEA.
  User: k1212
  Date: 2019-03-05
  Time: 오전 9:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>주소록 추가</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/form.js"></script>
    <style type="text/css">
        #insert_form {
            border: 1px solid #666;
            border-radius: 10px;
            padding: 10px;
            margin: 50px 50px;
            width: 250px;
        }

        fieldset {
            border: 0px;
        }

        fieldset label, fieldset input {
            display: block;
        }

        input[type=submit] {
            display: block;
        }

    </style>
</head>
<body>
<h1>주소록 Servlet</h1>
<h2>새 주소 등록</h2>
<div id="insert">
    <form id="insert_form" name="insertform" method="post" action="<%=request.getContextPath()%>/"
          onsubmit="return checkForm(this)">
        <fieldset>
            <label for="name">이름</label>
            <input type="text" name="name" placeholder="이름을 입력하세요.">
        </fieldset>
        <fieldset>
            <label for="name">휴대전화</label>
            <input type="text" name="hp" placeholder="휴대전화를 입력하세요.">
        </fieldset>
        <fieldset>
            <label for="name">집전화</label>
            <input type="text" name="tel" placeholder="집전화를 입력하세요.">
        </fieldset>
        <input type="hidden" name="a" value="insert">
        <input type="submit" value="주소 등록">
    </form>
    <div class="footer">
        <a href="<%=request.getContextPath()%>/">목록 보기</a>
    </div>
</div>
</body>
</html>
