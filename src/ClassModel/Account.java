/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClassModel;

/**
 *
 * @author Quoc Bao
 */
public class Account {
    private String accountId;
    private String accountName;
    private String accountPassword;
    private String accountRole;

    public Account() {
    }

    public Account(String accountId, String accountName, String accountPassword, String accountRole) {
        this.accountId = accountId;
        this.accountName = accountName;
        this.accountPassword = accountPassword;
        this.accountRole = accountRole;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountPassword() {
        return accountPassword;
    }

    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }

    public String getAccountRole() {
        return accountRole;
    }

    public void setAccountRole(String accountRole) {
        this.accountRole = accountRole;
    }

    @Override
    public String toString() {
        return "Account{" + "accountId=" + accountId + ", accountName=" + accountName + ", accountPassword=" + accountPassword + ", accountRole=" + accountRole + '}';
    }
    
}
