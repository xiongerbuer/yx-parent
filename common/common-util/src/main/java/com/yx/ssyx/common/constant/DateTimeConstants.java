package com.yx.ssyx.common.constant;

import javax.annotation.Nonnull;
import java.time.LocalDateTime;
import java.time.OffsetTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public interface DateTimeConstants {

    String YYYY_MM_DD = "yyyy-MM-dd";
    String HH_MM_SS = "HH:mm:ss";
    String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    DateTimeFormatter DATE = DateTimeFormatter.ofPattern(YYYY_MM_DD);
    DateTimeFormatter TIME = DateTimeFormatter.ofPattern(HH_MM_SS);
    DateTimeFormatter DATE_TIME = DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS);

    ZoneId ZONE_ID = ZoneId.of("Asia/Shanghai");
    ZoneOffset ZONE_OFFSET = OffsetTime.now(ZONE_ID).getOffset();

    static long toTimestamp(@Nonnull LocalDateTime localDateTime) {
        return localDateTime.toInstant(ZONE_OFFSET).getEpochSecond();
    }

    static LocalDateTime longToDateTime(long timestamp) {
        return LocalDateTime.ofEpochSecond(timestamp, 0, ZONE_OFFSET);
    }

}
