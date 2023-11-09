package io.prajwal.runnerapp.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Formatter {

    public String formatDate(LocalDateTime date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
        return date != null ? date.format(formatter): "";
    };

    public String formatDateTime (LocalDateTime date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy 'T'HH:mm");
        return date != null ? date.format(formatter): "";
    };

    public String formatTime(LocalDateTime date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return date != null ? date.format(formatter): "";
    }
}
