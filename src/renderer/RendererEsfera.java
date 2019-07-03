package renderer;

import IU.TexturaIU;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;
import com.jogamp.opengl.util.gl2.GLUT;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureData;
import com.jogamp.opengl.util.texture.TextureIO;
import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.InputStream;

public class RendererEsfera implements GLEventListener, KeyListener, FocusListener {

    private GL2 gl;
    private GLU glu;
    private GLUT glut;
    private double foco;
    
    private int x;
    private int y;
    private int z;
    private int sx;
    private int sy;
    private int sz;
    private int tx;
    private int ty;
    private int tz;
   
    public RendererEsfera() {
        // Utilitarios
        glu = new GLU();
        glut = new GLUT();
        foco = 0;
        z=60;
        y=0;
        x=0;
        sz=1;
        sy=1;
        sx=1;
        tz=0;
        ty=0;
        tx=0;
   
    }

    // Toma en cuenta para la inicialización aquí.
    @Override
    public void init(GLAutoDrawable drawable) {
        // Crear una Interfaz con OPENGL 2.0        
        gl = drawable.getGL().getGL2();
        // Recuperar el ancho y alto de ventama de visualización
        int w = ((Component) drawable).getWidth();
        int h = ((Component) drawable).getHeight();
        // Establecer un visor en todo el area de la ventana de visualización
        gl.glViewport(0, 0, w, h);
        // Establecer el uso de matrices en Modelo-Vista
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        // Carga la matriz identidad
        // gl.glLoadIdentity();
        // Posición de la camara
        glu.gluLookAt(
                0, 0, 60,
                0, 0, 0,
                0, 1, 0
        );

        // Habilitar el Buffer de colores RGB
        gl.glDrawBuffer(GL2.GL_FRONT_AND_BACK);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        // Aspect is width/height
        double aspect = w / h;
        glu.gluPerspective(60.0, aspect, 10.0, 100.0);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
//        gl.glLoadIdentity();

        // Load earth texture.
        

    }

    /* Toma en cuenta para Hacer el dibujo aquí */
    @Override
    public void display(GLAutoDrawable drawable) {
        System.out.println(sx);
        System.out.println(sy);
        System.out.println(sz);
        System.out.println(tx);
        System.out.println(ty);
        System.out.println(tz);
        System.out.println(x);
        System.out.println(y);
        System.out.println(z);
        
        gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
        glu.gluLookAt(
                x, y, z,
                tx, ty, tz,
                0, 1, 0
        );
        gl.glPushMatrix();
        
        gl.glClearColor(0, (float) foco, 0, 1);
        
        
        gl.glPushMatrix();
        gl.glScalef(sx, sy, sz);
        //gl.glTranslatef(tx, ty, tz);
        //gl.glRotatef(40, 1, 0, 0);
        glut.glutWireCube(2);
        gl.glPopMatrix();
    }

    /**
     * Llamado cuando el GLDrawable (GLCanvas o GLJPanel) ha cambiado de tamaño.
     * Nosotros por ahora no necesitarremos esto, pero puede que eventualmente
     * lo necesito - pero ahora simplemente no todavía.
     */
    @Override
    public void reshape(GLAutoDrawable glad, int i, int i1, int i2, int i3) {
        System.out.println("reshape");
    }

    @Override
    public void dispose(GLAutoDrawable glad) {

    }

    @Override
    public void keyTyped(KeyEvent e) {
       if (e.getKeyChar() == KeyEvent.VK_5) {
            y += 1;
        } else if (e.getKeyChar() == KeyEvent.VK_2) {
            y -= 1;
        } else if (e.getKeyChar() == KeyEvent.VK_1) {
            x -= 1;
        } else if (e.getKeyChar() == KeyEvent.VK_4) {
            x += 1;
        } else if (e.getKeyChar() == KeyEvent.VK_6) {
            z += 1;
        } else if (e.getKeyChar() == KeyEvent.VK_3) {
            z -= 1;
        } else if (e.getKeyChar() == KeyEvent.VK_W) {
            sy += 1;
        } else if (e.getKeyChar() == KeyEvent.VK_S) {
            sy -= 1;
        } else if (e.getKeyChar() == KeyEvent.VK_A) {
            sx -= 1;
        } else if (e.getKeyChar() == KeyEvent.VK_Q) {
            sx += 1;
        } else if (e.getKeyChar() == KeyEvent.VK_E) {
            sz += 1;
        } else if (e.getKeyChar() == KeyEvent.VK_D) {
            sz -= 1;
        } else if (e.getKeyChar() == KeyEvent.VK_T) {
            ty += 1;
        } else if (e.getKeyChar() == KeyEvent.VK_G) {
            ty -= 1;
        } else if (e.getKeyChar() == KeyEvent.VK_F) {
            tx -= 1;
        } else if (e.getKeyChar() == KeyEvent.VK_R) {
            tx += 1;
        } else if (e.getKeyChar() == KeyEvent.VK_Y) {
            tz += 1;
        } else if (e.getKeyChar() == KeyEvent.VK_H) {
            tz -= 1;
        }
       
        TexturaIU.glCanvas.repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
         //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
         //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void focusGained(FocusEvent e) {
        foco = 1.0f;
        TexturaIU.glCanvas.repaint(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void focusLost(FocusEvent e) {
        foco = 0.0f;
        TexturaIU.glCanvas.repaint(); //To change body of generated methods, choose Tools | Templates.
    }
}
