package com.bl.ipl;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class IPLAnalyserTest {
    private static final String IPL_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostRuns.csv";


    @Test
    public void givenIndianCensusCSVFileReturnsCorrectRecords() {
        try {
            IPLAnalyser iplRuns = new IPLAnalyser();
            int numOfRecords = iplRuns.loadIPLData(IPL_CSV_FILE_PATH);
            Assert.assertEquals(101, numOfRecords);
        } catch (IPLException e) {
            e.printStackTrace();
        }
    }

}