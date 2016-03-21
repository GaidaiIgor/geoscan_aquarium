import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLJPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CustomPanel extends GLJPanel implements GLEventListener, KeyListener {
    private float rotateX = 15;
    private float rotateY = 15;
    private float rotateZ = 0;
    private float scale = 1;
    private float translateX = 0;
    private float translateY = 0;
    private float translateZ = 0;
    private float rotationStep = 15;
    private float scaleStep = 1;
    private float translationStep = 1;

    public CustomPanel(GLCapabilities capabilities) {
        super(capabilities);
        setPreferredSize(new Dimension(500, 500));
        addGLEventListener(this);
        addKeyListener(this);

        setDefault();
    }

    private void setDefault() {
        rotateX = 15;
        rotateY = 15;
        rotateZ = 0;
        scale = 10;
        translateX = 0;
        translateY = 0;
        translateZ = 0;
    }

    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        GL2 gl = glAutoDrawable.getGL().getGL2();
        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glEnable(GL2.GL_LIGHTING);
        gl.glEnable(GL2.GL_LIGHT0);
        gl.glEnable(GL2.GL_COLOR_MATERIAL);
    }

    @Override
    public void dispose(GLAutoDrawable glAutoDrawable) {

    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        GL2 gl = glAutoDrawable.getGL().getGL2();
        gl.glClearColor(0, 0, 0, 0);
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);

        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(-scale, scale, -scale, scale, -scale, scale);

        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glRotatef(rotateX, 1, 0, 0);
        gl.glRotatef(rotateY, 0, 1, 0);
        gl.glRotatef(rotateZ, 0, 0, 1);
        gl.glTranslatef(translateX, translateY, translateZ);
    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int x, int y, int width, int height) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_A) {
            rotateY += rotationStep;
        } else if (key == KeyEvent.VK_D) {
            rotateY -= rotationStep;
        } else if (key == KeyEvent.VK_S) {
            rotateX += rotationStep;
        } else if (key == KeyEvent.VK_W) {
            rotateX -= rotationStep;
        } else if (key == KeyEvent.VK_E) {
            rotateZ -= rotationStep;
        } else if (key == KeyEvent.VK_Q) {
            rotateZ += rotationStep;
        } else if (key == KeyEvent.VK_F) {
            scale -= scaleStep;
        } else if (key == KeyEvent.VK_C) {
            scale += scaleStep;
        } else if (key == KeyEvent.VK_J) {
            translateX -= translationStep;
        } else if (key == KeyEvent.VK_L) {
            translateX += translationStep;
        } else if (key == KeyEvent.VK_K) {
            translateY -= translationStep;
        } else if (key == KeyEvent.VK_I) {
            translateY += translationStep;
        } else if (key == KeyEvent.VK_U) {
            translateZ -= translationStep;
        } else if (key == KeyEvent.VK_O) {
            translateZ += translationStep;
        } else if (key == KeyEvent.VK_R) {
            setDefault();
        } else if (key == KeyEvent.VK_ESCAPE) {
            JFrame parent = (JFrame) getTopLevelAncestor();
            parent.dispose();
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
