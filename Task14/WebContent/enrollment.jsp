<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
<head>
  <meta charset="UTF-8">
  <title>Giant Eagle | Welcome </title>
  <link href='http://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
      <link rel="stylesheet" href="css/style.css">

  
</head>

<body>
  <div class="form">
      
      <ul class="tab-group">
        <li class="tab active"><a href="#signup">Sign Up</a></li>
        <li class="tab"><a href="#login">Login</a></li>
      </ul>
      
      <div class="tab-content">
        <div id="signup">   
          <h1>Personal information</h1>
          
          <form action="/" method="post">
          
          <div class="top-row">
            <div class="field-wrap">
              <label>
                First Name<span class="req">*</span>
              </label>
              <input name = "firstname" type="text" required autocomplete="off" />
            </div>

        
            <div class="field-wrap">
              <label>
                Last Name<span class="req">*</span>
              </label>
              <input name = "lastname" type="text"required autocomplete="off"/>
            </div>
          </div>

          <!-- <div class="field-wrap">
            <label>
              Street Address<span class="req">*</span>
            </label>
            <input type="text"required autocomplete="off"/>
          </div>

          <div class="field-wrap">
           <label>
              Address Line 2<span class="req">(optional)</span>
            </label>
            <input type="text"required autocomplete="off"/>
          </div> -->



            <!-- div class="top-row">
            <div class="field-wrap">
            <label>
              City<span class="req">*</span>
            </label>
            <input type="text"required autocomplete="off"/ "> 
            </div>


          <div class="field-wrap">
            <label>
              Zip<span class="req">*</span>
            </label>
            <input type="text"required autocomplete="off" /"> 
            </div>


            </div>

            <div class="top-row">
            <div class="field-wrap">
            <label>
              State<span class="req">*</span>
            </label>
            <input type="text"required autocomplete="off"/ "> 
            </div> -->

<!-- 
          <div class="field-wrap">
            <label>
              Country<span class="req">*</span>
            </label>
            <input type="text"required autocomplete="off" /"> 
            </div>
            </div> -->



            <div class="field-wrap">
           <!--  <label>
              Birth Date<span class="req">*</span>
            </label> -->
            <!-- <input type="date"required autocomplete="off"/> -->
<!-- 
            <input class="form-control" type="text" onfocus="(this.type='date'); document.getElementById('datePlaceholder').innerText = ''" onblur="(this.type='text'); datePlaceholder" id="date"> -->
            <!-- <input onfocus="(this.type='date')" class="js-form-control" placeholder="Birth Date*"> -->
            <input name="dob" placeholder="Birth Date*" type="text" required onfocus="(this.type='date')" onfocusout="(this.type='text')">
          </div>


             <div class="field-wrap">
            <label>
              Phone number<span class="req">(optional)</span>
            </label>
            <input name="phonenumber" type="text"required autocomplete="off"/>
          </div>

          <h1>Account details</h1>

  <div class="field-wrap">
            <label>
              Email Address<span class="req">*</span>
            </label>
            <input name="email" type="email"required autocomplete="off"/>
          </div>


            <input name="subscription" id="checkbox" type="checkbox" style="float: left; width: 25px;"><h5> 
              Sign up to receive email updates on discounts, savings, promotions and other Giant Eagle offerings. 
            </h5></input>
          
          <div class="field-wrap">
            <label>
              Password<span class="req">*</span>
            </label>
            <input name="password" type="password"required autocomplete="off"/>
          </div>

          <div class="field-wrap">
            <label>
              Confirm Password<span class="req">*</span>
            </label>
            <input name="confirmpassword" type="password"required autocomplete="off"/>
          </div>

          <h5> By creating an account, you agree to Giant Eagle’s Terms of Use and <a href="#later">Privacy Policy. </a> </h5>
          
          <button name="registerbutton" type="submit" class="button button-block"/>Register</button>
           <h3 class="tab">Already have an account? <a href="#login">Login</a></ </h3>


          </form>


        </div>
        
        <div id="login">   
          
          
          <form action="/" method="post">
          
          <div class="field-wrap">
            <label>
              Email Address<span class="req">*</span>
            </label>
            <input name = "loginemail" type="email"required autocomplete="off"/>
          </div>
          
          <div class="field-wrap">
            <label>
              Password<span class="req">*</span>
            </label>
            <input name = "loginpassword" type="password"required autocomplete="off"/>
          </div>
          
          <p class="forgot"><a href="#">Forgot Password?</a></p>
          
          <button class="button button-block"/>Log In</button>
          
          </form>

        </div>
        
      </div><!-- tab-content -->
      
</div> <!-- /form -->
  <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

    <script src="js/index.js"></script>

</body>
</html>
