import React from "react";

const ChangePassword = () => {
  return (
    <div className="col-lg-8 change-password">
      <div className="password-content">
        <h3>Change Password</h3>
        <p>Here you can change your password please fill up the form.</p>
        <form data-bitwarden-watching="1">
          <div className="row">
            <div className="col-lg-12">
              <div className="form-group">
                <label>Old Password</label>
                <input className="form-control" type="password" name="password" />
              </div>
            </div>
            <div className="col-lg-12">
              <div className="form-group">
                <label>New Password</label>
                <input className="form-control" type="password" name="password" />
                <i className="bx bxs-low-vision"></i>
              </div>
            </div>
            <div className="col-lg-12">
              <div className="form-group last">
                <label>Confirm Password</label>
                <input className="form-control" type="password" name="password" />
                <i className="bx bxs-low-vision"></i>
              </div>
            </div>
            <div className="col-lg-12">
              <div className="button">
                <button className="btn">Save Change</button>
              </div>
            </div>
          </div>
        </form>
      </div>
    </div>
  );
};

export default ChangePassword;
