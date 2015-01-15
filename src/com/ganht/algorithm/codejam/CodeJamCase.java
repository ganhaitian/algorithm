package com.ganht.algorithm.codejam;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public abstract class CodeJamCase {

    protected abstract void runCase();

    protected void parseInput(File file, InputCaseLineParser lineParser) {
        try {
            BufferedReader bufferReader = new BufferedReader(new FileReader(file));
            String line = bufferReader.readLine();
            int lineNumber = 0;

            while (line != null) {
                lineParser.parseLine(++lineNumber, line);
                line = bufferReader.readLine();
            }
            bufferReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void parseInput(File file, InputCaseBlockParser blockParser) {
        try {
            BufferedReader bufferReader = new BufferedReader(new FileReader(file));
            String[] caseContent = new String[blockParser.getCaseLineNumber()];
            int lineCount = 0;
            int tmpIndex = 0;
            int caseIndex = 0;
            String caseNumber = bufferReader.readLine();
            String line = bufferReader.readLine();
            int caseLineNumber = blockParser.getCaseLineNumber();

            while (line != null) {
                tmpIndex = (++lineCount) % caseLineNumber;
                if (tmpIndex == 0) {
                    tmpIndex = caseLineNumber;
                }
                caseContent[tmpIndex - 1] = line;
                if (tmpIndex == caseLineNumber) {
                    blockParser.parseLine(++caseIndex, caseContent);
                }
                line = bufferReader.readLine();
            }
            bufferReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void acceptInput(CaseInputReader caseInputReader) {

    }

    protected void parseInput(File file) {
        try {
            BufferedReader bufferReader = new BufferedReader(new FileReader(file));
            acceptInput(CaseInputReader.build(bufferReader));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
