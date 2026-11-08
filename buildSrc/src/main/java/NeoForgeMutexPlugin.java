import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.provider.Provider;
import org.gradle.api.services.BuildServiceRegistry;

public class NeoForgeMutexPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        BuildServiceRegistry sharedServices = project.getGradle().getSharedServices();

        Provider<NeoForgeMutex> mutex = sharedServices.registerIfAbsent(
            "createMinecraftArtifactsMutex",
            NeoForgeMutex.class,
            spec -> {
                spec.getMaxParallelUsages().set(1);
            }
        );

        // Attach the mutex to every createMinecraftArtifacts task
        project.getTasks().configureEach(task -> {
            if ("createMinecraftArtifacts".equals(task.getName())) {
                task.usesService(mutex);
            }
        });
    }
}