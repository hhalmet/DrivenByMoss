// Written by Jürgen Moßgraber - mossgrabers.de
// (c) 2017-2018
// Licensed under LGPLv3 - http://www.gnu.org/licenses/lgpl-3.0.txt

package de.mossgrabers.push.mode.track;

import de.mossgrabers.framework.controller.display.Display;
import de.mossgrabers.framework.controller.display.Format;
import de.mossgrabers.framework.daw.IChannelBank;
import de.mossgrabers.framework.daw.IModel;
import de.mossgrabers.framework.daw.data.ITrack;
import de.mossgrabers.push.PushConfiguration;
import de.mossgrabers.push.controller.DisplayMessage;
import de.mossgrabers.push.controller.PushControlSurface;


/**
 * Mode for editing a volume parameter of all tracks.
 *
 * @author J&uuml;rgen Mo&szlig;graber
 */
public class VolumeMode extends AbstractTrackMode
{
    /**
     * Constructor.
     *
     * @param surface The control surface
     * @param model The model
     */
    public VolumeMode (final PushControlSurface surface, final IModel model)
    {
        super (surface, model);
    }


    /** {@inheritDoc} */
    @Override
    public void onValueKnob (final int index, final int value)
    {
        this.model.getCurrentTrackBank ().getTrack (index).changeVolume (value);
    }


    /** {@inheritDoc} */
    @Override
    public void onValueKnobTouch (final int index, final boolean isTouched)
    {
        this.isKnobTouched[index] = isTouched;

        final IChannelBank tb = this.model.getCurrentTrackBank ();
        final ITrack t = tb.getTrack (index);
        if (!t.doesExist ())
            return;

        if (isTouched)
        {
            if (this.surface.isDeletePressed ())
                t.resetVolume ();
            else
                this.surface.getDisplay ().notify ("Volume: " + t.getVolumeStr (8));
        }

        t.touchVolume (isTouched);
        this.checkStopAutomationOnKnobRelease (isTouched);
    }


    /** {@inheritDoc} */
    @Override
    public void updateDisplay1 ()
    {
        final Display d = this.surface.getDisplay ();
        final IChannelBank tb = this.model.getCurrentTrackBank ();
        final PushConfiguration config = this.surface.getConfiguration ();
        for (int i = 0; i < 8; i++)
        {
            final ITrack t = tb.getTrack (i);
            d.setCell (0, i, t.doesExist () ? "Volume" : "").setCell (1, i, t.getVolumeStr (8));
            if (t.doesExist ())
                d.setCell (2, i, config.isEnableVUMeters () ? t.getVu () : t.getVolume (), Format.FORMAT_VALUE);
            else
                d.clearCell (2, i);
        }
        d.done (0).done (1).done (2);

        this.drawRow4 ();
    }


    /** {@inheritDoc} */
    @Override
    public void updateDisplay2 ()
    {
        this.updateChannelDisplay (DisplayMessage.GRID_ELEMENT_CHANNEL_VOLUME, true, false);
    }
}