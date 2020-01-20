import java.util.Comparator;

public class AdminUnitNameComparator implements Comparator<AdminUnit> {

    @Override
    public int compare(AdminUnit u1, AdminUnit u2) {
        return u1.name.compareTo(u2.name);
    }

}