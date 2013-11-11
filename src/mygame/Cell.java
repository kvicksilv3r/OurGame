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
    public static Spatial model;
    public static int index;
    // index can be used as an offset aswell as being able to move objects in relativity to a specific cell
    public static Vector3f position;
}
