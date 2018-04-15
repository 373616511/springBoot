package com.asyf.springboot.servlet;

import com.ckfinder.connector.ConnectorServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/ckfinder/core/connector/java/connector.java", initParams = {
        @WebInitParam(name = "XMLConfig", value = "classpath:ckfinder.xml"),
        @WebInitParam(name = "debug", value = "false"),
        @WebInitParam(name = "configuration", value = "com.asyf.springboot.config.CKFinderConfig")
})
public class ImageBrowseServlet extends ConnectorServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        prepareGetResponse(request, response, false);
        super.doGet(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        prepareGetResponse(request, response, true);
        super.doPost(request, response);
    }

    private void prepareGetResponse(final HttpServletRequest request,
                                    final HttpServletResponse response, final boolean post) throws ServletException {

    }
}
