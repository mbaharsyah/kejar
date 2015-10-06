package xyz.mbaharsyah.gradle.kejar

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.Copy;

/**
 * @author mbaharsyah
 */
class KejarPlugin implements Plugin<Project> {
    @Override
    def void apply(Project project) {

        // Only create tasks for library project
        if (project.android.hasProperty('libraryVariants')) {

            def outputDir = "${project.buildDir}/outputs/"

            project.android.libraryVariants.all { variant ->

                def taskName = "kejar${variant.name.capitalize()}"
                def baseFilename = "${project.name}-${variant.baseName}"

                project.task(taskName, type: Copy) {
                    description "Generate jar file for ${variant.name} variant"
                    group 'build'
                    inputs.file("${outputDir}/aar/${baseFilename}.aar")
                    outputs.file("${outputDir}/jar/${baseFilename}.jar")

                    from(project.zipTree("${project.buildDir}/outputs/aar/${baseFilename}.aar")) {
                        include 'classes.jar'
                        rename 'classes.jar', "${baseFilename}.jar"
                    }
                    into "${outputDir}/jar"
                }

                def taskToFinalize = ":${project.name}:assemble${variant.name.capitalize()}"
                project.getTasks().findByPath(taskToFinalize).finalizedBy taskName
            }

        }

    }
}
