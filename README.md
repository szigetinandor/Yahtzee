### Communication
The server and the clients send simple string messages to each other.
There are 4 types of messages, 2 requests coming from the clients, a message used for informing the clients about the current points of the players, and a roll result message that the server sends to the player currently rolling the dice.
The messages contain parts that are separated with `;` characters. The first part is the type of the message.

#### WriteScoreRequest
This message is sent from the client requesting to write the player's score to the scoresheet.
Format:

    WriteScore;sender;column;dice1;dice2;dice3;dice4;dice5

#### RerollRequest
A re-roll request is sent when a player is wishing to re-roll one or more of the dice.
Format:

    Reroll;sender;diceID;[diceID];[diceID];[diceID];[diceID]

#### PointsInformation
The server sends the current points of the players to every client when a change occurred.
Format:

    Points;player;points;player;points;[player;points]...

#### Roll
A message contains the result of a player's roll attempt. It is sent to the client.
Format:

    Roll;dice1;[dice2];[dice3];[dice4];[dice5]


