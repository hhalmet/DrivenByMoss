// Written by Jürgen Moßgraber - mossgrabers.de
// (c) 2017-2022
// Licensed under LGPLv3 - http://www.gnu.org/licenses/lgpl-3.0.txt

package de.mossgrabers.framework.controller.valuechanger;

/**
 * Callback interface for getting notified about the relative knob sensitivity.
 *
 * @author J&uuml;rgen Mo&szlig;graber
 */
@FunctionalInterface
public interface ISensitivityCallback
{
    /**
     * Called when the knob sensitivity has changed.
     */
    void knobSensitivityHasChanged ();
}
