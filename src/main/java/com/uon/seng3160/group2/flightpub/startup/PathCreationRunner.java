package com.uon.seng3160.group2.flightpub.startup;

import java.util.List;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uon.seng3160.group2.flightpub.entity.Destination;
import com.uon.seng3160.group2.flightpub.entity.DestinationMatrix;
import com.uon.seng3160.group2.flightpub.service.PathAlgorithmRevised;
import com.uon.seng3160.group2.flightpub.service.impl.PathService;

@Service
@Transactional
public class PathCreationRunner implements ApplicationRunner {
    private final DestinationMatrix destinationMatrix;
    private final PathService pathService;
    private final PathAlgorithmRevised pathAlgorithmRevised;

    public PathCreationRunner(DestinationMatrix destinationMatrix, PathService pathService,
            PathAlgorithmRevised pathAlgorithmRevised) {
        this.destinationMatrix = destinationMatrix;
        this.pathService = pathService;
        this.pathAlgorithmRevised = pathAlgorithmRevised;
    }

    public void run(ApplicationArguments args) throws Exception {
        System.out.println("running");
        this.destinationMatrix.populate();
        List<Destination> destinations = this.destinationMatrix.getDestinations();
        System.out.println(destinations);
        // this.pathAlgorithmRevised.printMatrix();
        this.pathService.populate(destinations);
    }
}
