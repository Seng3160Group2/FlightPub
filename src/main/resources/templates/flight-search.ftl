<#import "spring.ftl" as spring>
<#import "/website/head.ftl" as head>

<!DOCTYPE html>
<html>
    <@head.html title="Flight Search"/>
    <body>
        <#include "/website/header.ftl">
        <#include "/searchpage/search-bar.ftl">
        <div class="o-section o-section--empty">
        </div>
        <#--  <div class="o-wrapper">
            <form action="/flights/search-results" method="get" class="s-search-bar">
                <@spring.formInput "flightSearchForm.departure"/>
                <@spring.showErrors "<br/>"/>

                <@spring.formInput "flightSearchForm.destination"/>
                <@spring.showErrors "<br/>"/>

                <@spring.formInput "flightSearchForm.departureTime"/>
                <@spring.showErrors "<br/>"/>

                <@spring.formInput "flightSearchForm.returnTime"/>
                <@spring.showErrors "<br/>"/>

                <#if bindingResult?? && bindingResult.hasErrors()>
                    <p class="errorClass">
                        <#list bindingResult.allErrors as error>
                            ${error.defaultMessage}<br/>
                        </#list>
                    </p>
                </#if>

                <button type="submit" class="s-search-bar__button c-button">Search</button>
            </form>
        </div>  -->
        <#include "/website/footer.ftl">
        <#include "/website/scripts.ftl">
    </body>
</html>
