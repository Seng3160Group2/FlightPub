<#import "spring.ftl" as spring>

<!DOCTYPE html>
<html>
<head>
    <title>Flight Search Results</title>
</head>
<body>
    <h1>Flight Search Results</h1>
    <form action="/flight-search-returns" method="post">
    <#assign maxFlights = 0>
    <#if journeys?has_content>
    <#list journeys as journey>
        <ul>
            <#list journey as flight>
                <#if maxFlights < 8>
                    <h2>Flight ${flight.airlineCode} to ${flight.destinationCode}</h2>
                    <li><strong>Airline Code:</strong> ${flight.airlineCode}</li>
                    <li><strong>Flight Number:</strong> ${flight.flightNumber}</li>
                    <li><strong>Departure Code:</strong> ${flight.departureCode}</li>
                    <li><strong>Stopover Code:</strong> ${flight.stopOverCode}</li>
                    <li><strong>Destination Code:</strong> ${flight.destinationCode}</li>
                    <li><strong>Departure Time:</strong> ${flight.departureTime}</li>
                    <li><strong>Arrival Time (Stopover):</strong> ${flight.arrivalTimeStopOver}</li>
                    <li><strong>Departure Time (Stopover):</strong> ${flight.departureTimeStopOver}</li>
                    <li><strong>Arrival Time:</strong> ${flight.arrivalTime}</li>
                    <li><strong>Plane Code:</strong> ${flight.planeCode}</li>
                    <li><strong>Duration:</strong> ${flight.duration} minutes</li>
                    <li><strong>Duration (Second Leg):</strong> ${flight.durationSecondLeg} minutes</li>
                    <li><strong>Group Flight:</strong> ${flight.groupFlight?string('yes', 'no')}</li>  
                </#if>
                <#assign maxFlights = maxFlights + 1>  
            </#list>`
            <hr/> 
        </ul>
    </#list>
<#else>
    <p>No flights found.</p>
</#if>
<button type="submit">Book Flight</button>
    </form>
</body>
</html>
