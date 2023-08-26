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
            </section>
            <#--  <div class="vertical-line"></div>  -->
            <section class="c-user-details-summary">
                <h2>Summary</h2>
            </section>
        </div>
    </div>
     <#include "/website/footer.ftl">
</body>
</html>

