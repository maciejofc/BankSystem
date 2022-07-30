package pl.maciejowsky.banksystem.model;

public class BankConfig {

    private String announcement;
    private double interestRateOneMinute;

    private int minAmountOneMinuteDeposit;
    private double interestRateFiveMinute;
    private int minAmountFiveMinuteDeposit;
    private double interestRateTenMinute;
    private int minAmountTenMinuteDeposit;
    private double interestRateSavingRegular;
    private double interestRateSavingEntrepreneur;

    private boolean bankStatus;

    private int fkAdminId;


    public BankConfig() {
    }

    public BankConfig(String announcement, double interestRateOneMinute, int minAmountOneMinuteDeposit, double interestRateFiveMinute, int minAmountFiveMinuteDeposit, double interestRateTenMinute, int minAmountTenMinuteDeposit, double interestRateSavingRegular, double interestRateSavingEntrepreneur, boolean bankStatus, int fkAdminId) {
        this.announcement = announcement;
        this.interestRateOneMinute = interestRateOneMinute;
        this.minAmountOneMinuteDeposit = minAmountOneMinuteDeposit;
        this.interestRateFiveMinute = interestRateFiveMinute;
        this.minAmountFiveMinuteDeposit = minAmountFiveMinuteDeposit;
        this.interestRateTenMinute = interestRateTenMinute;
        this.minAmountTenMinuteDeposit = minAmountTenMinuteDeposit;
        this.interestRateSavingRegular = interestRateSavingRegular;
        this.interestRateSavingEntrepreneur = interestRateSavingEntrepreneur;
        this.bankStatus = bankStatus;
        this.fkAdminId = fkAdminId;
    }

    public String getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
    }

    public double getInterestRateOneMinute() {
        return interestRateOneMinute;
    }

    public void setInterestRateOneMinute(double interestRateOneMinute) {
        this.interestRateOneMinute = interestRateOneMinute;
    }

    public double getInterestRateFiveMinute() {
        return interestRateFiveMinute;
    }

    public void setInterestRateFiveMinute(double interestRateFiveMinute) {
        this.interestRateFiveMinute = interestRateFiveMinute;
    }

    public double getInterestRateTenMinute() {
        return interestRateTenMinute;
    }

    public void setInterestRateTenMinute(double interestRateTenMinute) {
        this.interestRateTenMinute = interestRateTenMinute;
    }

    public double getInterestRateSavingRegular() {
        return interestRateSavingRegular;
    }

    public void setInterestRateSavingRegular(double interestRateSavingRegular) {
        this.interestRateSavingRegular = interestRateSavingRegular;
    }

    public double getInterestRateSavingEntrepreneur() {
        return interestRateSavingEntrepreneur;
    }

    public void setInterestRateSavingEntrepreneur(double interestRateSavingEntrepreneur) {
        this.interestRateSavingEntrepreneur = interestRateSavingEntrepreneur;
    }

    public boolean isBankStatus() {
        return bankStatus;
    }

    public void setBankStatus(boolean bankStatus) {
        this.bankStatus = bankStatus;
    }

    public int getFkAdminId() {
        return fkAdminId;
    }

    public void setFkAdminId(int fkAdminId) {
        this.fkAdminId = fkAdminId;
    }

    public int getMinAmountOneMinuteDeposit() {
        return minAmountOneMinuteDeposit;
    }

    public void setMinAmountOneMinuteDeposit(int minAmountOneMinuteDeposit) {
        this.minAmountOneMinuteDeposit = minAmountOneMinuteDeposit;
    }

    public int getMinAmountFiveMinuteDeposit() {
        return minAmountFiveMinuteDeposit;
    }

    public void setMinAmountFiveMinuteDeposit(int minAmountFiveMinuteDeposit) {
        this.minAmountFiveMinuteDeposit = minAmountFiveMinuteDeposit;
    }

    public int getMinAmountTenMinuteDeposit() {
        return minAmountTenMinuteDeposit;
    }

    public void setMinAmountTenMinuteDeposit(int minAmountTenMinuteDeposit) {
        this.minAmountTenMinuteDeposit = minAmountTenMinuteDeposit;
    }
}


