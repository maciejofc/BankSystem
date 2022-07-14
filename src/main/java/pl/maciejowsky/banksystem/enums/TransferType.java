package pl.maciejowsky.banksystem.enums;

import java.math.BigDecimal;

public enum TransferType {
    NORMAL(new BigDecimal("0")),
    EXPRESS(new BigDecimal("5")),
    INSTANT(new BigDecimal("15"));
    private final BigDecimal fee;

     TransferType(BigDecimal fee) {
        this.fee = fee;
    }

    public BigDecimal getFee() {
        return fee;
    }

}
