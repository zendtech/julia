import julia
import time

def main():
	size = [500, 500]
	limits = [-1.5, 0.5, -1.0, 1.0]
	for i in range(2):
		n = 1
		zoom = 1.0
		t0 = time.time()
		while (zoom < 10.0):
			t1 = time.time()
			frac = julia.Julia(limits, size, 200)
			frac.zoom(n, zoom)
			frac.generateImage([-0.70176, -0.3842], 255, 70)
			t2 = time.time()
			_time = t2 - t1
			fps = 1.0 / _time
			print("Frame %3d zoom %0.1f time %0.3f sec FPS %0.1f" % (n, zoom, _time, fps))
			zoom *= 1.02
			n += 1
		_time = t2 - t0
		fps = (n - 1) / _time
		print("=======\nTotal Time %0.3f sec, AVG FPS %0.1f\n" % (_time, fps))

main()
