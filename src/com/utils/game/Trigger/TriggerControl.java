/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utils.game.Trigger;

import com.jme3.bounding.BoundingBox;
import com.jme3.bounding.BoundingVolume;
import com.jme3.export.InputCapsule;
import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.export.OutputCapsule;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Transform;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.VertexBuffer;
import com.jme3.scene.control.AbstractControl;
import com.jme3.scene.control.Control;
import com.jme3.scene.shape.Box;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Thonon
 */
public class TriggerControl extends AbstractControl {
    //Any local variables should be encapsulated by getters/setters so they
    //appear in the SDK properties window and can be edited.
    //Right-click a local variable to encapsulate it with getters and setters.

    private Spatial actor;
    private Collection<TriggerListener> collections = new ArrayList<TriggerListener>();
    private boolean OnTriggerIsDone = false;
    private boolean debug = false;
    private String name;
    
    public TriggerControl(String name)
    {
        this.name = name;
    }
  
    public void addTriggerListener(TriggerListener tl)
    {
        collections.add(tl);
    }
    
    public void subTriggerListener(TriggerListener tl)
    {
        collections.remove(tl);
    }
    
    public void clear()
    {
        collections.clear();
    }
    
    public int size()
    {
        return collections.size();
    }
    
    @Override
    protected void controlUpdate(float tpf) 
    {
       if(actor != null)
       {
           BoundingBox b = new BoundingBox(this.getSpatial().getLocalTranslation(),
                   this.getSpatial().getLocalScale().x,
                   this.getSpatial().getLocalScale().y,
                   this.getSpatial().getLocalScale().z);
         
           
             if(b.intersects(actor.getWorldBound()))
             {
                if(!OnTriggerIsDone)
                {
                    for(TriggerListener tl : collections)
                    {
                        tl.triggerOn(this); // appel triggerOn sur les objets attachés
                    }
                    
                     OnTriggerIsDone = true;
                }
                          
             }
             else
                 if(OnTriggerIsDone)
                 {
                      for(TriggerListener tl : collections)
                    {
                        tl.triggerOff(this); // appel triggerOff sur les objets attachés
                    }
                       OnTriggerIsDone = false;
                 }
       }
    }
    
    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) 
    {
      
    }
    
    public Control cloneForSpatial(Spatial spatial) {
        TriggerControl control = new TriggerControl(this.getName());
        //TODO: copy parameters to new Control
        return control;
    }
    
    @Override
    public void read(JmeImporter im) throws IOException {
        super.read(im);
        InputCapsule in = im.getCapsule(this);
        //TODO: load properties of this Control, e.g.
        //this.value = in.readFloat("name", defaultValue);
    }
    
    @Override
    public void write(JmeExporter ex) throws IOException {
        super.write(ex);
        OutputCapsule out = ex.getCapsule(this);
        //TODO: save properties of this Control, e.g.
        //out.write(this.value, "name", defaultValue);
    }

    /**
     * @return the actor
     */
    public Spatial getActor() {
        return actor;
    }

    /**
     * @param actor the actor to set
     */
    public void setActor(Spatial actor) {
        this.actor = actor;
    }

    /**
     * @return the debug
     */
    public boolean isDebug() {
        return debug;
    }

    /**
     * @param debug the debug to set
     */
    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    
}
