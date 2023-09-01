<#import "/website/head.ftl" as head>

<!DOCTYPE html>
<html>
    <@head.html title="Login"/>
    <body>
        <#--  use include when you just need to include   -->
        <main class="o-section o-section--full-page">
            <div class="o-wrapper o-wrapper--login">
                <section class="c-login">
                    <h2 class="c-login__heading">Login</h2>
                    <form action="/authenticate" method="post" class="c-login__form">
                        <input type="text" id="username" name="username" placeholder="username" required class="c-login__input">

                        <input type="password" id="password" name="password" placeholder="password" required class="c-login__input">

                        <button type="submit" class="c-button">Login</button>
                    </form>
                    <div class="c-login__divider"></div>
                    <p class="c-login__message">
                        Don't have an account? <a href="/register">Signup</a>
                    </p>
                </section>
            </div>
        </main>
        <#include "/website/scripts.ftl">
    </body>
</html>