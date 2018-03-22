<?php
require_once('julia.php');

function main() {
	$size = array(500, 500);
	$limits = array(-1.5, 0.5, -1.0, 1.0);
	$frac = new Julia(2, $limits, $size, 200);
	if (!empty($_GET['zoom'])) {
		$frac->zoom(0, $_GET['zoom']);
	}
	$frac->generateImage(array(-0.70176, -0.3842), 255, 70);
}

main();
