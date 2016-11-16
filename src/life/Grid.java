/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package life;

/**
 *
 * @author Mark Miller
 */
public class Grid {
    public Cell [][] grid;
    public Cell [][] temp;
    private int [][] aliveNeighbors;
    
    
    public Grid(int row, int col)
    {
      aliveNeighbors = new int [row][col];
      grid = new Cell[row][col];
      temp = new Cell[row][col];
      
      for(int i = 0; i < row; i++)
      {
          for(int j = 0; j < col; j++)
          {
              grid[i][j] = new Cell();
              grid[i][j].setX(j * 10);
              grid[i][j].setY(i * 10);
              
              temp[i][j] = new Cell();
              temp[i][j].setX(j * 10);
              temp[i][j].setY(i * 10);
          }
      }
    }
    public int getRow(int x)
    {
        return x % 10; 
    }
    public int getCol(int y)
    {
        return y % 10;
    }
    public void updateGrid()
    {
        for(int row = 0; row < grid.length; row++)
        {
            for(int col = 0; col < grid[0].length; col++)
            {
                Rules(row,col);
            }
        }
        
        for(int row = 0; row < grid.length; row++)
        {
            for(int col = 0; col < grid[0].length; col++)
            {
                if(temp[row][col].isAlive()){grid[row][col].makeAlive();}
                else{grid[row][col].makeDead();}
                }
            }
        }
        
   public boolean isGridDead(Cell [][] g)
   {
       for(int row = 0; row < grid.length; row++)
        {
            for(int col = 0; col < grid[0].length; col++)
            {
                if(g[row][col].isAlive()){return false;}
            }
        }
       return true;
   }
   
    public void printGrid()
    {
        
        for(int i = 0; i < grid.length; i++)
        {
            for(int j = 0; j < grid[0].length; j++)
            {
                if(j == grid[0].length - 1)
                {
                    if(grid[i][j].isAlive())
                    {
                        System.out.print("A\n");
                    }  
                    else
                    {
                        System.out.print(".\n");
                    }
                }
                else
                {
                  if(grid[i][j].isAlive())
                    {
                        System.out.print("A");
                    }  
                    else
                    {
                        System.out.print(".");
                    }  
                }
            }
        } 
    }
    
    public void play()
    {
        while(!isGridDead(grid))
        {
//            printGrid();
//            System.out.println("\n");
            updateGrid();
        }
    }
    public Cell getCell(int row, int col)
    {
     return grid[row][col];
    }
    public void makeAlive(int row, int col)
    {
        grid[row][col].makeAlive();
    }
    public void makeDead(int row, int col)
    {
        grid[row][col].makeDead();
    }
    
    public boolean isSide(int row, int col)
    {
        return row == 0 || col == 0 || col == (grid[row].length - 1)|| row == (grid.length - 1);
    }
    
    public int getSide(int row, int col)
    {
        if(!isCorner(row,col))
        {
            if(isSide(row,col))
            {
                if(row == 0){return 1;} //top
                if(col == grid[0].length - 1){return 2;} //right
                if(row == grid.length - 1){return 3;} //bottom
                if(col == 0){return 4;} //left
            }
        }
        return -1;
    }
    
    public boolean isCorner(int row, int col)
    {
        return (row == 0 && col == 0) || (row == 0 && col == grid[0].length - 1) || (row == grid.length -1 && col == grid[0].length - 1) || (row == grid.length - 1 && col == 0);
    }
    
    public int getCorner(int row, int col)
    {
        if(row == 0 && col == 0){return 1;}//top left
        if(row == 0 && col == grid[0].length - 1){return 2;} //top right
        if(row == grid.length -1 && col == grid[0].length - 1){return 3;} //bottom right
        if(row == grid.length - 1 && col == 0){return 4;}
        return -1;
    }
    public void Rules (int row, int col)
    {
       int neighbors = getNeighbors(row,col);
       boolean isAlive = grid[row][col].isAlive();
       
       if(isAlive && neighbors < 2)
       {
           temp[row][col].makeDead();
       }
       else if(isAlive && (neighbors > 1 && neighbors < 4))
       {
            temp[row][col].makeAlive();
       }
       else if(isAlive && neighbors > 3)
       {
           temp[row][col].makeDead();
       }
       else if(!(isAlive) && neighbors == 3)
       {
           temp[row][col].makeAlive();
       }
       
    }
    
