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

package de.bps.ims.qti.repository.handlers;

import java.util.ArrayList;
import java.util.List;

import org.olat.presentation.framework.layout.fullWebApp.LayoutMain3ColsController;
import org.olat.presentation.framework.core.UserRequest;
import org.olat.presentation.framework.core.control.Controller;
import org.olat.presentation.framework.core.control.WindowControl;
import org.olat.presentation.framework.core.control.generic.layout.MainLayoutController;
import org.olat.core.id.OLATResourceable;
import org.olat.core.logging.AssertException;
import org.olat.presentation.ims.qti.editor.AddNewQTIDocumentController;
import org.olat.ims.qti.fileresource.TestFileResource;
import org.olat.lms.ims.qti.process.AssessmentInstance;
import org.olat.lms.ims.qti.process.ImsRepositoryResolver;
import org.olat.lms.ims.qti.process.Resolver;
import org.olat.ims.qti.repository.handlers.QTITestHandler;
import org.olat.modules.iq.IQPreviewSecurityCallback;
import org.olat.modules.iq.IQSecurityCallback;
import org.olat.repository.RepositoryEntry;
import org.olat.repository.controllers.AddFileResourceController;
import org.olat.repository.controllers.IAddController;
import org.olat.repository.controllers.RepositoryAddCallback;
import org.olat.repository.controllers.RepositoryAddController;
import org.olat.repository.controllers.WizardCloseResourceController;

import de.bps.onyx.plugin.OnyxModule;
import de.bps.onyx.plugin.run.OnyxRunController;

/**
 * Initial Date: Apr 6, 2004
 * 
 * @author Mike Stock Comment:
 */
public class QTITestHandlerOnyx extends QTITestHandler {
	private static final boolean LAUNCHEABLE = true;
	private static final boolean DOWNLOADEABLE = true;
	private static final boolean EDITABLE = true;

	static List supportedTypes;

	/**
	 * Default construcotr.
	 */
	public QTITestHandlerOnyx() {
		super();
	}

	/**
	 * @see org.olat.repository.handlers.RepositoryHandler#getSupportedTypes()
	 */
	@Override
	public List getSupportedTypes() {
		return supportedTypes;
	}

	static { // initialize supported types
		supportedTypes = new ArrayList(1);
		supportedTypes.add(TestFileResource.TYPE_NAME);
	}

	/**
	 * @see org.olat.repository.handlers.RepositoryHandler#supportsLaunch()
	 */
	@Override
	public boolean supportsLaunch(final RepositoryEntry repoEntry) {
		return LAUNCHEABLE;
	}

	/**
	 * @see org.olat.repository.handlers.RepositoryHandler#supportsDownload()
	 */
	@Override
	public boolean supportsDownload(final RepositoryEntry repoEntry) {
		return DOWNLOADEABLE;
	}

	/**
	 * @see org.olat.repository.handlers.RepositoryHandler#supportsEdit()
	 */
	@Override
	public boolean supportsEdit(final RepositoryEntry repoEntry) {
		if (OnyxModule.isOnyxTest(repoEntry.getOlatResource())) { return false; }
		return EDITABLE;
	}

	@Override
	public MainLayoutController createLaunchController(final OLATResourceable res, final String initialViewIdentifier, final UserRequest ureq,
			final WindowControl wControl) {
		return (MainLayoutController) getLaunchController(res, ureq, wControl);
	}

	/**
	 * @param res
	 * @param ureq
	 * @param wControl
	 * @return Controller
	 */
	@Override
	public Controller getLaunchController(final OLATResourceable res, final UserRequest ureq, final WindowControl wControl) {

		if (OnyxModule.isOnyxTest(res)) {
			final Resolver resolver = new ImsRepositoryResolver(res);
			final IQSecurityCallback secCallback = new IQPreviewSecurityCallback();
			final Controller runController = new OnyxRunController(ureq, wControl, res);
			// IQManager.getInstance().createIQDisplayController(res, resolver, AssessmentInstance.QMD_ENTRY_TYPE_SELF, secCallback, ureq, wControl);
			// use on column layout
			final LayoutMain3ColsController layoutCtr = new LayoutMain3ColsController(ureq, wControl, null, null, runController.getInitialComponent(), null);
			layoutCtr.addDisposableChildController(runController); // dispose content on layout dispose
			return layoutCtr;
		} else {
			return super.getLaunchController(res, ureq, wControl);
		}
	}

	/**
	 * @see org.olat.repository.handlers.RepositoryHandler#getEditorController(org.olat.core.id.OLATResourceable org.olat.presentation.framework.UserRequest,
	 *      org.olat.presentation.framework.control.WindowControl)
	 */
	@Override
	public Controller createEditorController(final OLATResourceable res, final UserRequest ureq, final WindowControl wControl) {

		if (OnyxModule.isOnyxTest(res)) {
			return null;
		} else {
			return super.createEditorController(res, ureq, wControl);
		}

		// TestFileResource fr = new TestFileResource();
		// fr.overrideResourceableId(res.getResourceableId());
		//
		// //check if we can edit in restricted mode -> only typos
		// ReferenceManager refM = ReferenceManager.getInstance();
		// List referencees = refM.getReferencesTo(res);
		// //String referencesSummary = refM.getReferencesToSummary(res, ureq.getLocale());
		// //boolean restrictedEdit = referencesSummary != null;
		// QTIEditorMainController editor = new QTIEditorMainController(referencees,ureq, wControl, fr);
		// if (editor.isLockedSuccessfully()) {
		// return editor;
		// } else {
		// return null;
		// }
	}

	/**
	 * @see org.olat.repository.handlers.RepositoryHandler#getAddController(org.olat.repository.controllers.RepositoryAddCallback, java.lang.Object,
	 *      org.olat.presentation.framework.UserRequest, org.olat.presentation.framework.control.WindowControl)
	 */
	@Override
	public IAddController createAddController(final RepositoryAddCallback callback, final Object userObject, final UserRequest ureq, final WindowControl wControl) {
		if (userObject == null || userObject.equals(RepositoryAddController.PROCESS_ADD)) {
			return new AddFileResourceController(callback, supportedTypes, new String[] { "zip" }, ureq, wControl);
		} else {
			return new AddNewQTIDocumentController(AssessmentInstance.QMD_ENTRY_TYPE_ASSESS, callback, ureq, wControl);
		}
	}

	@Override
	protected String getDeletedFilePrefix() {
		return "del_qtitest_";
	}

	@Override
	public WizardCloseResourceController createCloseResourceController(final UserRequest ureq, final WindowControl wControl, final RepositoryEntry repositoryEntry) {
		throw new AssertException("not implemented");
	}

}
