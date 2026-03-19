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

## Features

- **Endless Exploration**

  The map is generated every new game is started. Grass, walls, and coins are placed randomly.
  
- **Randomized Combat**

  There is a 30% chance of encountering a monster in the grass. Each monster has unique ability (strength, agility, or intelligence).
  
- **Shop System**

  Player can buy offensive, defensive, and spell items using their coins. These items can help in battling monsters.

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

## How to Play

### Installation in CMD/Command Prompt

1. **Make sure Git and JDK are installed**

   For Git:
   ```bash
   git --version
   ```
   If not yet installed, download it [here](https://git-scm.com/downloads).

   For JDK:
   ```bash
   javac -version
   ```
   If not yet installed, download it [here](https://adoptium.net/temurin/releases).
3. **Clone the repository**  
   ```bash
   git clone https://github.com/stefani-gifta/oop-game-2025
    ```
4. **Navigate to the directory**  
   ```bash
   cd oop-game-2025
   ```
5. **Compile the Java files**  
   ```bash
   javac Main.java
   ```
6. **Run the game**  
   ```bash
   java Main
   ```

### Game Guide

#### Step-by-Step Instruction

1. **Register** your email and password if you haven't already.

   Data is temporary. It will be deleted once you exited the program.

3. **Login** to the registered email and password. You can also change your password in the main menu.
4. Choose an option from the game menu. Simply **choose 1 to start playing**.
5. You will see this in your screen:

   <img width="285" height="396" alt="image" src="https://github.com/user-attachments/assets/730ef590-eae6-4126-93d2-efdfec1f1d33" />
   
   This means you have **successfully entered the game**.
6. Use the below **controls and symbols** to explore the world.

#### Controls

| Key | Action                             |
| --- | ---------------------------------- |
| **W** | Move up                          |
| **A** | Move left                        |
| **S** | Move down                        |
| **D** | Move right                       |
| **I** | View purchased items             |
| **Z** | Open the shop                    |
| **E** | Exit the game                    |

#### Symbols

| Symbol | Meaning                                       |
| ------ | --------------------------------------------- |
| **O**    | Coin (collect to buy items)                 |
| **v / V**| Grass (may hide monsters)                   |
| **#**    | Wall (impassable)                           |
| **X**    | Player character                            |

Thank you for playing the 2025: OOP! I hope you enjoy your adventure.
If you have any questions or feedback, feel free to reach out.
