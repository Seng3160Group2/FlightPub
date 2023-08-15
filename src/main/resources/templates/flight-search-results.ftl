<#import "spring.ftl" as spring>
<#import "/website/head.ftl" as head>
<!DOCTYPE html>
<html>
<@head.html title="Flight Search Results"/>
<body>
    <#include "/website/header.ftl">
    <section class="recommendations">
        <div class="recommendation">
            <p class="destination-text">Test</p>
            <p class="booking-text">book now</p>
        </div>
        <div class="recommendation">
            <p class="destination-text">Test</p>
            <p class="booking-text">book now</p>
        </div>
        <div class="recommendation">
            <p class="destination-text">Test</p>
            <p class="booking-text">book now</p>
        </div>
        <div class="recommendation">
            <p class="destination-text">Test</p>
            <p class="booking-text">book now</p>
        </div>
    </section>


    <h1>Flight Search Results</h1>
    <#if flight?has_content>
        <ul>
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
            <li><strong>Group Flight:</strong> ${flight.groupFlight?string('yes', 'no') }</li>
        </ul>
    <#else>
        <p>No flight found.</p>
    </#if>
</body>
</html>
