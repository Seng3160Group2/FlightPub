<#import "spring.ftl" as spring>
<#import "/website/head.ftl" as head>
<!DOCTYPE html>
<html>
<@head.html title="Flight Search Results"/>
<body>
    <#include "/website/header.ftl">
    <#include "/searchpage/result.ftl">
    <section class="o-section">
        <div class="o-wrapper">
            ${result?string('Congrats your booking is successful', 'oops something went wrong')}
        </div>
    </section>
    <#include "/website/footer.ftl">
</body>
</html>
