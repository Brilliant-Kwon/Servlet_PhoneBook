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

@WebServlet("/")
public class PhoneBookServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        RequestDispatcher rd = req.getServletContext().getRequestDispatcher("/WEB-INF/views/home.jsp");
//        rd.forward(req, resp);
        String actionName = req.getParameter("a");

        if ("insertform".equals(actionName)) {
            RequestDispatcher rd = req.getServletContext().getRequestDispatcher("/WEB-INF/views/insert_form.jsp");
            rd.forward(req, resp);
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
        String actionName = req.getParameter("a");

        if ("search_do".equals(actionName)) {

            String str = req.getParameter("search_tag");

            PhoneBookDao dao = new PhoneBookDaoImpl(dbUser, dbPass);
            List<PhoneBookVo> list = dao.search(str);

            req.setAttribute("list", list);
            req.setAttribute("search_name", str);

            RequestDispatcher rd = req.getServletContext().getRequestDispatcher("/WEB-INF/views/home.jsp");
            rd.forward(req, resp);
        } else if ("insert".equals(actionName)) {
            String name = req.getParameter("name");
            String hp = req.getParameter("hp");
            String tel = req.getParameter("tel");

            PhoneBookVo vo = new PhoneBookVo(name, hp, tel);
            PhoneBookDao dao = new PhoneBookDaoImpl(dbUser, dbPass);
            boolean success = dao.insert(vo);

            if (success) {
                resp.sendRedirect(req.getContextPath() + "/");
            } else {
                resp.getWriter().println("<h1>Error : INSERT 실패</h1>");
            }
        } else if ("delete".equals(actionName)) {
            String id = req.getParameter("id");

            PhoneBookDao dao = new PhoneBookDaoImpl(dbUser, dbPass);

            boolean success = dao.delete(Long.parseLong(id));

            if (success) {
                System.out.println("삭제 완료");
            } else {
                System.out.println("삭제 실패");
            }

            resp.sendRedirect(req.getContextPath() + "/");
        }
    }
}
