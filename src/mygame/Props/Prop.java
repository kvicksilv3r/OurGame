package mygame.Props;

import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;

public class Prop {
    public Spatial model;  
    //public int index;
    public mygame.Cell c;
    public Vector3f offsetPos;
    public BoundingBox propBox;
    
    // Declares what object it is. 1 for tombstone, 2 for tree etcetc
    public int type;
}
