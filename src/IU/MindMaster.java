/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IU;

import static IU.TexturaIU.glCanvas;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import java.awt.Color;
import javax.swing.JPanel;
import renderer.tablero;

/**
 *
 * @author Docente
 */
public class MindMaster  extends javax.swing.JFrame{
    private tablero RendererTablero;
    public static GLCanvas glCanvas;
    private JPanel panel;
    public MindMaster(){
        setSize(600, 800);
        setLocationRelativeTo(null);
        inicomponent();
        RendererTablero = new tablero();
        createCanvasJOGL();
    }
    private void createCanvasJOGL() {
        //only three JOGL lines of code ... and here they are
        GLProfile profile = GLProfile.getDefault();
        GLCapabilities glcaps = new GLCapabilities(profile);
        glCanvas = new GLCanvas(glcaps);
        glCanvas.addGLEventListener(RendererTablero);
        panel.add(glCanvas);
        int w = panel.getWidth();
        int h = panel.getWidth();
        glCanvas.setSize(w, h);
    }
    private void inicomponent(){
        panel = new JPanel();
        this.getContentPane().add(panel);
        panel.setBackground(Color.red);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public static void main(String[] arg){
        MindMaster v1=new MindMaster();
        v1.setVisible(true);
    }
}
