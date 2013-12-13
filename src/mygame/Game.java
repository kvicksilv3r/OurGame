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
import com.jme3.renderer.queue.RenderQueue.Bucket;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import java.util.ArrayList;
import java.util.List;

public class Game {
    
    public static Game game;

    public InputManager inputManager;
    public Node rootNode;
    public static Player player;
    public static Vector3f extent;
    public static float timer;
    public float sinx;
    public static Vector3f defaultPos = new Vector3f(0, 1.5f, 0);
    public static float playerHeight = 1.5f;
    
    public static int currLane = 2;
    public static int nextLane = 2;
    
    public static boolean goingRight = false;
    public static boolean goingLeft = false;    
    
    public Vector3f leftLane;    
    public Vector3f rightLane;
    
    public ArrayList<Cell> cells;
    public ArrayList<mygame.Props.Prop> props;
    
    public Game() {
        Game.game = this;
    }
    
    public void gameInit() {
        player.model.setMaterial(Setup.playermat);
        currLane = 2;
        leftLane = new Vector3f(-extent.x/2, 0, 0);
        rightLane = new Vector3f(extent.x/2, 0, 0);
        rootNode.attachChild(player.model);
        player.position = player.model.getLocalTranslation();
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
    int move = 0;
    
    public static int thispos;
    public static int thatpos;
    
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
                 
        goingLeft = true; 
        goingRight = false;
                
        
        nextLane -= 1;
        
        }
        
        if (name.equals("RIGHT")) {    
       
       goingRight = true;
       goingLeft = false;      
                  
          nextLane += 1;     
        
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
               
        timer += tpf;
        player.model.setLocalTranslation(player.position);
        
        sinx += 0.1;
        
        if(timer > 1) { timer = 0; }
        
        // <editor-fold defaultstate="collapsed" desc="Cell for-loop">
        
        for(Cell c : cells)          
        {            
            //Tell the cells to get to their specified position
            c.model.setLocalTranslation(c.position);
            // Sets the speed of the cells. Its based on cell-length and framerate/time per frame
            c.position.z += extent.z * tpf;
            
            if(c.model.getLocalTranslation().z > extent.z*tpf*40)
            {
                for(mygame.Props.Prop p : props)
                {
                    if(p.index == c.index)
                    {
                        rootNode.detachChild(p.model);
                    }
                }
                
                c.model.setLocalTranslation(0,0,-(cells.size()-1)*extent.z*2);
                c.last = true;
                
                //This is experimental
                Spawner.spawnProps(c.index, rootNode);
            }
            
            c.position = c.model.getLocalTranslation();
            
            for(mygame.Props.Prop p : props)
            {
                if(p.index == c.index)
                {
                    p.model.setLocalTranslation(new Vector3f(c.position.x + p.offsetPos.x - extent.x, c.position.y + p.offsetPos.y, c.position.z + p.offsetPos.z - extent.z));

                }
            }
            
        } 
        
        // </editor-fold>

        
        // <editor-fold defaultstate="collapsed" desc="Player Movement">
                
        if(nextLane > 3)
        {
            nextLane = 3;
        }
        else if(nextLane < 1)
        {
            nextLane = 1;
        }
        
        //-----------------------------------------
                
        if(goingUp) 
        {
            player.model.move(0, FastMath.sin(sinx*1.75f)/7,0);
            
            if(player.position.y <= defaultPos.y)
            {
                goingUp = false;
            }
        }        
        if(goingDown)
        {
            player.model.move(0, -FastMath.sin(sinx*1.9f)/5,0);
            
            if(player.position.y >= defaultPos.y)
            {
                goingDown = false;
            }
        }  
        //-----------------------------------------
            
            if(goingLeft)
            {
                //if(currLane > nextLane)
                //{
                    if(nextLane == 1)
                    {
                        if(player.position.x > leftLane.x)
                        {
                        player.position.x -= (0.52*extent.x)*4*tpf;
                        }
                        else if(player.position.x < leftLane.x)
                        {
                            goingLeft = false;
                            currLane = 1;
                            player.position.x = leftLane.x;
                        }
                    }
                //}
                    
                    if(nextLane == 2)
                    {
                        if(player.position.x > defaultPos.x)
                        {
                        player.position.x -= (0.52*extent.x)*4*tpf;
                        }
                        else if(player.position.x < defaultPos.x)
                        {
                            goingLeft = false;
                            currLane = 2;
                            player.position.x = defaultPos.x;
                        }
                    }
            }
            
            if(goingRight)
            {
                //if(currLane > nextLane)
                //{
                    if(nextLane == 3)
                    {
                        if(player.position.x < rightLane.x)
                        {
                        player.position.x += (0.52*extent.x)*4*tpf;
                        }
                        else if(player.position.x > leftLane.x)
                        {
                            goingRight = false;
                            currLane = 3;
                            player.position.x = rightLane.x;
                        }
                    }
                     
                    if(nextLane == 2)
                    {
                        if(player.position.x < defaultPos.x)
                        {
                        player.position.x += (0.52*extent.x)*4*tpf;
                        }
                        else if(player.position.x > defaultPos.x)
                        {
                            goingRight = false;
                            currLane = 2;
                            player.position.x = defaultPos.x;
                        }
                    }
                //}
            }
            
        
        
        
        // </editor-fold>
    }
}
