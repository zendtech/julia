<?php
class Palette {

	private $_cache;
	private $_image;
	private $_redLevel;
	private $_blueLevel;
	private $insideImageColor;
	private $scaleImageColor;
	private $factor;

	public function  __construct($maxIterations, $image, $redLevel, $blueLevel) {
		$this->_image = $image;
		$this->_redLevel = $redLevel;
		$this->_blueLevel = $blueLevel;
		$this->insideImageColor = $this->_image->allocateColor(0, 0, 0);
		$this->scaleImageColor = $this->_image->allocateColor(0, 0, 0);
		$this->factor = 1 / log($maxIterations) * 255;
		$this->_cache = array_fill(0, $maxIterations, null);
	}

	public function getInsideColor() {
		return $this->insideImageColor;
	}

	public function getScaleColor() {
		return $this->scaleImageColor;
	}

	public function getColor($offset) {
		if (isset($this->_cache[$offset])) {
			return $this->_cache[$offset];
		}
		$green = (int)(log($offset) * $this->factor);
		$return = $this->_image->allocateColor($this->_redLevel, $green, $this->_blueLevel);
		$this->_cache[$offset] = $return;
		return $return;

	}
}
