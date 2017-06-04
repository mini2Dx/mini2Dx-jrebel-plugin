# mini2Dx-jrebel-plugin

A JRebel plugin for mini2Dx that listens to class reloading events and provides the following:
 * Re-layout of UiContainer instances when UI classes are reloaded
 
Usage
-------------------

First add the dependency to your buildscript section to ensure the plugin is downloaded:

```
buildscript {
    repositories {
		....
    }
    dependencies {
    	....
    	classpath "org.mini2Dx:mini2Dx-jrebel-plugin:1.0.0"
    	....
    }
```

Then, when running the game, add the following as JVM parameters:

__Note:__ Change the path to the correct path of your .m2 folder (usually in your home directory)

```
-Drebel.plugins=/PATH/TO/HOME/DIR/.m2/repository/org/mini2Dx/mini2Dx-jrebel-plugin/1.0.0/mini2Dx-jrebel-plugin-1.0.0.jar -Drebel.mini2Dx-jrebel-plugin=true
```
