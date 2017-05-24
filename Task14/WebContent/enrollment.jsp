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
        <li class="tab active"><a href="#signup">Enroll</a></li>
        <li class="tab"><a href="#login">Update Information</a></li>
      </ul>
      
      <div class="tab-content">
        <div id="signup">   
          <h1>Personal information</h1>
          
          <form action="#" method="post">
          
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

            <div class="field-wrap">
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

		  <input type="radio" name="consent" value="YES" style="float: left; width: 15px; height: 15px; margin-right: 10px;"> 
          <h5>
          	I agree to Giant Eagle’s Terms of Use and <a href="https://drive.google.com/file/d/0B8B5IJp1AaerbWxxa3pYc2dRcDg/view?usp=sharing" target="_blank">Privacy Policy. </a> <br>
          </h5>
           <input type="radio" name="consent" value="NO" style="float: left; width: 15px; height: 15px; margin-right: 10px;"> 
           <h5>
  			I agree to Giant Eagle’s Terms of Use and <a href="https://drive.google.com/file/d/0B8B5IJp1AaerbWxxa3pYc2dRcDg/view?usp=sharing" target="_blank">Privacy Policy</a>, but choose to remain deidentified. <br>
           </h5>
          
          <button name="registerbutton" type="submit" class="button button-block" onclick="showRegisterAlert()"/>Register</button>
           <h3 class="tab">Already have an account? <a href="#login">Login </a></h3>
<script>
	function showRegisterAlert() {
	    alert("Your information has been added to our database along with your preferences.");
	}
</script>


          </form>


        </div>
        
        <div id="login">   
  
          <h1>Personal information</h1>
          <form action="#" method="post">
          <div class="top-row">
            <div class="field-wrap">
              <label>
                First Name<span class="req">*</span>
              </label>
              <input name = "firstname" type="text" required />
            </div>

        
            <div class="field-wrap">
              <label>
                Last Name<span class="req">*</span>
              </label>
              <input name = "lastname" type="text"required autocomplete="off"/>
            </div>
          </div>

            <div class="field-wrap">
            <input name="dob" placeholder="Birth Date*" type="text" required onfocus="(this.type='date')" onfocusout="(this.type='text')">
            </div>


             <div class="field-wrap">
            <label>
              Phone number
            </label>
            <input name="phonenumber" type="text"required autocomplete="off"/>
          </div>

          <h1>Account details</h1>

  		  <div class="field-wrap">
            <label>
              Email Address
            </label>
            <input name="email" type="email" autocomplete="off"/>
          </div>
          <div class="field-wrap">
            <label>
              Street Address
            </label>
            <input name="address" type="text" autocomplete="off"/>
          </div>
          <div class="field-wrap">
            <label>
              City
            </label>
            <input name="city" type="text" autocomplete="off"/>
          </div>
          <div class="field-wrap">
            <label>
              ZIP
            </label>
            <input name="zip" type="text" autocomplete="off"/>
          </div>
          <div class="field-wrap">
            <label>
              State
            </label>
            <input name="state" type="text" autocomplete="off"/>
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
              Reason for Information Update<span class="req">*</span>
            </label>
            <textarea rows="4" cols="50" required="required"></textarea>
          </div>
          
		  <input type="radio" name="consent" value="YES" style="float: left; width: 15px; height: 15px; margin-right: 10px;"> 
          <h5>
          	I agree to Giant Eagle’s Terms of Use and <a href="https://drive.google.com/file/d/0B8B5IJp1AaerbWxxa3pYc2dRcDg/view?usp=sharing" target="_blank">Privacy Policy. </a> <br>
          </h5>
           <input type="radio" name="consent" value="NO" style="float: left; width: 15px; height: 15px; margin-right: 10px;"> 
           <h5>
  			I agree to Giant Eagle’s Terms of Use and <a href="https://drive.google.com/file/d/0B8B5IJp1AaerbWxxa3pYc2dRcDg/view?usp=sharing" target="_blank">Privacy Policy</a>, but choose to remain deidentified. <br>
           </h5>
          
          <button name="registerbuttonupdate" type="submit" class="button button-block" onclick="showAlert()"/>Update Information</button>
          
<script>
	function showAlert() {
	    alert("Your request has been submitted to Giant Bagel. It will be reviewed and updated.");
	}
</script>

		</form>
		</div>
        </div>
      </div><!-- tab-content -->
  <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

    <script src="js/index.js"></script>

</body>
</html>
