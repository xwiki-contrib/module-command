package org.xwiki.command;

import org.xwiki.component.annotation.ComponentRole;
import org.xwiki.component.manager.ComponentManager;

/**
 * <p>
 * This is the command interface. Each command should implement this interface and be annotated with a @Component
 * annotation for declaring its name. Don't forget to add the FQN of the command in the META-INF/components.txt
 * otherwise it will not be visible.
 * </p>
 */
@ComponentRole
public interface Command
{
    /**
     * <p>
     * Execute the command
     * </p>
     * 
     * @param componentManager The XWiki component manager for looking up the XWiki components needed for executing the
     *            command.
     * @param arguments The arguments extracted from the command line.
     * @return A String containing the result of the execution of the command.
     * @throws Exception
     */
    String execute(ComponentManager componentManager, String... arguments) throws Exception;
}
