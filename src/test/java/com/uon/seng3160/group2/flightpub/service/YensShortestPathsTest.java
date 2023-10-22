package com.uon.seng3160.group2.flightpub.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.MethodMode;
import org.springframework.test.context.jdbc.Sql;

import com.uon.seng3160.group2.flightpub.entity.Country;
import com.uon.seng3160.group2.flightpub.entity.Destination;
import com.uon.seng3160.group2.flightpub.entity.DestinationMatrix;
import com.uon.seng3160.group2.flightpub.entity.Distance;
import com.uon.seng3160.group2.flightpub.entity.Edge;
import com.uon.seng3160.group2.flightpub.util.ShortestPath;
import com.uon.seng3160.group2.flightpub.FlightpubApplication;

import org.springframework.test.context.jdbc.SqlConfig.TransactionMode;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.jdbc.SqlConfig;

public class YensShortestPathsTest {

    Destination syd;
    Destination bne;
    Destination mel;
    Destination cbr;
    Destination ool;

    List<Destination> destinations;

    @Mock
    DestinationService destinationService;
    DestinationMatrix destinationMatrix;
    PathAlgorithmRevised pathAlgorithmRevised;

    public YensShortestPathsTest() {
        Country aus = new Country("AU", "AUS", "Australia");
        this.syd = new Destination("SYD", null, aus);
        this.mel = new Destination("MEL", null, aus);
        this.cbr = new Destination("CBR", null, aus);
        this.bne = new Destination("BNE", null, aus);
        this.ool = new Destination("OOL", null, aus);

        Distance sydToMel = new Distance(null, this.syd, this.mel, 713);
        Distance sydToCbr = new Distance(null, this.syd, this.cbr, 246);
        Distance sydToBne = new Distance(null, this.syd, this.bne, 728);
        Distance sydToOol = new Distance(null, this.syd, this.ool, 679);
        Distance melToCbr = new Distance(null, this.mel, this.cbr, 464);
        Distance melToBne = new Distance(null, this.mel, this.bne, 1367);
        Distance cbrToBne = new Distance(null, this.cbr, this.bne, 939);

        this.syd.addDistanceFrom(sydToMel);
        this.syd.addDistanceFrom(sydToCbr);
        this.syd.addDistanceFrom(sydToBne);
        this.syd.addDistanceFrom(sydToOol);
        this.mel.addDistanceFrom(melToCbr);
        this.mel.addDistanceFrom(melToBne);
        this.cbr.addDistanceFrom(cbrToBne);

        this.destinations = new ArrayList<Destination>(4);
        destinations.add(this.syd);
        destinations.add(this.mel);
        destinations.add(this.cbr);
        destinations.add(this.bne);
    }

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.destinationMatrix = new DestinationMatrix(destinationService);
        when(destinationService.getAll()).thenReturn(this.destinations);
        this.destinationMatrix.populate();
        this.pathAlgorithmRevised = new PathAlgorithmRevised(destinationMatrix);
    }

    @Test
    public void testMockingWorked() {
        assertEquals(4, destinationService.getAll().size());
        assertEquals(4, this.destinationMatrix.getDestinations().size());
        List<List<Destination>> journeys = this.pathAlgorithmRevised.YensShortestPaths(this.destinations.get(0),
                this.destinations.get(1), 3);
        assertEquals(3, journeys.size());
    }

    /*
     * Number of paths returned works correctly
     * if 3 are expected and possible then 3 are returned
     * if less than expected is possible than the max paths is returned
     */
    @Test
    public void yensReturnsNumberOfPathsSpecifiedWhenItIsPossible() {
        int expectedNumberOfPaths = 3;
        List<List<Destination>> paths = this.pathAlgorithmRevised.YensShortestPaths(this.bne,
                this.mel, expectedNumberOfPaths);

        assertEquals(expectedNumberOfPaths, paths.size());
    }

    // expect one possible path from syd to ool
    @Test
    public void yensReturnsMaxNumberOfPathsWhenKIsLargerThanPossible() {
        int pathsWanted = 3;
        int pathsPossible = 1;

        List<List<Destination>> paths = this.pathAlgorithmRevised.YensShortestPaths(this.syd,
                this.ool, pathsWanted);

        assertEquals(pathsPossible, paths.size());
    }

    // expect two possible paths from syd to mel when you cant through to canberra
    @Test
    public void yensReturnsMaxNumberOfPathsWhenKIsLargerThanPossible2() {
        this.cbr.setIllegal(true);
        int pathsWanted = 3;
        int pathsPossible = 2;

        List<List<Destination>> paths = this.pathAlgorithmRevised.YensShortestPaths(this.syd,
                this.mel, pathsWanted);

        assertEquals(pathsPossible, paths.size());
    }

    /*
     * List of paths returned are in order of shortest to longest
     */
    @Test
    public void yensReturnsPathsInOrderOfShortestToLongest() {
        List<List<Destination>> paths = this.pathAlgorithmRevised.YensShortestPaths(this.bne,
                this.mel, 3);

        System.out.println(paths);

        int shortestPathLength = this.pathAlgorithmRevised.distanceCalc(paths.get(0));
        int secondShortestPathLength = this.pathAlgorithmRevised.distanceCalc(paths.get(1));
        int thirdShortestPathLength = this.pathAlgorithmRevised.distanceCalc(paths.get(2));

        assertEquals(1367, shortestPathLength);
        assertEquals(1403, secondShortestPathLength);
        assertEquals(1438, thirdShortestPathLength);
    }

}