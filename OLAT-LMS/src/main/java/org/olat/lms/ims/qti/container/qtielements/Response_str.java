/**
 * OLAT - Online Learning and Training<br>
 * http://www.olat.org
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); <br>
 * you may not use this file except in compliance with the License.<br>
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing,<br>
 * software distributed under the License is distributed on an "AS IS" BASIS, <br>
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. <br>
 * See the License for the specific language governing permissions and <br>
 * limitations under the License.
 * <p>
 * Copyright (c) since 2004 at Multimedia- & E-Learning Services (MELS),<br>
 * University of Zurich, Switzerland.
 * <p>
 */

package org.olat.lms.ims.qti.container.qtielements;

import org.dom4j.Element;
import org.olat.system.exception.AssertException;

/**
 * Initial Date: 24.11.2004
 * 
 * @author Mike Stock
 */
public class Response_str extends GenericQTIElement {

    /**
     * Comment for <code>xmlClass</code>
     */
    public static final String xmlClass = "response_str";

    /**
     * Comment for <code>RCARDINALITY_SINGLE</code>
     */
    public static final int RCARDINALITY_SINGLE = 0;
    /**
     * Comment for <code>RCARDINALITY_MULTIPLE</code>
     */
    public static final int RCARDINALITY_MULTIPLE = 1;
    /**
     * Comment for <code>RCARDINALITY_ORDERED</code>
     */
    public static final int RCARDINALITY_ORDERED = 2;

    private final String ident;
    private int rCardinality = RCARDINALITY_SINGLE;
    private boolean rTiming = false;

    /**
     * @param el_response_lid
     */
    public Response_str(final Element el_response_lid) {
        super(el_response_lid);

        ident = el_response_lid.attributeValue("ident");
        if (ident == null) {
            throw new AssertException("Element with missing ident.");
        }

        // get cardinality
        final String sRCardinality = el_response_lid.attributeValue("rcardinality");
        if (sRCardinality != null) {
            if (sRCardinality.equals("Single")) {
                rCardinality = RCARDINALITY_SINGLE;
            } else if (sRCardinality.equals("Multiple")) {
                rCardinality = RCARDINALITY_MULTIPLE;
            } else if (sRCardinality.equals("Ordered")) {
                rCardinality = RCARDINALITY_ORDERED;
            } else {
                throw new AssertException("Invalid rcardinality on element " + ident);
            }
        }

        // get rtiming
        final String sRTiming = el_response_lid.attributeValue("rtiming");
        if (sRTiming != null) {
            if (sRTiming.equals("Yes")) {
                rTiming = true;
            } else if (sRTiming.equals("No")) {
                rTiming = false;
            } else {
                throw new AssertException("Invalid rtiming on element " + ident);
            }
        }
    }

    /**
     * @return rCardinality
     */
    public int getRCardinality() {
        return rCardinality;
    }

    /**
     * @return rTiming
     */
    public boolean getRTiming() {
        return rTiming;
    }

    /**
	 */
    @Override
    public void render(final StringBuilder buffer, final RenderInstructions ri) {
        ri.put(RenderInstructions.KEY_RESPONSE_RCARDINALITY, new Integer(rCardinality));
        ri.put(RenderInstructions.KEY_RESPONSE_IDENT, getQTIIdent());
        super.render(buffer, ri);
    }
}
