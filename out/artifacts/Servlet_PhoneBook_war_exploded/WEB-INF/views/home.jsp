<%@ page import="java.util.List" %>
<%@ page import="PhoneBook.vo.PhoneBookVo" %><%--
  Created by IntelliJ IDEA.
  User: k1212
  Date: 2019-03-05
  Time: 오전 9:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>주소록 서블릿</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/form.js"></script>
</head>
<body>
<%
    List<PhoneBookVo> list = (List<PhoneBookVo>) request.getAttribute("list");
%>
<h1>주소록 Servlet</h1>
<h2>목록
    <%
        if (request.getAttribute("search_name") != null) {%>
    (검색어 : <%=request.getAttribute("search_name")%> )
    <%
            request.setAttribute("search_name", null);
        }
    %>
</h2>
<div id="search">
    <form id="search_form" name="searchform" method="post" action="<%=request.getContextPath()%>/">
        <label for="search_tag">검색어</label>
        <input type="text" name="search_tag" id="search_tag" placeholder="검색어를 입력하세요.">
        <input type="hidden" name="a" value="search_do">
        <input type="submit" value="검색">
    </form>
</div>
<div id="lists">
    <table id="lists_table" border="1px solid #fff" width="80%">
        <tr border="1px solid #000" bgcolor="#a9a9a9">
            <th>이름</th>
            <th>휴대전화</th>
            <th>전화번호</th>
            <th>도구</th>
        </tr>

        <%
            for (PhoneBookVo vo : list) {
        %>

        <tr border="1px solid #000">
            <td><%=vo.getName()%>
            </td>
            <td><%=vo.getHp()%>
            </td>
            <td><%=vo.getTel()%>
            </td>
            <td>
                <form name="delete" method="post" action="<%=request.getContextPath()%>/"
                      onsubmit="return delete_phonebook(this)">
                    <input type="hidden" name="a" value="delete">
                    <input type="hidden" name="id" value="<%=vo.getId()%>">
                    <input type="submit" value="삭제">
                </form>
            </td>
        </tr>

        <%
            }
        %>
    </table>
</div>
<a href="<%=request.getContextPath()%>/?a=insertform">새 주소 추가</a>
</body>
</html>
