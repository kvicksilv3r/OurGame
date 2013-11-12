/*
 * This is just the Cell class. Not much here to be explained
 */
package mygame;

import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;

/**
 *
 * @author stoscheline2150
 */
public class Cell {
    public  Spatial model;
    public  int index;
    // index can be used as an offset aswell as being able to move objects in relativity to a specific cell
    public  Vector3f position;
}
