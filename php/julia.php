<?php
require_once('escapeTime.php');

class Julia extends EscapeTime {

	public function generateImage($constant, $redLevel, $blueLevel) {
		$juliaReal = (double)$constant[0];
		$juliaImag = (double)$constant[1];
		$count = 0;
		$this->setUpImage($redLevel, $blueLevel);
		$width = (int)$this->_imageWidth;
		$height = (int)$this->_imageHeight;
		$minX = (double)$this->_minX;
		$maxX = (double)$this->_maxX;
		$minY = (double)$this->_minY;
		$maxY = (double)$this->_maxY;
		$maxIterations = (int)$this->_maxIterations;
		$limit = 4.0;
		$widthFactor = 1.0 / ($width-1);
		$heightFactor = 1.0 / ($height-1);
		for ($i=0; $i<$width; $i++) {
			for ($j=0; $j<$height; $j++) {
				// What values of x and y does this pixel represent?
				$x = $minX+$i*(($maxX - $minX) * $widthFactor);
				$y = $minY+$j*(($maxY - $minY) * $heightFactor);

				$iteration = 0;
				$z0 = $x;
				$z1 = $y;

				// Optimization: Store x^2 and y^2 so we don't have to keep calculating it
				$x2 = $x*$x;
				$y2 = $y*$y;

				// If the |z| > 2 ever, then the sequence will tend to infinity so we can exit the loop
				while ($x2+$y2 < $limit && $iteration <= $maxIterations ) {
// did try and do this within a beutiful class for Complex numbers but number of functional calls
// quickly becomes too large array arithmatic seems quickest way
					$z1 = 2*$z0*$z1 + $juliaImag;
					$z0 = $x2 - $y2 + $juliaReal;

					$x2 = $z0 * $z0;
					$y2 = $z1 * $z1;

					++$iteration;
				}
				if ($iteration >= $maxIterations) {
					$this->_image->setPixel($i, $j, $this->_colours->getInsideColor());
				} else {
					$this->_image->setPixel($i, $j, $this->_colours->getColor($iteration));
				}

				++$count;
			}
		}

		$this->_image->show($this->frame);
	}

}
