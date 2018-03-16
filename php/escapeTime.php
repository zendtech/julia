<?php
require_once('palette.php');
require_once('image.php');

class EscapeTime {
	protected $frame;

	protected $_minX;
	protected $_maxX;
	protected $_minY;
	protected $_maxY;

	protected $_imageWidth;
	protected $_imageHeight;

	protected $_maxIterations;
	protected $_image;
	protected $_colours;

	public function  __construct($limits, $size, $maximumIterations) {
		list($this->_minX, $this->_maxX, $this->_minY, $this->_maxY) = $limits;
		list($this->_imageWidth, $this->_imageHeight) = $size;

		$this->_maxIterations = $maximumIterations;
	}

	public function setUpImage($redLevel, $blueLevel) {
		$this->_image = new Image($this->_imageWidth, $this->_imageHeight);

		// Load the palette to find colours
		$this->_colours = new Palette($this->_maxIterations, $this->_image, $redLevel, $blueLevel);
	}

	public function zoom($frame, $factor) {
		$this->frame = $frame;
		$this->_minX /= $factor;
		$this->_minY /= $factor;
		$this->_maxX /= $factor;
		$this->_maxY /= $factor;
	}
}
