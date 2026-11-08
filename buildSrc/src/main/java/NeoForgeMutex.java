import org.gradle.api.services.BuildService;
import org.gradle.api.services.BuildServiceParameters;

/**
 * This whole thing prevents NeoForge from frying your computer
 * by recompiling Minecraft on multiple versions.
 */
public abstract class NeoForgeMutex implements BuildService<BuildServiceParameters.None> {
    // empty - just used as a mutex
}