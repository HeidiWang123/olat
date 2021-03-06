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

package org.olat.presentation.framework.core.components.form.flexible.elements;

/* TODO: ORID-1007 'File' */
import java.io.File;
import java.io.InputStream;
import java.util.Set;

import org.olat.data.commons.vfs.VFSContainer;
import org.olat.data.commons.vfs.VFSLeaf;
import org.olat.presentation.framework.core.components.form.flexible.FormMultipartItem;

/**
 * <h3>Description:</h3>
 * <p>
 * The FileElement represents a file within a flexi form. It offers a read only view of files and an upload view.
 * <p>
 * Initial Date: 08.12.2008 <br>
 * 
 * @author Florian Gnaegi, frentix GmbH, http://www.frentix.com
 */

public interface FileElement extends FormMultipartItem {
    public static final int UPLOAD_ONE_MEGABYTE = 1024 * 1024; // 1 Meg
    public static final int UPLOAD_UNLIMITED = -1;

    /**
     * Set an initial value for the file element. Optional. Use this to preload your file element with a previously submitted file.
     * 
     * @param initialFile
     */
    public void setInitialFile(File initialFile);

    /**
     * Get the initial file value
     * 
     * @return the file or NULL if not set
     */
    public File getInitialFile();

    /**
     * Set the KB that are allowed in the file upload. In case the user uploads too much, the error with the given key will be displayed.<br />
     * Use UPLOAD_UNLIMITED to set no limit.
     * 
     * @param maxFileSizeKB
     *            max file size in KB
     * @param i18nErrKey
     *            i18n key used in case user uploaded to big file
     * @param i18nArgs
     *            optional arguments for thei18nErrKey
     */
    public void setMaxUploadSizeKB(int maxFileSizeKB, String i18nErrKey, String[] i18nArgs);

    /**
     * Set a mime type limitation on which files are allowed in the upload process. Wildcards like image/* are also allowed.
     * 
     * @param mimeTypes
     * @param i18nErrKey
     *            i18n key used in case user uploaded wrong files
     * @param i18nArgs
     *            optional arguments for thei18nErrKey
     */
    public void limitToMimeType(Set<String> mimeTypes, String i18nErrKey, String[] i18nArgs);

    /**
     * Get the set of the mime types limitation
     * 
     * @return Set containing mime types. Can be empty but is never NULL.
     */
    public Set<String> getMimeTypeLimitations();

    /**
     * Set this form element mandatory.
     * 
     * @param mandatory
     *            true: is mandatory; false: is optional
     * @param i18nErrKey
     *            i18n key used in case user did not upload something
     */
    public void setMandatory(boolean mandatory, String i18nErrKey);

    //
    // Methods that are used when a file has been uploaded

    /**
     * @return true: file has been uploaded; false: file has not been uploaded
     */
    public boolean isUploadSuccess();

    /**
     * This is a security requirement: all successful file uploads must be logged.
     * <p>
     * This is called after the client decides that the uploaded file is OK (e.g. not too large, file name is not already in use, etc.) and will be kept. <br>
     * The logging must be called by the client controller, else the logger doesn't have enough information for logging. <br>
     * 
     */
    public void logUpload();

    /**
     * Get the size of the uploaded file
     * 
     * @return
     */
    public long getUploadSize();

    /**
     * @return The filename of the uploaded file
     */
    public String getUploadFileName();

    /**
     * @return The mime type of the uploaded file
     */
    public String getUploadMimeType();

    /**
     * Use the upload file only for temporary checks on the file. Use the moveUploadFileTo() to move the file to the final destination. The temp file will be deleted on
     * form disposal.
     * 
     * @return A reference to the uploaded file
     */
    public File getUploadFile();

    /**
     * Get the input stream of the uploaded file to copy it to some other place
     * 
     * @return
     */
    public InputStream getUploadInputStream();

    /**
     * Move the uploaded file from the temporary location to the given destination directory.
     * <p>
     * If in the destination a file with the given name does already exist, rename the file accordingly
     * <p>
     * Whenever possible use the second moveUploadFileTo method that takes a VFSContainer as an argument instead of the file.
     * 
     * @param destinationDir
     * @return A reference to the moved file or NULL if file could not be moved
     */
    public File moveUploadFileTo(File destinationDir);

    /**
     * Move the uploaded file from the temporary location to the given destination VFSContainer.
     * <p>
     * If in the destination a leaf with the given name does already exist, rename the leaf accordingly
     * <p>
     * The method optimizes for containers of type LocalFolderImpl in which case the file is moved. In other cases the content is copied via the file input stream.
     * 
     * @param destinationContainer
     * @return A reference of the new leaf file or NULL if the file could not be created
     */
    public VFSLeaf moveUploadFileTo(VFSContainer destinationContainer);

}
