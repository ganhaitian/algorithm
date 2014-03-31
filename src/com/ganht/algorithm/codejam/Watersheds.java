package com.ganht.algorithm.codejam;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Watersheds extends CodeJamCase {

  @Override
  protected void runCase() {
    parseInput(new File("G:\\dep\\code-jam-case\\watersheds-small.in"));
  }

  public static void main(String[] args) {
    new Watersheds().runCase();
  }

  public int NORTH = 0, WEST = 1, EAST = 2, SOUTH = 3;

  private char globalLabel = 'a' - 1;

  public class Cell {

    public boolean isSink;

    public List<Cell> neighbors = new ArrayList<Cell>();

    private int altitude;

    public Cell from;

    public Cell to;

    public String label;

    public Cell(int altitude) {
      this.setAltitude(altitude);
      neighbors.add(null);
      neighbors.add(null);
      neighbors.add(null);
      neighbors.add(null);
    }

    public int getAltitude() {
      return altitude;
    }

    public void setAltitude(int altitude) {
      this.altitude = altitude;
    }

    public void setNeighbor(int direction, Cell cell) {
      neighbors.set(direction, cell);
    }

    public void divide() {
      Cell lowestCell = this;
      int lowestAltitude = altitude;
      for (int index = 0; index < neighbors.size(); index++) {
        if (neighbors.get(index) != null) {
          if (neighbors.get(index).getAltitude() < lowestAltitude) {
            lowestAltitude = neighbors.get(index).getAltitude();
            lowestCell = neighbors.get(index);
          }
        }
      }

      if (lowestCell != this) {
        this.to = lowestCell;
        lowestCell.from = this;
        
        if(lowestCell.label == null){
          lowestCell.divide();
        }
          
        this.label = lowestCell.label;
       
      } else {
        globalLabel = (char) (globalLabel + 1);
        char currentLabel = globalLabel;
        this.isSink = true;
        this.label = String.valueOf(currentLabel);
      }
    }

    public String toString() {
      return String.format("Cell:{altitude:%d,labe:%s}", this.altitude, this.label);
    }

  }

  protected void acceptInput(CaseInputReader caseInputReader) {

    int caseNumber = Integer.parseInt(caseInputReader.readOneLine());
    String tmpCaseInfo = caseInputReader.readOneLine();
    List<Cell> lastLines = new ArrayList<Cell>();
    List<Cell> allCells = new ArrayList<Cell>();
    int caseIndex = 0;

    while (tmpCaseInfo != null) {
      String[] caseInfo = tmpCaseInfo.split(" ");
      int h = Integer.parseInt(caseInfo[0]);
      int w = Integer.parseInt(caseInfo[1]);

      List<String> lines = caseInputReader.readLines(h);
      //while (lines != null) {
        for (String line : lines) {
          String[] altitudes = line.split(" ");
          Cell lastCell = null;
          for (int index = 0; index < altitudes.length; index++) {
            Cell cell = new Cell(Integer.parseInt(altitudes[index]));

            if (lastCell != null) {
              cell.setNeighbor(WEST, lastCell);
              lastCell.setNeighbor(EAST, cell);
            }
            lastCell = cell;

            if (lastLines.size() < w) {
              lastLines.add(cell);
            } else {
              lastLines.get(index).setNeighbor(SOUTH, cell);
              cell.setNeighbor(NORTH, lastLines.get(index));
              lastLines.set(index, cell);
            }
            allCells.add(cell);
          }

        }

        System.out.printf("Case #%d:\n", ++caseIndex);
        int tmpCellIndex = 0;

        for (Cell cell : allCells) {
          if (cell.label == null) {
            cell.divide();
          }

          System.out.print(cell.label);

          if (++tmpCellIndex == w) {
            System.out.println("");
            tmpCellIndex = 0;
          }else{
            System.out.print(" ");
          }
        }

        allCells.clear();
        lastLines.clear();
        globalLabel = 'a' - 1;
        
        tmpCaseInfo = caseInputReader.readOneLine();
      }

      try {
        caseInputReader.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    //}


  }

}
