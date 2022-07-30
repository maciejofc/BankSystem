package pl.maciejowsky.banksystem;

import java.math.BigDecimal;
import java.math.MathContext;

public class Utility {

    public static BigDecimal roundBigDecimalTo2Places(BigDecimal bigDecimal) {
        MathContext mathContext = new MathContext(bigDecimal.toBigInteger().toString().length() + 2);
        return bigDecimal.round(mathContext);
    }
}
