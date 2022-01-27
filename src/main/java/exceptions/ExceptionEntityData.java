package exceptions;

import lombok.Getter;
import lombok.Setter;
import models.Categories;

@Getter
@Setter
public class ExceptionEntityData extends Throwable {
    private String message;

    public ExceptionEntityData(String message) {
        this.message = message;
    }

    public static void entityExceptionNull(Object cat) throws ExceptionEntityData {
        if (cat instanceof Categories){
            if (cat == null) {
                throw new ExceptionEntityData("The Category with that id not found!");
            }
        }

    }
}
