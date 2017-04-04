package com.readlearncode.dukechat.beans;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * Source code github.com/readlearncode
 *
 * @author Alex Theedom www.readlearncode.com
 * @version 1.0
 */
@Named
@SessionScoped
public class UserSession implements Serializable {

    public String logout() throws ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        httpRequest.logout();
        httpRequest.getSession().invalidate();
        return "/index?faces-redirect=true";
    }
}