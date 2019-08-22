package com.github.szsalyi.pulzator.spring.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LogoutSuccess implements LogoutSuccessHandler {


    @Override
    public void onLogoutSuccess(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse, final Authentication authentication)
            throws IOException, ServletException {
        if (authentication != null && authentication.getDetails() != null) {
            try {
                httpServletRequest.getSession().invalidate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
    }

}
