/* 
 * NOTE
 * ----
 * 
 * This class contains code borrowed, or derive from the MESA project
 * released under a MIT license. 
 * 
 * Copyright (C) 1999-2007  Brian Paul   All Rights Reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a
 * copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS
 * OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL BRIAN PAUL BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 */

package org.gnu.escher.x11.extension.glx;

/**
 * GLX visual configuration. A single display can support multiple screens. Each
 * screen can have several different visual types supported at different depths.
 */
public class XVisualInfo {

	// NOTE: in theory this class should extend the VisualInfo class
	// We also have few other "visual" classes, and they are all related.
	// The reason for this screwed design is that there is no clear
	// documentation about all this stuff, they are just randomly
	// referenced any here and there in the various GLX and X11 docs,
	// so we had no clue on what to do before hitting some problems
	// and working on them.
	// I feel really sorry for the design, but also quite disappointed, indeed,
	// by the bad docs.

	private int count = 0;

	/** Visual ID */
	private int id = (int) GLXConstants.GLX_DONT_CARE;

	/** One of the GLX X visual types, like GLX_TRUE_COLOR */
	private int visualType = (int) GLXConstants.GLX_DONT_CARE;

	private boolean rgbMode = false;
	private boolean floatMode = false;
	private boolean colorIndexMode = false;

	private long stereoMode = GLXConstants.GLX_DONT_CARE;
	private long doubleBufferMode;

	private boolean haveAccumBuffer = false;
	private boolean haveDepthBuffer = false;
	private boolean haveStencilBuffer = false;

	/** Bits per component */
	private int redBits = 0, greenBits = 0, blueBits = 0, alphaBits = 0;
	private long redMask = 0, greenMask = 0, blueMask = 0, alphaMask = 0;

	/** Total bits for rgb */
	private int rgbBits;

	/** Total bits for colorindex */
	private int indexBits;

	/** Bits for accumulation buffer. */
	private int accumRedBits = 0, accumGreenBits = 0, accumBlueBits = 0, accumAlphaBits = 0;

	private int depthBits = 0;
	private int stencilBits = 0;

	private int numAuxBuffers = 0;

	private int level = 0;

	private int pixmapMode = 0;

	/* ***** Please, try to keep the extensions ordered by GLX version. ***** */

	/** EXT_visual_rating / GLX 1.2 extension */
	private int visualRating = (int) GLXConstants.GLX_DONT_CARE;

	/* EXT_visual_info / GLX 1.2 */
	private int transparentPixel = (int) GLXConstants.GLX_DONT_CARE;;
	private int transparentRed = 0, transparentGreen = 0, transparentBlue = 0, transparentAlpha = 0;
	private int transparentIndex = 0;

	/* ARB_multisample / SGIS_multisample */
	private int sampleBuffers = 0;
	private int samples = 0;

	/** SGIX_fbconfig / GLX 1.3 */
	private boolean isFBConfig = false;
	private int drawableType = GLXConstants.GLX_WINDOW_BIT;
	private int renderType = 0;
	private int xRenderable = (int) GLXConstants.GLX_DONT_CARE;
	private int fbconfigID = (int) GLXConstants.GLX_DONT_CARE;

	/** SGIX_pbuffer / GLX 1.3 */
	private int maxPbufferWidth = 0;
	private int maxPbufferHeight = 0;
	private int maxPbufferPixels = 0;
	private int optimalPbufferWidth = 0; // Only for SGIX_pbuffer.
	private int optimalPbufferHeight = 0; // Only for SGIX_pbuffer

	/** SGIX_visual_select_group */
	private int visualSelectGroup = 0;

	/** OML_swap_method */
	private int swapMethod = (int) GLXConstants.GLX_DONT_CARE;
	private int screen = 0;

	/** EXT_texture_from_pixmap */
	private int bindToTextureRgb = 0;
	private int bindToTextureRgba = 0;
	private int bindToMipmapTexture = 0;
	private int bindToTextureTargets = 0;
	private int yInverted = 0;

//    /**
//     * Initialize an XVisualInfo class directly from the ResponseInputStream.
//     */
//    public XVisualInfo(ResponseInputStream stream, int count) {
//        
//        this.count = count;
//        
//        // read properties from the stream
//        this.id = stream.read_int32();
//    }

	/**
	 * Initialize a default, no FBConfig type
	 */
	public XVisualInfo() {
		this(false);
	}

	public XVisualInfo(boolean isFBConfig) {

		this.isFBConfig = isFBConfig;
		if (this.isFBConfig) {
			this.rgbMode = true;
			this.doubleBufferMode = GLXConstants.GLX_DONT_CARE;
		}

		this.renderType = this.rgbMode ? GLXConstants.GLX_RGBA_BIT : GLXConstants.GLX_COLOR_INDEX_BIT;
	}

