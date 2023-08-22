<#import "spring.ftl" as spring>
<#import "/website/head.ftl" as head>
<!DOCTYPE html>
<html>
<@head.html title="Account"/>
<body>
    <#include "/website/header.ftl">

    <h3 style="text-align: center;font-family:'Catamaran';padding:10px;">Account</h3>
    
    <section>
        <div class="c-menu">
            <div class="info">
                <div class="info-category">Name</div>
                <div class="info-value">John Doe</div>
            </div>
            <div class="info">
                <div class="info-category">Age</div>
                <div class="info-value">30</div>
            </div>
            <div class="info">
                <div class="info-category">Gender</div>
                <div class="info-value">one of them idk</div>
            </div>
            <div class="info">
                <div class="info-category">Email</div>
                <div class="info-value">whatever@gmail.com</div>
            </div>
            <div class="info">
                <div class="info-category">Preferences</div>
                <div class="info-value">idk</div>
            </div>
            <div class="info">
                <div class="info-category">User type</div>
                <div class="info-value">general</div>
            </div>
            <div class="info">
                <div class="info-category">Booked flights</div>
                <div class="info-value">[list of flights here]</div>
            </div>
            <div class="info">
                <div class="info-category">Flight history</div>
                <div class="info-value">[list of flights here]</div>
            </div>
            <table>
                <tr>
                    <td>
                        <div class="c-button">Edit Details</div>
                    </td>
                    <td>
                        <div class="c-button">Delete Account</div>
                    </td>
                    
                </tr>
                <tr>
                    
                </tr>
            </table>
            
            

        </div>
    </section>

     <#include "/website/footer.ftl">
</body>
</html>
