require('escapeTime')

Julia = EscapeTime:extend()

function Julia:generateImage(constant, redLevel, blueLevel)
	local juliaReal = constant[1]
	local juliaImag = constant[2]
	local count = 0
	self:setUpImage(redLevel, blueLevel)
	local width = self._imageWidth
	local height = self._imageHeight
	local minX = self._minX
	local maxX = self._maxX
	local minY = self._minY
	local maxY = self._maxY
	local maxIterations = self._maxIterations
	local limit = 4.0
	local widthFactor = 1.0 / (width-1)
	local heightFactor = 1.0 / (height-1)
	local i = 0

	while (i<width) do
		
		local j = 0
		while (j<height) do
			-- What values of x and y does this pixel represent?
			local x = minX + i * ((maxX - minX) * widthFactor)
			local y = minY + j * ((maxY - minY) * heightFactor)

			local iteration = 0
			local z0 = x
			local z1 = y

			-- Optimization: Store x^2 and y^2 so we don't have to keep calculating it
			local x2 = x * x
			local y2 = y * y

			-- If the |z| > 2 ever, then the sequence will tend to infinity so we can exit the loop
			while (x2 + y2 < limit and iteration <= maxIterations) do
-- did try and do this within a beutiful class for Complex numbers but number of functional calls
-- quickly becomes too large array arithmatic seems quickest way
				z1 = 2 * z0 * z1 + juliaImag
				z0 = x2 - y2 + juliaReal

				x2 = z0 * z0
				y2 = z1 * z1

				iteration = iteration + 1
			end
			
			if (iteration >= maxIterations) then
				self._image:setPixel(i, j, self._colours:getInsideColor())
			else
				self._image:setPixel(i, j, self._colours:getColor(iteration))
			end

			count = count + 1
			j = j + 1
		end
		i = i + 1
	end

	self._image:show(self.frame)
end
