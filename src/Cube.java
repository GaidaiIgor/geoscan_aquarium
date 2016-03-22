import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;

public class Cube implements GLEventListener {
    private final float size = 1;
    private float red;
    private float green;
    private float blue;
    private int xIndex;
    private int yIndex;

    public Cube(float red, float green, float blue, int xIndex, int yIndex) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.xIndex = xIndex;
        this.yIndex = yIndex;
    }

    public float getSize() {
        return size;
    }

    @Override
    public void init(GLAutoDrawable drawable) {
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        int cube_sides = 6;
        float indexScale = 1;
        float[][] rotations = {{0, 0, 0, 0}, {180, 0, 1, 0}, {-90, 0, 1, 0}, {90, 0, 1, 0}, {-90, 1, 0, 0}, {90, 1, 0, 0}};

        gl.glPushMatrix();
        gl.glTranslatef(xIndex * indexScale, yIndex * indexScale, 0);
        for (int i = 0; i < cube_sides; ++i) {
            gl.glPushMatrix();
            gl.glRotatef(rotations[i][0], rotations[i][1], rotations[i][2], rotations[i][3]);
            square(gl, red, green, blue);
            gl.glPopMatrix();
        }
        gl.glPopMatrix();
    }

    private void square(GL2 gl, float r, float g, float b) {
        gl.glColor3f(r, g, b);
        gl.glTranslatef(0, 0, size / 2);
        gl.glNormal3f(0, 0, 1);

        gl.glBegin(GL2.GL_QUADS);
        gl.glVertex2f(-size / 2, -size / 2);
        gl.glVertex2f(size / 2, -size / 2);
        gl.glVertex2f(size / 2, size / 2);
        gl.glVertex2f(-size / 2, size / 2);
        gl.glEnd();
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
    }
}
