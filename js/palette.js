function Palette(maxIterations, image, redLevel, blueLevel) {
	this._image = image;
	this._redLevel = redLevel;
	this._blueLevel = blueLevel;
	this.insideImageColor = this._image.allocateColor(0, 0, 0);
	this.scaleImageColor = this._image.allocateColor(0, 0, 0);
	this.factor = 1 / Math.log(maxIterations) * 255;
	var cache = [];
	var i;
	for (i = 0; i < maxIterations; i++) {
		cache[i] = -1;
	}
	this._cache = cache;

	this.getInsideColor = function() {
		return this.insideImageColor;
	}

	this.getScaleColor = function() {
		return this.scaleImageColor;
    }

    this.getColor = function(offset) {
		if (this._cache[offset] >= 0) {
			return this._cache[offset];
		}
		var green = (Math.log(offset) * this.factor) | 0;
		var ret = this._image.allocateColor(this._redLevel, green, this._blueLevel);
		this._cache[offset] = ret;
		return ret;
	}
}
