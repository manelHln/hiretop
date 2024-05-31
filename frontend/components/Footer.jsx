import Link from "next/link";
import React from "react";

const Footer = () => {
  return (
    <footer className="footer">
      <div className="footer-middle">
        <div className="container-sm">
          <div className="row">
            <div className="col-lg-4 col-md-6 col-12">
              <div className="f-about single-footer">
                <div className="logo">
                  <a href="index.html">
                    <img src="/images/logo/logo.svg" alt="Logo" />
                  </a>
                </div>
                <p>
                  Find your dream remote job.
                </p>
                <ul className="contact-address">
                  <li>
                    <span>Address:</span> Cotonou Benin
                  </li>
                  <li>
                    <span>Email:</span> holonouemmanuel0@gmail.com
                  </li>
                  <li>
                    <span>Call:</span> 229 96 42 42 45
                  </li>
                </ul>
                <div className="footer-social">
                  <ul>
                    <li>
                      <Link href="#">
                        <i className="lni lni-facebook-original"></i>
                      </Link>
                    </li>
                    <li>
                      <Link href="#">
                        <i className="lni lni-twitter-original"></i>
                      </Link>
                    </li>
                    <li>
                      <Link href="#">
                        <i className="lni lni-linkedin-original"></i>
                      </Link>
                    </li>
                    <li>
                      <Link href="#">
                        <i className="lni lni-pinterest"></i>
                      </Link>
                    </li>
                  </ul>
                </div>
              </div>
            </div>
            <div className="col-lg-8 col-12">
              <div className="row">
                <div className="col-lg-4 col-md-6 col-12">
                  <div className="single-footer f-link">
                    <h3>For Applicant</h3>
                    <ul>
                      <li>
                        <Link href="resume.html">User Dashboard</Link>
                      </li>
                      <li>
                        <Link href="#">Browse Jobs</Link>
                      </li>
                    </ul>
                  </div>
                </div>
                <div className="col-lg-4 col-md-6 col-12">
                  <div className="single-footer f-link">
                    <h3>For Employers</h3>
                    <ul>
                      <li>
                        <Link href="/account">Dashboard</Link>
                      </li>
                      <li>
                        <a href="/orgs">Employer List</a>
                      </li>
                      <li>
                        <Link href="/jobs">Jobs Listing</Link>
                      </li>
                    </ul>
                  </div>
                </div>
                <div className="col-lg-4 col-md-6 col-12">
                  <div className="single-footer newsletter">
                    <h3>Join Our Newsletter</h3>
                    <p>
                      Subscribe to get the latest jobs posted, candidates...
                    </p>
                    <form
                      className="newsletter-inner"
                    >
                      <input
                        name="email"
                        placeholder="Your email address"
                        className="common-input"
                        onFocus={(e)=>e.target.placeholder = ""}
                        onBlur={(e)=> e.target.placeholder = 'Your email address'}
                        required
                        type="email"
                      />
                      <div className="button">
                        <button className="btn">
                          Subscribe Now! <span className="dir-part"></span>
                        </button>
                      </div>
                    </form>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div className="footer-bottom">
        <div className="container-sm">
          <div className="inner">
            <div className="row">
              <div className="col-lg-6 col-md-6 col-12">
                <div className="left">
                  <p>
                    Developed by
                    <Link
                      href="https://github.com/manelHln/"
                      rel="nofollow"
                      target="_blank"
                    >
                      Emmanuel
                    </Link>
                  </p>
                </div>
              </div>
              <div className="col-lg-6 col-md-6 col-12">
                <div className="right">
                  <ul>
                    <li>
                      <Link href="#">Terms of use</Link>
                    </li>
                    <li>
                      <Link href="#"> Privacy Policy</Link>
                    </li>
                    <li>
                      <Link href="#">Faq</Link>
                    </li>
                    <li>
                      <Link href="#">Contact</Link>
                    </li>
                  </ul>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </footer>
  );
};

export default Footer;
