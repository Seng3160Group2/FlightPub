<#--  use imports when you need to inject values into a template  -->
<#import "/website/head.ftl" as head>

<!DOCTYPE html>
<html>
    <@head.html title="Example"/>
    <body>
        <#--  use include when you just need to include   -->
        <#include "/website/header.ftl">

        <main>
            IM MAINNNN
        </main>

        <#include "/website/footer.ftl">
        <#include "/website/scripts.ftl">
    </body>
</html>