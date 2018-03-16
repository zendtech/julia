Image = {}
Image.__index = Image

function Image:new(width, height)
	local self = setmetatable({}, Image)
	-- this.img = imagecreate(width, height);
	self.mode = 0
	return self
end

function Image:allocateColor(r, g, b) 
	-- return imagecolorallocate($this->img, $r, $g, $b);
	return 0
end

function Image:setPixel(x, y, color)
	-- imagesetpixel($this->img, $x, $y, $color);
end

function Image:show(frame)
	if (self.mode == 2) then
		-- header('Content-Type: image/png');
		-- imagepng($this->img);
	elseif (self.mode == 3) then
		-- imagepng($this->img, sprintf("julia-%03d.png", $frame));
	elseif (self.mode == 4) then
		-- imagedestroy($this->img);
	end
end
