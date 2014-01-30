/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.bounding.BoundingBox;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;

/**
 *
 * @author stoscheline2150
 */
public class Spawner {

    public static int wts;
    public static boolean forrest = false;
    
    public static void spawnProps(Cell c, Node localRootNode)
    {
        //Creates a new box, assigns a material, gives ít an offsetposition, number that tells there if will be in 
        // relativity to the cells, it gives the box the same index as the cell it belongs to
                
        for(int i = 0; i < FastMath.nextRandomInt(1, 4); i++)
        {
            wts = FastMath.nextRandomInt(1, 5);
        
            mygame.Props.Prop p = new mygame.Props.Prop();
            mygame.Props.Props d = new mygame.Props.Props();
            p.offsetPos = new Vector3f(FastMath.nextRandomFloat() * 2*Game.extent.x, 0, FastMath.nextRandomFloat()* 2 * Game.extent.z);
            //p.index = index;
            p.c = c;
            
            
            
            Game.game.props.add(p);
            
            switch(wts)
            {
                case 1:
                    p.model = mygame.Props.Props.tombstone1.clone();
                    p.PropBox = new BoundingBox (p.offsetPos, d.tombstoneExtent.x, d.tombstoneExtent.y, d.tombstoneExtent.z);
                    break;
                case 2:
                    p.model = mygame.Props.Props.ghost.clone();
                    p.PropBox = new BoundingBox (p.offsetPos, d.ghostExtent.x, d.ghostExtent.y, d.ghostExtent.z);
                    break;
                case 3:
                    p.model = mygame.Props.Props.tree.clone();
                    p.PropBox = new BoundingBox (p.offsetPos, d.treeExtent.x, d.treeExtent.y, d.treeExtent.z);
                    break;
                case 4:
                    p.model = mygame.Props.Props.ropetree.clone();
                    p.PropBox = new BoundingBox (p.offsetPos, d.ropetreeExtent.x, d.ropetreeExtent.y, d.ropetreeExtent.z);
                    break;
                case 5:
                    p.model = mygame.Props.Props.eye.clone();
                    p.PropBox = new BoundingBox (p.offsetPos, d.eyeExtent.x, d.eyeExtent.y, d.eyeExtent.z);
                    break;
                default:
                    //raise exception?
                    break;
            }
            
            localRootNode.attachChild(p.model);
        }
        
        // <editor-fold defaultstate="collapsed" desc=""Forrest"">                
        
        //Makes the forrest surrounding the level 
        if(forrest)
        {
            for(int i = 0; i<2; i++)
            {
                 for(int j = 0; j<5; j++)
                 {
                    mygame.Props.Prop p = new mygame.Props.Prop();
                    p.model = mygame.Props.Props.tree.clone();
                    p.model.scale(2);
                    p.offsetPos = new Vector3f(
                        ((Game.extent.x + 2) * 2 *i - 2),
                        0,
                        j * ((Game.extent.z*2)/5)
                    );
                    //p.index = index;
                    p.c = c;
                    p.model.rotate(
                        0,
                        FastMath.nextRandomFloat()*2*FastMath.PI,
                        0
                    );
                    Game.game.props.add(p);
                    localRootNode.attachChild(p.model); 
                 }
             }
        }
        
        // </editor-fold>
            
      }
        
        
 }
    

