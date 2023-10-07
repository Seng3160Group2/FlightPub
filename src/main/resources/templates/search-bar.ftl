<#import "/website/head.ftl" as head>

<!DOCTYPE html>
<html>
    <@head.html title="Login"/>
    <body>
        <div class="o-wrapper">
            <form class="c-search-bar">
                <input type="text" class="c-search-bar__input" placeholder="from...">
                <input type="text" class="c-search-bar__input" placeholder="to...">
                <input type="text" class="c-search-bar__input" placeholder="start date...">
                <input type="text" class="c-search-bar__input" placeholder="end date...">
                <input type="text" class="c-search-bar__input c-search-bar__input--clamp" placeholder="No. seats...">
                <button class="c-search-bar__button c-button">Search</button>
            </form>
        </div>
        <#include "/website/scripts.ftl">
    </body>
</html>