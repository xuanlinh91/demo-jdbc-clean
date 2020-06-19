package com.example.demo;

import java.time.LocalDateTime;

public class Test2 {
    private LocalDateTime time;

    public Test2() {
    }

    public Test2(LocalDateTime time) {
        this.time = time;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
