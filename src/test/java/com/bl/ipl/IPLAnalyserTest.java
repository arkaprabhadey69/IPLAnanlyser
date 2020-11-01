package com.bl.ipl;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class IPLAnalyserTest {
    private static final String IPL_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String IPL_WKTS_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostWkts.csv";

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
    @Test
    public void givenDataShouldReturnBatsmanWithHighestHundreds() {
        try {
            IPLAnalyser iPLAnalyser = new IPLAnalyser();
            iPLAnalyser.loadIPLData(IPL_CSV_FILE_PATH);
            String sortedIPLData = iPLAnalyser.getPlayersWithTopHundreds();
            IPLRuns[] iplRuns = new Gson().fromJson(sortedIPLData, IPLRuns[].class);
            Assert.assertEquals("David Warner", iplRuns[0].player);
        } catch (IPLException e) {
            e.printStackTrace();

        }
    }

    @Test
    public void givenIndianCensusCSVFileReturnsCorrectRecords2() {
        try {
            IPLAnalyser iplRuns = new IPLAnalyser();
            int numOfRecords = iplRuns.loadIPLDataWkts(IPL_WKTS_CSV_FILE_PATH);
            System.out.println(numOfRecords);
            Assert.assertEquals(99, numOfRecords);
        } catch (IPLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenDataShouldReturnBowlerWithLowestAvg() {
        try {
            IPLAnalyser iPLAnalyser = new IPLAnalyser();
            iPLAnalyser.loadIPLDataWkts(IPL_WKTS_CSV_FILE_PATH);
            String sortedIPLData = iPLAnalyser.getBowlersWithTopAverage();
            IPLWickets[] iplRuns = new Gson().fromJson(sortedIPLData, IPLWickets[].class);
            int index=iPLAnalyser.getPlayerIndex2(iplRuns);
            Assert.assertEquals("Anukul Roy", iplRuns[index].player);
        } catch (IPLException e) {
            e.printStackTrace();

        }
    }

    @Test
    public void givenDataShouldReturnBowlerWithLowestSR() {
        try {
            IPLAnalyser iPLAnalyser = new IPLAnalyser();
            iPLAnalyser.loadIPLDataWkts(IPL_WKTS_CSV_FILE_PATH);
            String sortedIPLData = iPLAnalyser.getBowlersWithTopSR();
            IPLWickets[] iplRuns = new Gson().fromJson(sortedIPLData, IPLWickets[].class);
            int index = iPLAnalyser.getPlayerIndex(iplRuns);
            Assert.assertEquals("Alzarri Joseph", iplRuns[index].player);
        } catch (IPLException e) {
            e.printStackTrace();

        }
    }
    @Test
    public void givenDataShouldReturnBowlerWithLowestEconomy() {
        try {
            IPLAnalyser iPLAnalyser = new IPLAnalyser();
            iPLAnalyser.loadIPLDataWkts(IPL_WKTS_CSV_FILE_PATH);
            String sortedIPLData = iPLAnalyser.getBowlersWithTopEcon();
            IPLWickets[] iplRuns = new Gson().fromJson(sortedIPLData, IPLWickets[].class);
            Assert.assertEquals("Shivam Dube", iplRuns[0].player);
        } catch (IPLException e) {
            e.printStackTrace();

        }
    }
    @Test
    public void givenDataShouldReturnBowlerWithHighest4wAnd5w() {
        try {
            IPLAnalyser iPLAnalyser = new IPLAnalyser();
            iPLAnalyser.loadIPLDataWkts(IPL_WKTS_CSV_FILE_PATH);
            String sortedIPLData = iPLAnalyser.getBowlersWithTopSRAnd4w();
            IPLWickets[] iplRuns = new Gson().fromJson(sortedIPLData, IPLWickets[].class);
            Assert.assertEquals("Lasith Malinga", iplRuns[iplRuns.length-1].player);
        } catch (IPLException e) {
            e.printStackTrace();

        }
    }
    @Test
    public void givenDataShouldReturnBowlerWithBestAvgAndSR() {
        try {
            IPLAnalyser iPLAnalyser = new IPLAnalyser();
            iPLAnalyser.loadIPLDataWkts(IPL_WKTS_CSV_FILE_PATH);
            String sortedIPLData = iPLAnalyser.getBowlersWithTopSRAndAvg();
            IPLWickets[] iplRuns = new Gson().fromJson(sortedIPLData, IPLWickets[].class);
            int index=iPLAnalyser.getPlayerIndex(iplRuns);
           Assert.assertEquals("Anukul Roy", iplRuns[index].player);
        } catch (IPLException e) {
            e.printStackTrace();

        }
    }
    @Test
    public void givenDataShouldReturnBowlerWithBestWickets() {
        try {
            IPLAnalyser iPLAnalyser = new IPLAnalyser();
            iPLAnalyser.loadIPLDataWkts(IPL_WKTS_CSV_FILE_PATH);
            String sortedIPLData = iPLAnalyser.getBowlersWithTopWickets();
            IPLWickets[] iplRuns = new Gson().fromJson(sortedIPLData, IPLWickets[].class);
            Assert.assertEquals("Imran Tahir", iplRuns[iplRuns.length-1].player);
        } catch (IPLException e) {
            e.printStackTrace();

        }
    }
    @Test
    public void givenIPLDataFindCricketerWithBestBowlingAndBattingAverage() throws IPLException {
        IPLAnalyser iplAnalyser = new IPLAnalyser();
        iplAnalyser.loadIPLDataWkts(IPL_WKTS_CSV_FILE_PATH);
        String sorted = iplAnalyser.getBowlersWithTopAverage();
        IPLWickets[] wickets = new Gson().fromJson(sorted, IPLWickets[].class);
        iplAnalyser.loadIPLData(IPL_CSV_FILE_PATH);
        String sortedBat = iplAnalyser.getPlayersWithTopAverages();
        IPLRuns[] average = new Gson().fromJson(sortedBat, IPLRuns[].class);
        String bestAvg=iplAnalyser.getBestAvg(average,wickets);
        Assert.assertEquals("Sherfane Rutherford", bestAvg);
    }


}