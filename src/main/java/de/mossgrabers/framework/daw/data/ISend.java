// Written by Jürgen Moßgraber - mossgrabers.de
// (c) 2017-2018
// Licensed under LGPLv3 - http://www.gnu.org/licenses/lgpl-3.0.txt

package de.mossgrabers.framework.daw.data;

/**
 * Interface to a send.
 *
 * @author J&uuml;rgen Mo&szlig;graber
 */
public interface ISend extends IParameter
{
    /**
     * Get the index.
     *
     * @return The index
     */
    int getIndex ();
}