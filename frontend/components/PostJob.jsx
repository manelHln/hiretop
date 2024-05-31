import React from "react";

const PostJob = () => {
  return (
    <>
      <div className="breadcrumbs overlay">
        <div className="container-sm">
          <div className="row">
            <div className="col-12">
              <div className="breadcrumbs-content">
                <h1 className="page-title">Post a Job</h1>
                <p>
                  Business plan draws on a wide range of knowledge from
                  different business
                  <br /> disciplines. Business draws on a wide range of
                  different business .
                </p>
              </div>
              <ul className="breadcrumb-nav">
                <li>
                  <a href="index.html">Home</a>
                </li>
                <li>
                  <a href="news-standard.html">Blog</a>
                </li>
                <li>Post a Job</li>
              </ul>
            </div>
          </div>
        </div>
      </div>

      <section className="job-post section">
        <div className="container-sm">
          <div className="row">
            <div className="col-lg-10 offset-lg-1 col-12">
              <div className="job-information">
                <h3 className="title">Job Information</h3>
                <form>
                  <div className="row">
                    <div className="col-lg-12">
                      <div className="form-group">
                        <label>Job title*</label>
                        <input
                          className="form-control"
                          type="text"
                          name="title"
                        />
                      </div>
                    </div>
                    <div className="col-lg-6 col-md-6">
                      <div className="form-group">
                        <label>Category*</label>
                        <select className="select">
                          <option value="1">UX/UI Designer</option>
                          <option value="2">Web Developer</option>
                          <option value="3">Web Designer</option>
                          <option value="4">Software Developer</option>
                          <option value="5">SEO</option>
                        </select>
                      </div>
                    </div>
                    <div className="col-lg-6 col-md-6">
                      <div className="form-group">
                        <label>Job Types*</label>
                        <select className="select">
                          <option value="1">Full Time</option>
                          <option value="2">Part Time</option>
                          <option value="3">Contract</option>
                          <option value="4">Internship</option>
                          <option value="5">Office</option>
                        </select>
                      </div>
                    </div>
                    <div className="col-lg-6 col-md-6">
                      <div className="form-group">
                        <label>Application Deadline</label>
                        <div className="input-group date" id="datetimepicker">
                          <input
                            type="text"
                            className="form-control"
                            placeholder="12/11/2020"
                          />
                          <span className="input-group-addon"></span>
                          <i className="bx bx-calendar"></i>
                        </div>
                      </div>
                    </div>
                    <div className="col-lg-6 col-md-6">
                      <div className="form-group">
                        <label>Salary Currency</label>
                        <select className="select">
                          <option value="1">Default</option>
                          <option value="2">20000 To 30000</option>
                          <option value="3">40000 To 50000</option>
                          <option value="4">60000 To 70000</option>
                          <option value="5">80000 To 90000</option>
                        </select>
                      </div>
                    </div>
                    <div className="col-lg-12">
                      <div className="form-group">
                        <label>Job Description*</label>
                        <textarea
                          name="message"
                          className="form-control"
                          rows="5"
                        ></textarea>
                      </div>
                    </div>
                  </div>
                  <h3 className="title">Company Information</h3>
                  <div className="row">
                    <div className="col-lg-12">
                      <div className="form-group">
                        <label>Company Name</label>
                        <input
                          className="form-control"
                          type="text"
                          name="Company"
                        />
                      </div>
                    </div>
                    <div className="col-lg-6 col-md-6">
                      <div className="form-group">
                        <label>Company Website</label>
                        <input
                          className="form-control"
                          type="text"
                          name="Website"
                        />
                      </div>
                    </div>
                    <div className="col-lg-6 col-md-6">
                      <div className="form-group">
                        <label>Company Industry</label>
                        <input
                          className="form-control"
                          type="text"
                          name="Industry"
                        />
                      </div>
                    </div>
                    <div className="col-lg-6 col-md-6">
                      <div className="form-group">
                        <label>Facebook Page (Link)</label>
                        <input
                          className="form-control"
                          type="text"
                          name="Link"
                        />
                      </div>
                    </div>
                    <div className="col-lg-6 col-md-6">
                      <div className="form-group">
                        <label>Linkedin Page (Link)</label>
                        <input
                          className="form-control"
                          type="text"
                          name="Link"
                        />
                      </div>
                    </div>
                    <div className="col-lg-6 col-md-6">
                      <div className="form-group">
                        <label>Twitter Page (Link)</label>
                        <input
                          className="form-control"
                          type="text"
                          name="Link"
                        />
                      </div>
                    </div>
                    <div className="col-lg-6 col-md-6">
                      <div className="form-group">
                        <label>Instagram Page (Link)</label>
                        <input
                          className="form-control"
                          type="text"
                          name="Link"
                        />
                      </div>
                    </div>
                    <div className="col-lg-12">
                      <div className="form-group">
                        <label>Company Description*</label>
                        <textarea
                          name="message"
                          className="form-control"
                          rows="5"
                        ></textarea>
                      </div>
                    </div>
                    <div className="col-lg-12">
                      <div className="choose-img">
                        <p>Logo (Optional)</p>
                        <label htmlFor="img">Select image:</label>
                        <input
                          type="file"
                          id="img"
                          name="img"
                          accept="image/*"
                        />
                        <p>Maximum file size: 2 MB</p>
                      </div>
                    </div>
                  </div>
                  <h3 className="title">Recruiter Information</h3>
                  <div className="row">
                    <div className="col-lg-6 col-md-6">
                      <div className="form-group">
                        <label>Full Name</label>
                        <input
                          className="form-control"
                          type="text"
                          name="Name"
                        />
                      </div>
                    </div>
                    <div className="col-lg-6 col-md-6">
                      <div className="form-group">
                        <label>Email</label>
                        <input
                          className="form-control"
                          type="email"
                          name="email"
                        />
                      </div>
                    </div>
                    <div className="col-12">
                      <div className="form-group checkboxs">
                        <input
                          type="checkbox"
                          className="checkboxs"
                          id="chb2"
                        />
                        <p>
                          By clicking checkbox, you agree to our{" "}
                          <a href="terms-conditions.html">
                            Terms &amp; Conditions
                          </a>{" "}
                          And <a href="privacy-policy.html">Privacy Policy.</a>
                        </p>
                      </div>
                    </div>
                    <div className="col-lg-12 button">
                      <button className="btn">Post a Job</button>
                    </div>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
      </section>
    </>
  );
};

export default PostJob;
