import numpy
import StringIO
from PIL import Image as PILImage

class Image:
	img=0
	mode=0

	def __init__(self, width, height):
		self.img = numpy.zeros((width, height, 3), dtype=numpy.uint8)

	def allocateColor(self, r, g, b):
		rgb = numpy.zeros(3, dtype=numpy.uint8)
		rgb[0] = r
		rgb[1] = g
		rgb[2] = b
		return rgb

	def setPixel(self, x, y, color):
		self.img[x, y] = color

	def show(self, frame):
		if (self.mode == 1):
			#header('Content-Type: image/png');
			#imagepng($this->img);
			pass
		elif (self.mode == 2):
			#imagepng($this->img, sprintf("julia-%03d.png", $frame));
			new_image = PILImage.fromarray(self.img)
			buf = StringIO.StringIO()
			buf.write("julia-%03d.png" % frame)
			new_image.save(buf.getvalue())
		elif (self.mode == 3):
			#imagedestroy($this->img);
			pass
