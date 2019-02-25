package pandaApp.util;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public class ValidationUtilImpl implements ValidationUtil{
    private Validator validator;

    public ValidationUtilImpl() {
        this.validator= Validation
                .buildDefaultValidatorFactory()
                .getValidator();
    }
    @Override
    public <M> boolean isValid(M model){
        return this.validator.validate(model).isEmpty();
    }
    @Override
    public <M> Set<ConstraintViolation<M>> getViolations(M model){
        return this.validator.validate(model);
    }
}
