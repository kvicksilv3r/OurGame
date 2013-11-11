package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Node;
import com.jme3.system.AppSettings;


public class Main extends SimpleApplication {

    public static Main app;    
    
    public static void main(String[] args) {
        app = new Main();
        AppSettings settings = new AppSettings(true);
        settings.setFrameRate(30);
        settings.setTitle("GraveRunner");
        app.setSettings(settings);
        app.start();
    }
    
    public Node getRoot(){return rootNode;}   
    Game g;

    @Override
    public void simpleInitApp() {
                
        settings.setFrameRate(30);        
        app.setSettings(settings);
        
        g = new Game();
        
        Setup.setItUp(assetManager);        
        this.flyCam.setEnabled(false);
        this.cam.setLocation(new Vector3f(0,2,6));
        this.cam.lookAt(new Vector3f(0, 0.5f, 0), new Vector3f(0,1,0));         
        g.inputManager = inputManager;
        g.rootNode = rootNode;
        g.simpleInitApp();
    }

    @Override
    public void simpleUpdate(float tpf) {
        g.gameUpdate(tpf);
    }

    @Override
    public void simpleRender(RenderManager rm) {        
    }
}
