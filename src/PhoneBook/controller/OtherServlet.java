package PhoneBook.controller;

import PhoneBook.dao.PhoneBookDao;
import PhoneBook.dao.PhoneBookDaoImpl;
import PhoneBook.vo.PhoneBookVo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//@WebServlet("/")
public class OtherServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String actionName = req.getParameter("a");

        if ("insert".equals(actionName)) {

        } else {
            PhoneBookDao dao = new PhoneBookDaoImpl(dbUser, dbPass);
            List<PhoneBookVo> list = dao.getList();

            req.setAttribute("list", list);

            RequestDispatcher rd = req.getServletContext().getRequestDispatcher("/WEB-INF/views/home.jsp");
            rd.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
