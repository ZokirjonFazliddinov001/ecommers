package uz.pdp.ecommers.entity;

import java.text.DecimalFormat;
import java.util.Locale;

public class NumberFormat {
    static DecimalFormat formatter = (DecimalFormat) java.text.NumberFormat.getNumberInstance(Locale.US);
    static {
        formatter.applyPattern("#,###");
    }
    public static String format(Integer num) {
        return formatter.format(num);
    }
    public static String format(Long num) {
        return formatter.format(num);
    }
}
