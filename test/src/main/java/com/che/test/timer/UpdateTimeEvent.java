package com.che.test.timer;

import lombok.Data;

/**
 * 作者：余天然 on 16/9/19 下午1:27
 */
@Data
public class UpdateTimeEvent {
    long time;

    public UpdateTimeEvent(long time) {
        this.time = time;
    }
}
