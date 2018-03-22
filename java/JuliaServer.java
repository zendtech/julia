import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import javax.imageio.ImageIO;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.util.*;

public class JuliaServer {
	public static void main(String[] args) throws Exception {
		HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
		server.createContext("/julia2", new JuliaHandler());
		server.setExecutor(null); // creates a default executor
//		server.setExecutor(java.util.concurrent.Executors.newFixedThreadPool(5));
		server.start();
	}

	static class JuliaHandler implements HttpHandler {
		public void handle(HttpExchange httpHandler) throws IOException {
			
			// Obtain zoom level if available
			String queryString = httpHandler.getRequestURI().getQuery();
			
			double zoomLevel = 1;	
			if (queryString != null) {
				String zoomLevelPair[] = queryString.split("=");
				if (zoomLevelPair.length > 1) {
					zoomLevel = Double.parseDouble(zoomLevelPair[1]);
				}
			}
			
			// compute fractal
			int size[] = new int[] { 500, 500 };
			double limits[] = new double[] { -1.5, 0.5, -1.0, 1.0 };
			Julia frac = new Julia(2, limits, size, 200);
			frac.zoom(0, zoomLevel);
			frac.generateImage(new double[] { -0.70176, -0.3842 }, 255, 70);
			
			BufferedImage image = frac.getImage();
			OutputStream os = httpHandler.getResponseBody();
			try {
				ByteArrayOutputStream bao = new ByteArrayOutputStream();
				ImageIO.write(image, "png", bao);
				httpHandler.getResponseHeaders().add("Content-Type", "image/png");
				httpHandler.getResponseHeaders().add("Cache-Control", "no-cache" );
				httpHandler.sendResponseHeaders(200, bao.toByteArray().length);

				os.write(bao.toByteArray());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			os.close();
//			System.gc();
		}
	}
}
