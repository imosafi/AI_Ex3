
/*
 * this class is used to read and parse the input.txt file
 * I assume that input.txt location is at the main project folder
 */

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class InputFileParser {
	private static final int DistanceComputationTypeRowIndex = 0;
	private static final int NumberOfClustersRowIndex = 1;
	private static final int PointsBeginningRowIndex = 2;

	Path path;
	Definitions.DistanceComputationType distanceComputatuionType;
	int numberOfClusters;
	ArrayList<Point> points;

	public InputFileParser() {
		path = FileSystems.getDefault().getPath("", "input.txt");
		points = new ArrayList<Point>();
	}

	public void parseFileContent() {
		try {
			ArrayList<String> fileLines = (ArrayList<String>)Files.readAllLines(path);
			distanceComputatuionType = fileLines.get(DistanceComputationTypeRowIndex).contains("single link") ? Definitions.DistanceComputationType.SingleLink
					: Definitions.DistanceComputationType.AverageLink;
			numberOfClusters = Integer.parseInt(fileLines.get(NumberOfClustersRowIndex));

			for (int i = PointsBeginningRowIndex; i < fileLines.size(); i++) {
				String[] inputLine = fileLines.get(i).split(",");
				points.add(new Point(Double.parseDouble(inputLine[0]), Double.parseDouble(inputLine[1])));
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}
