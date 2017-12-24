/**
 * Created by Виктория on 19.12.2017.
 */
public class Mountain {
    private final int size = 3;
    private double peakHeight;
    private double peak;
    private double leftEdge;
    private double rightEdge;
    private double bottomLevel;

    public Mountain(double peak, double peakHeight, double leftEdge, double rightEdge, double bottomLevel) {
        this.peak = peak;
        this.peakHeight = peakHeight;
        this.leftEdge = leftEdge;
        this.rightEdge = rightEdge;
        this.bottomLevel = bottomLevel;
    }

    public int getSize() {
        return this.size;
    }

    public double getPeakHeight() {
        return this.bottomLevel - this.peakHeight;
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

    public double[] getX() {
        double[] x = new double[]{leftEdge, peak, rightEdge};
        return x;
    }

    public double[] getY() {
        double[] y = new double[]{bottomLevel, bottomLevel - peakHeight, bottomLevel};
        return y;
    }
}
