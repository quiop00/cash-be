package com.ryu.common.utils;

import java.text.DecimalFormat;
import java.util.Random;

public class DataUtils {

    // generate OTP
    public static String generateOtp() {
        return new DecimalFormat("000000").format(new Random().nextInt(999999));
    }
}
