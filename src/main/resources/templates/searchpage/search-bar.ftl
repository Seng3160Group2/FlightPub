<div class="c-booking-progress">
    <div class="o-section">
        <div class="o-wrapper">
            <div class="c-booking-progress__progress-bar c-booking-progress__progress-bar--center ">
                <form action="/flights/search-results" method="get" class="s-search-bar">
                    <@spring.formInput "flightSearchForm.departure"/>

                    <@spring.formInput "flightSearchForm.destination"/>

                    <@spring.formInput "flightSearchForm.departureTime"/>

                    <@spring.formInput "flightSearchForm.returnTime"/>

                    <#if bindingResult?? && bindingResult.hasErrors()>
                        <p class="errorClass">
                            <#list bindingResult.allErrors as error>
                                ${error.defaultMessage}<br/>
                            </#list>
                        </p>
                    </#if>

                    <button type="submit" class="s-search-bar__button c-button">Search</button>
                </form>
            </div>
        </div>
    </div>
</div>