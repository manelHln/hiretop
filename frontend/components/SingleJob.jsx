import Link from "next/link";
import React from "react";

const SingleJob = ({ job, width = "col-12" }) => {
  return (
    <Link
      href={`/jobs/${job.id}`}
      className={`${width} hover:text-slate-600`}
    >
      <div
        className="single-job wow fadeInUp"
        data-wow-delay=".3s"
        style={{
          visibility: "visible",
          animationDelay: "0.3s",
          animationName: "fadeInUp",
        }}
      >
        <div className="job-image">
          <img src={job?.company?.logo} alt="#" />
        </div>
        <div className="job-content">
          <h4>
            <a href="job-details.html">{job?.jobTitle}</a>
          </h4>
          <div className="w-full">
            <div className="line-clamp-2">{job?.description}</div>
          </div>
          <ul>
            <li>
              <i className="lni lni-website"></i>
              <a href="#">{job?.company?.companyWebsite}</a>
            </li>
            <li>
              <i className="lni lni-dollar"></i> {job?.salary}
            </li>
            <li>
              <i className="lni lni-map-marker"></i> {job?.jobLocation}
            </li>
          </ul>
        </div>
        <div className="job-button">
          <ul className="flex gap-2 items-center">
            <li>
              <a href="job-details.html">Apply</a>
            </li>
            <li>
              <span>{job?.jobType}</span>
            </li>
          </ul>
        </div>
      </div>
    </Link>
  );
};

export default SingleJob;
