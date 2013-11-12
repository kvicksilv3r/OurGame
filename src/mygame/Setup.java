package mygame;

//Fixes all the shit that just needs to be done once, like setting up light and 
//positioning the character

import com.jme3.asset.AssetManager;
import com.jme3.bounding.BoundingBox;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.light.PointLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
import java.util.ArrayList;
import java.util.List;
import static mygame.Setup.blu;
import static mygame.Setup.grn;
import static mygame.Setup.modelmat;
import static mygame.Setup.pl;
import static mygame.Setup.plane;
import static mygame.Setup.red;

public class Setup {
    
    //public static Spatial player;
    public static Spatial plane;
    public static Spatial plane2;
    public static Spatial plane3;
    public static Material modelmat;
    public static Material red;
    public static Material blu;
    public static Material grn;
    public static PointLight pl;
    
    
    public static void setItUp(AssetManager aM){
                
        // <editor-fold defaultstate="collapsed" desc="Lighting">
        
        AmbientLight al= new AmbientLight();
        al.setColor(ColorRGBA.Blue);
        //Main.app.getRoot().addLight(al);
        
        DirectionalLight dl = new DirectionalLight();
        //Fap around here to change the overall color 
        dl.setColor(ColorRGBA.Orange);
        dl.setDirection(new Vector3f(-1, -1, -1));
        // Comment this out to remove the overall color (make everything black/white)
        Main.app.getRoot().addLight(dl);
        
        pl = new PointLight();
        //Mess around with this to change the color of the moving light
        pl.setColor(ColorRGBA.White);
        pl.setPosition(new Vector3f(-100, 4, 0));
        Main.app.getRoot().addLight(pl);
        
        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="Player">
        Game.player = aM.loadModel("Models/Reeaper.j3o");          
        Game.player.scale(0.3f);
        Game.player.setLocalTranslation(0, 0.5f, 0);
        Game.player.rotate(FastMath.QUARTER_PI, FastMath.PI, 0);
        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="Materials">
        
        modelmat = new Material(aM, 
                "Common/MatDefs/Light/Lighting.j3md");
        //modelmat.setBoolean("VTangent",true); 
        //modelmat.setBoolean("Minnaert",true);
        
        //modelmat.setTexture("DiffuseMap", aM.loadTexture("Textures/chessboard.png")); 
        //modelmat.setTexture("SpecularMap", aM.loadTexture("Textures/chessboard.png")); 
        //modelmat.setTexture("NormalMap", aM.loadTexture("Textures/chessboard.png")); 
        //modelmat.setTexture("ParallaxMap", aM.loadTexture("Textures/chessboard.png")); 
        
        red = new Material(aM, 
                "Common/MatDefs/Misc/Unshaded.j3md");  
        
        blu = red.clone();
        grn = red.clone();
        
        red.setColor("Color", ColorRGBA.Red);
        blu.setColor("Color", ColorRGBA.Blue);
        grn.setColor("Color", ColorRGBA.Green);
        //-----------------
        
        plane = aM.loadModel("Models/plane.j3o");
        
        // </editor-fold>  
        
        // <editor-fold defaultstate="collapsed" desc="Planes/Cells">
        
        Game.extent = ((BoundingBox) plane.getWorldBound()).getExtent(new Vector3f());
        
        plane = aM.loadModel("Models/plane.j3o");
        
        plane.setMaterial(modelmat);       

        //Main.app.getRoot().attachChild(plane);
        
        Game.game.cells = new ArrayList<Cell>();        
        
        for(int i = 0; i < 9; i++)
        {
            Cell c = new Cell();
            c.position = new Vector3f(0,0, -i*(Game.extent.z*2));            
            c.model = plane.clone();
            //c.model.setLocalTranslation(c.position);
            c.index = i;
            Main.app.getRootNode().attachChild(c.model);            
            Game.game.cells.add(c);
        } 

       
           
        // </editor-fold>  
        
    }
}
