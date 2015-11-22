package org.medusa.barrage.util;

import java.time.LocalDateTime;

/**
 * Created by 王湛智 on 2015/11/22.
 * 说明：
 */
public class DateUtil {

    public static String now() {
        return LocalDateTime.now().toString().replace("T", " ");
    }
}
