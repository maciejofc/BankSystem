package pl.maciejowsky.banksystem.model;

import pl.maciejowsky.banksystem.enums.UserType;

public class UserDetail {
    private int id;
    private int funds;
    private UserType userType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFunds() {
        return funds;
    }

    public void setFunds(int funds) {
        this.funds = funds;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public UserDetail(int id, int funds, UserType userType) {
        this.id = id;
        this.funds = funds;
        this.userType = userType;
    }

    public UserDetail() {
    }

    @Override
    public String toString() {
        return "UserDetail{" +
                "id=" + id +
                ", funds=" + funds +
                ", userType=" + userType +
                '}';
    }
}
