import java.util.Random;

public final class FallingBall {

	private FallingBall() {
		throw new AssertionError("Cannot be instantiated");
	}

	public static void computePath(int prevElevation, int i, int j, int rows, int cols, int[][] elevations) {
		if (elevations == null) {
			throw new IllegalArgumentException("Elevations cannot be null");
		}

		// 공이 격자 지도의 영역 안에 있는지 확인합니다.
		if (i >= 0 && i <= (rows - 1) && j >= 0 && j <= (cols - 1)) {
			int currentElevation = elevations[i][j];

			// 공이 이전 칸에서 현재 칸으로 떨어질 수 있는지 확인합니다.
			if (prevElevation >= currentElevation && currentElevation > 0) {
				// 다음 이동을 계산할 때 사용하기 위해 현재 고도를 저장합니다.
				prevElevation = currentElevation;

				// 이 칸을 방문했다고 표시합니다.
				elevations[i][j] = 0;

				// 공을 이동할 수 있는지 확인합니다.
				computePath(prevElevation, i, j - 1, rows, cols, elevations);
				computePath(prevElevation, i - 1, j, rows, cols, elevations);
				computePath(prevElevation, i, j + 1, rows, cols, elevations);
				computePath(prevElevation, i + 1, j, rows, cols, elevations);
			}
		}
	}

	public static void main(String[] args) {
		int cols = 5;
		int rows = 5;

		// 각 영역은 고도에 해당하는 1, 2, 3, 4, 5(가장 높음)라는 값을 갖습습니다.
		int elevation = 5;

		Random rnd = new Random();

		int[][] elevations = new int[rows][cols];

		// 임의의 고도를 추가합니다.
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				elevations[i][j] = 1 + rnd.nextInt(elevation);
			}
		}

		// 화면에 각 격자 영역에 들어 있는 고도의 값을 표시합니다.
		System.out.println("Initial grid:");
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				System.out.format("%2s", elevations[i][j]);
			}

			System.out.println();
		}

		System.out.println("Middle cell has elevation: " + elevations[rows / 2][cols / 2]);
		FallingBall.computePath(elevations[rows / 2][cols / 2], rows / 2, cols / 2, rows, cols, elevations);

		System.out.println("Result grid:");
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				System.out.format("%2s", elevations[i][j]);
			}

			System.out.println();
		}
	}

}