package mygame;

// DONT TOUCH THIS ITS GOOD AS IT IS

import com.jme3.app.SimpleApplication;
import com.jme3.font.BitmapText;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.post.FilterPostProcessor;
import com.jme3.post.filters.FogFilter;
import com.jme3.renderer.Camera;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Node;
import com.jme3.system.AppSettings;

public class Main extends SimpleApplication {
   
    public static Main app;    
    private FilterPostProcessor fpp;
    private FogFilter fog;
    
    public static void main(String[] args) {
        app = new Main();
        AppSettings settings = new AppSettings(true);
        settings.setFrameRate(30);
        settings.setTitle("GraveRunner");
        app.setSettings(settings);
        app.start();
    }
    
    public Node getRoot(){return rootNode;}  
    
    public Camera getCam(){return this.cam;} 
    Game g;

    @Override
    public void simpleInitApp() {        
        g = new Game();
        
        Setup.setItUp(assetManager, rootNode);
        this.flyCam.setEnabled(false);
        this.cam.setFrustumFar(75);
        this.cam.setLocation(new Vector3f(0,3,7));
        this.cam.lookAt(new Vector3f(0, 2, 2), new Vector3f(0,1,0));
        g.inputManager = inputManager;
        g.rootNode = rootNode;
        g.gameInit();
        
        Controls.Setup();
        
        fpp=new FilterPostProcessor(assetManager);
        fog=new FogFilter();
        fog.setFogColor(new ColorRGBA(0.0f, 0.0f, 0.0f, 1.0f));
        fog.setFogDistance(155);
        fog.setFogDensity(2.0f);
        fpp.addFilter(fog);
        
        viewPort.addProcessor(fpp);
        
        guiFont = assetManager.loadFont("Interface/Fonts/Default.fnt");
        BitmapText helloText = new BitmapText(guiFont, false);
        helloText.setSize(guiFont.getCharSet().getRenderedSize());
        helloText.setText("Hello World");
        helloText.setLocalTranslation(300, helloText.getLineHeight(), 0);
        guiNode.attachChild(helloText);
        
        Game.devText = helloText;
    } 
    
    @Override
    public void simpleUpdate(float tpf) {
        g.gameUpdate(tpf);
    }

    @Override
    public void simpleRender(RenderManager rm) {        
    }
}
