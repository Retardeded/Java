import java.io.IOException;

public class Main {

  public static void main(String[] args) {
    new Main();
  }

  public Main() {
    AdminUnitList list = new AdminUnitList();
    String filePath = getClass().getResource("/admin-units.csv").getPath();

    try {
      list.read(filePath);
      list.list(System.out, 0, 1);
      System.out.println(list.get(0).children);

    } catch (IOException e) {
      e.printStackTrace();
    } catch (ColumnNotFoundException e) {
      e.printStackTrace();
    }
  }
}

// https://github.com/MrPumpking/java-university-classes/tree/master/src/main/java/com/github/mrpumpking
