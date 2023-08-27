<#import "/website/head.ftl" as head>

<!DOCTYPE html>
<html>
    <@head.html title="Login"/>
    <body>
        <#--  use include when you just need to include   -->
        <main class="o-section o-section--full-page">
            <div class="o-wrapper o-wrapper--login">
                <article class="c-flight-card">
                    <div class="c-flight-card__container c-flight-card__container--full-width">
                        <span class="c-flight-card__text">Quantas</span>
                        <hr class="c-flight-card__line"></hr>
                    </div>
                    <div class="c-flight-card__container c-flight-card__container--horizontal c-flight-card__container--full-width">
                        <div class="c-flight-card__flight-details">
                            <div class="c-flight-card__container c-flight-card__container--align-right">
                                <span class="c-flight-card__text u-font-telex u-text--xl">06:00</span>
                                <span class="c-flight-card__text u-font-telex u-text--md u-text--upper">SYD</span>
                            </div>
                            <div class="c-flight-card__container c-flight-card__container--wide">
                                <span class="c-flight-card__text">1h 30m</span>
                                <hr class="c-flight-card__line"></hr>
                                <span class="c-flight-card__text c-flight-card__text--color-green">Direct</span>
                            </div>
                            <div class="c-flight-card__container c-flight-card__container--align-left">
                                <span class="c-flight-card__text u-font-telex u-text--xl">07:30</span>
                                <span class="c-flight-card__text u-font-telex u-text--md u-text--upper">BNE</span>
                            </div>
                        </div>
                        <div class="c-flight-card__container">
                            <span class="c-flight-card__text c-flight-card__text--color-grey u-text--sm">13 tickets left</span>
                            <span class="c-flight-card__text u-font-telex u-text--lg">$76</span>
                            <a href="" class="c-flight-card__button c-button u-font--bold">select</a>
                        </div>
                    </div>
                </article>
            </div>
        </main>
        <#include "/website/scripts.ftl">
    </body>
</html>