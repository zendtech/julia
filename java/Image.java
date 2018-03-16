public class Image {

	//private $img;
	private Color color;

	public Image(int width, int height) {
		//$this->img = imagecreate($width, $height);	
		this.color = new Color();
	}

	public Color allocateColor(int r, int g, int b) {
		//return imagecolorallocate($this->img, $r, $g, $b);
		return this.color;
	}

	public void setPixel(int x, int y, Color color) {
		//imagesetpixel($this->img, $x, $y, $color);
	}

	public void show(int frame) {
		//header('Content-Type: image/png');
		//imagepng($this->img);

		//imagepng($this->img, sprintf("julia-%03d.png", $frame));

		//imagedestroy($this->img);
	}
}
