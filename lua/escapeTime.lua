require('palette')
require('image')

EscapeTime = {}
--EscapeTime.__index = EscapeTime

function EscapeTime:extend()
	local o = {}
	setmetatable(o, self)
	self.__index = self
	return o
end

function EscapeTime:new(limits, size, maximumIterations)
	local o = {}
	setmetatable(o, self)
	self.__index = self

	o._minX = limits[1]
	o._maxX = limits[2]
	o._minY = limits[3]
	o._maxY = limits[4]
	o._imageWidth = size[1]
	o._imageHeight = size[2]
	o._maxIterations = maximumIterations

	o.frame = nil
	o._image = nil
	o._colours = nil

	return o
end

function EscapeTime:setUpImage(redLevel, blueLevel)
	self._image = Image:new(self._imageWidth, self._imageHeight)

	-- Load the palette to find colours
	self._colours = Palette:new(self._maxIterations, self._image, redLevel, blueLevel)
end

function EscapeTime:zoom(frame, factor)
	self.frame = frame
	self._minX = self._minX / factor
	self._minY = self._minY / factor
	self._maxX = self._maxX / factor
	self._maxY = self._maxY / factor
end
