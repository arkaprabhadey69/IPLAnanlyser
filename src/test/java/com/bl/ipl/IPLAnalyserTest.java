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
            String sortedIPLData = iPLAnalyser.getPlayersWithTopAverages();
            IPLRuns[] iplRuns = new Gson().fromJson(sortedIPLData, IPLRuns[].class);
            Assert.assertEquals("MS Dhoni", iplRuns[0].player);
        } catch (IPLException e) {
            e.printStackTrace();

        }
    }
    @Test
    public void givenDataShouldReturnBatsmanWithHighestSR() {
        try {
            IPLAnalyser iPLAnalyser = new IPLAnalyser();
            iPLAnalyser.loadIPLData(IPL_CSV_FILE_PATH);
            String sortedIPLData = iPLAnalyser.getPlayersWithTopSR();
            IPLRuns[] iplRuns = new Gson().fromJson(sortedIPLData, IPLRuns[].class);
            Assert.assertEquals("Ishant Sharma", iplRuns[0].player);
        } catch (IPLException e) {
            e.printStackTrace();

        }
    }
    @Test
    public void givenDataShouldReturnBatsmanWithHighestBoundary() {
        try {
            IPLAnalyser iPLAnalyser = new IPLAnalyser();
            iPLAnalyser.loadIPLData(IPL_CSV_FILE_PATH);
            String sortedIPLData = iPLAnalyser.getPlayersWithTop6and4();
            IPLRuns[] iplRuns = new Gson().fromJson(sortedIPLData, IPLRuns[].class);
            Assert.assertEquals("Andre Russell", iplRuns[0].player);
        } catch (IPLException e) {
            e.printStackTrace();

        }
    }
    @Test
    public void givenDataShouldReturnBatsmanWithHighestBoundaryAndSR() {
        try {
            IPLAnalyser iPLAnalyser = new IPLAnalyser();
            iPLAnalyser.loadIPLData(IPL_CSV_FILE_PATH);
            String sortedIPLData = iPLAnalyser.getPlayersWithTopSRandBoundary();
            IPLRuns[] iplRuns = new Gson().fromJson(sortedIPLData, IPLRuns[].class);
            Assert.assertEquals("Andre Russell", iplRuns[0].player);
        } catch (IPLException e) {
            e.printStackTrace();

        }
    }
    @Test
    public void givenDataShouldReturnBatsmanWithHighestAvgAndSR() {
        try {
            IPLAnalyser iPLAnalyser = new IPLAnalyser();
            iPLAnalyser.loadIPLData(IPL_CSV_FILE_PATH);
            String sortedIPLData = iPLAnalyser.getPlayersWithTopSRandAverage();
            IPLRuns[] iplRuns = new Gson().fromJson(sortedIPLData, IPLRuns[].class);
            Assert.assertEquals("MS Dhoni", iplRuns[0].player);
        } catch (IPLException e) {
            e.printStackTrace();

        }
    }
    @Test
    public void givenDataShouldReturnBatsmanWithHighestAvgAndRuns() {
        try {
            IPLAnalyser iPLAnalyser = new IPLAnalyser();
            iPLAnalyser.loadIPLData(IPL_CSV_FILE_PATH);
            String sortedIPLData = iPLAnalyser.getPlayersWithTopRunsAndAverage();
            IPLRuns[] iplRuns = new Gson().fromJson(sortedIPLData, IPLRuns[].class);
            Assert.assertEquals("David Warner", iplRuns[0].player);
        } catch (IPLException e) {
            e.printStackTrace();

        }
    }


}