package com.example.demo;

import java.time.LocalDateTime;

public class Test1 {
    private LocalDateTime time;

    public Test1() {
    }

    public Test1(LocalDateTime time) {
        this.time = time;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
