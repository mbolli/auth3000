package ch.mbolli.awt.auth3000;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by mbolli on 11/15/16.
 */
@XStreamAlias("users")
public class Users{

    @XStreamImplicit
    List<UserBean> users = new ArrayList<>();

    public void addUser(UserBean user) {
        this.users.add(user);
    }

    public UserBean validate(String email, String password) {
        List<UserBean> correctUser = users.stream().filter(
                p-> p.getEmail().equals((email)))
                .collect(Collectors.toList());

        if (!correctUser.isEmpty()) { // user with provided email address exists
            List<UserBean> correctPassword = correctUser.stream().filter(
                    p-> Arrays.equals(p.getPasswordHashed(),UserBean.hashPassword(password.toCharArray(), p.getSalt())))
                    .collect(Collectors.toList());
            System.out.println(correctPassword.toString());
            if(!correctPassword.isEmpty()) { // password is probably correct... at least the hashes match
                return correctPassword.get(0);
            }
        }

        return null;
    }
}