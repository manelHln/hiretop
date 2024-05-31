"use client";

import React, { useEffect, useState } from "react";
import Link from "next/link";
import Loading from "@/components/LoadingComponent";
import { convertISOToDate } from "@/utils/utils";

const page = ({ params }) => {
  const [jobDetails, setJobDeatails] = useState({});
  const jobId = params.id;
  console.log(jobId);

  useEffect(() => {
    fetch(`${process.env.NEXT_PUBLIC_BASE_API_URL}jobs/${jobId}`)
      .then((res) => res.json())
      .then((data) => {
        if (data.status === "200") {
          console.log(data);
          setJobDeatails(data.content);
        }
      })
      .catch((e) => console.log(e));
  }, []);

  if (!jobDetails.id) {
    return <Loading />;
  }

  return (
    <>
      <div className="breadcrumbs overlay">
        <div className="container-sm">
          <div className="row">
            <div className="col-12">
              <div className="breadcrumbs-content">
                <h1 className="page-title">Job Details</h1>
                <p>
                  Business plan draws on a wide range of knowledge from
                  different business
                  <br /> disciplines. Business draws on a wide range of
                  different business .
                </p>
              </div>
              <ul className="breadcrumb-nav">
                <li>
                  <Link href="/">Home</Link>
                </li>
                <li>Job Details</li>
              </ul>
            </div>
          </div>
        </div>
      </div>

      <div className="job-details section">
        <div className="container-sm">
          <div className="row mb-n5">
            <div className="col-lg-8 col-12">
              <div className="job-details-inner">
                <div className="job-details-head row mx-0">
                  <div className="company-logo col-auto">
                    <a
                      href="#"
                      style={{ borderRadius: "4px", overflow: "hidden" }}
                    >
                      <img src={jobDetails.company?.logo} alt="Company Logo" />
                    </a>
                  </div>
                  <div className="salary-type col-auto order-sm-3">
                    <span className="salary-range">{jobDetails.salary}</span>
                    <span className="badge badge-success">
                      {jobDetails.jobLevel}
                    </span>
                  </div>
                  <div className="content col">
                    <h5 className="title text-lg font-bold">
                      {jobDetails.jobTitle}
                    </h5>
                    <ul className="meta">
                      <li>
                        <strong className="text-primary">
                          <Link href={jobDetails.company.companyWebsite || "#"}>
                            {jobDetails.company.name}
                          </Link>
                        </strong>
                      </li>
                      <li>
                        <i className="lni lni-map-marker"></i>{" "}
                        {jobDetails.location}
                      </li>
                    </ul>
                  </div>
                </div>
                <div className="job-details-body">
                  <h6 className="mb-3">Job Description</h6>
                  <div
                    className="text-sm text-slate-600 font-light"
                    dangerouslySetInnerHTML={{ __html: jobDetails.description }}
                  ></div>
                </div>
              </div>
            </div>

            <div className="col-lg-4 col-12">
              <div className="job-details-sidebar">
                <div className="sidebar-widget">
                  <div className="inner">
                    <div className="row m-n2 button">
                      <div className="col-xl-auto col-lg-12 col-sm-auto col-12 p-2">
                        <a href="bookmarked.html" className="d-block btn">
                          <i className="fa fa-heart-o mr-1"></i> Save Job
                        </a>
                      </div>
                      <div className="col-xl-auto col-lg-12 col-sm-auto col-12 p-2">
                        <a
                          href="job-details.html"
                          className="d-block btn btn-alt"
                        >
                          Apply Now
                        </a>
                      </div>
                    </div>
                  </div>
                </div>

                <div className="sidebar-widget">
                  <div className="inner">
                    <h6 className="title">Job Overview</h6>
                    <ul className="job-overview list-unstyled">
                      <li>
                        <strong>Published on:</strong>{" "}
                        {convertISOToDate(jobDetails.createdDate)}
                      </li>
                      <li>
                        <strong>Employment Status:</strong> {jobDetails.jobType}
                      </li>
                      <li>
                        <strong>Experience:</strong> {jobDetails.jobLevel}
                      </li>
                      <li>
                        <strong>Job Location:</strong> {jobDetails.location}
                      </li>
                      <li>
                        <strong>Salary:</strong> {jobDetails.salary}
                      </li>
                    </ul>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default page;
