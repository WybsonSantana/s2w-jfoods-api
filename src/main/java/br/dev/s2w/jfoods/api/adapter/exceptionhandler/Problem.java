package br.dev.s2w.jfoods.api.adapter.exceptionhandler;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class Problem {
    private LocalDateTime timestamp;
    private String message;
}