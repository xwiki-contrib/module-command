This module adds a resource to the XWiki REST subsystem for executing commands.
This resource accepts text/plain POSTs containing a command line and sends back
responses in text/plain containing the result of the execution of the command.

A simple "ls" command which lists the spaces in a wiki is provided.

Clients can send commands using cURL in this way:

curl -d "ls" -H "Content-type: text/plain" http://localhost:8080/xwiki/rest/command
