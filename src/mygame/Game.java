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
    
    public ArrayList<Cell> cells;
    
    public Game() {
        Game.game = this;
    }
    
    public void gameInit() {
        player.setMaterial(Setup.modelmat);
        rootNode.attachChild(player);
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
        /*if(!goingUp && !goingDown)
        {
        goingUp = true;        
        sins = 0.55f; 
        }*/          
            thispos -= 1;
            Setup.pl.setPosition(new Vector3f(thatpos,height, thispos));
        }
        
        if (name.equals("LEFT")) {         
          //player.setLocalTranslation(v.x - value*speed, v.y, v.z);
            thatpos -= 1;
            Setup.pl.setPosition(new Vector3f(thatpos,height, thispos));
        }
        
        if (name.equals("RIGHT")) {          
         // player.setLocalTranslation(v.x + value*speed, v.y, v.z);
            thatpos += 1;
            Setup.pl.setPosition(new Vector3f(thatpos,height,thispos));
        }
        
        if (name.equals("DOWN")) {          
         /* if(!goingDown && !goingUp)
        {
        goingDown = true;        
        sins = 0.55f; 
        }
        */
            thispos += 1;
            Setup.pl.setPosition(new Vector3f(thatpos,height, thispos));
        }
      }
    };

    public void gameUpdate(float tpf) {
       
        timer += tpf;
        
        if(timer > 1) { timer = 0; }
        
        for(Cell c : cells)          
        {            
            c.model.setLocalTranslation(c.position);
            c.position.z += extent.z * tpf;
            
            
            if(c.model.getLocalTranslation().z > extent.z*2)
            {
                c.model.setLocalTranslation(0,0,-(cells.size()-1)*extent.z*2);
                c.last = true;
                
                //This is experimental
                Spawner.spawnProps();
            }
            
            c.position = c.model.getLocalTranslation();
            
        }         
    }
}
