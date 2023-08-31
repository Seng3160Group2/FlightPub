<#import "/website/head.ftl" as head>

<!DOCTYPE html>
<html>
    <@head.html title="Current Bookings"/>
    <body>
        <#--  use include when you just need to include   -->
        <#include "/website/header.ftl">

    <section>
        <div class="c-menu">
            <center><h3>Flight AB123</h3></center>
            <div class="info">
                <div class="info-category">Flight number</div>
                <div class="info-value">AB123</div>
            </div>
            <div class="info">
                <div class="info-category">Departure date</div>
                <div class="info-value">31/08/2023</div>
            </div>
            <div class="info">
                <div class="info-category">From</div>
                <div class="info-value">Newcastle</div>
            </div>
            <div class="info">
                <div class="info-category">To</div>
                <div class="info-value">New York</div>
            </div>
            <div class="info">
                <div class="info-category">Number of stopovers</div>
                <div class="info-value">2</div>
            </div>
        </div>
        <div class="c-menu">
            <center><h3>Flight AB124</h3></center>
            <div class="info">
                <div class="info-category">Flight number</div>
                <div class="info-value">AB124</div>
            </div>
            <div class="info">
                <div class="info-category">Departure date</div>
                <div class="info-value">02/0892023</div>
            </div>
            <div class="info">
                <div class="info-category">From</div>
                <div class="info-value">New York</div>
            </div>
            <div class="info">
                <div class="info-category">To</div>
                <div class="info-value">Newcastle</div>
            </div>
            <div class="info">
                <div class="info-category">Number of stopovers</div>
                <div class="info-value">3</div>
            </div>
        </div>
    </section>

        <#include "/website/footer.ftl">
        <#include "/website/scripts.ftl">
    </body>
</html>