package bank;

public class UserAccount {
    protected String userName;
    protected double inititalBalance;

    protected UserAccount(String userName, double inititalBalance) {
        this.userName = userName;
        this.inititalBalance = inititalBalance;
    }
    
    protected double getInititalBalance() {
        return this.inititalBalance;
    }

    protected String getUserName() {
        return this.userName;
    }
}
