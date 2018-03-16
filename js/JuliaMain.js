load('julia.js');

function main() {
	var size;
	var limits;
	var i;
	var n;
	var zoom;
	var t0;
	var t1;
	var t2;
	var time;
	var fps;
	var frac;

	size = [500, 500];
	limits = [-1.5, 0.5, -1.0, 1.0];
	for (i = 0; i < 2; i++) {
		n = 1;
		zoom = 1.0;
	    t0 = new Date().getTime() / 1000.0;
		while (zoom < 10.0) {
	        t1 = new Date().getTime() / 1000.0;
			frac = new Julia(limits, size, 200);
			frac.zoom(n, zoom);
			frac.generateImage([-0.70176, -0.3842], 255, 70);
        	t2 = new Date().getTime() / 1000.0;
        	time = t2 - t1;
        	fps = 1.0 / time;
	        print("Frame " + n.toString().padStart(3) + " zoom " + zoom.toFixed(1) + "time " + time.toFixed(3) + " sec FPS " + fps.toFixed(1));
			zoom *= 1.02;
			n++;
		}
       	time = t2 - t0;
       	fps = (n - 1) / time;
	    print("=======\nTotal Time " + time.toFixed(3) + " sec, AVG FPS " + fps.toFixed(1) + "\n");
	}
}

main();
