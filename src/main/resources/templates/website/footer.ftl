<footer class="c-footer">
    <a class="c-footer__logo" href="/">FlightPub</a>
    <ul class="c-footer__navbar">
        <li class="c-footer__navbar-item" id="view-details">Help</li>
        <li class="c-footer__navbar-item">FAQ</li>
    </ul>
</footer>  
<div class="c-user-card" id="popup-overlay">
        <div class="popup">
            <h3>Help</h3>
            <div class="details">
                <p><strong>Please email:</strong> c3380053@uon.edu.au</p>
                <p><strong>Or phone:</strong> 0481 710 700</p>
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
    </script>