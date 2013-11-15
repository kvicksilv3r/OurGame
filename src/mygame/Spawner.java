/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

/**
 *
 * @author stoscheline2150
 */
public class Spawner {

    public static int wts;
    public static int spawntree = 10;
    public static boolean forrest = true;

    public static void spawnProps(int index)
    {
        //Creates a new box, assigns a material, gives ít an offsetposition, number that tells there if will be in 
        // relativity to the cells, it gives the box the same index as the cell it belongs to
                
        wts = FastMath.nextRandomInt(1, 3);
        
        if(wts == 1)
        {
        mygame.Props.Prop p = new mygame.Props.Prop();
        p.model = mygame.Props.Props.tombstone1.clone();
        p.offsetPos = new Vector3f(FastMath.nextRandomFloat() * 2*Game.extent.x, 0, FastMath.nextRandomFloat()* 2 * Game.extent.z);
        p.index = index;
        Game.game.props.add(p);
        Main.app.getRootNode().attachChild(p.model);  
        }
        
        else if (wts == 2)
        {
        mygame.Props.Prop p = new mygame.Props.Prop();
        p.model = mygame.Props.Props.ghost.clone();
        p.offsetPos = new Vector3f(FastMath.nextRandomFloat() * 2*Game.extent.x, 0, FastMath.nextRandomFloat()* 2 * Game.extent.z);
        p.index = index;
        Game.game.props.add(p);
        Main.app.getRootNode().attachChild(p.model);  
        }
        
        else if (wts == 3)
        {
        mygame.Props.Prop p = new mygame.Props.Prop();
        p.model = mygame.Props.Props.tree.clone();
        p.offsetPos = new Vector3f(FastMath.nextRandomFloat() * 2*Game.extent.x, 0, FastMath.nextRandomFloat()* 2 * Game.extent.z);
        p.index = index;    
        Game.game.props.add(p);
        Main.app.getRootNode().attachChild(p.model);  
        }
        
        // <editor-fold defaultstate="collapsed" desc=""Forrest"">                
        
        //Makes the forrest surrounding the level 
        if(forrest)
        {
        for(int i = 0; i<2; i++)
         {
             for(int o = 0; o<10; o++)
             {
         mygame.Props.Prop p = new mygame.Props.Prop();
         p.model = mygame.Props.Props.tree.clone();
         p.offsetPos = new Vector3f((Game.extent.x * 2 *i) ,0, o * ((Game.extent.z*2)/10));
         p.index = index;
         p.model.rotate(0, FastMath.nextRandomFloat()*2*FastMath.PI, 0);
         Game.game.props.add(p);
         Main.app.getRootNode().attachChild(p.model); 
             }
         }
        }
        
        // </editor-fold>
            
      }
        
        
 }
    

