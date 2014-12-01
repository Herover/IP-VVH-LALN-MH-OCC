/**
 * Test om Matrix.equals og Matrix.comareTo gør som forventet
 */
public class TestMatrix {

  private static Matrix mat1 = new Matrix(1, 2);
  private static Matrix mat2 = new Matrix(1, 2);
  private static Matrix mat3 = new Matrix(1, 2);
  private static Matrix mat4 = new Matrix(2, 2);

  public static void main(String[] args) {
    
    //Opretter tre matrix-objekter hvor 2 er ens, alle 3 er med samme sum
    mat1.set(1, 1, 1);
    mat1.set(1, 2, 2);

    mat2.set(1, 1, 1);
    mat2.set(1, 2, 2);

    mat3.set(1, 1, 2);
    mat3.set(1, 2, 1);

    //Opretter et matrix objekt som hverken er ens eller har samme sum
    mat4.set(1, 1, 2);
    mat4.set(1, 2, 2);
    mat4.set(2, 1, 2);
    mat4.set(2, 2, 2);
    
    assertEquals(mat1, mat2);
    System.out.println("Den skulle være true");
    assertEquals(mat1, mat3);
    System.out.println("Den skulle være false");
    assertEquals(mat1, mat4);
    System.out.println("Den skulle være false");
    System.out.println("");
    System.out.println("compareTo tests");
    assertCompareTo(mat1, mat2);
    System.out.println("Forventet: 0");
    assertCompareTo(mat1, mat3);
    System.out.println("Forventet: 0");
    assertCompareTo(mat1, mat4);
    System.out.println("Forventet: -");
  }
  public static void assertEquals(Matrix expected, Matrix actual) {
    System.out.println("Er matrix'erne ens: " + expected.equals(actual));
  }
  public static void assertTrue(boolean condition) {
    if(!condition)
      System.out.println("Fik" + condition + ", forventede true");
  }
  public static void assertCompareTo(Matrix expected, Matrix actual) {
    System.out.println("Matrix-sammenligning: " + expected.compareTo(actual));
  }
}
