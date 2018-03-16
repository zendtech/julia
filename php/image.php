<?php
class Image {

	private $img;
	public $mode = 0;

	public function __construct($width, $height) {
		if ($this->mode != 0) {
			$this->img = imagecreate($width, $height);
		}
	}

	public function allocateColor($r, $g, $b) {
		if ($this->mode != 0) {
			return imagecolorallocate($this->img, $r, $g, $b);
		} else {
			return 0;
		}
	}

	public function setPixel($x, $y, $color) {
		if ($this->mode != 0) {
			imagesetpixel($this->img, $x, $y, $color);
		}
	}

	public function show($frame) {
		if ($this->mode == 2) {
			header('Content-Type: image/png');
			imagepng($this->img);
		} else if ($this->mode == 3) {
			imagepng($this->img, sprintf("julia-%03d.png", $frame));
		} else if ($this->mode == 4) {
			imagedestroy($this->img);
		}
	}
}
