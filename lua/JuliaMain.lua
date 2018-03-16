require('julia')

local function main()
	local size, limits, constant, i, n, zoom, t0, t1, t2, time, fps, frac

	size = {500, 500}
	limits = {-1.5, 0.5, -1.0, 1.0}
	constant = {-0.70176, -0.3842}
	i = 0;
	while (i < 2) do
		n = 1
		zoom = 1.0
		t0 = os.clock()
		while (zoom < 10.0) do
			t1 = os.clock()
			frac = Julia:new(limits, size, 200)
			frac:zoom(n, zoom)			
			frac:generateImage(constant, 255, 70)
			t2 = os.clock()
			time = t2 - t1
			fps = 1.0 / time
			io.write(string.format("Frame %3d zoom %.1f time %.3f sec FPS %.1f\n", n, zoom, time, fps))
			zoom = zoom * 1.02
			n = n + 1
		end
		time = t2 - t0
		fps = (n - 1) / time
		io.write(string.format("=======\nTotal Time %.3f sec, AVG FPS %.1f\n\n", time, fps))
		i = i + 1
	end
end

main()
