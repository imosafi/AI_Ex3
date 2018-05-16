/*
 * this class is used to calculate distances
 * we choose the required method singleLink or average link and according to that return the distance
 */

public class DistanceCalculator {

	public DistanceCalculator() {
		// double x = CalculateDistance(new Point(2, -1), new Point(-2, 2));

// ArrayList<Point> l1 = new ArrayList<Point>();
// l1.add(new Point(1.2, 1));
// l1.add(new Point(1, 3));
// l1.add(new Point(2, 2.5));
// ArrayList<Point> l2 = new ArrayList<Point>();
// l2.add(new Point(4, 2.5));
// l2.add(new Point(5, 2.5));
// l2.add(new Point(6, 2.5));
//
// double t = CalculateAverageLinkDistance(new Cluster(l1), new Cluster(l2));
	}

	public double calculateDistance(Cluster cluster1, Cluster cluster2, Definitions.DistanceComputationType computationType) {
		return computationType == Definitions.DistanceComputationType.SingleLink ? calculateSingleLinkDistance(cluster1, cluster2)
				: calculateAverageLinkDistance(cluster1, cluster2);
	}

	private double calculateSingleLinkDistance(Cluster cluster1, Cluster cluster2) {
		double minDistance = Double.MAX_VALUE;
		for (Point p1 : cluster1.points)
			for (Point p2 : cluster2.points) {
				double distance = CalculateDistance(p1, p2);
				if (distance < minDistance)
					minDistance = distance;
			}
		return minDistance;
	}

	private double calculateAverageLinkDistance(Cluster cluster1, Cluster cluster2) {
		double distanceSum = 0;
		for (Point p1 : cluster1.points)
			for (Point p2 : cluster2.points) {
				distanceSum += CalculateDistance(p1, p2);
			}
		return distanceSum * ((double)1 / (cluster1.points.size() * cluster2.points.size()));

	}

	private double CalculateDistance(Point p1, Point p2) {
		return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
	}

}
