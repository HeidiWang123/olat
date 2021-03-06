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

package org.olat.presentation.examples.guidemo;

import org.apache.log4j.Logger;
import org.olat.presentation.examples.GUIDemoMainController;
import org.olat.presentation.framework.core.UserRequest;
import org.olat.presentation.framework.core.components.Component;
import org.olat.presentation.framework.core.components.panel.Panel;
import org.olat.presentation.framework.core.components.velocity.VelocityContainer;
import org.olat.presentation.framework.core.control.WindowControl;
import org.olat.presentation.framework.core.control.controller.BasicController;
import org.olat.system.event.Event;
import org.olat.system.logging.log4j.LoggerHelper;

public class GuiDemoController extends BasicController {
    // how to create a logger
    private static final Logger log = LoggerHelper.getLogger();

    Panel p;
    VelocityContainer vcMain;

    public GuiDemoController(final UserRequest ureq, final WindowControl wControl) {
        super(ureq, wControl);
        setBasePackage(GUIDemoMainController.class);
        // simple Text as first node in the guidemo tree
        vcMain = createVelocityContainer("guidemo");
        p = putInitialPanel(vcMain);
    }

    @Override
    @SuppressWarnings("unused")
    protected void event(final UserRequest ureq, final Component source, final Event event) {
        // no events to catch
    }

    @Override
    protected void doDispose() {
        // no resources to dispose
    }

}
