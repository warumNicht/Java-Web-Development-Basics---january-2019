package cats2App.domain.entities;

public enum GenderType {
    Male("Male"),
    Female("Female");

    private String label;

    private GenderType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
