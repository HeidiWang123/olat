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

package org.olat.lms.course.nodes.projectbroker;

import org.olat.data.basesecurity.Identity;
import org.olat.data.course.nodes.projectbroker.Project;
import org.olat.lms.course.nodes.CourseNode;
import org.olat.lms.course.run.environment.CourseEnvironment;
import org.olat.presentation.framework.core.translator.Translator;
import org.olat.system.mail.MailTemplate;
import org.olat.system.mail.MailerResult;

/**
 * @author guretzki
 */

public interface ProjectBrokerMailer {
    /**
     * Send enrolled mail to student.
     * 
     * @param enrolledIdentity
     * @param project
     * @param pT
     * @return
     */
    public MailerResult sendEnrolledEmailToParticipant(Identity enrolledIdentity, Project project, Translator pT);

    /**
     * Send enrolled mail to project managers.
     * 
     * @param enrolledIdentity
     * @param project
     * @param pT
     * @return
     */
    public MailerResult sendEnrolledEmailToManager(Identity enrolledIdentity, Project project, Translator pT);

    /**
     * Send 'cancel enrollment' confirmation email to candidates.
     * 
     * @param identity
     * @param currentProject
     * @param pT
     * @return
     */
    public MailerResult sendCancelEnrollmentEmailToParticipant(Identity identity, Project currentProject, Translator pT);

    /**
     * Send 'cancel enrollment' information email to project-managers.
     * 
     * @param identity
     * @param currentProject
     * @param pT
     * @return
     */
    public MailerResult sendCancelEnrollmentEmailToManager(Identity identity, Project currentProject, Translator pT);

    /**
     * Send 'Project has been changed' email to participants.
     * 
     * @param identity
     * @param project
     * @param pT
     * @return
     */
    public MailerResult sendProjectChangedEmailToParticipants(Identity identity, Project project, Translator pT);

    /**
     * Send 'Deleted Project' email to participants.
     * 
     * @param identity
     * @param project
     * @param pT
     * @return
     */
    public MailerResult sendProjectDeletedEmailToParticipants(Identity identity, Project project, Translator pT);

    /**
	 * Send 'Deleted Project' email to manager.
	 * @param identity
	 * @param project
	 * @param pT
	 * @return
	 */
	public MailerResult sendProjectDeletedEmailToManager(Identity identity, Project project, Translator pT);
	
	/**
	 * Send'Deleted Project' email to accountManagers ( "Themenverantwortliche" / "topic authors" )
	 * 
	 * @param identity
	 * @param project
	 * @param pT
	 * @return
	 */
	public MailerResult sendProjectDeletedEmailToAccountManagers(Identity changer, Project project,CourseEnvironment courseEnv,  CourseNode node , Translator pT);
	
	/**
     * Create mail-template which can be used when a candidate will be removed.
     * 
     * @param project
     * @param projectManager
     * @param pT
     * @return
     */
    public MailTemplate createRemoveAsCandiadateMailTemplate(Project project, Identity projectManager, Translator pT);

    /**
     * Create mail-template which can be used when a candidate will be accepted.
     * 
     * @param project
     * @param projectManager
     * @param pT
     * @return
     */
    public MailTemplate createAcceptCandiadateMailTemplate(Project project, Identity projectManager, Translator pT);

    /**
     * Create mail-template which can be used when a candidate will be added as candidate.
     * 
     * @param project
     * @param projectManager
     * @param translator
     * @return
     */
    public MailTemplate createAddCandidateMailTemplate(Project project, Identity projectManager, Translator translator);

    /**
     * Create mail-template which can be used when a participant will be removed.
     * 
     * @param project
     * @param identity
     * @param translator
     * @return
     */
    public MailTemplate createRemoveParticipantMailTemplate(Project project, Identity identity, Translator translator);

    /**
     * Create mail-template which can be used when a participant will be added as participant.
     * 
     * @param project
     * @param identity
     * @param translator
     * @return
     */
    public MailTemplate createAddParticipantMailTemplate(Project project, Identity identity, Translator translator);
}
