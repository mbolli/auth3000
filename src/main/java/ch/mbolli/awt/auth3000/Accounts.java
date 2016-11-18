package ch.mbolli.awt.auth3000;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by mbolli on 11/15/16.
 */
@XStreamAlias("accounts")
public class Accounts {

    @XStreamImplicit
    List<AccountBean> accounts = new ArrayList<>();

    public void addAccount(AccountBean account) {
        this.accounts.add(account);
    }

    public List<AccountBean> getUserAccounts() {
        String userid = SessionUtils.getUserId();
        List<AccountBean> filteredAccounts = accounts.stream().filter(
                p-> p.getUserid().equals((userid)))
                .collect(Collectors.toList());
        System.out.println(filteredAccounts.toString());
        return filteredAccounts;
    }
}
