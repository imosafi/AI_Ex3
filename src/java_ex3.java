/*
 * main class containing the main function
 * class name is identical to the exercise name
 */
public class java_ex3 {

	public static void main(String[] args) {
		InputFileParser inputFileParser = new InputFileParser();
		OutputWriter outputWriter = new OutputWriter();
		ClusterManager clusterManager;

		inputFileParser.parseFileContent();
		clusterManager = new ClusterManager(inputFileParser.numberOfClusters, inputFileParser.distanceComputatuionType, inputFileParser.points);
		clusterManager.runClusterDividing();

		// change way of giving ids to clusters, probably after finishing and
		// not before
// clusterManager.ReassignClustersIds(); // check with Ariel?? is it ok??

		for (Point point : clusterManager.points)
			outputWriter.writeToFile(String.valueOf(clusterManager.getPointClusterId(point)));

		outputWriter.closeStream();

	}

}
