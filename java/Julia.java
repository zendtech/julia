public class Julia extends EscapeTime {

	public Julia(double[] limits, int[] size, int maximumIterations) {
		super(limits, size, maximumIterations);
	}

	public void generateImage(double constant[], int redLevel, int blueLevel) {
		double juliaReal = constant[0];
		double juliaImag = constant[1];
		int count = 0;
		this.setUpImage(redLevel, blueLevel);
		int width = this._imageWidth;
		int height = this._imageHeight;
		double minX = this._minX;
		double maxX = this._maxX;
		double minY = this._minY;
		double maxY = this._maxY;
		int maxIterations = this._maxIterations;
		double limit = 4.0;
		double widthFactor = 1.0 / (width-1);
		double heightFactor = 1.0 / (height-1);
		for (int i=0; i<width; i++) {
			for (int j=0; j<height; j++) {
				// What values of x and y does this pixel represent?
				double x = minX + i * ((maxX - minX) * widthFactor);
				double y = minY + j * ((maxY - minY) * heightFactor);

				int iteration = 0;
				double z0 = x;
				double z1 = y;

				// Optimization: Store x^2 and y^2 so we don't have to keep calculating it
				double x2 = x * x;
				double y2 = y * y;

				// If the |z| > 2 ever, then the sequence will tend to infinity so we can exit the loop
				while (x2 + y2 < limit && iteration <= maxIterations) {
// did try and do this within a beutiful class for Complex numbers but number of functional calls
// quickly becomes too large array arithmatic seems quickest way
					z1 = 2 * z0 * z1 + juliaImag;
					z0 = x2 - y2 + juliaReal;

					x2 = z0 * z0;
					y2 = z1 * z1;

					++iteration;
				}
				if (iteration >= maxIterations) {
					this._image.setPixel(i, j, this._colours.getInsideColor());
				} else {
					this._image.setPixel(i, j, this._colours.getColor(iteration));
				}

				++count;
			}
		}

		this._image.show(this.frame);
	}

}
