/*
 * We dont need this class right now, might need to do i later
 * 
 */
package mygame;

import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;

/**
 *
 * @author stoscheline2150
 */
public class Player {
    public Vector3f position;
    public Spatial model;
    public static Vector3f playerExtent;
    public mygame.Props.BoundingBox playerBbox = new mygame.Props.BoundingBox(position,
            playerExtent.x, playerExtent.y, playerExtent.z);
}
