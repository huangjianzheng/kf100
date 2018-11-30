package com.medhead.kf100.common.util.sms;

public class SmsLimitEntity {

    Integer count;
    Long lastTimeStamp;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Long getLastTimeStamp() {
        return lastTimeStamp;
    }

    public void setLastTimeStamp(Long lastTimeStamp) {
        this.lastTimeStamp = lastTimeStamp;
    }
}
