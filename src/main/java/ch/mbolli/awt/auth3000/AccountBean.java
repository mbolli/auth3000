package ch.mbolli.awt.auth3000;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@ManagedBean(name="account")
@SessionScoped
public class AccountBean implements Serializable {

    private String username;
    private String title;
    private String website;
    private String pwd; //encrypted
    private String userid;

    public String save() {
        Persist.getInstance().addAccount(this);
        return "account_save_success";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "AccountBean{" +
                "username='" + username + '\'' +
                ", title='" + title + '\'' +
                ", website='" + website + '\'' +
                ", pwd='" + pwd + '\'' +
                ", userid='" + userid + '\'' +
                '}';
    }
}
