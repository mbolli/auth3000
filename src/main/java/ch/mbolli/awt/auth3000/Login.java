package ch.mbolli.awt.auth3000;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

@ManagedBean
@SessionScoped
public class Login implements Serializable {

    private String pwd;
    private String email;
    private byte[] hash;
    private Users users;


    //validate login
    public String validate() {
        try {
            users = Persist.getInstance().getUsers();
            UserBean valid = users.validate(email, pwd);
            if (valid != null) {
                HttpSession session = SessionUtils.getSession();
                session.setAttribute("email", valid.getEmail());
                session.setAttribute("keyid", valid.getSalt());
                hash = valid.getSalt();
                return "loginSuccess";
            } else {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN,
                                "Incorrect Username and Password",
                                "Please enter correct username and Password"));
                return "login";
            }
        } catch(Exception e) {
            e.printStackTrace();
            return "login";
        }
    }

    //logout event, invalidate session
    public String logout() {
        HttpSession session = SessionUtils.getSession();
        session.invalidate();
        return "login";
    }

    public boolean loggedin() {
        String userid = SessionUtils.getUserId();
        if (userid != null) {
            return true;
        } else {
            return false;
        }
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getHash() {
        return (hash == null) ? SessionUtils.getUserKey() : hash;
    }

    public void setHash(byte[] hash) {
        this.hash = hash;
    }
}