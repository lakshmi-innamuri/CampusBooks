

		<section class="sectionContents">
			<form id="emailSeller"
				action="<%=request.getContextPath()%>/CampusBooksServlet">

				<div class="textpadding" >
					<h3>Contact Seller</h3>
				</div>

				<div class="textpadding" style="margin-left: 1px;">
					<div class="alignleft">
						<label>Subject :</label>
					</div>
					<div class="alignleft" style="padding-left: 110px;">
						<input type="text" name="subject" title="Subject">
					</div>
					<div style="clear: both;"></div>
				</div>
				<div class="textpadding">
					<div class="alignleft">
						<label>Write your message :</label>
					</div>
					<div class="alignleft" style="padding-left: 15px;">
						<textarea rows=5 cols=50 name="message" title="Message"></textarea>
					</div>
					<div style="clear: both;"></div>
				</div>
				<div class="textpadding buttonAlignment">
					<input class="button" type="submit" value="Send Email"
						name="emailSeller">
				</div>

			</form>

		</section>
	
