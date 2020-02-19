package org.minbox.framework.command.execute;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.*;
import org.codehaus.plexus.util.cli.Commandline;

import java.io.File;
import java.util.List;

/**
 * Command execute mojo
 * Use Maven "mvn exec:exec" to execute configured commands
 *
 * @author 恒宇少年
 */
@Mojo(name = "execute", threadSafe = true, defaultPhase = LifecyclePhase.COMPILE)
@Execute(phase = LifecyclePhase.COMPILE)
public class CommandExecuteMojo extends AbstractMojo {

    @Parameter(defaultValue = "false")
    private boolean skip;

    @Parameter(required = true)
    private String executable;

    @Parameter
    private List<?> arguments;

    @Parameter(readonly = true, required = true, defaultValue = "${basedir}")
    private File basedir;

    @Parameter(readonly = true, required = true, defaultValue = "${project.build.directory}")
    private File buildDirectory;

    @Parameter(defaultValue = "false")
    private boolean async;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info("输出了..." + skip + ", " + executable);
        getLog().info(basedir.getPath());
        getLog().info(buildDirectory.getPath());
        try {
            Commandline commandline = new Commandline("npm run-script build");
            commandline.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
