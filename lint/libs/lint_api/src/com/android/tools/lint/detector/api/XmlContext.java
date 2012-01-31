/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.tools.lint.detector.api;

import com.android.annotations.NonNull;
import com.android.annotations.Nullable;
import com.android.tools.lint.client.api.IDomParser;
import com.android.tools.lint.client.api.Lint;
import com.google.common.annotations.Beta;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import java.io.File;

/**
 * A {@link Context} used when checking XML files.
 * <p/>
 * <b>NOTE: This is not a public or final API; if you rely on this be prepared
 * to adjust your code for the next tools release.</b>
 */
@Beta
public class XmlContext extends Context {
    /** The XML parser */
    public IDomParser parser;
    /** The XML document */
    public Document document;

    /**
     * Construct a new {@link XmlContext}
     *
     * @param driver the driver running through the checks
     * @param project the project containing the file being checked
     * @param main the main project if this project is a library project, or
     *            null if this is not a library project. The main project is
     *            the root project of all library projects, not necessarily the
     *            directly including project.
     * @param file the file being checked
     */
    public XmlContext(
            @NonNull Lint driver,
            @NonNull Project project,
            @Nullable Project main,
            @NonNull File file) {
        super(driver, project, main, file);
    }

    /**
     * Returns the location for the given node, which may be an element or an attribute.
     *
     * @param node the node to look up the location for
     * @return the location for the node
     */
    @NonNull
    public Location getLocation(@NonNull Node node) {
        if (parser != null) {
            return parser.getLocation(this, node);
        }

        return Location.create(file);
    }
}