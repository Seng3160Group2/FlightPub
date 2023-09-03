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
                <div class="info-value">${account.firstName} ${account.middleName} ${account.lastName}</div>
            </div>
            <div class="info">
                <div class="info-category">Date of birth</div>
                <div class="info-value">${account.dob}</div>
            </div>
            <div class="info">
                <div class="info-category">Gender</div>
                <div class="info-value">${account.gender}</div>
            </div>
            <div class="info">
                <div class="info-category">Email</div>
                <div class="info-value">${account.email}</div>
            </div>
            <div class="info">
                <div class="info-category">Preferences</div>
                <div class="info-value">None</div>
            </div>
           <div class="info">
                <div class="info-category">Phone number</div>
                <div class="info-value">${account.phone}</div>
            </div>
            <#--  <div class="info">
                <div class="info-category">User type</div>
                <div class="info-value">general</div>
            </div>
            <div class="info">
                <div class="info-category">Booked flights</div>
                <div class="info-value">[list of flights here]</div>
            </div>  -->
            <#--  <div class="info">
                <div class="info-category">Flight history</div>
                <div class="info-value">[list of flights here]</div>
            </div>  -->
            <table>
                <tr>
                    <td>
                        <div class="c-button">Edit Details</div>
                    </td>
                    <td>
                        <div class="c-button"><a href="/account/current-bookings">View Bookings</a></div>
                    </td>
                    <td>
                        <div class="c-button"><a href="/account/flight-history">View Flight History</a></div>
                    </td>
                    <#--  <td>
                        <div class="c-button" id="view-details">View Passenger Details Card</div>
                    </td>  -->
                    <td>
                        <div class="c-button">Delete Account</div>
                    </td>
                    
                </tr>
                <tr>
                <td>
                    <div class="c-button"><a href="login">Log out</a></div>
                    </td>
                </tr>
            </table>
        </div>
    </section>
    <#--  <div class="c-user-card" id="popup-overlay">
        <div class="popup">
            <h3>Passenger Details</h3>
            <div class="details">
                <p><strong>Name:</strong> John Doe</p>
                <p><strong>Email:</strong> john@example.com</p>
                <p><strong>Phone:</strong> 123-456-7890</p>
                <p><strong>Age:</strong> 30</p>
                <p><strong>Gender:</strong> Male</p>
            </div>
            <div class="c-button" id="close-details">Close</div>
        </div>
    </div>
    <script>
        const openPopupButton = document.getElementById('view-details');
const closePopupButton = document.getElementById('close-details');
const popupOverlay = document.getElementById('popup-overlay');

openPopupButton.addEventListener('click', () => {
    popupOverlay.style.display = 'block'; // Show the overlay
});

closePopupButton.addEventListener('click', () => {
    popupOverlay.style.display = 'none'; // Hide the overlay
});
    </script>  -->
     <#include "/website/footer.ftl">
</body>
</html>

