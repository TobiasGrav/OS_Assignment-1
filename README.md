# OS_Assignment-1
Project with a purpose of demonstrating the efficiency of threading

How to run:
This is a maven project, just clone this 
repository and run the code in your preffered IDE.

Observation:
When creating a single threaded server it was only able to handle requests
in a sequential order. Since the server has to wait for the first process request to
finish before handeling any other requests, this is of course a very slow
way of doing it.

However when we created a multi-threaded server which created a new thread for every
request, it was able to take requests at the same time as it was processing the last 
request. By doing it this way it was able to handle many requests simultaneously making
the server very fast and efficient.

In this assignment we have learned the use of threading in a 
program as a way of understanding the efficacy of using threads, 
when running programs as they allow us to run multiple codes at 
once in the same application/program. The way this works is that
our computers have multiple processing cores which can handle multiple
operations at once.
