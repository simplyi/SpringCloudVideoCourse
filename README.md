## README

ANSI terminal coloring wasn't working for in Intellj Idea. To enable it environment variable can be used:
`spring.output.ansi.enabled=always`

JCE comes bundled with JDK11 so we don't need to install it separately:
`https://www.oracle.com/java/technologies/javase/jdk11-readme.html`

To run rabbitmq I use docker image instead of installing it manually. Pulling an image:

`docker pull rabbitmq:management`

Then running:

`docker run –d --hostname my-rabbit --name some-rabbit –p 15672:15672 –p 5672:5672 rabbitmq:management`

**Other useful docker commands.**

Show all running containers:

`docker ps`

And then stop container:

`docker stop YOUR_CONTAINER_ID`

Or stop all running containers:

`docker stop $(docker ps -aq)`

Before trying running anything in Docker use for each project:

`mvn clean install`

Check compose file for syntax-errors:

`docker-compose config`

Build our images, create the defined containers, and start in detached mode via one command:
`docker-compose up --build -d`

To stop the containers, remove them from Docker and remove the connected networks from it:

`docker-compose down`





