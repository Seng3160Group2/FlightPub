<#import "/website/head.ftl" as head>

<!DOCTYPE html>
<html>
    <@head.html title="Edit Details"/>
    <body>
        <#--  use include when you just need to include   -->
        <main class="o-section o-section--full-page">
            <div class="o-wrapper o-wrapper--signup">
                <section class="c-signup">
                    <h2 class="c-signup__heading">edit details</h2>
                    <form action="/signup" method="post" class="c-signup__form">
                        <section class="c-signup__form-section">
                            <h3 class="c-signup__sub-heading">Personal details</h3>
                            <input type="text" id="firstname" name="firstName" placeholder="first name" required class="c-signup__input" value="${account.firstName}"/>
                            <input type="text" id="lastname" name="lastName" placeholder="last name" required class="c-signup__input" value="${account.lastName}"/>
                            <input type="text" id="middlename" name="middleName" placeholder="middle name (optional)" class="c-signup__input" value="${account.middleName}"/>
                            <select id="gender" name="gender" placeholder="gender" required class="c-signup__input">
                                <option value="">--Please choose--</option>
                                <option value="male">male</option>
                                <option value="femal">female</option>
                                <option value="femal">other</option>
                            </select>
                            <label for="dob">Date of birth</label>
                            <input type="date" id="dob" name="dob" placeholder="dob" required class="c-signup__input"/>
                        </section>
                        <section class="c-signup__form-section c-signup__form-section--dark">
                            <h3 class="c-signup__sub-heading">Account details</h3>
                            <input type="email" id="email" name="email" placeholder="email" required class="c-signup__input c-signup__input--light"  value="${account.email}"/>
                            <input type="tel" id="phone" name="phone" pattern="04[0-9]{8}" placeholder="mobile number" required class="c-signup__input c-signup__input--light" value="${account.phone}"/>
                            <#--  <input type="password" id="password" name="password" placeholder="password" required class="c-signup__input c-signup__input--light"/>
                            <input type="password" id="confirmpassword" name="confirmPassword" placeholder="confirm password" required class="c-signup__input c-signup__input--light"/>  -->
                            <button type="submit" class="c-signup__button c-button">confirm</button>
                        </section>
                    </form>
                    <#--  <div class="c-signup__divider"></div>
                    <p class="c-signup__message">
                        Don't have an account? <a href="">Signup</a>
                    </p>  -->
                    <div class="c-button" style="margin:1rem; width:3rem"><a href="/account">Back</a></div>
                </section>
            </div>
        </main>
        <#include "/website/scripts.ftl">
    </body>
</html>