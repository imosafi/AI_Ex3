
/*
 * this class is used to define a cluster
 * includes multiple ctors
 */
import java.util.ArrayList;
import java.util.Arrays;

public class Cluster {
	public static int runningId;
	public int id;
	ArrayList<Point> points;

	static {
		runningId = 1;
	}

	public Cluster() {
		this.points = new ArrayList<Point>();
	}

	public Cluster(ArrayList<Point> points) {
		this.points = points;
	}

	public Cluster(Point point) {
		this.points = new ArrayList<Point>(Arrays.asList(point));
	}
}
