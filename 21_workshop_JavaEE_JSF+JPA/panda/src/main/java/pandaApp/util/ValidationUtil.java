package pandaApp.util;

import javax.validation.ConstraintViolation;
import java.util.Set;

public interface ValidationUtil {

    <M> boolean isValid(M model);

    <M> Set<ConstraintViolation<M>> getViolations(M model);
}
