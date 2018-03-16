public class EscapeTime {
	protected int frame;

	protected double _minX;
	protected double _maxX;
	protected double _minY;
	protected double _maxY;

	protected int _imageWidth;
	protected int _imageHeight;

	protected int _maxIterations;
	protected Image _image;
	protected Palette _colours;

	public EscapeTime(double limits[], int size[], int maximumIterations) {
		this._minX = limits[0];
		this._maxX = limits[1];
		this._minY = limits[2];
		this._maxY = limits[3];
		this._imageWidth = size[0];
		this._imageHeight = size[1];
		this._maxIterations = maximumIterations;
	}

	public void setUpImage(int redLevel, int blueLevel) {
		this._image = new Image(this._imageWidth, this._imageHeight);

		// Load the palette to find colours
		this._colours = new Palette(this._maxIterations, this._image, redLevel, blueLevel);
	}

	public void zoom(int frame, double factor) {
		this.frame = frame;
		this._minX /= factor;
		this._minY /= factor;
		this._maxX /= factor;
		this._maxY /= factor;
	}
}
