package cs.ifmo.web.lab3;

public class PointChecker {
    public static boolean checkCoordinates(double x, double y, double r) {
        return  (((x <= r) && (x >= 0) && (y <= 0) && (y >= -r / 2)) && (y >= (x - r) / 2) || // треугольник
                ((x <= 0) && (x >= -r) && (y >= 0) && (y <= r / 2)) || // прямоугольник
                ((Math.pow(x, 2) + Math.pow(y, 2)) <= ((Math.pow(r / 2, 2))) && (x <= 0) && (y <= 0))); // круг
    }
}