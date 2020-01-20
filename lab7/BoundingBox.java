public class BoundingBox {
    double xMin;
    double yMin;
    double xMax;
    double yMax;

    void addPoint(double x, double y){

        if(isEmpty())
        {
            xMin = xMax = x;
            yMin = yMax = y;
            return;
        }

        if(contains(x,y))
            return;

        xMin = Math.min(x,xMin);
        xMax = Math.max(x,xMax);
        yMin = Math.min(y,yMin);
        yMax = Math.max(y,yMax);
    }

    /**
     * Sprawdza, czy BB zawiera punkt (x,y)
     * @param x
     * @param y
     * @return
     */
    boolean contains(double x, double y){

        if(isEmpty())
            return false;

        if( (x <= xMax && x >= xMin) && (y <= yMax && y >= yMin) )
            return true;

        return false;
    }

    /**
     * Sprawdza czy dany BB zawiera bb
     * @param bb
     * @return
     */
    boolean contains(BoundingBox bb){

        return contains(bb.xMin,bb.yMin) && contains(bb.xMax,bb.yMax);
    }

    /**
     * Sprawdza, czy dany BB przecina się z bb
     * @param bb
     * @return
     */
    boolean intersects(BoundingBox bb){

        if(isEmpty())
            return false;

        if((xMin <= bb.xMax)
            && (xMax >= bb.xMin)
            && (yMin <= bb.yMax)
            && (yMax >= bb.yMin))
            return true;

        return false;
    }
    /**
     * Powiększa rozmiary tak, aby zawierał bb oraz poprzednią wersję this
     * @param bb
     * @return
     */
    BoundingBox add(BoundingBox bb){

        if(isEmpty())
            return bb;

        xMin = Math.min(bb.xMin,xMin);
        xMax = Math.max(bb.xMax,xMax);
        yMin = Math.min(bb.yMin,yMin);
        yMax = Math.max(bb.yMax,yMax);

        return this;
    }
    /**
     * Sprawdza czy BB jest pusty
     * @return
     */
    boolean isEmpty(){

        return Double.isNaN(xMax) && Double.isNaN(xMin) && Double.isNaN(yMax) && Double.isNaN(yMin);
    }

    /**
     * Oblicza i zwraca współrzędną x środka
     * @return if !isEmpty() współrzędna x środka else wyrzuca wyjątek
     * (sam dobierz typ)
     */
    double getCenterX() throws NoSuchFieldException {

        if(isEmpty())
            throw new NoSuchFieldException("Empty bounding box");

        return (xMax + xMin)/2;

    }
    /**
     * Oblicza i zwraca współrzędną y środka
     * @return if !isEmpty() współrzędna y środka else wyrzuca wyjątek
     * (sam dobierz typ)
     */
    double getCenterY() throws NoSuchFieldException {
        if(isEmpty())
            throw new NoSuchFieldException("Empty bounding box");

        return (yMax + yMin)/2;
    }

    /**
     * Oblicza odległość pomiędzy środkami this bounding box oraz bbx
     * @param bbx prostokąt, do którego liczona jest odległość
     * @return if !isEmpty odległość, else wyrzuca wyjątek lub zwraca maksymalną możliwą wartość double
     * Ze względu na to, że są to współrzędne geograficzne, zamiast odległosci euklidesowej możesz użyć wzoru haversine
     * (ang. haversine formula)
     */
    double distanceTo(BoundingBox bbx) throws NoSuchFieldException {
        if(isEmpty() || bbx.isEmpty() )
            throw new NoSuchFieldException("Empty bounding box");

        double x1 = getCenterX();
        double y1 = getCenterY();
        double x2 = bbx.getCenterX();
        double y2 = bbx.getCenterY();

        return haversineDistance(
                Math.min(y1, y2),
                Math.min(x1, x2),
                Math.max(y1, y2),
                Math.max(x1, x2));

        //return ( Math.pow( (Math.pow(x1-x2,2) + Math.pow(x1-x2,2)),0.5) );
    }

     double haversineDistance(double startLat, double startLong,
                                  double endLat, double endLong) {

         int earthRadius = 6371;

        double dLat  = Math.toRadians((endLat - startLat));
        double dLong = Math.toRadians((endLong - startLong));

        startLat = Math.toRadians(startLat);
        endLat   = Math.toRadians(endLat);

        double a = haversin(dLat) + Math.cos(startLat) * Math.cos(endLat) * haversin(dLong);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return earthRadius * c; // <-- d
    }

    double haversin(double val) {
        return Math.pow(Math.sin(val / 2), 2);
    }


    @Override
    public String toString() {
        return "BoundingBox{"
                + "xMin="
                + xMin
                + ", xMax="
                + xMax
                + ", yMin="
                + yMin
                + ", yMax="
                + yMax
                + '}';
    }
}