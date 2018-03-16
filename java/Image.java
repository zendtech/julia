import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Image {

	private BufferedImage img;
	public int mode = 0;

	public Image(int width, int height) {
		this.img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	}

	public int allocateColor(int r, int g, int b) {
		return (r << 16) | (g << 8) | b;
	}

	public void setPixel(int x, int y, int color) {
		this.img.setRGB(x, y, color);
	}

	public void show(int frame) {
		if (mode == 1) {
			//header('Content-Type: image/png');
			//imagepng($this->img);
		} else if (mode == 2) {
			try {
				File outputfile = new File(String.format("julia-%03d.png", frame));
				ImageIO.write(this.img, "png", outputfile);
			} catch (IOException e) {
				// TODO:
			}
		} else if (mode == 3) {
			//imagedestroy($this->img);
		}
	}
}
