load('./escapeTime.js');

function Julia(limits, size, maximumIterations) {
	EscapeTime.call(this, limits, size, maximumIterations);

	this.generateImage = function(constant, redLevel, blueLevel) {
		var juliaReal = + constant[0];
		var juliaImag = + constant[1];
		var count = 0;
		this.setUpImage(redLevel, blueLevel);
		var width = this._imageWidth | 0;
		var height = this._imageHeight | 0;
		var minX = + this._minX;
		var maxX = + this._maxX;
		var minY = + this._minY;
		var maxY = + this._maxY;
		var maxIterations = this._maxIterations | 0;
		var limit = 4.0;
		var widthFactor = 1.0 / (width-1);
		var heightFactor = 1.0 / (height-1);
		for (var i=0; i<width; i++) {
			for (var j=0; j<height; j++) {
				// What values of x and y does this pixel represent?
				var x = minX + i * ((maxX - minX) * widthFactor);
				var y = minY + j * ((maxY - minY) * heightFactor);

				var iteration = 0;
				var z0 = x;
				var z1 = y;

				// Optimization: Store x^2 and y^2 so we don't have to keep calculating it
				var x2 = x * x;
				var y2 = y * y;

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
