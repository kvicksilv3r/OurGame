/*
 * This is where the magic happens, mess around with what you want, but
 * don't commit anjything to the git, i don't want to break things even more xD
 */
package mygame;

import com.jme3.bounding.BoundingBox;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import java.util.ArrayList;
import java.util.List;
import static mygame.Game.height;
import static mygame.Game.player;
import static mygame.Game.thatpos;
import static mygame.Game.thispos;
import static mygame.Game.timer;

public class Game {
    
    public static Game game;

    public InputManager inputManager;
    public Node rootNode;
    public static Spatial player;   
    public static Vector3f extent;
    public static float timer;
    public float sinx;
    public Vector3f playerPos;
    public static Vector3f deafaultPos = new Vector3f(0, 1.5f, 0);
    
    public ArrayList<Cell> cells;
    public ArrayList<Boxes> boxs;
    
    public Game() {
        Game.game = this;
    }
    
    public void gameInit() {
        player.setMaterial(Setup.modelmat);
        rootNode.attachChild(player);
        playerPos = player.getLocalTranslation();
        initKeys();
    }
    
    private void initKeys() {
    // You can map one or several inputs to one named action
    inputManager.addMapping("UP",  new KeyTrigger(KeyInput.KEY_UP));
    inputManager.addMapping("LEFT",   new KeyTrigger(KeyInput.KEY_LEFT));
    inputManager.addMapping("RIGHT",  new KeyTrigger(KeyInput.KEY_RIGHT));
    inputManager.addMapping("DOWN", new KeyTrigger(KeyInput.KEY_DOWN));
    // Add the names to the action listener.
    inputManager.addListener(analogListener,"LEFT", "RIGHT", "UP", "DOWN");
    }
    
    boolean goingUp = false;
    boolean goingDown = false;
    boolean goingRight = false;
    boolean goingLeft = false;
    int move = 0;
    
    public static int thispos;
    public static int thatpos;
    public static int height = 4;
    
    
    
    private AnalogListener analogListener = new AnalogListener() {
    public void onAnalog(String name, float value, float tpf) {

        if (name.equals("UP")) {
        if(!goingUp && !goingDown)
        {
        goingUp = true;        
        sinx = 0.55f; 
        }          

        }
        
        if (name.equals("LEFT")) {         
          player.setLocalTranslation(playerPos.x - value * 3, playerPos.y, playerPos.z);

        }
        
        if (name.equals("RIGHT")) {          
         player.setLocalTranslation(playerPos.x + value * 3, playerPos.y, playerPos.z);

        }
        
        if (name.equals("DOWN")) {          
        if(!goingDown && !goingUp)
        {
        goingDown = true;        
        sinx = 0.55f; 
        }
        }
      }
    };

    public void gameUpdate(float tpf) {
       
        playerPos = player.getLocalTranslation(); 
        timer += tpf;
        
        sinx += 0.1;
        
        if(timer > 1) { timer = 0; }
        
        // <editor-fold defaultstate="collapsed" desc="Cell for-loop">
        
        for(Cell c : cells)          
        {            
            //Tell the cells to get to their specified position
            c.model.setLocalTranslation(c.position);
            // Sets the speed of the cells. Its based on cell-length and framerate/time per frame
            c.position.z += extent.z * tpf;
            
            if(c.model.getLocalTranslation().z > extent.z*2)
            {
                for(Boxes b : boxs)
                {
                    if(b.index == c.index)
                    {
                        rootNode.detachChild(b.geom);
                    }
                }
                
                c.model.setLocalTranslation(0,0,-(cells.size()-1)*extent.z*2);
                c.last = true;
                
                //This is experimental
                Spawner.spawnProps(c.index);
            }
            
            c.position = c.model.getLocalTranslation();
            
            for(Boxes b : boxs)
            {
                if(b.index == c.index)
                {
                    b.geom.setLocalTranslation(new Vector3f(c.position.x + b.offsetPos.x - extent.x, c.position.y + b.offsetPos.y, c.position.z + b.offsetPos.z - extent.z));
                }
            }
            
        } 
        
        // </editor-fold>
        
               
        if(goingUp) 
        {
            player.move(0, FastMath.sin(sinx*1.75f)/7,0);
            
            if(playerPos.y < deafaultPos.y)
            {
                goingUp = false;
            }
        }        
        if(goingDown)
        {
            player.move(0, -FastMath.sin(sinx*1.9f)/5,0);
            
            if(playerPos.y > deafaultPos.y)
            {
                goingDown = false;
            }
        }
        

        
    }
}
