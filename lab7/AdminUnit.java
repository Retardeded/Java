import java.util.List;

public class AdminUnit {
    String name;
    int adminLevel;
    double population;
    double area;
    double density;
    AdminUnit parent;
    List<AdminUnit> children;
    BoundingBox bbox = new BoundingBox();

    @Override
    public String toString() {
        return "AdminUnit{"
                + "name='"
                + name
                + '\''
                + ", adminLevel="
                + adminLevel
                + ", population="
                + population
                + ", area="
                + area
                + ", density="
                + density
                + ", parent="
                + parent
                + ", bbox="
                + bbox
                + '}';
    }

    void fixMissingValues() {
        if (density != -1 && population != -1) {
            return;
        }

        AdminUnit parentUnit = parent;

        if (parentUnit == null) {
            return;
        }

        density = parentUnit.density;
        population = area * density;
    }

}