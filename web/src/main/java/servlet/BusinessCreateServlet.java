package servlet;

import dao.BusinessDao;
import model.Business;
import util.JspPathUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/business-create")
public class BusinessCreateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext()
                .getRequestDispatcher(JspPathUtil.get("business-create"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Business business = Business.builder()
                .name(req.getParameter("name"))
                .unp(Integer.valueOf(req.getParameter("unp")))
                .build();
        Business created = BusinessDao.getInstance().create(business);

        resp.sendRedirect("/ibank/business-info?id=" + created.getId());
    }
}
