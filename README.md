# Vexter - A Top-Down 2D Game

## Project Overview

Vexter is a top-down 2D game created during my high school years. In this game, players control a character using the arrow keys to move around a procedurally generated world composed of rooms. The player can collide with walls, and a complete movement animation was implemented.

Additionally, I built a separate fully functional GUI program for designing individual rooms, their exits, tiles, sprites, dimensions, and how the rooms connect with each other. All this data was stored in a `.rmf` file and parsed when the world was being generated. 

Unfortunately, this is as far as development went due to the increasing demands of school, summer jobs, and eventually university. Despite the limited feature set, Vexter represents a significant personal achievement in game development, as all aspects of the game—art, animation, programming, and gameplay—were created by me.

## Code Recovery and Preservation

When I was younger, I didn't know much about managing source code or using platforms like GitHub. Consequently, the original source code was lost. However, the compiled bytecode (JAR file) somehow survived, and I managed to retrieve the code using the Fernflower decompiler. This recovered code, although not perfect, serves as a snapshot of my early programming efforts. The additional room designer program was lost as well for similar reasons. 

## Features

- **Player Movement**: Control the character using the arrow keys.
- **Procedurally Generated World**: Rooms are generated procedurally, creating a unique experience each time.
- **Collision Detection**: The player can collide with walls.
- **Player Animation**: Basic movement animations are implemented.
- **Room Design Tool**: A separate GUI program allows for the design of individual rooms, including exits, tiles, sprites, and how they connect. This data is saved in `.rmf` files and used during world generation.

## Future Development

While the project has been on hold due to other commitments, I may continue working on it in the future. The possibilities for expanding Vexter are endless, from adding enemies and items to enhancing the procedural generation.

## Open to Contributions

I am open to anyone downloading the code or art to work on the project for personal reasons. However, please do not use it commercially without my permission.

## Running the Project

To run the project, please follow these steps:

1. **Download** the repository ZIP file and extract it to your desired location.
2. **Ensure** that Java and Maven are installed on your system.
3. **Navigate** to the root folder of the project.
4. Run the following commands:

   ```bash
   mvn clean package
   java -jar target/vexter-1.0.jar
   ```

This will build the project and run the game.

## Tools Used

- **IDE**: Eclipse
- **Decompiler**: Fernflower
- **Build Tool**: Maven
- **Room Design Tool**: Custom-built GUI application

## Contact

If you have any questions or would like to request permission for commercial use, please feel free to reach out.
