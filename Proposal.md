# Final Project Proposal
### Topic: User Preference Study and Song Feeding

In our final project, we are going to implement track one which is explained in [instructions](/instructions/project.pdf). Specifically, we are going to implement music feeding algorithms, which can study user performances, profile user listening preferences, and finally feed new songs to user.

There are two components of our program. The first component is datasets which includes user playlist, server-side playlist, and user preference data. User playlist the csv file which stores the songs from server-side playlist that user likes. Server-side playlist is the csv file which contains all playable songs. The second component is the java code. There will be a main class which will read the user playlist, and print the feed list at the end. There will also be a study class which will scale the like degree, and finally get a list of genre. The third class is searching class which will use searching algorithm to locate each genre. Finally there will be a feeding class which will randomly choose songs from the list, and according to like degree to sort the feed list.

Here is the mind map:
![Mind map](/images/MindMap.jpg)

In this program, we would use a searching algorithm to locate specific genres, a priority queue to sort the feeding list, and a array to store playlists.

Here are some source I think can give us some hint:
- []()
- []()
- []()
- []()
