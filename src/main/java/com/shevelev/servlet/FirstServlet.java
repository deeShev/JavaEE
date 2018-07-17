package com.shevelev.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


@WebServlet("/hello")
public class FirstServlet extends HttpServlet {

    private static final String NAME_PARAMETER = "query";
    private Map<String, String> stringMap;

    @Override
    public void init() throws ServletException {
        stringMap = new HashMap<>();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String reqValue = req.getParameter(NAME_PARAMETER);
        if (stringMap.containsKey(reqValue)) {
            resp.getWriter().append(stringMap.get(reqValue));
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Enumeration<String> parameterNames = req.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String currentParameter = parameterNames.nextElement();
            stringMap.put(currentParameter, req.getParameter(currentParameter));
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String reqValue = req.getParameter(NAME_PARAMETER);
        if (stringMap.containsKey(reqValue)) {
            stringMap.remove(reqValue);
            resp.sendError(HttpServletResponse.SC_OK);
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
