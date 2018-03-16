<?php
require_once('julia.php');

function main() {
	$size = array(500, 500);
	$limits = array(-1.5, 0.5, -1.0, 1.0);
	for ($i = 0; $i < 2; $i++) {
		$n = 1;
		$zoom = 1.0;
	    $t0 = microtime(1);
		while ($zoom < 10.0) {
	        $t1 = microtime(1);
			$frac = new Julia($limits, $size, 200);
			$frac->zoom($n, $zoom);
			$frac->generateImage(array(-0.70176, -0.3842), 255, 70);
        	$t2 = microtime(1);
        	$time = $t2 - $t1;
        	$fps = 1.0 / $time;
	        printf("Frame %3d zoom %0.1f time %0.3f sec FPS %0.1f\n", $n, $zoom, $time, $fps);
			$zoom *= 1.02;
			$n++;
		}
       	$time = $t2 - $t0;
       	$fps = ($n - 1) / $time;
	    printf("=======\nTotal Time %0.3f sec, AVG FPS %0.1f\n\n", $time, $fps);
	}
}

main();
