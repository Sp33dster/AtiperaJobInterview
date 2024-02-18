package pl.atipera.task.error;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionMessage {
    private int status;
    private String message;

    public ExceptionMessage(int value, String somethingWentWrong) {
        this.status = value;
        this.message = somethingWentWrong;
    }
}
