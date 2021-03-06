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
 * Copyright (c) frentix GmbH<br>
 * http://www.frentix.com<br>
 * <p>
 */
package org.olat.data.commons.vfs.version;

import java.io.InputStream;
import java.util.List;

import org.olat.data.basesecurity.Identity;
import org.olat.data.commons.vfs.VFSContainer;
import org.olat.data.commons.vfs.VFSItem;
import org.olat.data.commons.vfs.VFSLeaf;
import org.olat.system.commons.manager.BasicManager;

/**
 * Description:<br>
 * <P>
 * Initial Date: 21 sept. 2009 <br>
 * 
 * @author srosse
 */
public abstract class VersionsManager extends BasicManager {

    protected static VersionsManager INSTANCE;

    /**
     * access via spring
     * 
     * @return
     */
    @Deprecated
    public static VersionsManager getInstance() {
        return INSTANCE;
    }

    /**
     * Get or create the versions datas of this file
     * 
     * @param a
     *            file
     * @return
     */
    public abstract Versions createVersionsFor(VFSLeaf leaf);

    /**
     * Return the list of deleted files in this container.
     * 
     * @param container
     * @return
     */
    public abstract List<Versions> getDeletedFiles(VFSContainer container);

    /**
     * Only used internally
     * 
     * @param versions
     * @return
     */
    public abstract String getNextRevisionNr(Versions versions);

    /**
     * Add a new version of the file. The current version will be saved and secured, The new version replaced the old one.
     * 
     * @param currentVersion
     * @param author
     * @param comment
     * @param newVersion
     * @return
     */
    public abstract boolean addVersion(Versionable currentVersion, Identity author, String comment, InputStream newVersion);

    public abstract boolean addToRevisions(Versionable currentVersion, Identity author, String comment);

    /**
     * Move a versioned file to the target container
     * 
     * @param currentVersion
     * @param target
     *            container
     * @return
     */
    public abstract boolean move(Versionable currentVersion, VFSContainer container);

    /**
     * Restore a versioned file to the selected revision. The current version is secured before being replaced by the revision's file
     * 
     * @param currentVersion
     * @param version
     * @return
     */
    public abstract boolean restore(Versionable currentVersion, VFSRevision version, String comment);

    /**
     * Restore a revision in the target container, usefull to restore deleted files
     * 
     * @param target
     *            container
     * @param selected
     *            revision
     * @return
     */
    public abstract boolean restore(VFSContainer container, VFSRevision revision);

    /**
     * Delete a list of revisions from a file
     * 
     * @param currentVersion
     * @param revisionsToDelete
     * @return
     */
    public abstract boolean deleteRevisions(Versionable currentVersion, List<VFSRevision> revisionsToDelete);

    /**
     * Delete and remove from versioning a list of deleted versions (files)
     * 
     * @param versions
     * @return
     */
    public abstract boolean deleteVersions(List<Versions> versions);

    /**
     * Delete a full container
     * 
     * @param container
     * @param force
     *            , if true delete it definitely (the deleted files don't appear in the list of deleted files)
     * @return
     */
    public abstract boolean delete(VFSItem item, boolean force);

    /**
     * Rename a file and propagate the change to the version.
     * 
     * @param item
     * @param newname
     * @return
     */
    public abstract boolean rename(VFSItem item, String newname);

}
