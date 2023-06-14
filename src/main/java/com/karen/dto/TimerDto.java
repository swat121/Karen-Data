package com.karen.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TimerDto {

    private String microName;

    private String switcherName;

    private String status;

    private LocalTime timeOff;

    private int timerTime;
}
