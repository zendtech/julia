import math

class Palette:
	_cache=0
	_image=0
	_redLevel=0
	_blueLevel=0
	insideImageColor=0
	scaleImageColor=0
	factor=0

	def __init__(self, maxIterations, image, redLevel, blueLevel):
		self._image = image
		self._redLevel = redLevel
		self._blueLevel = blueLevel
		self.insideImageColor = self._image.allocateColor(0, 0, 0)
		self.scaleImageColor = self._image.allocateColor(0, 0, 0)
		self.factor = 1 / math.log(maxIterations) * 255
		self._cache = [None for i in range(maxIterations)]

	def getInsideColor(self):
		return self.insideImageColor

	def getScaleColor(self):
		return self.scaleImageColor

	def getColor(self, offset):
		if self._cache[offset] is not None:
			return self._cache[offset]
		green = int(math.log(offset) * self.factor)
		ret = self._image.allocateColor(self._redLevel, green, self._blueLevel)
		self._cache[offset] = ret
		return ret;
