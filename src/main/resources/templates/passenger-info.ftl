<#import "spring.ftl" as spring>
<#import "/website/head.ftl" as head>
<!DOCTYPE html>
<html>
<@head.html title="Booking"/>
<body>
    <#include "/website/header.ftl">

    <h3 style="text-align: center;font-family:'Catamaran';padding:10px;">Overview -> Booking</h3>
    <div class="o-wrapper">
        <div class="c-user-details-wrapper">
            <section class="c-user-details-input">
                <h2>Passenger Info</h2>
                <div class="input-container">
                    <p>Passenger Name</p>
                    <input type="text" placeholder="First Name">
                    <input type="text" placeholder="Middle Name">
                    <input type="text" placeholder="Surname">
                </div>
                <div class="input-container">
                    <p style="margin-top:1rem; margin-bottom:0.2rem">Country Code</p>
                    <input type="text" placeholder="select">
                </div>
                <div class="input-container">
                    <p style="margin-top:1rem; margin-bottom:0.2rem">Phone Number</p>
                    <input type="tel" placeholder="input" pattern="[0-9]{3}-[0-9]{3}-[0-9]{4}" title="Format: XXX-XXX-XXXX">
                </div>
                <div class="input-container">
                    <p style="margin-top:1rem; margin-bottom:0.2rem">Age</p>
                    <input type="number" placeholder="select" min="0" max="120">
                </div>
                <div class="input-container">
                    <p style="margin-top:1rem; margin-bottom:0.2rem">Gender</p>
                    <select>
                        <option value="" disabled selected>select</option>
                        <option value="male">Male</option>
                        <option value="female">Female</option>
                        <option value="other">Other/Unspecified</option>
                    </select>
                </div>
                <div class="input-container">
                    <p style="margin-top:1rem; margin-bottom:0.2rem">Dietary Requirements</p>
                    <input type="text" placeholder="please specify any requirements">
                </div>
                <div class="input-container">
                    <p style="margin-top:1rem; margin-bottom:0.2rem">Seat</p>
                    <input type="text" placeholder="select">
                </div>
                <div class="vertical-line"></div>
                <section class="user-flight-details"></section>
                    <#--  <h2>Flight Details</h2>  -->
                    <!-- Flight details here -->
                </section>
                

            </section>
            <#--  <div class="vertical-line"></div>  -->
            <section class="c-user-details-summary">
                <h2>Summary</h2>
                <p>Adult Ticket</p>
                <p>Tax</p>
                <hr>
                <h2>Total</h2>
            </section>
        </div>
    </div>
     <#include "/website/footer.ftl">
</body>
</html>

