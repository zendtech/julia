import palette
import image

class EscapeTime:
	frame=0
	_minX=0
	_maxX=0
	_minY=0
	_maxY=0
	_imageWidth=0
	_imageHeight=0
	_maxIterations=0
	_image=0
	_colours=0

	def __init__(self, limits, size, maximumIterations):
		self._minX, self._maxX, self._minY, self._maxY = limits
		self._imageWidth, self._imageHeight = size
		self._maxIterations = maximumIterations

	def setUpImage(self, redLevel, blueLevel):
		self._image = image.Image(self._imageWidth, self._imageHeight)
		# Load the palette to find colours
		self._colours = palette.Palette(self._maxIterations, self._image, redLevel, blueLevel)

	def zoom(self, frame, factor):
		self.frame = frame
		self._minX /= factor
		self._minY /= factor
		self._maxX /= factor
		self._maxY /= factor
