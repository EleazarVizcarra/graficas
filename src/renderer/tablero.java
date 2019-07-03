package renderer;
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
import java.io.IOException;
import java.io.InputStream;

public class tablero implements GLEventListener {

    private GL2 gl;
    private GLU glu;
    private GLUT glut;
    private boolean detenido;
    private double x;
    private double y;
    private double z;
    private double theta;
    /** The earth texture. */
    private Texture earthTexture;

    public tablero() {
        // Utilitarios
        glu = new GLU();
        glut = new GLUT();
        detenido = true;
        x = 0;
        y = 10;
        z = 0;
        theta = 0;
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
                0, 0, 50,
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
        try {
            InputStream stream = getClass().getResourceAsStream("earth.png");
            GLProfile profile = GLProfile.getDefault();
            TextureData data = TextureIO.newTextureData(profile, stream, false, "png");
            earthTexture = TextureIO.newTexture(data);
        } catch (IOException exc) {
            exc.printStackTrace();
            System.exit(1);
        }

    }

    /* Toma en cuenta para Hacer el dibujo aquí */
    @Override
    public void display(GLAutoDrawable drawable) {
        System.out.println("display");
        gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT
                | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glClearColor(0, 0, 0, 1);

        // Apply texture.
        earthTexture.enable(gl);
        earthTexture.bind(gl);
        
        gl.glScaled(2, 2, 2);
        gl.glRotated(45, 0, 1, 0);
        // Draw sphere (possible styles: FILL, LINE, POINT).
        GLUquadric earth = glu.gluNewQuadric();
        glu.gluQuadricTexture(earth, true);
        glu.gluQuadricDrawStyle(earth, GLU.GLU_FILL);
        glu.gluQuadricNormals(earth, GLU.GLU_FLAT);
        glu.gluQuadricOrientation(earth, GLU.GLU_OUTSIDE);
        final float radius = 6.378f;
        final int slices = 16;
        final int stacks = 16;
        glu.gluSphere(earth, radius, slices, stacks);
        glu.gluDeleteQuadric(earth);
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
}
