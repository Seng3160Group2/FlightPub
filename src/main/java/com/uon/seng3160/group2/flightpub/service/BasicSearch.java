
import org.springframework.data.jpa.repository.JpaRepository;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BasicSearch{
    int magnitude = 4;
    List<Destination> searchDestinationsList = new ArrayList<>();
    Destination startDestination;
    Destination endDestination;
    List<List<Destination>> searchResults = new ArrayList<>();

    public BasicSearch(DestinationBean start, DestinationBean end){
        destinations = new DestinationRepository().getAll();
        distances = new DistanceRepository().getAll();
        startDestination = start;
        endDestination = end;
        createGraph();
    }

    private void createGraph(){
        searchDestinationsList.add(startDestination);
        Set<Distance> distances = new HashSet<Distance>;
        for(int j = 0; j < searchDestinationsList.size(); j++){
            distances = searchDestinationList.get(j).getDistancesFrom();
            for(int i = 0; i < distances.size(); i++){
                searchDestinationList.add(distances.get(i).getDestinationTo());
            }
            distances = searchDestinationList.get(i).getDistancesTo();
            for(int i = 0; i < distances.size(); i++){
                searchDestinationList.add(distances.get(i).getDestinationFrom());
            }
        }
    }

    public List<List<Destination>> bfs(Destination[] graph, Destination start, Destination end){
        List<List<Destination>> pathsList = new ArrayList<>();
        Queue<List<Destination>> queue = new LinkedList<>();

        queue.add(new ArrayList<>(List.of(start)));

        while(!queue.empty()){
            List<Destination> currentPath = queue.poll();
            Destination lastNode = currentPath.get(currentPath.size()-1);

            if(lastNode == end){
                pathsList.add(new ArrayList<>(currentPath));
            }
            else {
                //get shortest distance unvisited neighbour
                Distance[] distances = currentPath.get(currentPathNode).getDistances().toArray;
                for(int i = 0; i < distances.length; i++){
                    if(!distances[i].getDestination().getVisited() && !currentPath.contains(distances[i].getDestination())){
                        List<Destination> newPath = new ArrayList<>(currentPath);
                        newPath.add(distances[i].getDestination());
                        queue.add(newPath);
                    }
                }
            }
        }
        return pathsList;
    }

    public void createSearchResults(Destination[] graph, Destination start, Destination end){
        createGraph();
        List<Destination>[] pathsList = bfs(graph, start, end).toArray();
        int[] pathDistances = new int[pathsList.length];
        int pathDist = 0;
        for(int i = 0; i < pathsList.length; i++){
            for(int j = 0; j < pathsList.get(i).size()-1; j++){
                //get the distance from jth destination to j+1th destination and add to total
                pathDist += pathsList[i].get(j).getDistanceTo(pathsList[i].get(j+1));
            }
            pathDistances[i] = pathDist;
        }
        int tempDist = pathDistances[0];
        int indexOfTemp = 0;
        while(!pathDistances.empty()){
            for(int i = 1; i < pathDistances.length; i++){
                if(tempDist > pathDistances[i]){
                    tempDist = pathDistances[i];
                    indexOfTemp = i;
                }
            }
            searchResults.add(pathsList[indexOfTemp]);
            pathsList.remove(indexOfTemp);
            pathDistances.remove(indexOfTemp);
        }
    }
}