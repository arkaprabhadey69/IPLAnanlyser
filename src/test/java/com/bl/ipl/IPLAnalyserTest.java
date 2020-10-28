package com.bl.ipl;

import com.google.gson.Gson;
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

    @Test
    public void givenDataShouldReturnBatsmanWithHighestAverage() {
        try {
            IPLAnalyser iPLAnalyser = new IPLAnalyser();
            iPLAnalyser.loadIPLData(IPL_CSV_FILE_PATH);
            String sortCensusData = iPLAnalyser.getPlayersWithTopAverages();
            IPLRuns[] iplRuns = new Gson().fromJson(sortCensusData, IPLRuns[].class);
            Assert.assertEquals("MS Dhoni", iplRuns[0].player);
        } catch (IPLException e) {
            e.printStackTrace();

        }
    }

}