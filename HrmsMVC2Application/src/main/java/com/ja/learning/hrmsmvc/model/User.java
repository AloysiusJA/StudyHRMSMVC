package com.ja.learning.hrmsmvc.model;


	public class User {
		
		private long userID;
		private String userName;
		private String password;
		private String email;
		private long phone;
		private long roleid;
		private boolean isValid;
		public long getUserID() {
			return userID;
		}
		public void setUserID(long userID) {
			this.userID = userID;
		}
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public long getPhone() {
			return phone;
		}
		public void setPhone(long phone) {
			this.phone = phone;
		}
		public long getRoleid() {
			return roleid;
		}
		public void setRoleid(long roleid) {
			this.roleid = roleid;
		}
		public boolean isValid() {
			return isValid;
		}
		public void setValid(boolean isValid) {
			this.isValid = isValid;
		}
		@Override
		public String toString() {
			return "User [userID=" + userID + ", userName=" + userName + ", password=" + password + ", email=" + email
					+ ", phone=" + phone + ", roleid=" + roleid + "]";
		}
		
		
		

}
