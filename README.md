```text
  ____   ___   ____   ____        ____   ____  ____ 
 |___ \ / _ \ |___ \||  ___|     / __ \ / __ \|  _ \
   __) | | | |  __) ||  |__   . | |  | | |  | | |_) /
  / __/| |_| | / __/ \___  \  . | |__| | |__| |  __/
 |_____|\___/ |_____||_____/     \____/ \____/|_|
```

# 2025: Open-World Odyssey Project (OOP)

Welcome to the **2025: OOP**, a CLI-based console game where players can explore a vast map, collect coins, battle monsters, and purchase items.

This project is built using **Object-Oriented Programming (OOP)** principles.

---

## Table of Contents

1. [Features](#features)
2. [How to Play](#how-to-play)
3. [Game Mechanics](#game-mechanics)
4. [Installation](#installation)

---

## Features

- **Endless Exploration**  
  The map is generated every new game is started.
  Grass, walls, and coins are placed randomly.
- **Randomized Combat**
  There is a 30% chance of encountering a monster in the grass.
  Each monster has unique ability (strength, agility, or intelligence).
- **Shop System**  
  Player can buy offensive, defensive, and spell items using their coins.
  These items can help in battling monsters.

---

## How to Play

### Controls

| Key | Action                             |
| --- | ---------------------------------- |
| **W** | Move up                          |
| **A** | Move left                        |
| **S** | Move down                        |
| **D** | Move right                       |
| **I** | View purchased items             |
| **Z** | Open the shop                    |
| **E** | Exit the game                    |

### Symbols

| Symbol | Meaning                                       |
| ------ | --------------------------------------------- |
| **O**    | Coin (collect to buy items)                 |
| **v / V**| Grass (may hide monsters)                   |
| **#**    | Wall (impassable)                           |
| **X**    | Player character                            |

---

## Game Mechanics

### Map

- The world is a 300×300 grid with randomized placement of grass, walls, and coins.
- Upon reaching the end of the map, player automatically jumps to the center.

### Monsters

Monsters lurk in grass and have special abilities:

- **Strength**: Deflects a portion of incoming damage.
- **Agility**: Deals critical bonus damage.
- **Intelligence**: Adds extra bonus damage to each attack.

### Combat

Player's turn options:

- **Pure Attack**: Deal base damage to the monster.
- **Item-Based Attack**: Use an offensive or spell item, consuming its uses or mana cost.
- **Store Mana**: Regain mana instead of attacking.

Player option during monster's turn:

- **Defensive Use**: Equip a defensive item during a monster’s attack to reduce damage.

### Shop

- Press Z in-game to open the shop.
- Purchase items with coins collected on the map.
- Press I to view purchased items.

### Items

Three types of items available for purchase:

- **Offensive**: Increase player's attack damage.
- **Defensive**: Deflect incoming damage from monster.
- **Spell**: Costs mana to deal additional magic damage.

---

## Installation

1. **Clone the repository**  
   ```bash
   git clone https://github.com/stefani-gifta/oop-game-2025
    ```
2. **Open the project** in your favorite IDE
3. **Navigate to the src directory**  
   ```bash
   cd src
   ```
4. **Compile the Java files**  
   ```bash
   javac Main.java
   ```
5. **Run the game**  
   ```bash
   java Main
   ```
6. **Enjoy the game**

Thank you for playing the 2025: OOP! I hope you enjoy your adventure.
If you have any questions or feedback, feel free to reach out.