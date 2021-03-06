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

package org.olat.data.catalog;

import org.olat.data.basesecurity.SecurityGroup;
import org.olat.data.commons.database.CreateInfo;
import org.olat.data.commons.database.Persistable;
import org.olat.data.repository.RepositoryEntry;
import org.olat.system.commons.resource.OLATResourceable;

/**
 * Description: <br>
 * A CatalogEntry represents either a category or an alias to a linked repository entry. The distinction is made by using the <code>TYPE_XXX</code> attributes. <br>
 * Further the catalog entries together build up the catalog, which is a tree structure. This tree structure is implemented by having each catalog entry pointing to its
 * parent.
 * 
 * @author Felix Jost
 */
public interface CatalogEntry extends CreateInfo, Persistable, OLATResourceable {
    /**
     * define a catalog entry as a node <code>TYPE_NODE</code>
     */
    public final static int TYPE_NODE = 0;
    /**
     * define a catalog entry as a leaf <code>TYPE_LEAF</code>
     */
    public final static int TYPE_LEAF = 1;

    /**
     * getter
     * 
     * @return String
     */
    public abstract String getDescription();

    /**
     * setter
     * 
     * @param description
     */
    public abstract void setDescription(String description);

    /**
     * getter
     * 
     * @return String
     */
    public abstract String getName();

    /**
     * setter
     * 
     * @param name
     */
    public abstract void setName(String name);

    /**
     * getter
     * 
     * @return RepositoryEntry
     */
    public abstract RepositoryEntry getRepositoryEntry();

    /**
     * setter
     * 
     * @param repositoryEntry
     */
    public abstract void setRepositoryEntry(RepositoryEntry repositoryEntry);

    /**
     * getter
     * 
     * @return SecurityGroup
     */
    public abstract SecurityGroup getOwnerGroup();

    /**
     * getter
     * 
     * @param ownerGroup
     */
    public abstract void setOwnerGroup(SecurityGroup ownerGroup);

    /**
     * getter
     * 
     * @return int
     */
    public abstract int getType();

    /**
     * setter
     * 
     * @param type
     */
    public abstract void setType(int type);

    /**
     * getter for an external URL pointer of ths catalogentry
     * 
     * @return String
     */
    public abstract String getExternalURL();

    /**
     * setter for an external URL pointer of ths catalogentry
     * 
     * @param externalURL
     */
    public abstract void setExternalURL(String externalURL);

    /**
     * parent node of this catalog entry
     * 
     * @param parent
     */
    public void setParent(CatalogEntry parent);

    /**
     * parent node of this catalog entry
     * 
     * @return CatalogEntry
     */
    public CatalogEntry getParent();
}
