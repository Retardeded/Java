import java.io.IOException;
import java.util.Locale;
import java.util.function.Predicate;

public class Main {

    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        AdminUnitList list = new AdminUnitList();
        String fileName = "admin-units.csv";

        try {
            list.read(fileName);

            System.out.println("województwo małopolskie".matches(".*małop.*"));
            System.out.println("województwo małopolskie".matches("^wojew.*"));
            System.out.println("województwo pomorskie".matches(".*skie"));
            System.out.println("województwo małopolskie".contains("małop"));

            AdminUnit unit = list.selectByName(".*małop.*", true).units.get(0);

            double t1 = System.nanoTime()/1e6;
            AdminUnitList neigh = list.getNeighbors(unit,15);
            double t2 = System.nanoTime()/1e6;
            System.out.printf(Locale.US,"t2-t1=%f\n",t2-t1);
            neigh.list(System.out, 0, 15);
            System.out.print("\n");

            list.filter(a->a.name.startsWith("Ż")).sortInplaceByArea().list(System.out);

            System.out.println("=== Filtering");
            Predicate<AdminUnit> predicateStartingWithI = item -> item.name.startsWith("I");
            AdminUnitList unitsStartingWithI = list.filter(predicateStartingWithI).sortInplaceByName();
            AdminUnitList districts =
                    list.filter(
                            item ->
                                    item.adminLevel == 6
                                            && item.parent.name.equals("województwo małopolskie"));

            unitsStartingWithI.list(System.out);
            System.out.print("\n");
            districts.list(System.out);
            System.out.print("\n");

            AdminUnitList unitsNotStartingWithK = list.filter(predicateStartingWithI.negate());

            AdminUnitQuery query = new AdminUnitQuery()
                    .selectFrom(list)
                    .where(a->a.area>1000)
                    .or(a->a.name.startsWith("Sz"))
                    .sort((a,b)->Double.compare(a.area,b.area))
                    .limit(100);
            query.execute().list(System.out);

        } catch (IOException | ColumnNotFoundException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}