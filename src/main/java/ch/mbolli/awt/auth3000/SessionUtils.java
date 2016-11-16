package ch.mbolli.awt.auth3000;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtils {

    public static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
    }

    public static String getUserId() {
        HttpSession session = getSession();
        if (session != null)
            return (String) session.getAttribute("email");
        else
            return null;
    }

    public static byte[] getUserKey() {
        HttpSession session = getSession();
        if (session != null)
            return (byte[]) session.getAttribute("keyid");
        else
            return null;
    }
}