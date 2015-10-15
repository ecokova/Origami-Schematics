# Origami-Schematics

This is a work-in-progress project I began this past summer. It arose out of a desperate need to know whether I had enough paper of a particular shade of blue to be able to create an origami peacock for my friend. (The design I based it on can be seen here: http://denierim.deviantart.com/art/Russian-Peacock-3D-Origami-297821291 ). Since I had never used this schematic before, I did not already have detailed notes on the exact dimensions of the bird and how many pieces of each color I would need. As a result, the only way to determine that was to stare at a photo and meticulously count all the pieces I could see and estimate on the pieces I couldn't see.

There were over 1,000 pieces, and the process was as painful as it sounds. 

To avoid this in the future, I decided to make a simple program that would let me save the details of each schematic in a database. The most important feature is, naturally, the piece counter. An added bonus is that it would also allow me to easily play around with new color combinations without needing to build the entire piece each time.

#####Current Functionality (see dev branch)
The program currently allows the user to create simple rectangular schematics in two ways:
* Defining a width and height and a pattern to be repeated within those dimensions
* Defining a pattern one row at a time, with spaces/underscores as gaps between pieces

Colors can be defined with a Java color string (eg: "GREEN") or three integers representing RGB values.

After the schematic is entered, the user can view it by entering a "viewing option" (numbered between 0 and 35). This is currently implemented in a very crude wait. (eg: Not all 36 numbers map to a valid option, and there is no prompt telling the user to even enter them.)

#####Current Goals
* Fix the above issues
* Replace text-based console with GUI
