package pl.maciejowsky.banksystem.model;

public class FormUser {
    private User user;
    private UserDetail userDetail;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserDetail getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
    }

    public FormUser() {
    }

    public FormUser(User user, UserDetail userDetail) {
        this.user = user;
        this.userDetail = userDetail;
    }
}
