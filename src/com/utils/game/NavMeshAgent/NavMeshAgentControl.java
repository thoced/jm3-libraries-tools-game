/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utils.game.NavMeshAgent;

import com.jme3.ai.navmesh.NavMeshPathfinder;
import com.jme3.ai.navmesh.Path.Waypoint;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.export.InputCapsule;
import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.export.OutputCapsule;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.AbstractControl;
import com.jme3.scene.control.Control;
import java.io.IOException;

/**
 *
 * @author Thonon
 */
public class NavMeshAgentControl extends AbstractControl {
    //Any local variables should be encapsulated by getters/setters so they
    //appear in the SDK properties window and can be edited.
    //Right-click a local variable to encapsulate it with getters and setters.

    private CharacterControl characterControl;
    private NavMeshPathfinder navi;
    private float speed = 1.0f;
    private float precision = 2.0f;
    
    public NavMeshAgentControl(){}
    
    public NavMeshAgentControl(CharacterControl characterControl,NavMeshPathfinder navi,float speed,float precision)
    {
        this.characterControl = characterControl;
        this.navi = navi;
        this.speed = speed;
        this.precision = precision;
    }
    
    @Override
    protected void controlUpdate(float tpf)
    {
       characterControl.setWalkDirection(Vector3f.ZERO);
       this.getSpatial().setLocalTranslation(characterControl.getPhysicsLocation());
        
        if(navi != null && characterControl != null)
        {
            Waypoint point = getNavi().getNextWaypoint();
            
            if(point == null)
                return;
            // calcul du vecteur de direction
            Vector3f vDir = point.getPosition().subtract(this.getSpatial().getLocalTranslation());
            // application du déplacement
            characterControl.setWalkDirection(vDir.normalize().mult(getSpeed() * tpf));
            // on verifie si on est pas arrivé au point
            if(getCharacterControl().getPhysicsLocation().distance(point.getPosition()) < getPrecision() && !navi.isAtGoalWaypoint())
            {
                getNavi().goToNextWaypoint();
            }
            
            if(getNavi().isAtGoalWaypoint())
                getNavi().clearPath();
          
        }
        
       
    }
    
    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
        //Only needed for rendering-related operations,
        //not called when spatial is culled.
    }
    
    public Control cloneForSpatial(Spatial spatial) {
        NavMeshAgentControl control = new NavMeshAgentControl();
        control.setCharacterControl(characterControl);
        control.setNavi(navi);
        control.setPrecision(precision);
        control.setSpeed(speed);
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
     * @return the characterControl
     */
    public CharacterControl getCharacterControl() {
        return characterControl;
    }

    /**
     * @param characterControl the characterControl to set
     */
    public void setCharacterControl(CharacterControl characterControl) {
        this.characterControl = characterControl;
    }

    /**
     * @return the navi
     */
    public NavMeshPathfinder getNavi() {
        return navi;
    }

    /**
     * @param navi the navi to set
     */
    public void setNavi(NavMeshPathfinder navi) {
        this.navi = navi;
    }

    /**
     * @return the speed
     */
    public float getSpeed() {
        return speed;
    }

    /**
     * @param speed the speed to set
     */
    public void setSpeed(float speed) {
        this.speed = speed;
    }

    /**
     * @return the precision
     */
    public float getPrecision() {
        return precision;
    }

    /**
     * @param precision the precision to set
     */
    public void setPrecision(float precision) {
        this.precision = precision;
    }
}
