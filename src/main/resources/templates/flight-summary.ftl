<#import "/website/head.ftl" as head>

<!DOCTYPE html>
<html>
    <@head.html title="Login"/>
    <body>
        <#--  use include when you just need to include   -->
        <main class="o-section o-section--full-page">
            <div class="o-wrapper o-wrapper--login">
                <article class="c-flight-card">
                    <hr class="c-flight-card__line"></hr>
                    <div class="c-flight-card__container c-flight-card__container--horizontal c-flight-card__container--full-width c-flight-card__container--gap">
                        <span class="c-flight-card__text u-font-telex">MH183</span>
                        <div class="c-flight-card__container c-flight-card__container--horizontal c-flight-card__container--gap">
                            <span class="c-flight-card__text u-font-telex">Sydney</span>
                            <i class="fa-solid fa-arrow-right"></i>
                            <span class="c-flight-card__text u-font-telex">Perth</span>
                        </div>
                        <span class="c-flight-card__text u-font-telex">Sunday 27 Aug 2023, 9:45am</span>
                    </div>
                </article>
            </div>
        </main>
        <#include "/website/scripts.ftl">
    </body>
</html>