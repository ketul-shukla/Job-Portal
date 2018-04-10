package com.ketul.jobportal.filter;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class UserFilter implements Filter {
    
    private static final boolean debug = true;

   // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured.
    private FilterConfig filterConfig = null;
    
    public UserFilter() {
    }    
    
       static class FilteredRequest extends HttpServletRequestWrapper {

       

       public FilteredRequest(ServletRequest request) {
            super((HttpServletRequest)request);
        }

           public String sanitize( String data)
            {
             if( null == data) return null;

            String newcheck = data;
            newcheck = newcheck.replaceAll("(script|[<>]|javascript|[?]|iframe|select)", ""); 
            return newcheck;
            }

       public String getParameter(String paramName) {
            String value = super.getParameter(paramName);
                  value = sanitize(value);
            
            return value;
        }

       public String[] getParameterValues(String paramName) {
            String values[] = super.getParameterValues(paramName);
               for (int index = 0; index < values.length; index++) {
                    values[index] = sanitize(values[index]);
                
            }
            return values;
        }
    }

   public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        chain.doFilter(new FilteredRequest(request), response);
    }

   public void destroy() {
    }

   public void init(FilterConfig filterConfig) {
    }
}