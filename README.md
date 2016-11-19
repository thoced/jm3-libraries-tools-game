# jm3-libraries-tools-game
A tool library for the JMonkeyEngine game engine /  Une librairie d'outils pour le moteur de jeu JMonkeyEngine

## TriggerControl:


The TriggerControl class triggers an event picked up by a listener. TriggerControl is attached to a Spatial Empty created from the Blender software. An actor is then attached to the TriggerControl and when the actor object enters the trigger area, an event is sent to all objects that implement TriggerListener. When the actor leaves the TriggerControl area. Another event is also sent.

La classe TriggerControl permet de déclencher un évenement capté par un listener. TriggerControl est attaché à un Spatial Empty créé depuis le logiciel Blender. Un acteur est attaché ensuite au TriggerControl et lorsque l'objet acteur entre dans la zone du trigger, un evenement est envoyé à tous les objets qui implémentent TriggerListener. Lorsque l'acteur sort de la zone du TriggerControl. Un autre évenement est également envoyé.


### code example for use TriggerControl

``` 
Spatial triggerSpatial = scene.getChild("Trigger01"); // Obtaining the space with the name 'Trigger01'
TriggerControl tc = new TriggerControl("Trigger01");
tc.setActor(Actor);             // Actor is a spatial object that will trigger the event
tc.addTriggerListener(this);    // Adding the listener
triggerSpatial.addControl(tc);  // Adding the controler to the Spatial Node
```

### code example for use TriggerListener

``` 
 public void triggerOn(TriggerControl tc)
 {
     // Code to execute when the actor enters the trigger.
 }

 public void triggerOff(TriggerControl tc) 
 {
     // Code to execute when the actor exits the trigger.    
 }
  ``` 
### Creating the Empty Trigger with the blender software

![Image Add](https://github.com/thoced/jm3-libraries-tools-game/blob/master/src/images/blender-add-empty.png)

![Image Scene](https://github.com/thoced/jm3-libraries-tools-game/blob/master/src/images/blender-trigger.png)

![Image Scene-graph](https://github.com/thoced/jm3-libraries-tools-game/blob/master/src/images/blender-scene.png)

