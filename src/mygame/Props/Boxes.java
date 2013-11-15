package mygame.Props;



import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;

public class Boxes {
    
    Box b = new Box(0.5f,0.5f,0.5f);
    
    public  Geometry geom = new Geometry("Box", b);
    
    public  int index;
    // index can be used as an offset aswell as being able to move objects in relativity to a specific cell
    public Vector3f offsetPos;


}
