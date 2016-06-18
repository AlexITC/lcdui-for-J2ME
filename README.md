# lcdui-for-J2ME
lcdui for J2ME is a kind of widget toolkit similar to Java swing, it is intended to keep consistency in user interfaces for mobile devices

This project was intended to keep consistency in the user interface built for this project:
https://github.com/AlexITC/Minesweeper-game-for-j2me

The idea is similar to Java swing, you can have a Frame (the main windown) and insert components into it.

At this stage the project supports to have a Frame visible only and it contains action buttons (softkeys in mobile devices), the softkeys interacts with the frame drawing shadows when they are pressed and hold. Also there is direct implementation for Frame class (see ListMenu class), which is a list containing several items, and allowing the set listeners for handling events (similar to JList in swing).

You can also use canvas to draw the content directly as you can see in the Minesweeper project.
