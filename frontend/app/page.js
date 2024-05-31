"use client";

import { useState, useEffect } from "react";
import SingleJob from "@/components/SingleJob";
import Link from "next/link";
import { useRouter } from "next/navigation";

const Home = () => {
  const [categories, setCategories] = useState([]);
  const [jobs, setJobs] = useState([]);
  const [searchterm, setSearchTerm] = useState("")
  const router = useRouter()

  const handleSearch = () => {
    router.push(`/jobs?q=${searchterm}`)
  }

  useEffect(() => {
    fetch(`${process.env.NEXT_PUBLIC_BASE_API_URL}jobs?page=0&size=6`)
      .then((res) => res.json())
      .then((data) => setJobs(data.content))
      .catch((e) => {
        console.log(e);
      });

    fetch(`${process.env.NEXT_PUBLIC_BASE_API_URL}categories/`)
      .then((res) => res.json())
      .then((data) => {
        if (data.error) {
          toast({
            title: "Oops, something went wrong",
            description: data.error.message,
            variant: "destructive",
          });
          return;
        }
        setCategories(data.content);
      })
      .catch((e) => {
        toast({
          title: "Oops, something went wrong",
          description: e.message,
          variant: "destructive",
        });
      });
  }, []);

  return (
    <>
      <section className="hero-area">
        <div className="hero-inner">
          <div className="container-sm">
            <div className="row ">
              <div className="col-lg-6 co-12">
                <div className="inner-content">
                  <div className="hero-text">
                    <h1 className="wow fadeInUp" data-wow-delay=".3s">
                      Find Your Career <br />
                      to Make a Better Life
                    </h1>
                    <p className="wow fadeInUp" data-wow-delay=".5s">
                      Creating a beautiful job website is not easy <br />{" "}
                      always. To make your life easier, we are
                      <br /> introducing Jobcamp template.
                    </p>
                  </div>
                  <div
                    className="job-search-wrap-two mt-50 wow fadeInUp"
                    data-wow-delay=".7s"
                  >
                    <div className="job-search-form">
                      <form action={handleSearch}>
                        <div className="single-field-item keyword">
                          <label htmlFor="keyword">What</label>
                          <input
                            id="keyword"
                            placeholder="What jobs you want?"
                            name="keyword"
                            type="text"
                            value={searchterm}
                            onChange={(e)=> setSearchTerm(e.target.value)}
                          />
                        </div>
                        <div className="submit-btn">
                          <button className="btn" type="submit">
                            Search
                          </button>
                        </div>
                      </form>
                    </div>
                    {/* <div className="trending-keywords mt-30">
                                    <div className="keywords style-two">
                                        <span className="title">Popular Keywords:</span>
                                        <ul>
                                            <li><a href="#">Administrative</a></li>
                                            <li><a href="#">Android</a></li>
                                            <li><a href="#">app</a></li>
                                            <li><a href="#">ASP.NET</a></li>
                                        </ul>
                                    </div>
                                </div> */}
                  </div>
                </div>
              </div>
              <div className="col-lg-6 co-12">
                <div
                  className="hero-video-head wow fadeInRight"
                  data-wow-delay=".5s"
                >
                  <div className="video-inner">
                    <img src="/images/hero/hero-image.png" alt="#" />
                    {/* <a href="https://www.youtube.com/watch?v=cz4z8CyvDas" className="glightbox hero-video"><i
                                        className="lni lni-play"></i></a> */}
                    {/* <div className="promo-video">
                                    <div className="waves-block">
                                        <div className="waves wave-1"></div>
                                        <div className="waves wave-2"></div>
                                        <div className="waves wave-3"></div>
                                    </div>
                                </div> */}
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>

      <section className="apply-process section">
        <div className="container-sm">
          <div className="row">
            <div className="col-lg-4 col-md-4 col-12">
              <div className="process-item">
                <i className="lni lni-user"></i>
                <h4>Register Your Account</h4>
                <p>
                  Lorem Ipsum is simply dummy text of the printing and
                  typesetting industry.
                </p>
              </div>
            </div>
            <div className="col-lg-4 col-md-4 col-12">
              <div className="process-item">
                <i className="lni lni-book"></i>
                <h4>Upload Your Resume</h4>
                <p>
                  Lorem Ipsum is simply dummy text of the printing and
                  typesetting industry.
                </p>
              </div>
            </div>
            <div className="col-lg-4 col-md-4 col-12">
              <div className="process-item">
                <i className="lni lni-briefcase"></i>
                <h4>Apply for Dream Job</h4>
                <p>
                  Lorem Ipsum is simply dummy text of the printing and
                  typesetting industry.
                </p>
              </div>
            </div>
          </div>
        </div>
      </section>

      <section className="job-category section">
        <div className="container-sm">
          <div className="row">
            <div className="col-12">
              <div className="section-title">
                <span className="wow fadeInDown" data-wow-delay=".2s">
                  Job Category
                </span>
                <h2 className="wow fadeInUp" data-wow-delay=".4s">
                  Choose Your Desire Category
                </h2>
                <p className="wow fadeInUp" data-wow-delay=".6s">
                  There are many variations of passages of Lorem Ipsum
                  available, but the majority have suffered alteration in some
                  form.
                </p>
              </div>
            </div>
          </div>
          <div className="cat-head">
            <div className="row">
              <div className="col-lg-3 col-md-6 col-12">
                <a
                  href="job-details.html"
                  className="single-cat wow fadeInUp"
                  data-wow-delay=".2s"
                >
                  <div className="icon">
                    <i className="lni lni-cog"></i>
                  </div>
                  <h3>
                    Technical
                    <br /> Support
                  </h3>
                </a>
              </div>
              <div className="col-lg-3 col-md-6 col-12">
                <a
                  href="job-details.html"
                  className="single-cat wow fadeInUp"
                  data-wow-delay=".4s"
                >
                  <div className="icon">
                    <i className="lni lni-layers"></i>
                  </div>
                  <h3>
                    Business
                    <br /> Development
                  </h3>
                </a>
              </div>
              <div className="col-lg-3 col-md-6 col-12">
                <a
                  href="job-details.html"
                  className="single-cat wow fadeInUp"
                  data-wow-delay=".6s"
                >
                  <div className="icon">
                    <i className="lni lni-home"></i>
                  </div>
                  <h3>
                    Real Estate
                    <br /> Business
                  </h3>
                </a>
              </div>
              <div className="col-lg-3 col-md-6 col-12">
                <a
                  href="job-details.html"
                  className="single-cat wow fadeInUp"
                  data-wow-delay=".8s"
                >
                  <div className="icon">
                    <i className="lni lni-search"></i>
                  </div>
                  <h3>
                    Share Maeket
                    <br /> Analysis
                  </h3>
                </a>
              </div>
              <div className="col-lg-3 col-md-6 col-12">
                <a
                  href="job-details.html"
                  className="single-cat wow fadeInUp"
                  data-wow-delay=".2s"
                >
                  <div className="icon">
                    <i className="lni lni-investment"></i>
                  </div>
                  <h3>
                    Finance & Banking <br /> Service
                  </h3>
                </a>
              </div>
              <div className="col-lg-3 col-md-6 col-12">
                <a
                  href="job-details.html"
                  className="single-cat wow fadeInUp"
                  data-wow-delay=".4s"
                >
                  <div className="icon">
                    <i className="lni lni-cloud-network"></i>
                  </div>
                  <h3>
                    IT & Networing <br /> Sevices
                  </h3>
                </a>
              </div>
              <div className="col-lg-3 col-md-6 col-12">
                <a
                  href="job-details.html"
                  className="single-cat wow fadeInUp"
                  data-wow-delay=".6s"
                >
                  <div className="icon">
                    <i className="lni lni-restaurant"></i>
                  </div>
                  <h3>
                    Restaurant <br /> Services
                  </h3>
                </a>
              </div>
              <div className="col-lg-3 col-md-6 col-12">
                <a
                  href="job-details.html"
                  className="single-cat wow fadeInUp"
                  data-wow-delay=".8s"
                >
                  <div className="icon">
                    <i className="lni lni-fireworks"></i>
                  </div>
                  <h3>
                    Defence & Fire <br /> Service
                  </h3>
                </a>
              </div>
            </div>
          </div>
        </div>
      </section>

      <section className="find-job section">
        <div className="container-sm">
          <div className="row">
            <div className="col-12">
              <div className="section-title">
                <span
                  className="wow fadeInDown"
                  data-wow-delay=".2s"
                  style={{
                    visibility: "visible",
                    animationDelay: "0.2s",
                    animationName: "fadeInDown",
                  }}
                >
                  Hot Jobs
                </span>
                <h2
                  className="wow fadeInUp"
                  data-wow-delay=".4s"
                  style={{
                    visibility: "visible",
                    animationDelay: "0.4s",
                    animationName: "fadeInUp",
                  }}
                >
                  Browse Recent Jobs
                </h2>
                <p
                  className="wow fadeInUp"
                  data-wow-delay=".6s"
                  style={{
                    visibility: "visible",
                    animationDelay: "0.6s",
                    animationName: "fadeInUp",
                  }}
                >
                  There are many variations of passages of Lorem Ipsum
                  available, but the majority have suffered alteration in some
                  form.
                </p>
              </div>
            </div>
          </div>
          <div className="single-head">
            <div className="row">
              {jobs?.map((job) => (
                <SingleJob job={job} key={job.id} width="col-lg-6 col-12" />
              ))}
            </div>
            <div className="flex justify-center items-center mt-10">
              <Link
                href="/jobs"
                className="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-lg px-5 py-2.5 me-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800"
              >
                View all jobs
              </Link>
            </div>
          </div>
        </div>
      </section>
    </>
  );
};

export default Home;
