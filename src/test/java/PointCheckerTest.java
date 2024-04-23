import cs.ifmo.web.lab3.PointChecker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PointCheckerTest {
    @Test
    public void checkCoordinate_withRectangle() {
        System.out.println("Test 1");
        // given
        List<List<Double>> inputTrueVariables = List.of(
                List.of(-1D, 0D, 1D),
                List.of(-2D, 1D, 3D),
                List.of(-0.5D, 0.5D, 2D)
        );

        List<List<Double>> inputFalseVariables = List.of(
                List.of(1D, 0.5D, 1D),
                List.of(-4D, 1D, 3D),
                List.of(-0.5D, -0.5D, 0.25D)
        );

        // when
        List<Boolean> trueResults = inputTrueVariables.stream()
                .map(point -> PointChecker.checkCoordinates(point.get(0), point.get(1), point.get(2)))
                .toList();
        List<Boolean> falseResults = inputFalseVariables.stream()
                .map(point -> PointChecker.checkCoordinates(point.get(0), point.get(1), point.get(2)))
                .toList();

        // then
        trueResults.forEach(Assertions::assertTrue);
        falseResults.forEach(Assertions::assertFalse);
    }

    @Test
    public void checkCoordinate_withCircleQuarter() {
        System.out.println("Test 2");
        // given
        List<List<Double>> inputTrueVariables = List.of(
                List.of(-0.5D, -0.5D, 3D),
                List.of(-0.5D, -1D, 3D),
                List.of(-0.5D, -0.25D, 5D)
        );

        List<List<Double>> inputFalseVariables = List.of(
                List.of(1D, 0.5D, 1D),
                List.of(-3D, -1D, 3D),
                List.of(-0.5D, 0.5D, 0.25D)
        );

        // when
        List<Boolean> trueResults = inputTrueVariables.stream()
                .map(point -> PointChecker.checkCoordinates(point.get(0), point.get(1), point.get(2)))
                .toList();
        List<Boolean> falseResults = inputFalseVariables.stream()
                .map(point -> PointChecker.checkCoordinates(point.get(0), point.get(1), point.get(2)))
                .toList();

        // then
        trueResults.forEach(Assertions::assertTrue);
        falseResults.forEach(Assertions::assertFalse);
    }

    @Test
    public void checkCoordinate_withTriangleQuarter() {
        System.out.println("Test 3");
        // given
        List<List<Double>> inputTrueVariables = List.of(
                List.of(1D, -0.5D, 3D),
                List.of(2D, -1D, 4D),
                List.of(0.5D, -0.25D, 5D)
        );

        List<List<Double>> inputFalseVariables = List.of(
                List.of(-1D, -0.5D, 1D),
                List.of(-3D, -10D, 3D),
                List.of(0.5D, 0.25D, 2D)
        );

        // when
        List<Boolean> trueResults = inputTrueVariables.stream()
                .map(point -> PointChecker.checkCoordinates(point.get(0), point.get(1), point.get(2)))
                .toList();
        List<Boolean> falseResults = inputFalseVariables.stream()
                .map(point -> PointChecker.checkCoordinates(point.get(0), point.get(1), point.get(2)))
                .toList();

        // then
        trueResults.forEach(Assertions::assertTrue);
        falseResults.forEach(Assertions::assertFalse);
    }
}
