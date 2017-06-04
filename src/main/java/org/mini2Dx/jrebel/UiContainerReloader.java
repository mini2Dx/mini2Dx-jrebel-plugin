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

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.zeroturnaround.javarebel.LoggerFactory;

/**
 * Triggers re-layout of all UiContainer instances when a UI class is reloaded
 */
public class UiContainerReloader {
	private final List<Class> uiClasses = new ArrayList<Class>();
	private Class uiContainerClass = null;
	
	private void initUiClasses() throws ClassNotFoundException {
		if(!uiClasses.isEmpty()) {
			return;
		}
		uiClasses.add(Class.forName("org.mini2Dx.ui.UiContainer"));
		uiContainerClass = uiClasses.get(0);
		
		uiClasses.add(Class.forName("org.mini2Dx.ui.element.UiElement"));
		uiClasses.add(Class.forName("org.mini2Dx.ui.render.RenderNode"));
		
		uiClasses.add(Class.forName("org.mini2Dx.ui.listener.ActionListener"));
		uiClasses.add(Class.forName("org.mini2Dx.ui.listener.HoverListener"));
		uiClasses.add(Class.forName("org.mini2Dx.ui.listener.ScreenSizeListener"));
		uiClasses.add(Class.forName("org.mini2Dx.ui.listener.ScrollListener"));
		uiClasses.add(Class.forName("org.mini2Dx.ui.listener.TextAnimationListener"));
		uiClasses.add(Class.forName("org.mini2Dx.ui.listener.UiContainerListener"));
		uiClasses.add(Class.forName("org.mini2Dx.ui.listener.UiEffectListener"));
		
		uiClasses.add(Class.forName("org.mini2Dx.ui.animation.TextAnimation"));
		uiClasses.add(Class.forName("org.mini2Dx.ui.effect.UiEffect"));
		uiClasses.add(Class.forName("org.mini2Dx.ui.layout.LayoutRuleset"));
		uiClasses.add(Class.forName("org.mini2Dx.ui.layout.LayoutState"));
		uiClasses.add(Class.forName("org.mini2Dx.ui.layout.OffsetRule"));
		uiClasses.add(Class.forName("org.mini2Dx.ui.layout.SizeRule"));
		uiClasses.add(Class.forName("org.mini2Dx.ui.navigation.UiNavigation"));
		uiClasses.add(Class.forName("org.mini2Dx.ui.style.StyleRule"));
	}

	public void triggerReload(int eventType, Class clazz) {
		try {
			initUiClasses();
			
			for(Class uiClass : uiClasses) {
				if(uiClass.isAssignableFrom(clazz)) {
					Method relayoutMethod = uiContainerClass.getMethod("relayoutAllUiContainers");
					if(relayoutMethod == null) {
						continue;
					}
					relayoutMethod.setAccessible(true);
					relayoutMethod.invoke(null);
					return;
				}
			}
		} catch (Exception e) {
			LoggerFactory.getInstance().error(e);
		}
	}
}
