import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;

/**
 * This class is responsible for cube rendering
 */
public class Cube implements GLEventListener {
    public final static float size = 1;
    // coordinates of this cube in grid
    private int xIndex;
    private int yIndex;
    private CustomPanel parent;
    private Type type;

    public Cube(CustomPanel parent, int xIndex, int yIndex, Type type) {
        this.parent = parent;
        this.xIndex = xIndex;
        this.yIndex = yIndex;
        this.type = type;
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
            square(gl);
            gl.glPopMatrix();
        }
        gl.glPopMatrix();
    }

    // renders one side
    private void square(GL2 gl) {
        gl.glTranslatef(0, 0, size / 2);
        gl.glNormal3f(0, 0, 1);

        if (type == Type.Ground) {
            gl.glBindTexture(GL2.GL_TEXTURE_2D, parent.getGroundTexture());
        } else if (type == Type.Water) {
            gl.glBindTexture(GL2.GL_TEXTURE_2D, parent.getWaterTexture());
        }

        gl.glBegin(GL2.GL_QUADS);
        gl.glTexCoord2f(0, 0);
        gl.glVertex2f(-size / 2, -size / 2);
        gl.glTexCoord2f(1, 0);
        gl.glVertex2f(size / 2, -size / 2);
        gl.glTexCoord2f(1, 1);
        gl.glVertex2f(size / 2, size / 2);
        gl.glTexCoord2f(0, 1);
        gl.glVertex2f(-size / 2, size / 2);
        gl.glEnd();
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
    }

    public enum Type {Ground, Water}
}