	void setID(int id) {
		this.id = id;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getVisualType() {
		return visualType;
	}

	public void setVisualType(int visualType) {
		this.visualType = visualType;
	}

	public boolean isRgbMode() {
		return rgbMode;
	}

	public void setRgbMode(boolean rgbMode) {
		this.rgbMode = rgbMode;
	}

	public boolean isFloatMode() {
		return floatMode;
	}

	public void setFloatMode(boolean floatMode) {
		this.floatMode = floatMode;
	}

	public boolean isColorIndexMode() {
		return colorIndexMode;
	}

	public void setColorIndexMode(boolean colorIndexMode) {
		this.colorIndexMode = colorIndexMode;
	}

	public long getStereoMode() {
		return stereoMode;
	}

	public void setStereoMode(long stereoMode) {
		this.stereoMode = stereoMode;
	}

	public long getDoubleBufferMode() {
		return doubleBufferMode;
	}

	public void setDoubleBufferMode(long doubleBufferMode) {
		this.doubleBufferMode = doubleBufferMode;
	}

	public boolean isHaveAccumBuffer() {
		return haveAccumBuffer;
	}

	public void setHaveAccumBuffer(boolean haveAccumBuffer) {
		this.haveAccumBuffer = haveAccumBuffer;
	}

	public boolean isHaveDepthBuffer() {
		return haveDepthBuffer;
	}

	public void setHaveDepthBuffer(boolean haveDepthBuffer) {
		this.haveDepthBuffer = haveDepthBuffer;
	}

	public boolean isHaveStencilBuffer() {
		return haveStencilBuffer;
	}

	public void setHaveStencilBuffer(boolean haveStencilBuffer) {
		this.haveStencilBuffer = haveStencilBuffer;
	}

	public int getRedBits() {
		return redBits;
	}

	public void setRedBits(int redBits) {
		this.redBits = redBits;
	}

	public int getGreenBits() {
		return greenBits;
	}

	public void setGreenBits(int greenBits) {
		this.greenBits = greenBits;
	}

	public int getBlueBits() {
		return blueBits;
	}

	public void setBlueBits(int blueBits) {
		this.blueBits = blueBits;
	}

	public int getAlphaBits() {
		return alphaBits;
	}

	public void setAlphaBits(int alphaBits) {
		this.alphaBits = alphaBits;
	}

	public long getRedMask() {
		return redMask;
	}

	public void setRedMask(long redMask) {
		this.redMask = redMask;
	}

	public long getGreenMask() {
		return greenMask;
	}

	public void setGreenMask(long greenMask) {
		this.greenMask = greenMask;
	}

	public long getBlueMask() {
		return blueMask;
	}

	public void setBlueMask(long blueMask) {
		this.blueMask = blueMask;
	}

	public long getAlphaMask() {
		return alphaMask;
	}

	public void setAlphaMask(long alphaMask) {
		this.alphaMask = alphaMask;
	}

	public int getRgbBits() {
		return rgbBits;
	}

	public void setRgbBits(int rgbBits) {
		this.rgbBits = rgbBits;
	}

	public int getIndexBits() {
		return indexBits;
	}

	public void setIndexBits(int indexBits) {
		this.indexBits = indexBits;
	}

	public int getAccumRedBits() {
		return accumRedBits;
	}

	public void setAccumRedBits(int accumRedBits) {
		this.accumRedBits = accumRedBits;
	}

	public int getAccumGreenBits() {
		return accumGreenBits;
	}

	public void setAccumGreenBits(int accumGreenBits) {
		this.accumGreenBits = accumGreenBits;
	}

	public int getAccumBlueBits() {
		return accumBlueBits;
	}

	public void setAccumBlueBits(int accumBlueBits) {
		this.accumBlueBits = accumBlueBits;
	}

	public int getAccumAlphaBits() {
		return accumAlphaBits;
	}

	public void setAccumAlphaBits(int accumAlphaBits) {
		this.accumAlphaBits = accumAlphaBits;
	}

	public int getDepthBits() {
		return depthBits;
	}

	public void setDepthBits(int depthBits) {
		this.depthBits = depthBits;
	}

	public int getStencilBits() {
		return stencilBits;
	}

	public void setStencilBits(int stencilBits) {
		this.stencilBits = stencilBits;
	}

	public int getNumAuxBuffers() {
		return numAuxBuffers;
	}

	public void setNumAuxBuffers(int numAuxBuffers) {
		this.numAuxBuffers = numAuxBuffers;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getPixmapMode() {
		return pixmapMode;
	}

	public void setPixmapMode(int pixmapMode) {
		this.pixmapMode = pixmapMode;
	}

	public int getVisualRating() {
		return visualRating;
	}

	public void setVisualRating(int visualRating) {
		this.visualRating = visualRating;
	}

	public int getTransparentPixel() {
		return transparentPixel;
	}

	public void setTransparentPixel(int transparentPixel) {
		this.transparentPixel = transparentPixel;
	}

	public int getTransparentRed() {
		return transparentRed;
	}

	public void setTransparentRed(int transparentRed) {
		this.transparentRed = transparentRed;
	}

	public int getTransparentGreen() {
		return transparentGreen;
	}

	public void setTransparentGreen(int transparentGreen) {
		this.transparentGreen = transparentGreen;
	}

	public int getTransparentBlue() {
		return transparentBlue;
	}

	public void setTransparentBlue(int transparentBlue) {
		this.transparentBlue = transparentBlue;
	}

	public int getTransparentAlpha() {
		return transparentAlpha;
	}

	public void setTransparentAlpha(int transparentAlpha) {
		this.transparentAlpha = transparentAlpha;
	}

	public int getTransparentIndex() {
		return transparentIndex;
	}

	public void setTransparentIndex(int transparentIndex) {
		this.transparentIndex = transparentIndex;
	}

	public int getSampleBuffers() {
		return sampleBuffers;
	}

	public void setSampleBuffers(int sampleBuffers) {
		this.sampleBuffers = sampleBuffers;
	}

	public int getSamples() {
		return samples;
	}

	public void setSamples(int samples) {
		this.samples = samples;
	}

	public boolean isFBConfig() {
		return isFBConfig;
	}

	public void setFBConfig(boolean isFBConfig) {
		this.isFBConfig = isFBConfig;
	}

	public int getDrawableType() {
		return drawableType;
	}

	public void setDrawableType(int drawableType) {
		this.drawableType = drawableType;
	}

	public int getRenderType() {
		return renderType;
	}

	public void setRenderType(int renderType) {
		this.renderType = renderType;
	}

	public int getxRenderable() {
		return xRenderable;
	}

	public void setxRenderable(int xRenderable) {
		this.xRenderable = xRenderable;
	}

	public int getFbconfigID() {
		return fbconfigID;
	}

	public void setFbconfigID(int fbconfigID) {
		this.fbconfigID = fbconfigID;
	}

	public int getMaxPbufferWidth() {
		return maxPbufferWidth;
	}

	public void setMaxPbufferWidth(int maxPbufferWidth) {
		this.maxPbufferWidth = maxPbufferWidth;
	}

	public int getMaxPbufferHeight() {
		return maxPbufferHeight;
	}

	public void setMaxPbufferHeight(int maxPbufferHeight) {
		this.maxPbufferHeight = maxPbufferHeight;
	}

	public int getMaxPbufferPixels() {
		return maxPbufferPixels;
	}

	public void setMaxPbufferPixels(int maxPbufferPixels) {
		this.maxPbufferPixels = maxPbufferPixels;
	}

	public int getOptimalPbufferWidth() {
		return optimalPbufferWidth;
	}

	public void setOptimalPbufferWidth(int optimalPbufferWidth) {
		this.optimalPbufferWidth = optimalPbufferWidth;
	}

	public int getOptimalPbufferHeight() {
		return optimalPbufferHeight;
	}

	public void setOptimalPbufferHeight(int optimalPbufferHeight) {
		this.optimalPbufferHeight = optimalPbufferHeight;
	}

	public int getVisualSelectGroup() {
		return visualSelectGroup;
	}

	public void setVisualSelectGroup(int visualSelectGroup) {
		this.visualSelectGroup = visualSelectGroup;
	}

	public int getSwapMethod() {
		return swapMethod;
	}

	public void setSwapMethod(int swapMethod) {
		this.swapMethod = swapMethod;
	}

	public int getScreen() {
		return screen;
	}

	public void setScreen(int screen) {
		this.screen = screen;
	}

	public int getBindToTextureRgb() {
		return bindToTextureRgb;
	}

	public void setBindToTextureRgb(int bindToTextureRgb) {
		this.bindToTextureRgb = bindToTextureRgb;
	}

	public int getBindToTextureRgba() {
		return bindToTextureRgba;
	}

	public void setBindToTextureRgba(int bindToTextureRgba) {
		this.bindToTextureRgba = bindToTextureRgba;
	}

	public int getBindToMipmapTexture() {
		return bindToMipmapTexture;
	}

	public void setBindToMipmapTexture(int bindToMipmapTexture) {
		this.bindToMipmapTexture = bindToMipmapTexture;
	}

	public int getBindToTextureTargets() {
		return bindToTextureTargets;
	}

	public void setBindToTextureTargets(int bindToTextureTargets) {
		this.bindToTextureTargets = bindToTextureTargets;
	}

	public int getyInverted() {
		return yInverted;
	}

	public void setyInverted(int yInverted) {
		this.yInverted = yInverted;
	}
}
