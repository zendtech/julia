import escapeTime

class Julia(escapeTime.EscapeTime):

	def generateImage(self, constant, redLevel, blueLevel):
		juliaReal = float(constant[0])
		juliaImag = float(constant[1])
		count = 0
		self.setUpImage(redLevel, blueLevel)
		width = int(self._imageWidth)
		height = int(self._imageHeight)
		minX = float(self._minX)
		maxX = float(self._maxX)
		minY = float(self._minY)
		maxY = float(self._maxY)
		maxIterations = int(self._maxIterations)
		limit = 4.0
		widthFactor = 1.0 / (width-1)
		heightFactor = 1.0 / (height-1)
		for i in range(width):
			for j in range(height):
				# What values of x and y does this pixel represent?
				x = minX + i * ((maxX - minX) * widthFactor)
				y = minY + j * ((maxY - minY) * heightFactor)
				iteration = 0
				z0 = x
				z1 = y
				# Optimization: Store x^2 and y^2 so we don't have to keep calculating it
				x2 = x * x
				y2 = y * y
				# If the |z| > 2 ever, then the sequence will tend to infinity so we can exit the loop
				while (x2 + y2 < limit and iteration <= maxIterations):
					# did try and do this within a beutiful class for Complex numbers but number of functional calls
					# quickly becomes too large array arithmatic seems quickest way
					z1 = 2 * z0 * z1 + juliaImag
					z0 = x2 - y2 + juliaReal
					x2 = z0 * z0
					y2 = z1 * z1
					iteration += 1
				if (iteration >= maxIterations):
					self._image.setPixel(i, j, self._colours.getInsideColor())
				else:
					self._image.setPixel(i, j, self._colours.getColor(iteration))
				count += 1
		self._image.show(self.frame)
