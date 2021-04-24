package org.gnu.escher.x11.extension.glx;

import org.gnu.escher.utils.Matrix4x4Double;
import org.gnu.escher.utils.Vector4Double;

/**
 * OpenGL utility library. The specification can be found
 * <a href= "http://escher.sourceforge.net/etc/specification/glu-1.3.ps.gz"
 * >here</a>.
 *
 *
 * <p>
 * Modified from <code>src-glu/*.c</code> in <a href= "http://mesa3d.org/">Mesa
 * 3D</a> by Brian Paul.
 */
public class GLU { // TODO
	public static final int SMOOTH = 100000;
	public static final int FLAT = 100001;
	public static final int NONE = 100002;

	public GL gl;

	public GLU(GL gl) {
		this.gl = gl;
	}

	/**
	 * @see <a href="gluLookAt.html">gluLookAt</a>
	 */
	public void look_at(double eyex, double eyey, double eyez, double centerx, double centery, double centerz,
			double upx, double upy, double upz) {

		Matrix4x4Double matrix = new Matrix4x4Double();
		Vector4Double eye = new Vector4Double(eyex, eyey, eyez, 1.0);
		Vector4Double center = new Vector4Double(centerx, centery, centerz, 1.0);
		Vector4Double up = new Vector4Double(upx, upy, upz, 1.0);

		Vector4Double forward = new Vector4Double();
		Vector4Double side = new Vector4Double();

		// -- make rotation matrix

		// forward = center - eye
		forward.minus(center, eye).normalize();

		// side = (forward x up), then normalized
		side.cross(forward, up).normalize();

		// up = side x forward
		up.cross(side, forward);

		/*
		 * [side_x up_x -forward_x 0] [side_y up_y -forward_y 0] [side_z up_z -forward_z
		 * 0] [ 0 0 0 1]
		 */
		matrix.setColumn(side, up, forward.negate(), Vector4Double.ZERO);
		matrix.setRow(3, 0.0, 0.0, 0.0, 1.0);
		gl.mult_matrixd(matrix.getMatrix());

		// -- translate eye to origin
		gl.translated(-eyex, -eyey, -eyez);
	}

	/**
	 * @see <a href="gluOrtho2D.html">gluOrtho2D</a>
	 */
	public void ortho_2d(double left, double right, double bottom, double top) {

		gl.ortho(left, right, bottom, top, -1.0, 1.0);
	}

	/**
	 * @see <a href="gluPerspective.html">gluPerspective</a>
	 */
	public void perspective(double fovy, double aspect, double near, double far) {

		double ymax = near * Math.tan(Math.toRadians(fovy / 2));
		double ymin = -ymax;
		double xmin = ymin * aspect;
		double xmax = ymax * aspect;

		gl.frustum(xmin, xmax, ymin, ymax, near, far);
	}

	/**
	 * @see <a href="gluProject.html">gluProject</a>
	 */
	public double[] project(double object_x, double object_y, double object_z, double[] modelview, double[] projection,
			int[] viewport) {

		Vector4Double window = new Vector4Double();
		return window.getVector();
	}

	/**
	 * @see <a href="gluUnProject.html">gluUnProject</a>
	 */
	public double[] un_project(double window_x, double window_y, double window_z, double[] modelview,
			double[] projection, int[] viewport) {

		Matrix4x4Double product = new Matrix4x4Double();
		Matrix4x4Double.multiply(modelview, projection, product.getMatrix());
		if (product.invert() == null)
			return null;

		Vector4Double A = new Vector4Double(window_x, window_y, window_z, 1.0);

		// map x and y from window coordinates
		A.getVector()[0] = (A.getVector()[0] - viewport[0]) / viewport[2];
		A.getVector()[1] = (A.getVector()[1] - viewport[1]) / viewport[3];

		// map to range -1 to 1, A = 2*A - I
		A.scalarMultiply(2).scalarMinus(1);

		A.multiplyRight(product);
		double d = A.getVector()[3];
		if (d == 0)
			return null;

		double[] result = new double[3]; // vs. 4
		result[0] = A.getVector()[0] / d;
		result[1] = A.getVector()[1] / d;
		result[2] = A.getVector()[2] / d;
		return result;
	}
}
