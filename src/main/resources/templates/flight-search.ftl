<#import "spring.ftl" as spring>

<!DOCTYPE html>
<html>
<head>
    <title>Flight Search</title>
</head>
<body>
    <h1>Flight Search</h1>
    <form action="/flights/search-results" method="get">
        <label for="airlineCode">Airline Code:</label>
        <@spring.formInput "flightSearchForm.airlineCode"/>
        <@spring.showErrors "<br/>"/>

        <label for="flightNumber">Flight Number:</label>
        <@spring.formInput "flightSearchForm.flightNumber"/>
        <@spring.showErrors "<br/>"/>

        <label for="departureTime">Departure Time:</label>
        <@spring.formInput "flightSearchForm.departureTime"/>
        <@spring.showErrors "<br/>"/>

        <#if bindingResult?? && bindingResult.hasErrors()>
            <p class="errorClass">
                <#list bindingResult.allErrors as error>
                    ${error.defaultMessage}<br/>
                </#list>
            </p>
        </#if>

        <button type="submit">Search</button>
    </form>
</body>
</html>

