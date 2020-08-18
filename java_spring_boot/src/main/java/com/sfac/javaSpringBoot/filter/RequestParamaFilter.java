package com.sfac.javaSpringBoot.filter;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;

@WebFilter(filterName = "requestParamaFilter",urlPatterns ="/**" )
public class RequestParamaFilter implements Filter {
    //创建日志实例
    private final static Logger LOGGER= LoggerFactory.getLogger(RequestParamaFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.debug("==========init request param filter==============");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        LOGGER.debug("====== Do Request Param Filter ======");
        HttpServletRequest httpRequest= (HttpServletRequest) request;
//        Map<String,String[]> paramsMap=httpRequest.getParameterMap();
//        paramsMap.put("paramKey",new String[]{"***"});
        HttpServletRequestWrapper wrapper=new HttpServletRequestWrapper(httpRequest){
            @Override
            public String getParameter(String name) {
                //在这里如果为空则调用重写的方法进行字符串替换，否则调用继承的父类的方法
                String value=httpRequest.getParameter(name);
                if (StringUtils.isNoneBlank(value)){
                    return value.replaceAll("fuck","***");
                }
                return super.getParameter(name);
            }

            @Override
            public String[] getParameterValues(String name) {
                String[] values=httpRequest.getParameterValues(name);
                if (values!=null && values.length>0){
                    for (int i = 0; i < values.length; i++) {
                        values[i] =values[i].replace("fuck","***");
                    }
                    return values;
                }
                return super.getParameterValues(name);
            }
        };

        filterChain.doFilter(wrapper,response);
    }

    @Override
    public void destroy() {
        LOGGER.debug("====== destroy request param Filter ======");
    }
}
