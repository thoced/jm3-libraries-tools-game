/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utils.game.Trigger;

import com.utils.game.Trigger.TriggerControl;
import java.util.EventListener;

/**
 *
 * @author Thonon
 */
public interface TriggerListener extends EventListener
{
    void triggerOn(TriggerControl tc);
    void triggerOff(TriggerControl tc);
}
