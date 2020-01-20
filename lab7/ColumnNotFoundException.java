public class ColumnNotFoundException extends Exception {
    public ColumnNotFoundException(String column) {
        super(String.format("Column \"%s\" not found", column));
    }
}