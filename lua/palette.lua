Palette = {}
Palette.__index = Palette

function Palette:new(maxIterations, image, redLevel, blueLevel)
	local self = setmetatable({}, Palette)
	self._image = image
	self._redLevel = redLevel
	self._blueLevel = blueLevel
	self.insideImageColor = self._image.allocateColor(0, 0, 0)
	self.scaleImageColor = self._image.allocateColor(0, 0, 0)
	self.factor = 1 / math.log(maxIterations) * 255;
	local cache = {};
	local i = 1
	while (i <= maxIterations) do
		cache[i] = -1
		i = i + 1
	end
	self._cache = cache
	return self
end

function Palette:getInsideColor()
	return self.insideImageColor
end

function Palette:getScaleColor()
	return self.scaleImageColor
end

function Palette:getColor(offset)
	if (self._cache[offset+1] >= 0) then
		return self._cache[offset+1]
	end
	local green = (math.log(offset) * self.factor);
	local ret = self._image.allocateColor(self._redLevel, green, self._blueLevel)
	self._cache[offset+1] = ret
	return ret
end
