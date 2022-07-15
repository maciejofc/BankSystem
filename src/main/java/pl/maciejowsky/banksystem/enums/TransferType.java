package pl.maciejowsky.banksystem.enums;

import java.math.BigDecimal;

public enum TransferType {
    NORMAL(new BigDecimal("0"), 60000),
    EXPRESS(new BigDecimal("5"), 30000),
    INSTANT(new BigDecimal("15"), 0);
    private final BigDecimal fee;
    private final int timeOfSendingInSec;

    TransferType(BigDecimal fee, int timeOfSendingInSec) {
        this.fee = fee;
        this.timeOfSendingInSec = timeOfSendingInSec;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public int getTimeOfSendingInSec() {
        return timeOfSendingInSec;
    }

}
