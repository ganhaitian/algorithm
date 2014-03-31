package com.ganht.algorithm.codejam;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Watersheds extends CodeJamCase {

  @Override
  protected void runCase() {
    parseInput(new File(""));
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
        lowestCell.divide();

        if (lowestCell.isSink) {
          this.label = lowestCell.label;
        }
      } else {
        char currentLabel = (char) (globalLabel + 1);
        this.isSink = true;
        this.label = String.valueOf(currentLabel);
      }
    }

  }

  protected void acceptInput(CaseInputReader caseInputReader) {

    // int caseNumber = Integer.parseInt(caseInputReader.readOneLine());
    String tmpCaseInfo = caseInputReader.readOneLine();
    List<Cell> lastCases = new ArrayList<Cell>();
    List<Cell> allCells = new ArrayList<Cell>();
    int caseIndex = 0;

    while (tmpCaseInfo != null) {
      String[] caseInfo = tmpCaseInfo.split(" ");
      int h = Integer.parseInt(caseInfo[0]);

      List<String> lines = caseInputReader.readLines(h);
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

          if (lastCases.size() > 0) {
            lastCases.get(index).setNeighbor(SOUTH, cell);
            cell.setNeighbor(NORTH, lastCases.get(index));
            lastCases.set(index, cell);
          }
          allCells.add(cell);
        }

        System.out.printf("Case #%d:/n", ++caseIndex);

        for (Cell cell : allCells) {
          if (cell.label == null) {
            cell.divide();
          } else {
            System.out.print(cell.label);
          }
        }
      }
    }


  }

}
