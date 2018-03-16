public class Palette {

	private Color[] _cache;
	private Image _image;
	private int _redLevel;
	private int _blueLevel;
	private Color insideImageColor;
	private Color scaleImageColor;
	private double factor;

    public Palette(int maxIterations, Image image, int redLevel, int blueLevel) {
		this._image = image;
		this._redLevel = redLevel;
		this._blueLevel = blueLevel;
		this.insideImageColor = this._image.allocateColor(0, 0, 0);
		this.scaleImageColor = this._image.allocateColor(0, 0, 0);
		this.factor = 1 / Math.log(maxIterations) * 255;
		this._cache = new Color[maxIterations];
	}

    public Color getInsideColor() {
		return this.insideImageColor;
    }

    public Color getScaleColor() {
		return this.scaleImageColor;
    }

    public Color getColor(int offset) {
	    if (this._cache[offset] instanceof Color) {
	     	return this._cache[offset];
	    }
		int green = (int)(Math.log(offset) * this.factor);
		Color ret = this._image.allocateColor(this._redLevel, green, this._blueLevel);
		this._cache[offset] = ret;
		return ret;
    }
}
