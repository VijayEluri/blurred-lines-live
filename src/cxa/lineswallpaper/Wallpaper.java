package cxa.lineswallpaper;

import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;

import net.rbgrn.opengl.GLWallpaperService;
import android.opengl.GLSurfaceView.EGLContextFactory;

public class Wallpaper extends GLWallpaperService {

	class GLES20ContextFactory implements EGLContextFactory {
		private int EGL_CONTEXT_CLIENT_VERSION = 0x3098;

		@Override
		public EGLContext createContext(final EGL10 egl,
				final EGLDisplay display, final EGLConfig config) {
			int[] attrib_list = { EGL_CONTEXT_CLIENT_VERSION, 2, EGL10.EGL_NONE };
			return egl.eglCreateContext(display, config, EGL10.EGL_NO_CONTEXT,
					attrib_list);
		}

		@Override
		public void destroyContext(final EGL10 egl, final EGLDisplay display,
				final EGLContext context) {
			egl.eglDestroyContext(display, context);
		}
	}

	class WallpaperEngine extends GLEngine {
		private static final String TAG = "BlurredLinesLiveWallpaperEngine";

		public WallpaperEngine() {
			super();

			setEGLContextFactory(new GLES20ContextFactory());

			GLES20LinesRenderer renderer = new GLES20LinesRenderer(null);
			setRenderer(renderer);
			setRenderMode(RENDERMODE_CONTINUOUSLY);
		}
	}

	@Override
	public Engine onCreateEngine() {
		return new WallpaperEngine();
	}
}
