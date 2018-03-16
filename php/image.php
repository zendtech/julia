<?php
class Image {

	private $img;

	public function __construct($width, $height) {
		//$this->img = imagecreate($width, $height);	
	}

	public function allocateColor($r, $g, $b) {
		//return imagecolorallocate($this->img, $r, $g, $b);
		return 0;
	}

	public function setPixel($x, $y, $color) {
		//imagesetpixel($this->img, $x, $y, $color);
	}

	public function show($frame) {
		//header('Content-Type: image/png');
		//imagepng($this->img);

		//imagepng($this->img, sprintf("julia-%03d.png", $frame));

		//imagedestroy($this->img);
	}
}
