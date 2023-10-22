package com.uon.seng3160.group2.flightpub.service;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.MethodMode;
import org.springframework.test.context.jdbc.Sql;

import com.uon.seng3160.group2.flightpub.entity.Destination;
import com.uon.seng3160.group2.flightpub.entity.DestinationMatrix;
import com.uon.seng3160.group2.flightpub.entity.Edge;
import com.uon.seng3160.group2.flightpub.util.ShortestPath;
import com.uon.seng3160.group2.flightpub.FlightpubApplication;

import org.springframework.test.context.jdbc.SqlConfig.TransactionMode;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.jdbc.SqlConfig;

@SpringBootTest(classes = FlightpubApplication.class)
@Transactional
@Sql(scripts = "/create-djikstra-test-data.sql", config = @SqlConfig(transactionMode = TransactionMode.ISOLATED))
public class DjikstrasTest {

    @Autowired
    DestinationMatrix destinationMatrix;

    @Autowired
    PathAlgorithmRevised pathAlgorithmRevised;

    Destination brisbane;
    Destination melbourne;

    @BeforeEach
    public void setup() {
        this.destinationMatrix.populate();
        brisbane = destinationMatrix.getNode("BNE").get();
        melbourne = destinationMatrix.getNode("MEL").get();
    }

    /*
     * Testing Djikstras alogrithm with illegal edges and nodes implementation
     * 
     * shortest paths order:
     * BNE -> MEL = 1367
     * BNE -> CBR -> MEL = 1403
     * BNE -> SYD -> CBR -> MEL = 1438
     * BNE -> SYD -> MEL = 1441
     */
    @Test
    public void djikstrasReturnsShortestPath() {
        // Act
        ShortestPath shortestPath = this.pathAlgorithmRevised.djikstras(this.brisbane,
                this.melbourne);

        // Arrange
        int distanceTravelled = shortestPath.getLength();
        Destination start = shortestPath.getPath().get(0);
        Destination end = shortestPath.getPath().get(1);

        // Assert
        assertEquals(1367, distanceTravelled);
        assertEquals(this.brisbane, start);
        assertEquals(this.melbourne, end);
    }

    // given edge bne -> mel is illegal, what is the new shortest path?
    // next shortest path: bne -> cbr -> mel = 1403 is expected
    @Test
    @DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
    public void djikstrasWhenEdgeIsIllegalReturnsShortestPathWitoutIllegalEdge() {
        // Arrange
        Edge edge = this.destinationMatrix.getEdge(this.brisbane, this.melbourne).get();
        edge.setIllegal(true);

        // Act
        ShortestPath shortestPath = this.pathAlgorithmRevised.djikstras(this.brisbane,
                this.melbourne);

        // Arrange
        Destination start = shortestPath.getPath().get(0);
        Destination leg = shortestPath.getPath().get(1);
        Destination end = shortestPath.getPath().get(2);
        int distanceTravelled = shortestPath.getLength();
        Destination canberra = this.destinationMatrix.getNode("CBR").get();

        // Act
        assertEquals(this.brisbane, start);
        assertEquals(canberra, leg);
        assertEquals(this.melbourne, end);
        assertEquals(1403, distanceTravelled);
    }

    // given edge bne -> mel is illegal, and node CBR is illegal
    // next shortest path: bne -> syd -> mel = 1441 is expected
    @Test
    @DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
    public void djikstrasWhenEdgeAndNodeAreIllegalReturnsShortestPathWithoutIllegalEdgeAndNode() {
        // Arrange
        Edge edge = this.destinationMatrix.getEdge(this.brisbane, this.melbourne).get();
        edge.setIllegal(true);
        Destination canberra = this.destinationMatrix.getNode("CBR").get();
        canberra.setIllegal(true);

        // Act
        ShortestPath shortestPath = this.pathAlgorithmRevised.djikstras(this.brisbane,
                this.melbourne);

        // Arrange
        int distanceTravelled = shortestPath.getLength();
        Destination start = shortestPath.getPath().get(0);
        Destination leg = shortestPath.getPath().get(1);
        Destination end = shortestPath.getPath().get(2);
        Destination sydney = this.destinationMatrix.getNode("SYD").get();

        // Assert
        assertEquals(this.brisbane, start);
        assertEquals(sydney, leg);
        assertEquals(this.melbourne, end);
        assertEquals(1441, distanceTravelled);
    }

    @Test
    @DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
    public void djikstrasReturnsEmptyShortestPathWhenNoPossiblePatExists() {
        // Arrange
        Destination canberra = this.destinationMatrix.getNode("CBR").get();
        canberra.setIllegal(true);
        Destination sydney = this.destinationMatrix.getNode("SYD").get();
        sydney.setIllegal(true);
        Destination adelaide = this.destinationMatrix.getNode("ADL").get();
        Destination goldCoast = this.destinationMatrix.getNode("OOL").get();

        // Act
        ShortestPath shortestPath = this.pathAlgorithmRevised.djikstras(adelaide, goldCoast);

        // Arrange
        int pathLength = shortestPath.getPath().size();

        // Assert
        assertEquals(0, pathLength);
    }

    @Test
    public void djikstasWhenStartEqualsEndReturnsPathMadeOfJustStart() {
        // Act
        ShortestPath shortestPath = this.pathAlgorithmRevised.djikstras(this.brisbane,
                this.brisbane);

        // Arrange
        int distanceTravelled = shortestPath.getLength();
        int pathSize = shortestPath.getPath().size();
        Destination start = shortestPath.getPath().get(0);

        // Assert
        assertEquals(0, distanceTravelled);
        assertEquals(1, pathSize);
        assertEquals(this.brisbane, start);
    }

}