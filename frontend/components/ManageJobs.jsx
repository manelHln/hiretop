import React from "react";

const ManageJobs = () => {
  return (
    <div className="col-lg-8 col-12 manage-jobs">
      <div className="job-items">
        <div className="manage-list">
          <div className="row">
            <div className="col-lg-3 col-md-3 col-12">
              <p>Name</p>
            </div>
            <div className="col-lg-3 col-md-3 col-12">
              <p>Contract Type</p>
            </div>
            <div className="col-lg-3 col-md-3 col-12">
              <p>Candidates</p>
            </div>
            <div className="col-lg-3 col-md-3 col-12">
              <p>Featured</p>
            </div>
          </div>
        </div>
        <div className="manage-content">
          <div className="row align-items-center justify-content-center">
            <div className="col-lg-3 col-md-3 col-12">
              <h3>Web Designer</h3>
            </div>
            <div className="col-lg-3 col-md-3 col-12">
              <p>
                <span className="time">Full-Time</span>
              </p>
            </div>
            <div className="col-lg-3 col-md-3 col-12">
              <div className="can-img">
                <a href="#">
                  <img src="assets/images/jobs/candidates.png" alt="#" />
                </a>
              </div>
            </div>
            <div className="col-lg-3 col-md-3 col-12">
              <p>
                <i className="lni lni-star"></i>
              </p>
            </div>
          </div>
        </div>
        <div className="manage-content">
          <div className="row align-items-center justify-content-center">
            <div className="col-lg-3 col-md-3 col-12">
              <h3>UI/UX designer</h3>
            </div>
            <div className="col-lg-3 col-md-3 col-12">
              <p>
                <span className="time">Full-Time</span>
              </p>
            </div>
            <div className="col-lg-3 col-md-3 col-12">
              <div className="can-img">
                <a href="#">
                  <img src="assets/images/jobs/candidates.png" alt="#" />
                </a>
              </div>
            </div>
            <div className="col-lg-3 col-md-3 col-12">
              <p>
                <i className="lni lni-star"></i>
              </p>
            </div>
          </div>
        </div>
        <div className="manage-content">
          <div className="row align-items-center justify-content-center">
            <div className="col-lg-3 col-md-3 col-12">
              <h3>Developer</h3>
            </div>
            <div className="col-lg-3 col-md-3 col-12">
              <p>
                <span className="time">Full-Time</span>
              </p>
            </div>
            <div className="col-lg-3 col-md-3 col-12">
              <div className="can-img">
                <a href="#">
                  <img src="assets/images/jobs/candidates.png" alt="#" />
                </a>
              </div>
            </div>
            <div className="col-lg-3 col-md-3 col-12">
              <p>
                <i className="lni lni-star"></i>
              </p>
            </div>
          </div>
        </div>
        <div className="manage-content">
          <div className="row align-items-center justify-content-center">
            <div className="col-lg-3 col-md-3 col-12">
              <h3>Senior UX Designer</h3>
            </div>
            <div className="col-lg-3 col-md-3 col-12">
              <p>
                <span className="time">Full-Time</span>
              </p>
            </div>
            <div className="col-lg-3 col-md-3 col-12">
              <div className="can-img">
                <a href="#">
                  <img src="assets/images/jobs/candidates.png" alt="#" />
                </a>
              </div>
            </div>
            <div className="col-lg-3 col-md-3 col-12">
              <p>
                <i className="lni lni-star"></i>
              </p>
            </div>
          </div>
        </div>
        <div className="manage-content">
          <div className="row align-items-center justify-content-center">
            <div className="col-lg-3 col-md-3 col-12">
              <h3>Graphics Design</h3>
            </div>
            <div className="col-lg-3 col-md-3 col-12">
              <p>
                <span className="time">Full-Time</span>
              </p>
            </div>
            <div className="col-lg-3 col-md-3 col-12">
              <div className="can-img">
                <a href="#">
                  <img src="assets/images/jobs/candidates.png" alt="#" />
                </a>
              </div>
            </div>
            <div className="col-lg-3 col-md-3 col-12">
              <p>
                <i className="lni lni-star"></i>
              </p>
            </div>
          </div>
        </div>
        <div className="manage-content">
          <div className="row align-items-center justify-content-center">
            <div className="col-lg-3 col-md-3 col-12">
              <h3>Sales Manager</h3>
            </div>
            <div className="col-lg-3 col-md-3 col-12">
              <p>
                <span className="time">Part-Time</span>
              </p>
            </div>
            <div className="col-lg-3 col-md-3 col-12">
              <div className="can-img">
                <a href="#">
                  <img src="assets/images/jobs/candidates.png" alt="#" />
                </a>
              </div>
            </div>
            <div className="col-lg-3 col-md-3 col-12">
              <p>
                <i className="lni lni-star"></i>
              </p>
            </div>
          </div>
        </div>
      </div>
      <div className="pagination left pagination-md-center">
        <ul className="pagination-list">
          <li>
            <a href="#">
              <i className="lni lni-arrow-left"></i>
            </a>
          </li>
          <li className="active">
            <a href="#">1</a>
          </li>
          <li>
            <a href="#">2</a>
          </li>
          <li>
            <a href="#">3</a>
          </li>
          <li>
            <a href="#">4</a>
          </li>
          <li>
            <a href="#">
              <i className="lni lni-arrow-right"></i>
            </a>
          </li>
        </ul>
      </div>
    </div>
  );
};

export default ManageJobs;
