/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

/**
 *
 * @author stoscheline2150
 */
public class Spawner {


    public static void spawnProps(int index)
    {
        //Creates a new box, assigns a material, gives ít an offsetposition, number that tells there if will be in 
        // relativity to the cells, it gives the box the same index as the cell it belongs to
        
        Boxes b = new Boxes();
        b.geom.setMaterial(Setup.modelmat);
        b.offsetPos = new Vector3f(FastMath.nextRandomFloat() * 2*Game.extent.x, 2, FastMath.nextRandomFloat()* 2 * Game.extent.z);
        b.index = index;
        Game.game.boxs.add(b);
        Main.app.getRootNode().attachChild(b.geom);        
    }
    
}
