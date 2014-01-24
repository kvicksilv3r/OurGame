package mygame.Props;

import com.jme3.bounding.BoundingBox;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;

public class Prop {
    public Spatial model;  
    //public int index;
    public mygame.Cell c;
    public Vector3f offsetPos;    
    public Vector3f bboxv3f = new Vector3f(0,0,0);
    
    public float x;
    public float y;
    public float z;
    public BoundingBox PropBox = new BoundingBox(bboxv3f, x, y, z);
    
    
    // Declares what object it is. 1 for tombstone, 2 for tree etcetc
    public int type;
}
