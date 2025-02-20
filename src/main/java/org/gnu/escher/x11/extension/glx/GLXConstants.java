package org.gnu.escher.x11.extension.glx;

/**
 * Set of constants used to query GLX extensions.
 *
 * @author Mario Torre neugens@aicas.com
 */
public class GLXConstants {
	/* NOTE: we really want to generate this class from jogl GL and GLX */

	/* NOTE: values taken from GLX.java, generated jogl class */

	public static final int VisualNoMask = 0x0;
	public static final int VisualIDMask = 0x1;
	public static final int VisualScreenMask = 0x2;
	public static final int VisualDepthMask = 0x4;
	public static final int VisualClassMask = 0x8;
	public static final int VisualRedMaskMask = 0x10;
	public static final int VisualGreenMaskMask = 0x20;
	public static final int VisualBlueMaskMask = 0x40;
	public static final int VisualColormapSizeMask = 0x80;
	public static final int VisualBitsPerRGBMask = 0x100;
	public static final int VisualAllMask = 0x1FF;
	public static final int GLX_USE_GL = 1;
	public static final int GLX_BUFFER_SIZE = 2;
	public static final int GLX_LEVEL = 3;
	public static final int GLX_RGBA = 4;
	public static final int GLX_DOUBLEBUFFER = 5;
	public static final int GLX_STEREO = 6;
	public static final int GLX_AUX_BUFFERS = 7;
	public static final int GLX_RED_SIZE = 8;
	public static final int GLX_GREEN_SIZE = 9;
	public static final int GLX_BLUE_SIZE = 10;
	public static final int GLX_ALPHA_SIZE = 11;
	public static final int GLX_DEPTH_SIZE = 12;
	public static final int GLX_STENCIL_SIZE = 13;
	public static final int GLX_ACCUM_RED_SIZE = 14;
	public static final int GLX_ACCUM_GREEN_SIZE = 15;
	public static final int GLX_ACCUM_BLUE_SIZE = 16;
	public static final int GLX_ACCUM_ALPHA_SIZE = 17;
	public static final int GLX_BAD_SCREEN = 1;
	public static final int GLX_BAD_ATTRIBUTE = 2;
	public static final int GLX_NO_EXTENSION = 3;
	public static final int GLX_BAD_VISUAL = 4;
	public static final int GLX_BAD_CONTEXT = 5;
	public static final int GLX_BAD_VALUE = 6;
	public static final int GLX_BAD_ENUM = 7;
	public static final int GLX_VENDOR = 1;
	public static final int GLX_VERSION = 2;
	public static final int GLX_EXTENSIONS = 0x3;
	public static final int GLX_CONFIG_CAVEAT = 0x20;
	public static final long GLX_DONT_CARE = 0xFFFFFFFF;
	public static final int GLX_SLOW_CONFIG = 0x8001;
	public static final int GLX_NON_CONFORMANT_CONFIG = 0x800D;
	public static final int GLX_X_VISUAL_TYPE = 0x22;
	public static final int GLX_TRANSPARENT_TYPE = 0x23;
	public static final int GLX_TRANSPARENT_INDEX_VALUE = 0x24;
	public static final int GLX_TRANSPARENT_RED_VALUE = 0x25;
	public static final int GLX_TRANSPARENT_GREEN_VALUE = 0x26;
	public static final int GLX_TRANSPARENT_BLUE_VALUE = 0x27;
	public static final int GLX_TRANSPARENT_ALPHA_VALUE = 0x28;
	public static final int GLX_MAX_PBUFFER_WIDTH = 0x8016;
	public static final int GLX_MAX_PBUFFER_HEIGHT = 0x8017;
	public static final int GLX_MAX_PBUFFER_PIXELS = 0x8018;
	public static final int GLX_PRESERVED_CONTENTS = 0x801B;
	public static final int GLX_LARGEST_PBUFFER = 0x801C;
	public static final int GLX_WIDTH = 0x801D;
	public static final int GLX_HEIGHT = 0x801E;
	public static final int GLX_EVENT_MASK = 0x801F;
	public static final int GLX_DRAWABLE_TYPE = 0x8010;
	public static final int GLX_FBCONFIG_ID = 0x8013;
	public static final int GLX_VISUAL_ID = 0x800B;
	public static final int GLX_WINDOW_BIT = 0x00000001;
	public static final int GLX_PIXMAP_BIT = 0x00000002;
	public static final int GLX_PBUFFER_BIT = 0x00000004;
	public static final int GLX_AUX_BUFFERS_BIT = 0x00000010;
	public static final int GLX_FRONT_LEFT_BUFFER_BIT = 0x00000001;
	public static final int GLX_FRONT_RIGHT_BUFFER_BIT = 0x00000002;
	public static final int GLX_BACK_LEFT_BUFFER_BIT = 0x00000004;
	public static final int GLX_BACK_RIGHT_BUFFER_BIT = 0x00000008;
	public static final int GLX_DEPTH_BUFFER_BIT = 0x00000020;
	public static final int GLX_STENCIL_BUFFER_BIT = 0x00000040;
	public static final int GLX_ACCUM_BUFFER_BIT = 0x00000080;
	public static final int GLX_RENDER_TYPE = 0x8011;
	public static final int GLX_X_RENDERABLE = 0x8012;
	public static final int GLX_NONE = 0x8000;
	public static final int GLX_TRUE_COLOR = 0x8002;
	public static final int GLX_DIRECT_COLOR = 0x8003;
	public static final int GLX_PSEUDO_COLOR = 0x8004;
	public static final int GLX_STATIC_COLOR = 0x8005;
	public static final int GLX_GRAY_SCALE = 0x8006;
	public static final int GLX_STATIC_GRAY = 0x8007;
	public static final int GLX_TRANSPARENT_RGB = 0x8008;
	public static final int GLX_TRANSPARENT_INDEX = 0x8009;
	public static final int GLX_RGBA_TYPE = 0x8014;
	public static final int GLX_COLOR_INDEX_TYPE = 0x8015;
	public static final int GLX_COLOR_INDEX_BIT = 0x00000002;
	public static final int GLX_RGBA_BIT = 0x00000001;
	public static final int GLX_SCREEN = 0x800C;
	public static final int GLX_PBUFFER_CLOBBER_MASK = 0x08000000;
	public static final int GLX_DAMAGED = 0x8020;
	public static final int GLX_SAVED = 0x8021;
	public static final int GLX_WINDOW = 0x8022;
	public static final int GLX_PBUFFER = 0x8023;
	public static final int GLX_PBUFFER_HEIGHT = 0x8040;
	public static final int GLX_PBUFFER_WIDTH = 0x8041;
	public static final int GLX_SAMPLE_BUFFERS = 0x186a0;
	public static final int GLX_SAMPLES = 0x186a1;
	public static final int GLX_GLXEXT_VERSION = 15;
	public static final int GLX_SAMPLE_BUFFERS_ARB = 100000;
	public static final int GLX_SAMPLES_ARB = 100001;
	public static final int GLX_RGBA_FLOAT_TYPE_ARB = 0x20B9;
	public static final int GLX_RGBA_FLOAT_BIT_ARB = 0x00000004;
	public static final int GLX_SAMPLE_BUFFERS_SGIS = 100000;
	public static final int GLX_SAMPLES_SGIS = 100001;
	public static final int GLX_X_VISUAL_TYPE_EXT = 0x22;
	public static final int GLX_TRANSPARENT_TYPE_EXT = 0x23;
	public static final int GLX_TRANSPARENT_INDEX_VALUE_EXT = 0x24;
	public static final int GLX_TRANSPARENT_RED_VALUE_EXT = 0x25;
	public static final int GLX_TRANSPARENT_GREEN_VALUE_EXT = 0x26;
	public static final int GLX_TRANSPARENT_BLUE_VALUE_EXT = 0x27;
	public static final int GLX_TRANSPARENT_ALPHA_VALUE_EXT = 0x28;
	public static final int GLX_NONE_EXT = 0x8000;
	public static final int GLX_TRUE_COLOR_EXT = 0x8002;
	public static final int GLX_DIRECT_COLOR_EXT = 0x8003;
	public static final int GLX_PSEUDO_COLOR_EXT = 0x8004;
	public static final int GLX_STATIC_COLOR_EXT = 0x8005;
	public static final int GLX_GRAY_SCALE_EXT = 0x8006;
	public static final int GLX_STATIC_GRAY_EXT = 0x8007;
	public static final int GLX_TRANSPARENT_RGB_EXT = 0x8008;
	public static final int GLX_TRANSPARENT_INDEX_EXT = 0x8009;
	public static final int GLX_VISUAL_CAVEAT_EXT = 0x20;
	public static final int GLX_SLOW_VISUAL_EXT = 0x8001;
	public static final int GLX_NON_CONFORMANT_VISUAL_EXT = 0x800D;
	public static final int GLX_SHARE_CONTEXT_EXT = 0x800A;
	public static final int GLX_VISUAL_ID_EXT = 0x800B;
	public static final int GLX_SCREEN_EXT = 0x800C;
	public static final int GLX_WINDOW_BIT_SGIX = 0x00000001;
	public static final int GLX_PIXMAP_BIT_SGIX = 0x00000002;
	public static final int GLX_RGBA_BIT_SGIX = 0x00000001;
	public static final int GLX_COLOR_INDEX_BIT_SGIX = 0x00000002;
	public static final int GLX_DRAWABLE_TYPE_SGIX = 0x8010;
	public static final int GLX_RENDER_TYPE_SGIX = 0x8011;
	public static final int GLX_X_RENDERABLE_SGIX = 0x8012;
	public static final int GLX_FBCONFIG_ID_SGIX = 0x8013;
	public static final int GLX_RGBA_TYPE_SGIX = 0x8014;
	public static final int GLX_COLOR_INDEX_TYPE_SGIX = 0x8015;
	public static final int GLX_PBUFFER_BIT_SGIX = 0x00000004;
	public static final int GLX_BUFFER_CLOBBER_MASK_SGIX = 0x08000000;
	public static final int GLX_FRONT_LEFT_BUFFER_BIT_SGIX = 0x00000001;
	public static final int GLX_FRONT_RIGHT_BUFFER_BIT_SGIX = 0x00000002;
	public static final int GLX_BACK_LEFT_BUFFER_BIT_SGIX = 0x00000004;
	public static final int GLX_BACK_RIGHT_BUFFER_BIT_SGIX = 0x00000008;
	public static final int GLX_AUX_BUFFERS_BIT_SGIX = 0x00000010;
	public static final int GLX_DEPTH_BUFFER_BIT_SGIX = 0x00000020;
	public static final int GLX_STENCIL_BUFFER_BIT_SGIX = 0x00000040;
	public static final int GLX_ACCUM_BUFFER_BIT_SGIX = 0x00000080;
	public static final int GLX_SAMPLE_BUFFERS_BIT_SGIX = 0x00000100;
	public static final int GLX_MAX_PBUFFER_WIDTH_SGIX = 0x8016;
	public static final int GLX_MAX_PBUFFER_HEIGHT_SGIX = 0x8017;
	public static final int GLX_MAX_PBUFFER_PIXELS_SGIX = 0x8018;
	public static final int GLX_OPTIMAL_PBUFFER_WIDTH_SGIX = 0x8019;
	public static final int GLX_OPTIMAL_PBUFFER_HEIGHT_SGIX = 0x801A;
	public static final int GLX_PRESERVED_CONTENTS_SGIX = 0x801B;
	public static final int GLX_LARGEST_PBUFFER_SGIX = 0x801C;
	public static final int GLX_WIDTH_SGIX = 0x801D;
	public static final int GLX_HEIGHT_SGIX = 0x801E;
	public static final int GLX_EVENT_MASK_SGIX = 0x801F;
	public static final int GLX_DAMAGED_SGIX = 0x8020;
	public static final int GLX_SAVED_SGIX = 0x8021;
	public static final int GLX_WINDOW_SGIX = 0x8022;
	public static final int GLX_PBUFFER_SGIX = 0x8023;
	public static final int GLX_SYNC_FRAME_SGIX = 0x00000000;
	public static final int GLX_SYNC_SWAP_SGIX = 0x00000001;
	public static final int GLX_DIGITAL_MEDIA_PBUFFER_SGIX = 0x8024;
	public static final int GLX_BLENDED_RGBA_SGIS = 0x8025;
	public static final int GLX_MULTISAMPLE_SUB_RECT_WIDTH_SGIS = 0x8026;
	public static final int GLX_MULTISAMPLE_SUB_RECT_HEIGHT_SGIS = 0x8027;
	public static final int GLX_SAMPLE_BUFFERS_3DFX = 0x8050;
	public static final int GLX_SAMPLES_3DFX = 0x8051;
	public static final int GLX_VISUAL_SELECT_GROUP_SGIX = 0x8028;
	public static final int GLX_SWAP_METHOD_OML = 0x8060;
	public static final int GLX_SWAP_EXCHANGE_OML = 0x8061;
	public static final int GLX_SWAP_COPY_OML = 0x8062;
	public static final int GLX_SWAP_UNDEFINED_OML = 0x8063;
	public static final int GLX_FLOAT_COMPONENTS_NV = 0x20B0;
	public static final int GLX_HYPERPIPE_PIPE_NAME_LENGTH_SGIX = 80;
	public static final int GLX_BAD_HYPERPIPE_CONFIG_SGIX = 91;
	public static final int GLX_BAD_HYPERPIPE_SGIX = 92;
	public static final int GLX_HYPERPIPE_DISPLAY_PIPE_SGIX = 0x00000001;
	public static final int GLX_HYPERPIPE_RENDER_PIPE_SGIX = 0x00000002;
	public static final int GLX_PIPE_RECT_SGIX = 0x00000001;
	public static final int GLX_PIPE_RECT_LIMITS_SGIX = 0x00000002;
	public static final int GLX_HYPERPIPE_STEREO_SGIX = 0x00000003;
	public static final int GLX_HYPERPIPE_PIXEL_AVERAGE_SGIX = 0x00000004;
	public static final int GLX_HYPERPIPE_ID_SGIX = 0x8030;
	public static final int GLX_VIDEO_OUT_COLOR_NV = 0x20C3;
	public static final int GLX_VIDEO_OUT_ALPHA_NV = 0x20C4;
	public static final int GLX_VIDEO_OUT_DEPTH_NV = 0x20C5;
	public static final int GLX_VIDEO_OUT_COLOR_AND_ALPHA_NV = 0x20C6;
	public static final int GLX_VIDEO_OUT_COLOR_AND_DEPTH_NV = 0x20C7;
	public static final int GLX_VIDEO_OUT_FRAME_NV = 0x20C8;
	public static final int GLX_VIDEO_OUT_FIELD_1_NV = 0x20C9;
	public static final int GLX_VIDEO_OUT_FIELD_2_NV = 0x20CA;
	public static final int GLX_BIND_TO_TEXTURE_RGB_EXT = 0x20D0;
	public static final int GLX_BIND_TO_TEXTURE_RGBA_EXT = 0x20D1;
	public static final int GLX_BIND_TO_MIPMAP_TEXTURE_EXT = 0x20D2;
	public static final int GLX_BIND_TO_TEXTURE_TARGETS_EXT = 0x20D3;
	public static final int GLX_Y_INVERTED_EXT = 0x20D4;
	public static final int GLX_TEXTURE_FORMAT_EXT = 0x20D5;
	public static final int GLX_TEXTURE_TARGET_EXT = 0x20D6;
	public static final int GLX_MIPMAP_TEXTURE_EXT = 0x20D7;
	public static final int GLX_TEXTURE_FORMAT_NONE_EXT = 0x20D8;
	public static final int GLX_TEXTURE_FORMAT_RGB_EXT = 0x20D9;
	public static final int GLX_TEXTURE_FORMAT_RGBA_EXT = 0x20DA;
	public static final int GLX_TEXTURE_1D_BIT_EXT = 0x00000001;
	public static final int GLX_TEXTURE_2D_BIT_EXT = 0x00000002;
	public static final int GLX_TEXTURE_RECTANGLE_BIT_EXT = 0x00000004;
	public static final int GLX_TEXTURE_1D_EXT = 0x20DB;
	public static final int GLX_TEXTURE_2D_EXT = 0x20DC;
	public static final int GLX_TEXTURE_RECTANGLE_EXT = 0x20DD;
	public static final int GLX_FRONT_LEFT_EXT = 0x20DE;
	public static final int GLX_FRONT_RIGHT_EXT = 0x20DF;
	public static final int GLX_BACK_LEFT_EXT = 0x20E0;
	public static final int GLX_BACK_RIGHT_EXT = 0x20E1;
	public static final int GLX_FRONT_EXT = 0x20DE;
	public static final int GLX_BACK_EXT = 0x20E0;
	public static final int GLX_AUX0_EXT = 0x20E2;
	public static final int GLX_AUX1_EXT = 0x20E3;
	public static final int GLX_AUX2_EXT = 0x20E4;
	public static final int GLX_AUX3_EXT = 0x20E5;
	public static final int GLX_AUX4_EXT = 0x20E6;
	public static final int GLX_AUX5_EXT = 0x20E7;
	public static final int GLX_AUX6_EXT = 0x20E8;
	public static final int GLX_AUX7_EXT = 0x20E9;
	public static final int GLX_AUX8_EXT = 0x20EA;
	public static final int GLX_AUX9_EXT = 0x20EB;
	public static final int GLX_VERSION_1_3 = 1;
	public static final int GLX_VERSION_1_4 = 1;
}
