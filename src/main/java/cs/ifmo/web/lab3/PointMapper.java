package cs.ifmo.web.lab3;

import java.time.LocalDateTime;
import java.util.Map;

import static cs.ifmo.web.lab3.DateTimeBean.FORMATTER;

public class PointMapper {
    public static Point toPoint(PointBean b) {
        return new Point(b.getX(), b.getY(), b.getR(), b.isInside(), LocalDateTime.now());
    }

    public static PointBean toBean(Point p) {
        return new PointBean(p.getX(), p.getY(), p.getR(), p.isInside(), p.getDt().format(FORMATTER));
    }
}
