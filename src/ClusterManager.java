
/*
 * this class is the the manager and the engine for the exercise
 * it includes the actual decreasing of the clusters to the exact required amount
 */

import java.util.ArrayList;

public class ClusterManager {
	private int numberOfClusters;
	private Definitions.DistanceComputationType distanceComputationType;
	ArrayList<Point> points;
	ArrayList<Cluster> clusters;
	private DistanceCalculator distanceCalculator;

	public ClusterManager(int numberOfClusters, Definitions.DistanceComputationType distanceComputationType, ArrayList<Point> pointsList) {
		this.numberOfClusters = numberOfClusters;
		this.distanceComputationType = distanceComputationType;
		this.points = pointsList;
		this.clusters = new ArrayList<Cluster>();
		this.distanceCalculator = new DistanceCalculator();
		initializeClusters();
	}

	private void initializeClusters() {
		for (Point point : points)
			clusters.add(new Cluster(point));
	}

	public void runClusterDividing() { // check if they can trick you with
										// example than won't allow you to stop
		while (clusters.size() != numberOfClusters)
			runIteration();
		SetClustersIds();
	}

	private void SetClustersIds() {
		for (Cluster cluster : clusters)
			cluster.id = Cluster.runningId++;
	}

	private void runIteration() {
		Pair<Cluster> closestClusters = getClosestClusters();
		mergeClusters(closestClusters);
	}

	private void mergeClusters(Pair<Cluster> closestClusters) {
		Cluster c1 = closestClusters.obj1;
		Cluster c2 = closestClusters.obj2;

		while (c2.points.size() > 0)
			c1.points.add(c2.points.remove(0));
		clusters.remove(c2);
	}

	private Pair<Cluster> getClosestClusters() {
		Cluster pairObj1 = null, pairObj2 = null;
		double clustersDistance = Double.MAX_VALUE;

		for (Cluster c1 : clusters) {
			for (Cluster c2 : clusters) {
				if (c1 != c2) {
					double tempDistance = distanceCalculator.calculateDistance(c1, c2, distanceComputationType);
					if (tempDistance < clustersDistance) {
						clustersDistance = tempDistance;
						pairObj1 = c1;
						pairObj2 = c2;
					}
				}
			}
		}
		return new Pair<Cluster>(pairObj1, pairObj2);
	}

	public int getPointClusterId(Point point) {
		for (Cluster cluster : clusters)
			if (cluster.points.contains(point))
				return cluster.id;
		return -1;
	}

// public void ReassignClustersIds() {
// int id = 1;
// for (Cluster cluster : clusters)
// cluster.id = id++;
// }

}