    public int getNeighbors(int row, int col)
    {
        aliveNeighbors[row][col] = 0;
        if(isCorner(row, col))
        {
            switch (getCorner(row,col)) {
                case 1: //top left corner
                    if(grid[row][col + 1].isAlive()){aliveNeighbors[row][col]++;} // right 1
                    if(grid[row + 1][col].isAlive()){aliveNeighbors[row][col]++;} //down 1
                    if(grid[row + 1][col + 1].isAlive()){aliveNeighbors[row][col]++;} //down 1 right 1
                    break;
                case 2: //top right corner
                    if(grid[row][col - 1].isAlive()){aliveNeighbors[row][col]++;} //left 1
                    if(grid[row + 1][col].isAlive()){aliveNeighbors[row][col]++;} //down 1
                    if(grid[row + 1][col - 1].isAlive()){aliveNeighbors[row][col]++;} //down 1 left 1
                    break;
                case 3: //bottom right corner
                    if(grid[row - 1][col].isAlive()){aliveNeighbors[row][col]++;} //up 1
                    if(grid[row - 1][col - 1].isAlive()){aliveNeighbors[row][col]++;} //up 1 left 1
                    if(grid[row][col - 1].isAlive()){aliveNeighbors[row][col]++;} //left 1
                    break;
                case 4: //bottom left corner
                    if(grid[row - 1][col].isAlive()){aliveNeighbors[row][col]++;} //up 1
                    if(grid[row - 1][col + 1].isAlive()){aliveNeighbors[row][col]++;} //up 1 right 1
                    if(grid[row][col + 1].isAlive()){aliveNeighbors[row][col]++;} //right 1
                    break;
                default:
                    break;
            }
        }
        else if(isSide(row,col))
        {
           switch (getSide(row,col)) {
                case 1: //top side
                    if(grid[row][col - 1].isAlive()){aliveNeighbors[row][col]++;} //left 1
                    if(grid[row + 1][col - 1].isAlive()){aliveNeighbors[row][col]++;} //left 1 down 1
                    if(grid[row + 1][col].isAlive()){aliveNeighbors[row][col]++;} //down 1
                    if(grid[row + 1][col + 1].isAlive()){aliveNeighbors[row][col]++;} //down 1 right 1
                    if(grid[row][col + 1].isAlive()){aliveNeighbors[row][col]++;} //right 1
                    break;
                case 2: //right side
                    if(grid[row - 1][col].isAlive()){aliveNeighbors[row][col]++;} //up 1
                    if(grid[row - 1][col - 1].isAlive()){aliveNeighbors[row][col]++;} //up 1 left 1
                    if(grid[row][col - 1].isAlive()){aliveNeighbors[row][col]++;} //left 1
                    if(grid[row + 1][col - 1].isAlive()){aliveNeighbors[row][col]++;} //down 1 left 1
                    if(grid[row - 1][col].isAlive()){aliveNeighbors[row][col]++;} //down 1
                    break;
                case 3: //bottom side
                    if(grid[row][col + 1].isAlive()){aliveNeighbors[row][col]++;} //right 1
                    if(grid[row - 1][col + 1].isAlive()){aliveNeighbors[row][col]++;} //up 1 right 1
                    if(grid[row - 1][col].isAlive()){aliveNeighbors[row][col]++;} //up 1
                    if(grid[row - 1][col - 1].isAlive()){aliveNeighbors[row][col]++;} //up 1 left 1
                    if(grid[row][col - 1].isAlive()){aliveNeighbors[row][col]++;} //left 1
                    break;
                case 4: //left side
                    if(grid[row + 1][col].isAlive()){aliveNeighbors[row][col]++;} //down 1
                    if(grid[row + 1][col + 1].isAlive()){aliveNeighbors[row][col]++;} //down 1 right 1
                    if(grid[row][col + 1].isAlive()){aliveNeighbors[row][col]++;} //right 1
                    if(grid[row - 1][col + 1].isAlive()){aliveNeighbors[row][col]++;} //up 1 right 1
                    if(grid[row - 1][col].isAlive()){aliveNeighbors[row][col]++;} //up 1
                    break;
                default:
                    break;
            } 
        }
        else
        {
           if(grid[row - 1][col].isAlive()){aliveNeighbors[row][col]++;} //up 1
           if(grid[row - 1][col + 1].isAlive()){aliveNeighbors[row][col]++;} //up 1 right 1
           if(grid[row][col + 1].isAlive()){aliveNeighbors[row][col]++;} //right 1
           if(grid[row + 1][col + 1].isAlive()){aliveNeighbors[row][col]++;} //down 1 right 1
           if(grid[row + 1][col].isAlive()){aliveNeighbors[row][col]++;} //down 1
           if(grid[row + 1][col - 1].isAlive()){aliveNeighbors[row][col]++;} //down 1 left 1
           if(grid[row][col - 1].isAlive()){aliveNeighbors[row][col]++;} //left 1
           if(grid[row - 1][col - 1].isAlive()){aliveNeighbors[row][col]++;} //up 1 left 1
        }
        return aliveNeighbors[row][col];
    }
    public void clearGrid(){
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j <grid[0].length; j++){
                grid[i][j].makeDead();
                temp[i][j].makeDead();
            }
        }
    }
}
