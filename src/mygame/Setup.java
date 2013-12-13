package mygame;

//Fixes all the shit that just needs to be done once, like setting up light and 
//positioning the character

import com.jme3.asset.AssetManager;
import com.jme3.bounding.BoundingBox;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.light.PointLight;
import com.jme3.material.Material;
import com.jme3.material.RenderState.BlendMode;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.post.FilterPostProcessor;
import com.jme3.renderer.queue.RenderQueue.Bucket;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import com.jme3.texture.Texture;
import com.jme3.util.SkyFactory;
import java.util.ArrayList;
import java.util.List;
import com.jme3.post.filters.FogFilter;

public class Setup {
    
    //public static Spatial player;
    public static Spatial plane;
    public static Material modelmat;
    public static Material playermat;
    public static Texture player;
    public static Material red;
    public static Material blu;
    public static Material grn;
    public static PointLight pl;
    public static Spatial lastplane; 
    public static Texture playerTex;
    
    public static void setItUp(AssetManager aM, Node localRootNode){
                
        // <editor-fold defaultstate="collapsed" desc="Lighting">
        
        AmbientLight al= new AmbientLight();
        al.setColor(ColorRGBA.Blue);
        //Main.app.getRoot().addLight(al);
        
        DirectionalLight dl = new DirectionalLight();
        //Fap around here to change the overall color 
        dl.setColor(ColorRGBA.Orange);
        dl.setDirection(new Vector3f(-1, -1, -1));
        // Comment this out to remove the overall color (make everything black/white)
        localRootNode.addLight(dl);
        
        pl = new PointLight();
        //Mess around with this to change the color of the moving light
        pl.setColor(ColorRGBA.White);
        pl.setPosition(new Vector3f(-100, 4, 0));
        //localRootNode.addLight(pl);
        
        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="Player">
        Game.player = new Player();
        Game.player.model = aM.loadModel("Models/SUP.obj");          
        Game.player.model.scale(0.3f);
        Game.player.model.setLocalTranslation(0, 1.5f, 0);       
        Game.player.model.rotate(FastMath.QUARTER_PI, FastMath.PI, 0);
        //Game.player.setMaterial(modelmat);        
        
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
        
        playerTex = aM.loadTexture("Textures/texture_grim_fin.png");
        
        playermat = new Material(aM, 
                "Common/MatDefs/Misc/Unshaded.j3md"); 
        
        playermat.setTexture("ColorMap", playerTex);

        blu = red.clone();
        grn = red.clone();
        
        red.setColor("Color", ColorRGBA.Red);
        blu.setColor("Color", ColorRGBA.Blue);
        grn.setColor("Color", ColorRGBA.Green);
        //-----------------
        
        // </editor-fold>  
        
        // <editor-fold defaultstate="collapsed" desc="Model Loader">
        
        plane = aM.loadModel("Models/plane.j3o");
        mygame.Props.Props.tombstone1 = aM.loadModel("Models/Tombstone1.j3o");
        mygame.Props.Props.ghost = aM.loadModel("Models/ghost.j3o");
        mygame.Props.Props.tree = aM.loadModel("Models/tree.j3o");
        
        // </editor-fold>  
        
        // <editor-fold defaultstate="collapsed" desc="Props">
        
        Game.game.props = new ArrayList<mygame.Props.Prop>();
        mygame.Props.Props.tombstone1.scale(0.5f);
        mygame.Props.Props.ghost.scale(0.5f);
        mygame.Props.Props.tree.scale(0.5f);
        
        
        // </editor-fold>        
        
        // <editor-fold defaultstate="collapsed" desc="Planes/Cells">
        localRootNode.attachChild(SkyFactory.createSky(
            aM, "Textures/clrs.jpg", true));
        
        Game.extent = ((BoundingBox) plane.getWorldBound()).getExtent(new Vector3f());
        
        plane.setMaterial(modelmat);
        plane.scale(1.5f, 1, 1);
        
        lastplane = plane.clone();
        lastplane.setMaterial(blu);
        lastplane.setLocalTranslation(0, 0, -Game.extent.z*10);
        lastplane.rotate(FastMath.QUARTER_PI, 0, 0);
        localRootNode.attachChild(lastplane);
                
        Game.game.cells = new ArrayList<Cell>();
        
        for(int i = 0; i < 7; i++)
        {
            Cell c = new Cell();
            c.position = new Vector3f(0,0, -(Game.extent.z*2)*i);
            c.model = plane.clone();
            c.index = i;  
            
            if(i == 2 || i == 5 || i == 8)
            {
                c.model.setMaterial(red);
            }
            else if( i == 1 || i == 4 || i == 7)
            {
                c.model.setMaterial(grn);
            }
            else{
                c.model.setMaterial(blu);
            }
            
            localRootNode.attachChild(c.model);            
            Game.game.cells.add(c);
        }
        // </editor-fold>  
        
        Spawner.spawnProps(6, localRootNode);      
       
        
    }
}
