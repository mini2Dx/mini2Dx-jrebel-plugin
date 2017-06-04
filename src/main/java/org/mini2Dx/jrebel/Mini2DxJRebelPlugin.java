/**
 * MIT License
 * 
 * Copyright (c) 2017 Thomas Cashman
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.mini2Dx.jrebel;

import org.zeroturnaround.javarebel.ClassEventListener;
import org.zeroturnaround.javarebel.ClassResourceSource;
import org.zeroturnaround.javarebel.Plugin;
import org.zeroturnaround.javarebel.ReloaderFactory;

/**
 * Implements the JRebel plugin API
 */
public class Mini2DxJRebelPlugin implements Plugin {
	private static final String ID = "mini2Dx-jrebel-plugin";
	private static final String NAME = "mini2Dx JRebel Plugin";
	private static final String DESCRIPTION = "JRebel plugin for mini2Dx";
	private static final String AUTHOR = "Thomas Cashman";
	private static final String WEBSITE = "https://mini2dx.org";

	@Override
	public boolean checkDependencies(ClassLoader classLoader, ClassResourceSource classResourceSource) {
		return classResourceSource.getClassResource("org.mini2Dx.ui.UiContainer") != null;
	}

	@Override
	public void preinit() {
		final UiContainerReloader uiContainerReloader = new UiContainerReloader();
		
		ReloaderFactory.getInstance().addClassReloadListener(new ClassEventListener() {
			public void onClassEvent(int eventType, Class clazz) {
				uiContainerReloader.triggerReload(eventType, clazz);
			}

			@Override
			public int priority() {
				return 0;
			}
		});
	}

	@Override
	public String getSupportedVersions() {
		return null;
	}

	@Override
	public String getTestedVersions() {
		return null;
	}

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public String getDescription() {
		return DESCRIPTION;
	}

	@Override
	public String getAuthor() {
		return AUTHOR;
	}

	@Override
	public String getWebsite() {
		return WEBSITE;
	}
}
