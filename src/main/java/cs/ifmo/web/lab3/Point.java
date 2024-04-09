package cs.ifmo.web.lab3;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "points")
@NamedQuery(name="Point.selectAll", query="SELECT p FROM Point p")
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double x;
    private double y;
    private int r;
    private boolean inside;
    private LocalDateTime dt;

    public Point(double x, double y, int r, boolean inside, LocalDateTime dt) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.inside = inside;
        this.dt = dt;
    }
}
