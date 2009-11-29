package org.xwiki.command.commands;

import java.util.Formatter;
import java.util.List;

import org.xwiki.command.Command;
import org.xwiki.component.annotation.Component;
import org.xwiki.component.manager.ComponentManager;
import org.xwiki.rest.Utils;

import com.xpn.xwiki.api.XWiki;

/**
 * <p>
 * This command lists all the spaces present in a wiki
 * </p>
 */
@Component("ls")
public class LS implements Command
{
    public String execute(ComponentManager componentManager, String... arguments) throws Exception
    {
        XWiki xwikiApi = Utils.getXWikiApi(componentManager);

        List<String> spaces = xwikiApi.getSpaces();

        Formatter f = new Formatter();
        for (String space : spaces) {
            f.format("%s\n", space);
        }

        return f.toString();
    }
}
