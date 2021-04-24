package org.gnu.escher.utils;


/** 4-element vector of <code>double</code> in mathematics. */
public class Vector4Double {
  public static final Vector4Double ZERO = new Vector4Double ();
  private double [] vector = new double [4];


  public Vector4Double () {}


  public Vector4Double (double [] v) {
    this.vector = v;
  }


  public Vector4Double (double x, double y, double z, double w) {
    vector [0] = x;
    vector [1] = y;
    vector [2] = z;
    vector [3] = w;
  }


  /** <code>C = A x B</code>. */
  private double [] cross (double [] A, double [] B, double [] C) {
    C [0] = A [1] * B [2] - A [2] * B [1];
    C [1] = -A [0] * B [2] + A [2] * B [0];
    C [2] = A [0] * B [1] - A [1] * B [0];
    return C;
  }


  public Vector4Double cross (Vector4Double A, Vector4Double B) {
    double [] C = vector;
    if (A == this || B == this) C = new double [4];
    vector = cross (A.vector, B.vector, C);
    return this;
  }


  public double length () {
    return length (vector);
  }


  public static double length (double [] A) {
    return java.lang.Math.sqrt (lengthSquare (A));
  }


  public double lengthSquare () {
    return lengthSquare (vector);
  }


  /** <code>|A| = x*x + y*y + z*z</code>. */
  public static double lengthSquare (double [] A) {
    return A [0] * A [0] + A [1] * A [1] + A [2] * A [2];
  }


  public Vector4Double minus (Vector4Double A, Vector4Double B) {
    minus (A.vector, B.vector, vector);
    return this;
  }


  /** <code>C = A - B</code>. */
  private double [] minus (double [] A, double [] B, double [] C) {
    C [0] = A [0] - B [0];
    C [1] = A [1] - B [1];
    C [2] = A [2] - B [2];
    C [3] = A [3] - B [3];
    return C;
  }
  

  public Vector4Double multiplyRight (Matrix4x4Double A) {
    return multiplyRight (this, A);
  }


  public Vector4Double multiplyRight (Vector4Double V, Matrix4x4Double A) {
    double [] B = vector;
    if (V == this) B = new double [4];
    vector = multiplyRight (V.vector, A.getMatrix(), B);
    return this;
  }


  /** <code>B = V * A</code>. */
  private double [] multiplyRight (double [] V, double [] A, 
    double [] B) {

    B [0] = V [0] * A [0] + V [1] * A [4]
      + V [2] * A [8] + V [3] * A [12];
    B [1] = V [0] * A [1] + V [1] * A [5]
      + V [2] * A [9] + V [3] * A [13];
    B [2] = V [0] * A [2] + V [1] * A [6]
      + V [2] * A [10] + V [3] * A [14];
    B [3] = V [0] * A [3] + V [1] * A [7]
      + V [2] * A [11] + V [3] * A [15];
    return B;
  }    


  public Vector4Double negate () {
    return negate (this);
  }


  public Vector4Double negate (Vector4Double A) {
    negate (A.vector, vector);
    return this;
  }


  /** <code>B = -A</code>. */
  private double [] negate (double [] A, double [] B) {
    return scalarMultiply (A, -1, B);
  }


  public Vector4Double normalize () {
    return normalize (this);
  }


  public Vector4Double normalize (Vector4Double A) {
    normalize (A.vector, vector);
    return this;
  }


  /** <code>B = A / |A|</code>. */
  private double [] normalize (double [] A, double [] B) {
    return scalarDivide (A, length (A), B);
  }


  /** <code>B = A + (a * I)</code>. */
  private double [] scalarAddition (double [] A, double d, double [] B) {
    B [0] = A [0] + d;
    B [1] = A [1] + d;
    B [2] = A [2] + d;
    B [3] = A [3] + d;
    return B;
  }


  /** <code>B = (1/d) * A</code>. */
  private double [] scalarDivide (double [] A, double d, double [] B) {
    return scalarMultiply (A, 1/d, B);
  }
    

  public Vector4Double scalarMinus (double d) {
    scalarMinus (vector, d, vector);
    return this;
  } 

  /** <code>B = A - (a * I)</code>. */
  private double [] scalarMinus (double [] A, double d, double [] B) {
    return scalarAddition (A, -d, B);
  }


  public Vector4Double scalarMultiply (double d) {
    return scalarMultiply (this, d);
  }


  private Vector4Double scalarMultiply (Vector4Double A, double d) {
    scalarMultiply (A.vector, d, vector);
    return this;
  }


  /** <code>B = d * A</code>. */
  private double [] scalarMultiply (double [] A, double d, double [] B) {
    B [0] = A [0] * d;
    B [1] = A [1] * d;
    B [2] = A [2] * d;
    B [3] = A [3] * d;
    return B;
  }
    

  public String toString () {
    return "#Vector4d: " + vector [0] + " " + vector [1] + " " + vector [2] + " " + vector [3];
  }
  
  // Get and Set
  
  public double[] getVector() {
    return vector;
  }
 
  public void setVector(double[] vector) {
    this.vector = vector;
  }
}
