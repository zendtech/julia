function Image(width, height) {
	//this.img = imagecreate(width, height);
	this.mode = 0;

	this.allocateColor = function(r, g, b) {
		//return imagecolorallocate($this->img, $r, $g, $b);
		return 0
	}

	this.setPixel = function(x, y, color) {
		//imagesetpixel($this->img, $x, $y, $color);
	}

	this.show = function(frame) {
		if (this.mode == 1) {
			//header('Content-Type: image/png');
			//imagepng($this->img);
		} else if (this.mode == 2) {
			//imagepng($this->img, sprintf("julia-%03d.png", $frame));
		} else if (this.mode == 3) {
			//imagedestroy($this->img);
		}
	}
}
