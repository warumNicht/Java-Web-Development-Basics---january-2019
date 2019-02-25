package cats2App.web.appBeans;

import cats2App.domain.entities.GenderType;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named(value = "gender")
@ApplicationScoped
public class GenderBean {
    private GenderType[] genderTypes;

    public GenderBean() {
        this.genderTypes=GenderType.values();
    }

    public GenderType[] getGenderTypes() {
        return genderTypes;
    }
}
