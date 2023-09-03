<#import "spring.ftl" as spring>
<#import "/website/head.ftl" as head>
<!DOCTYPE html>
<html>
<@head.html title="Flight Search Results"/>
<body>
    <#include "/website/header.ftl">
    <section class="recommendations">
        <div class="recommendation">
            <img src="https://en.wikipedia.org/wiki/File:View_of_Empire_State_Building_from_Rockefeller_Center_New_York_City_dllu_(cropped).jpg" alt="New York"/>
            <p class="destination-text">New York</p>
            <p class="booking-text">book now</p>
        </div>
        <div class="recommendation">
            <img src="" alt="Hong Kong"/>
            <p class="destination-text">Hong Kong</p>
            <p class="booking-text">book now</p>
        </div>
        <div class="recommendation">
            <img src="" alt="Dubai"/>
            <p class="destination-text">Dubai</p>
            <p class="booking-text">book now</p>
        </div>
        <div class="recommendation">
            <img src="" alt="Paris"/>
            <p class="destination-text">Paris</p>
            <p class="booking-text">book now</p>
        </div>
    </section>

<section class="searchbar-section">
    <div class="searchbar-container">
        <div class="searchbar-segment">
            <input type="text" class="searchbar-input searchbar-start" placeholder="from...">
        </div>
        <div class="searchbar-segment">
            <input type="text" class="searchbar-input searchbar-middle" placeholder="to...">
        </div>
        <div class="searchbar-segment">
            <input type="text" class="searchbar-input searchbar-middle" placeholder="start date...">
        </div>
        <div class="searchbar-segment">
            <input type="text" class="searchbar-input searchbar-middle" placeholder="end date...">
        </div>
        <div class="searchbar-segment">
            <input type="text" class="searchbar-input searchbar-end" placeholder="No. seats...">
        </div>
    </div>
    <button class="searchbar-button">Search</button>
</section>

    <h3 style="text-align: center;font-family:'Catamaran';padding:10px;">Flight Search Results</h3>
    <section class="filter-results">
        
        <section class="filters-section">
            <h4>Filters</h4>
            <p>Price Range</p>
            <p>Date Range</p>
            <p>Class</p>
            <p>Sort by</p>
        </section>

        <section class="search-results-section">
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
        </section>


    </section>
     <#include "/website/footer.ftl">
</body>
</html>
