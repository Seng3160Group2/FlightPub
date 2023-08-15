<#import "/website/head.ftl" as head>

<!DOCTYPE html>
<html>
    <@head.html title="Login"/>
    <body>
        <div class="o-wrapper">
            <section class="c-search-bar">
                <div class="c-search-bar__input-container">
                    <input type="text" class="c-search-bar__input" placeholder="from...">
                    <input type="text" class="c-search-bar__input" placeholder="to...">
                    <input type="text" class="c-search-bar__input" placeholder="start date...">
                    <input type="text" class="c-search-bar__input" placeholder="end date...">
                    <input type="text" class="c-search-bar__input c-search-bar__input--clamp" placeholder="No. seats...">
                </div>
                <button class="c-search-bar__button c-button">Search</button>
            </section>
        </div>
        <#include "/website/scripts.ftl">
    </body>
</html>