/**
 * Information about ome mountain.
 */
public class Mountain {
    private final int size = 3;
    private double peakHeight;
    private double peak;
    private double leftEdge;
    private double rightEdge;
    private double bottomLevel;

    private final double rightEdgeAngle;
    private final double leftEdgeAngle;

    public Mountain(double peak, double peakHeight, double leftEdge, double rightEdge, double bottomLevel) {
        this.peak = peak;
        this.peakHeight = bottomLevel - peakHeight;
        this.leftEdge = leftEdge;
        this.rightEdge = rightEdge;
        this.bottomLevel = bottomLevel;

        this.rightEdgeAngle = Math.atan(peakHeight / (rightEdge - peak));
        this.leftEdgeAngle = Math.atan(peakHeight / (leftEdge - peak));
    }

    public int getSize() {
        return this.size;
    }

    public double getPeakHeight() {
        return this.peakHeight;
    }

    public double getPeak() {
        return this.peak;
    }

    public double getLeftEdge() {
        return this.leftEdge;
    }

    public double getRightEdge() {
        return this.rightEdge;
    }

    /**
     * converts x coordinates of mountain vertexes to array
     * @return array with x coordinates
     */
    public double[] getX() {
        double[] x = new double[]{leftEdge, peak, rightEdge};
        return x;
    }

    /**
     * converts y coordinates of mountain vertexes to array
     * @return array with y coordinates
     */
    public double[] getY() {
        double[] y = new double[]{bottomLevel, peakHeight, bottomLevel};
        return y;
    }

    public double getLeftEdgeAngle() {
        return leftEdgeAngle;
    }

    public double getRightEdgeAngle() {
        return rightEdgeAngle;
    }
}
