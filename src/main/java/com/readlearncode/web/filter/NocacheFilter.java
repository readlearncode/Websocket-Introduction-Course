package com.readlearncode.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
    
public class NocacheFilter implements Filter {
    
    public void doFilter(ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        
        HttpServletResponse httpResponse = (HttpServletResponse)response;
        httpResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        httpResponse.setHeader("Pragma", "no-cache");
        httpResponse.setDateHeader("Expires", 0);
        
        chain.doFilter(request, response);
    }
    
    public void destroy() {}
    public void init(FilterConfig fConfig) throws ServletException {}
}
