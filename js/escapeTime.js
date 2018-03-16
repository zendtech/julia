load('./palette.js');
load('./image.js');

function EscapeTime(limits, size, maximumIterations) {
	this._minX = limits[0];
	this._maxX = limits[1];
	this._minY = limits[2];
	this._maxY = limits[3];
	this._imageWidth = size[0];
	this._imageHeight = size[1];
	this._maxIterations = maximumIterations;

	this.frame = null;
	this._image = null;
	this._colours = null;

	this.setUpImage = function(redLevel, blueLevel) {
		this._image = new Image(this._imageWidth, this._imageHeight);

		// Load the palette to find colours
		this._colours = new Palette(this._maxIterations, this._image, redLevel, blueLevel);
	}

	this.zoom = function(frame, factor) {
		this.frame = frame;
		this._minX /= factor;
		this._minY /= factor;
		this._maxX /= factor;
		this._maxY /= factor;
	}
}
