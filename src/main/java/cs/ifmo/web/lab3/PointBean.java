package cs.ifmo.web.lab3;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Data
@Named
@ApplicationScoped
public class PointBean implements Serializable {
    private Double x;
    private Double y;
    private Double yMouse;
    private boolean[] rMap;
    private boolean inside;
    private String dateTime;
    private int lastR = 3;

    private static List<Integer> Y_RANGE = List.of(-5, -4, -3, -2, -1, 0, 1, 2, 3, 4);
    private static List<Integer> R_RANGE = List.of(1, 2, 3, 4, 5);

    @Inject
    private PointService service;

    public PointBean() {
        this.rMap = new boolean[R_RANGE.size()];
        this.rMap[lastR - 1] = true;
    }

    public PointBean(double x, double y, int r, boolean inside, String dateTime) {
        this.x = x;
        this.y = y;
        this.rMap = new boolean[R_RANGE.size()];
        this.rMap[r - 1] = true;
        this.inside = inside;
        this.dateTime = dateTime;
        this.lastR = r;
    }

    public void create() {
        y = y != -5 ? y : yMouse;
        inside = PointChecker.checkCoordinates(x, y, getR());
        var point = PointMapper.toPoint(this);
        service.add(point);
        y = null;
        yMouse = null;
    }

    public int getR() {
        for (int i = 0; i < rMap.length; i++) {
            if (rMap[i])
                return i + 1;
        }
        throw new IllegalStateException("No last R");
    }

    public void setR(int r) {
        Arrays.fill(rMap, false);
        rMap[r - 1] = true;
        lastR = r;
    }

    public List<Integer> getYRange() {
        return Y_RANGE;
    }

    public List<Integer> getRRange() {
        return R_RANGE;
    }

    public List<PointBean> getList() {
        return service.getAll()
                .stream()
                .map(PointMapper::toBean)
                .collect(toList());
    }
}
