package org.xwiki.command;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.xwiki.component.annotation.Component;
import org.xwiki.rest.XWikiResource;

/**
 * <p>
 * This class exposes a new REST resource rooted at /command (relative to the base XWiki REST servlet URI). It accepts
 * only POST requests in text/plain and sends text/plain responses. The request contain a string that is interpreted as
 * if it were a command line. The response contains the execution of the command if successful.
 * </p>
 * <p>
 * Commands are XWiki components that implements the Command interface. The hint of the component will be considered as
 * the command name.
 * </p>
 */
@Path("/command")
@Component("org.xwiki.command.CommandResource")
public class CommandResource extends XWikiResource
{
    @POST
    @Produces("text/plain")
    @Consumes("text/plain")
    public String post(String commandLine) throws Exception
    {
        Map<String, Command> stringToCommandMap;

        /* Look up all the commands available in the system */
        stringToCommandMap = componentManager.lookupMap(Command.class);

        /*
         * Process the command line. This is just for testing. A more sophisticated for splitting the command line will
         * be needed.
         */
        List<String> components = Arrays.asList(commandLine.split(" "));
        String commandName = components.get(0);
        List<String> arguments = components.subList(1, components.size());

        /* Look for the requested command name in the map. */
        for (String s : stringToCommandMap.keySet()) {
            if (s.equalsIgnoreCase(commandName)) {
                /* If it's found execute it by passing the previously splitted arguments. */
                return stringToCommandMap.get(s).execute(componentManager, arguments.toArray(new String[0]));
            }
        }

        /* If we are here then the requested command doesn't exist. */
        return String.format("Unknown command '%s'\n", commandName);
    }
}
