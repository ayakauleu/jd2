package servlet;

import database.dao.BusinessDaoImpl;
import database.model.Business;
import database.util.JspPathUtil;

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
        Business business = new Business(
                req.getParameter("name"),
                Integer.valueOf(req.getParameter("unp"))
        );
        Long createdId = BusinessDaoImpl.getInstance().save(business);

        resp.sendRedirect("/ibank/business-info?id=" + createdId);
    }
}
