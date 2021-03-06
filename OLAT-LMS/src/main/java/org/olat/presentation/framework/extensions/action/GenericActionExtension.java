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
 * frentix GmbH, Switzerland, http://www.frentix.com
 * <p>
 */
package org.olat.presentation.framework.extensions.action;

import java.util.List;
import java.util.Locale;

import org.olat.presentation.framework.core.UserRequest;
import org.olat.presentation.framework.core.control.Controller;
import org.olat.presentation.framework.core.control.WindowControl;
import org.olat.presentation.framework.core.control.creator.AutoCreator;
import org.olat.presentation.framework.core.control.creator.ControllerCreator;
import org.olat.presentation.framework.core.translator.PackageTranslator;
import org.olat.presentation.framework.core.translator.Translator;
import org.olat.presentation.framework.extensions.AbstractExtension;
import org.olat.presentation.framework.extensions.Extension;
import org.olat.presentation.framework.extensions.ExtensionElement;
import org.olat.presentation.framework.extensions.helpers.ExtensionElements;

/**
 * Description:<br>
 * This type of ActionExtension can be used to create menu content nodes together with an implementation of GenericMainController.
 * <P>
 * Initial Date: 03.07.2008 <br>
 * 
 * @author Roman Haag, frentix GmbH, roman.haag@frentix.com
 */
public class GenericActionExtension extends AbstractExtension implements ActionExtension, Extension {

    private ExtensionElements elements = new ExtensionElements();
    private ControllerCreator actionControllerCreator;
    private String i18nActionKey;
    private String i18nDescriptionKey;
    private List<String> extensionPoints;
    private String translationPackageName;
    private String translationPackageNameDerived;
    private Translator translator;
    private String contentControllerClassName;

    protected GenericActionExtension() {
        // only for instantiation by spring
    }

    public void initExtensionPoints() {
        for (String extPoint : extensionPoints) {
            elements.putExtensionElement(extPoint, this);
        }
    }

    /**
	 */
    @Override
    @SuppressWarnings("unused")
    public Controller createController(UserRequest ureq, WindowControl wControl, Object arg) {
        return actionControllerCreator.createController(ureq, wControl);
    }

    @Override
    public ExtensionElement getExtensionFor(String extensionPoint) {
        if (isEnabled())
            return elements.getExtensionElement(extensionPoint);
        else
            return null;
    }

    /**
	 */
    @Override
    public String getActionText(Locale loc) {
        initPackageTranslator(loc);
        if (i18nActionKey == null)
            i18nActionKey = getClassNameOfCorrespondingController() + ".menu.title";
        return translator.translate(i18nActionKey);
    }

    /**
	 */
    @Override
    public String getDescription(Locale loc) {
        initPackageTranslator(loc);
        if (i18nDescriptionKey == null)
            i18nDescriptionKey = getClassNameOfCorrespondingController() + ".menu.title.alt";
        return translator.translate(i18nDescriptionKey);
    }

    private String getClassNameOfCorrespondingController() {
        return contentControllerClassName.substring(contentControllerClassName.lastIndexOf(".") + 1);
    }

    private void initPackageTranslator(Locale loc) {
		if (translator == null || !translator.getLocale().equals(loc)) {
            if (translationPackageName == null) {
                translationPackageName = translationPackageNameDerived;
            }
            translator = new PackageTranslator(translationPackageName, loc);
        }
    }

    /**
     * [used by spring]
     */
    public void setTranslationPackage(String transPackage) {
        translationPackageName = transPackage;
    }

    /**
     * [used by spring] REVIEW:RH:2009-12-19:PB:Fixed problematic cast.
     */
    public void setActionController(AutoCreator actionControllerCreator) {
        this.actionControllerCreator = actionControllerCreator;
        contentControllerClassName = actionControllerCreator.getClassName();
        translationPackageNameDerived = contentControllerClassName.substring(0, contentControllerClassName.lastIndexOf("."));
    }

    public void setActionController(ControllerCreator actionControllerCreator, String contentControllerClassName) {
        this.actionControllerCreator = actionControllerCreator;
        this.contentControllerClassName = contentControllerClassName;
        translationPackageNameDerived = contentControllerClassName.substring(0, contentControllerClassName.lastIndexOf("."));
    }

    protected ControllerCreator getActionController() {
        return actionControllerCreator;
    }

    /**
     * [used by spring]
     */
    public void setI18nActionKey(String i18nActionKey) {
        this.i18nActionKey = i18nActionKey;
    }

    /**
     * [used by spring]
     */
    public void setI18nDescriptionKey(String i18nDescriptionKey) {
        this.i18nDescriptionKey = i18nDescriptionKey;
    }

    /**
     * [used by spring]
     */
    public void setExtensionPoints(List<String> extensionPoints) {
        this.extensionPoints = extensionPoints;
    }

}
