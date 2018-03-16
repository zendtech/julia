public class Palette {

	private int[] _cache;
	private Image _image;
	private int _redLevel;
	private int _blueLevel;
	private int insideImageColor;
	private int scaleImageColor;
	private double factor;

	public Palette(int maxIterations, Image image, int redLevel, int blueLevel) {
		this._image = image;
		this._redLevel = redLevel;
		this._blueLevel = blueLevel;
		this.insideImageColor = this._image.allocateColor(0, 0, 0);
		this.scaleImageColor = this._image.allocateColor(0, 0, 0);
		this.factor = 1 / Math.log(maxIterations) * 255;
		this._cache = new int[maxIterations];
		for (int i = 0; i < maxIterations; i++) {
			this._cache[i] = -1;
		}
	}

	public int getInsideColor() {
		return this.insideImageColor;
	}

	public int getScaleColor() {
		return this.scaleImageColor;
	}

	public int getColor(int offset) {
		if (this._cache[offset] >= 0) {
			return this._cache[offset];
		}
		int green = (int)(Math.log(offset) * this.factor);
		int ret = this._image.allocateColor(this._redLevel, green, this._blueLevel);
		this._cache[offset] = ret;
		return ret;
	}
}
