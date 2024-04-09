package cs.ifmo.web.lab3;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Named
@ViewScoped
public class DateTimeBean implements Serializable {
    final static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final int interval = 13;

    public String getDateTime() {
        return LocalDateTime.now().format(FORMATTER);
    }
}
