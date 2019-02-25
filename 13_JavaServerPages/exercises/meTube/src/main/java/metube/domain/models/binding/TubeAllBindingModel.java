package metube.domain.models.binding;

public class TubeAllBindingModel {
    private String name;

    public TubeAllBindingModel(String name) {
        this.name = name;
    }

    public TubeAllBindingModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
