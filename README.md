# Gradle Multiloader mod template (fabric/neoforge)

### An easy-to-use template for creating fabric and neoforge mods using gradle for Minecraft 1.20.1 and 1.21+

## Usage
0. **Create your repo** <br>
   Click the "Use this template" button in the top right to create a repository, then clone & import your new repository into IntelliJ idea (or your preferred IDE)

> [!TIP]
> Select "Include all branches" when creating the repository if you want both 1.21+ and 1.20.1

1. **Add mod info** <br>
   Check the gradle.properties file and set your mod id, package, name and version
2. **IDE integration** <br>
   First, import your repo into IDEA and import the gradle project if it isn't importing already (this might take a while). Once done, run the `genSources` task and then use the "download sources" button in your IDE. This ensures you have access to the Minecraft source code in all modules
3. **Rename package** <br>
   In the `common`, `fabric` and `neoforge` modules, refactor the package name from `net.yourpackage.yourmod` to your actual package name. Also adjust the java file names and the `modID` field
4. **Done** <br>
   You can now enjoy modding in a multiloader setup!
   Working run configurations are automatically generated - just select the relevant one in the top right of your IDE window and run the game right from your IDE.

> [!NOTE]
> For 1.20.1 Forge run configurations to show up, run the `genIntellijRuns` task and restart IDEA

## Project structure
A multiloader project consists of a root gradle project and three subprojects: `common`, `fabric` and `neoforge`. <br>
The root project should not contain any code. It's build.gradle.kts file is used for some  common configuration for all the subprojects. <br>
The `common` subproject contains all the **common mod code**, which will be included in all built jars. It has access to all of Minecraft,
and the ability to add Access wideners and mixins (see the comments in it's build script), but no access to any modloader's API.
It's build.gradle.kts is the place to put most of your required dependencies. <br>
The `fabric` and `neoforge` subprojects contain initialisation and **loader-specific code**, as well as loader-specific resources like `fabric.mod.json` and `neoforge.mods.toml` <br>
