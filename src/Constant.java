public class Constant {

	public static final double DURATION = .60;

	public static final int FRAME_WIDTH = 400;
	public static final int FRAME_HEIGHT = 700;

	// Tetris Square class
	public static  int SQUARE_WIDTH = 25;
	public static  int SQUARE_HEIGHT = 25;

	public static final int BOOST = 475;
	public static final int MOVEPIECE = 25;


	/**
	 * 2-D array that makes different shapes.
	 * the first column is the x position and second column is y position
	 *
	 */

	public static final double[][] REDPIECE = {{200, 50},
			                                {200,75},
			                                {200,100},
			                                 {200,125}};

		public static final double[][] YELLOWPIECE = {{200,50},
													{200,75},
													{200,100},
													{225,50}};

		public static final double[][] PURPLEPIECE = {{200,50},
				                                    {200,75},
													{225,50},
													{225,75}};

		public static final double[][] ORANGEPIECE = {{200,50},
													{200,75},
													{200,100},
													{225,75}};

		public static final double[][] GREENPIECE = {{200,50},
													{200,75},
													{200,100} ,
													{175,50}};


		public static final double[][] LIGHTBLUEPIECE = {{200,50},
														{200,75},
														{225,75},
														{225,100}};

		public static final double[][] BLUEPIECE = {{200,50},
													{200,75},
													{175,75},
													{175,100}};


}
