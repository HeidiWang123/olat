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

package org.olat.lms.activitylogging;

import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.olat.system.logging.log4j.LoggerHelper;

/**
 * Initial Date: 01.12.2009 <br>
 * 
 * @author bja
 */
public class LogModule {

    private static final Logger log = LoggerHelper.getLogger();

    /**
     * Anonymous logging is disabled by default.
     */
    private static boolean logAnonymous;
    /**
     * List of operator keys like lt, le, ...
     */
    private static List<String> operators;

    /**
     * User properties like firstName, lastName, etc to be filled from ConfigurationManager.
     */
    private static Set<String> userProperties;

    private LogModule(boolean logAnonymous, List<String> logOperators) {
        LogModule.logAnonymous = logAnonymous;
        log.info("Log Module set logAnonymous to: " + new Boolean(logAnonymous));
        operators = logOperators;
    }

    /**
     * is anonymous logging configured
     * 
     * @return boolean
     */
    public static boolean isLogAnonymous() {
        return logAnonymous;
    }

    /**
     * @param userProperties
     */
    public static void setUserProperties(Set<String> userProperties) {
        LogModule.userProperties = userProperties;
        log.info("Log Module set userProperties to: " + userProperties);
    }

    /**
     * @return
     */
    public static Set<String> getUserProperties() {
        return userProperties;
    }

    /**
     * The position can be set to 1..n. The value 0 means that the list userProperties doesn't contain the value of attribute.
     * 
     * @param attribute
     * @return
     */
    public static int getUserPropertyPosition(String attribute) {
        int position = 0;
        if (userProperties.contains(attribute)) {
            int i = 0;
            for (String userProperty : userProperties) {
                if (userProperty.equals(attribute)) {
                    position = i + 1;
                    break;
                }
                i++;
            }
        }
        return position;
    }

    public static List<String> getOperatorKeys() {
        return operators;
    }

}
