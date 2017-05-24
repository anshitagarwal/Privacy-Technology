<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
<head>
  <meta charset="UTF-8">
  <title>Giant Eagle | Auditor Page </title>
  <link href='http://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
      <link rel="stylesheet" href="css/style.css">

  
</head>

<body>
	<div class="form">
	<div class="tab-content" style="text-align: -webkit-center;">
	<form action="#">
		<h1>Auditor's Credentials</h1>
				<div class="top-row">
					<div class="field-wrap">
						<label> First Name<span class="req">*</span>
						</label> <input name="firstname" type="text" required autocomplete="off" />
					</div>
					<div class="field-wrap">
						<label> Last Name<span class="req">*</span>
						</label> <input name="lastname" type="text" required autocomplete="off" />
					</div>
				</div>
				<div class="field-wrap">
            <label>
              Purpose of Downloading Information
            </label>
            <textarea rows="4" cols="50"></textarea>
          </div>
		<h5>Check out our <a href="https://drive.google.com/file/d/0B8B5IJp1AaerbWxxa3pYc2dRcDg/view?usp=sharing" target="_blank">Privacy Policy.</a></h5>
				<h1>Download data of users who gave</h1>
		<ul class="tab-group">
		<button name="act" type="submit" class="button button-block" value="consent" style="cursor: pointer; margin: 10px; font-size: 20px; text-transform: none;"/>Consent</button>
		<button name="act" type="submit" class="button button-block" value="noconsent" style="cursor: pointer; margin: 10px; font-size: 20px; text-transform: none;"/>No Consent</button>
		</ul>
			</form>
	
	</div>
	</div>
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

<script src="js/index.js"></script>

</body>

</html>