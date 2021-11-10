package com.amirhosseinemadi.myAccountant.common;

public class FaNumber {

    public static String faNum(String numbers)
    {
        String[][] mChar = new String[][]
                {
                        {"0","۰"},
                        {"1","۱"},
                        {"2","۲"},
                        {"3","۳"},
                        {"4","۴"},
                        {"5","۵"},
                        {"6","۶"},
                        {"7","۷"},
                        {"8","۸"},
                        {"9","۹"},
                };

        for (String[] num : mChar)
        {
            numbers = numbers.replace(num[0],num[1]);
        }

        return numbers;
    }

}
