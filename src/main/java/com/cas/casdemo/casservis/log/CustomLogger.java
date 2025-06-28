// src/main/java/com/cas/casdemo/log/CustomLogger.java
package com.cas.casdemo.casservis.log;

import com.cas.casdemo.casservis.entity.LogEntry;
import com.cas.casdemo.casservis.repository.LogEntryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class CustomLogger {

    private final LogEntryRepository logRepo;

    public void info(String tag, String message) {
        saveLog("INFO", tag, message, null);
    }

    public void error(String tag, String message, Throwable ex) {
        saveLog("ERROR", tag, message, getStackTrace(ex));
    }

    private void saveLog(String level, String tag, String message, String stackTrace) {
        LogEntry log = LogEntry.builder()
                .level(level)
                .tag(tag)
                .message(message)
                .stackTrace(stackTrace)
                .timestamp(LocalDateTime.now())
                .build();

        logRepo.save(log);
    }

    private String getStackTrace(Throwable ex) {
        if (ex == null) return null;
        StringWriter sw = new StringWriter();
        ex.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }
}
