# Pathfinding in a Maze (UCS & A* Search)

## Overview
This project implements **pathfinding algorithms** to navigate a robot through a randomly generated **N x N maze** with obstacles. The goal is to find the shortest path from a **starting position (S)** to the **nearest goal (G1 or G2)** using two search algorithms:
- **Uniform Cost Search (UCS)**
- **A* Search with an admissible heuristic**

## Features
- **Random Maze Generation**  
  - The maze is an `N x N` grid where each cell is either **free** or **an obstacle** (determined by probability `p`).
- **Pathfinding Algorithms**  
  - **UCS (Uniform Cost Search)**: Finds the optimal path using a priority queue.
  - **A* Search**: Uses a heuristic function `h(n)` for efficient pathfinding.
- **Customizable Inputs**  
  - The user defines `N`, `p`, and the positions of `S`, `G1`, and `G2` at runtime.
- **Movement Options**  
  - The robot can move **horizontally, vertically, or diagonally** to a free neighboring cell.
- **Evaluation Metrics**  
  - The program prints:
    - The **maze** with the **computed path**.
    - The **path cost**.
    - The **number of node expansions** for both algorithms.
- **Algorithm Comparison**  
  - Allows users to test different values of `N` and `p` to compare UCS and A* efficiency.
