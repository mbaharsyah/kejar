package xyz.mbaharsyah.gradle.kejar.sample;

/**
 * @author mbaharsyah
 */
public class MyClass {

    private int a;
    private int b;

    public MyClass(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int plus() {
        return a + b;
    }

    public int minus() {
        return a - b;
    }

    public void swap() {
        a = a + b;
        b = a - b;
        a = a - b;
    }

}
