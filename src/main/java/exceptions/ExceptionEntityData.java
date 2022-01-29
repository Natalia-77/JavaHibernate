package exceptions;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ExceptionEntityData extends Throwable {
    private String message;

    public ExceptionEntityData(String message) {
        this.message = message;
    }

    public static void entityExceptionNull(Object obj) throws ExceptionEntityData {
        if (obj == null) {
            throw new ExceptionEntityData("The instance with that id not found!");
        }
    }
}
