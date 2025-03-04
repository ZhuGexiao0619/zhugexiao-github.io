package com.example;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.io.Serial;

@WebServlet("/VisitorCounter") // 访问路径
public class VisitorCounterServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;
    private int visitorCount = 0; // 总访问计数器

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        visitorCount++; // 每次访问 +1

        // 获取用户 Session
        HttpSession session = request.getSession();
        Integer sessionCount = (Integer) session.getAttribute("sessionCount");

        if (sessionCount == null) {
            sessionCount = 1;
        } else {
            sessionCount++;
        }
        session.setAttribute("sessionCount", sessionCount);

        // 输出 HTML 页面
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><head><title>访问计数器</title></head><body>");
        out.println("<h2>欢迎来到访问计数器</h2>");
        out.println("<p>总访问次数：" + visitorCount + "</p >");
        out.println("<p>你的会话访问次数：" + sessionCount + "</p >");
        out.println("</body></html>");
    }
}