package com.lvg.tcreator.converters;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Victor Levchenko LVG Corp. on 21.03.2020.
 */
public class DateConverter {
    public static void main(String[] args)throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date date = sdf.parse("20-03-2020");

        System.out.println(sdf.format(date));
    }
}
