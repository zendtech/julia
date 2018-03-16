<?php
class Image {

	private $img;
	public $mode = 0;

	public function __construct($width, $height) {
		$this->img = imagecreate($width, $height);
	}

	public function allocateColor($r, $g, $b) {
		return imagecolorallocate($this->img, $r, $g, $b);
	}

	public function setPixel($x, $y, $color) {
		imagesetpixel($this->img, $x, $y, $color);
	}

	public function show($frame) {
		if ($mode == 1) {
			header('Content-Type: image/png');
			imagepng($this->img);
		} else if ($mode == 2) {
			imagepng($this->img, sprintf("julia-%03d.png", $frame));
		} else if ($mode == 3) {
			imagedestroy($this->img);
		}
	}
}
