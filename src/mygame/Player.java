/*
 * We dont need this class right now, might need to do i later
 * 
 */
package mygame;

import com.jme3.bounding.BoundingBox;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;

/**
 *
 * @author stoscheline2150
 */
public class Player {
    public Vector3f position;
    public Spatial model;
    //public static Vector3f playerExtent;
    public static Vector3f playerExtent;
    
    //placeholders for the placement and size for the player characters boundingbox
    public Vector3f plbboxv3f = new Vector3f(0,0,0);
    public float plx;
    public float ply;
    public float plz;
    
    public BoundingBox PlayerBox = new BoundingBox(plbboxv3f, plx, ply, plz);
}
