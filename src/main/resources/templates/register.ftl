<#import "/website/head.ftl" as head>

<!DOCTYPE html>
<html>
    <@head.html title="Signup"/>
    <body>
        <#--  use include when you just need to include   -->
        <main class="o-section o-section--full-page">
            <div class="o-wrapper o-wrapper--signup">
                <section class="c-signup">
                    <h2 class="c-signup__heading">signup</h2>
                    <form action="/register/save" method="post" class="c-signup__form">
                        <section class="c-signup__form-section">
                            <h3 class="c-signup__sub-heading">Personal details</h3>
                            <input type="text" id="firstName" name="firstName" placeholder="first name" required class="c-signup__input"/>
                            <input type="text" id="lastName" name="lastName" placeholder="last name" required class="c-signup__input"/>
                            <#--  <input type="text" id="middlename" name="middleName" placeholder="middle name (optional)" class="c-signup__input"/>
                            <select id="gender" name="gender" placeholder="gender" required class="c-signup__input">
                                <option value="">--Please choose--</option>
                                <option value="male">male</option>
                                <option value="femal">female</option>
                                <option value="femal">other</option>
                            </select>
                            <label for="dob">Date of birth</label>
                            <input type="date" id="dob" name="dob" placeholder="dob" required class="c-signup__input"/>  -->
                        </section>
                        <section class="c-signup__form-section c-signup__form-section--dark">
                            <h3 class="c-signup__sub-heading">Account details</h3>
                            <input type="email" id="email" name="email" placeholder="email" required class="c-signup__input c-signup__input--light"/>
                            <#--  <input type="tel" id="phone" name="phone" pattern="04[0-9]{8}" placeholder="mobile number" required class="c-signup__input c-signup__input--light"/>  -->
                            <input type="password" id="password" name="password" placeholder="password" required class="c-signup__input c-signup__input--light"/>
                            <#--  <input type="password" id="confirmpassword" name="confirmPassword" placeholder="confirm password" required class="c-signup__input c-signup__input--light"/>  -->
                            <button type="submit" class="c-signup__button c-button">signup</button>
                        </section>
                    </form>
                    <div class="c-signup__divider"></div>
                    <p class="c-signup__message">
                        Already have an account? <a href="/login">login</a>
                    </p>
                </section>
            </div>
        </main>
        <#include "/website/scripts.ftl">
    </body>
</html>