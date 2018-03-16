import java.util.*;

public class JuliaMain {
	public static void main(String[] args) throws Exception {
		int size[] = new int[] { 500, 500 };
		double limits[] = new double[] { -1.5, 0.5, -1.0, 1.0 };
		double t0, t1, t2 = 0.0, time, fps;
		for (int i = 0; i < 2; i++) {
			int n = 1;
			double zoom = 1.0;
			t0 = new Date().getTime() / 1000.0;
			while (zoom < 10.0) {
		 		t1 = new Date().getTime() / 1000.0;
				Julia frac = new Julia(limits, size, 200);
				frac.zoom(n, zoom);
				frac.generateImage(new double[] { -0.70176, -0.3842 }, 255, 70);
				t2 = new Date().getTime() / 1000.0;
				time = t2 - t1;
				fps = 1.0 / time;
				System.out.printf("Frame %3d zoom %.1f time %.3f sec FPS %.1f\n", n, zoom, time, fps);
				zoom *= 1.02;
				n++;
			}
			time = t2 - t0;
			fps = (n - 1) / time;
			System.out.printf("=======\nTotal Time %.3f sec, AVG FPS %.1f\n\n", time, fps);
		}
	}
}
