import java.io.IOException;
import java.io.PrintStream;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AdminUnitList {
    List<AdminUnit> units = new ArrayList<>();
    List<AdminUnit> woj = new ArrayList<>();
    Map<Long, AdminUnit> idToAdminUnit = new HashMap<>();
    Map<AdminUnit, Long> adminUnitToId = new HashMap<>();
    Map<Long,List<AdminUnit>> parentidToChild = new HashMap<>();
    CSVReader reader;

    public void read(String filename) throws IOException, ColumnNotFoundException {

        reader = new CSVReader(filename, ",", true);
        List<AdminUnit> rootChildren = new ArrayList<>();

        while (reader.next()) {
            AdminUnit unit = extractCurrentAdminUnit();
            units.add(unit);

            idToAdminUnit.put(reader.getLong("id"), unit);

            long parentId = reader.getLong("parent");

            adminUnitToId.put(unit, parentId);

            if (parentId != -1) {
                if (parentidToChild.containsKey(parentId)) {
                    parentidToChild.get(parentId).add(unit);
                } else {
                    List<AdminUnit> children = new ArrayList<>();
                    children.add(unit);
                    parentidToChild.put(parentId, children);
                }
            }
        }
        setRelations();
        fixMissingValues();
        }

    private AdminUnit extractCurrentAdminUnit() throws ColumnNotFoundException {
        AdminUnit unit = new AdminUnit();
        BoundingBox bbox = new BoundingBox();

        unit.name = reader.get("name");
        unit.adminLevel = reader.getInt("admin_level");
        unit.population = reader.getDouble("population");
        unit.density = reader.getDouble("density");
        unit.area = reader.getDouble("area");

        bbox.xMin = reader.getDouble("x1");
        bbox.xMax = reader.getDouble("x3");
        bbox.yMin = reader.getDouble("y1");
        bbox.yMax = reader.getDouble("y3");

        unit.bbox = bbox;
        return unit;
    }

    private void fixMissingValues() {
        units.forEach(AdminUnit::fixMissingValues);
    }

        private void setRelations() {

            for (AdminUnit unit:units) {
                long parentId = adminUnitToId.get(unit);
                unit.parent = idToAdminUnit.getOrDefault(parentId,null);
                if (unit.parent != null) {
                    unit.parent.children = parentidToChild.getOrDefault(parentId, new ArrayList<>());
                }
                else
                    woj.add(unit);
            }
        }

    void list(PrintStream out){
        units.forEach(out::println);
    }

    void list(PrintStream out,int offset, int limit ){

        units.stream().skip(offset).limit(limit).forEach(out::println);
    }

    AdminUnitList selectByName(String pattern, boolean regex){
        AdminUnitList ret = new AdminUnitList();
        for (AdminUnit unit:units
             ) {
            if(regex)
                if( unit.name.matches((pattern)) )
                    ret.units.add(unit);
            else
                if( unit.name.contains((pattern)) )
                    ret.units.add(unit);
        }

        return ret;
    }

    AdminUnitList getNeighbors(AdminUnit unit, double maxdistance) throws NoSuchFieldException {
        List<AdminUnit> units = new ArrayList<>();
        List<AdminUnit> neighbors = new ArrayList<>();
        units.addAll(woj);

        while(true)
        {
            if(units.get(0).adminLevel == unit.adminLevel)
                break;

            AdminUnit potential = units.remove(0);

            if(potential.bbox.intersects(unit.bbox))
            {
                units.addAll(potential.children);
            }
        }


        if(unit.adminLevel != 8)
        {
            for (AdminUnit u:units
            ) {
                if(u.bbox.intersects(unit.bbox))
                    neighbors.add(u);
            }
        }
        else
        {
            for (AdminUnit u:units
            ) {
                if(u.bbox.distanceTo(unit.bbox) < maxdistance)
                    neighbors.add(u);
            }
        }

        AdminUnitList list = new AdminUnitList();
        list.units.addAll(neighbors);

        return list;
    }

    AdminUnitList sortInplaceByName() {
        units.sort(new AdminUnitNameComparator());
        return this;
    }

    AdminUnitList sortInplaceByArea() {
        units.sort(
                new Comparator<AdminUnit>() {
                    @Override
                    public int compare(AdminUnit u1, AdminUnit u2) {
                        return Double.compare(u1.area, u2.area);
                    }
                });
        return this;
    }

    AdminUnitList sortInplaceByPopulation() {
        units.sort(
                (u1, u2) -> Double.compare(u1.population, u2.population));
        return this;
    }

    AdminUnitList sortInplace(Comparator<AdminUnit> cmp) {
        units.sort(cmp);
        return this;
    }

    AdminUnitList sort(Comparator<AdminUnit> cmp){
        AdminUnitList copy = copyList(units);
        copy.sortInplace(cmp);
        return  copy;
    }

    AdminUnitList filter(Predicate<AdminUnit> pred) {

        List<AdminUnit> filtered = new ArrayList<>();

        for (AdminUnit u:
                units) {
            if(pred.test(u))
            filtered.add(u);
        }

        return copyList(filtered);
    }

    AdminUnitList filter(Predicate<AdminUnit> pred, int limit) {
        List<AdminUnit> filtered =
                units.stream().limit(limit).filter(pred).collect(Collectors.toList());
        return copyList(filtered);
    }

    AdminUnitList filter(Predicate<AdminUnit> pred, int offset, int limit) {
        List<AdminUnit> filtered =
                units.stream().skip(offset).limit(limit).filter(pred).collect(Collectors.toList());
        return copyList(filtered);
    }

    AdminUnitList copyList(List<AdminUnit> list)
    {
        AdminUnitList copy = new AdminUnitList();
        for (AdminUnit u:
             list) {
            copy.units.add(u);
        }
        return copy;
    }

}



