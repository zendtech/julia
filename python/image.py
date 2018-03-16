import StringIO
from PIL import Image as PILImage

class Image:
	img=0
	mode=0

	def __init__(self, width, height):
		self.img = PILImage.new('RGB', (width, height))

	def allocateColor(self, r, g, b):
		return (b << 16) | (g << 8) | r

	def setPixel(self, x, y, color):
		self.img.putpixel((x, y), color)

	def show(self, frame):
		if (self.mode == 1):
			#header('Content-Type: image/png');
			#imagepng($this->img);
			pass
		elif (self.mode == 2):
			#imagepng($this->img, sprintf("julia-%03d.png", $frame));
			buf = StringIO.StringIO()
			buf.write("julia-%03d.png" % frame)
			self.img.save(buf.getvalue())
		elif (self.mode == 3):
			#imagedestroy($this->img);
			pass
