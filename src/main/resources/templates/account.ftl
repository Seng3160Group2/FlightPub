<#import "spring.ftl" as spring>
<#import "/website/head.ftl" as head>
<!DOCTYPE html>
<html>
<@head.html title="Account"/>
<body>
    <#include "/website/header.ftl">

    <h3 style="text-align: center;font-family:'Catamaran';padding:10px;">Account</h3>
    
    <section>
        <div class="menu">
            <div class="info">
                <div class="info-category">Name</div>
                <div class="info-value">John Doe</div>
            </div>
            <div class="info">
                <div class="info-category">Age</div>
                <div class="info-value">30</div>
            </div>

        </div>
    </section>

     <#include "/website/footer.ftl">
</body>
</html>
