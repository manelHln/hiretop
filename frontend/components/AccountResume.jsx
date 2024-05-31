import React, { useEffect } from "react";
import UserResumeEdit from "@/components/UserResumeEdit";
import ExperienceCard from "./ExperienceCard";
import EducationCard from "./EducationCard";
import { Pencil } from "lucide-react";

const AccountResume = ({ userDetails, setShouldUpdate }) => {
  return (
    <div className="col-lg-8 col-12 resume">
      <div className="inner-content">
        <div className="personal-top-content">
          <div className="row">
            <div className="col-lg-5 col-md-5 col-12">
              <div className="name-head">
                <a className="mb-2 relative rounded-lg" href="#">
                  <img
                    className="circle-54"
                    src={userDetails?.profilePicture || "/images/hero/hero-image.jpg"}
                    alt=""
                  />
                  <div className="absolute top-0 bottom-0 left-0 right-0 z-10 flex justify-center items-center hover:bg-slate-300 hover:opacity-50 duration-300 rounded-lg group">
                    <Pencil size={30} className="hidden text-slate-600 group-hover:block" />
                  </div>
                </a>
                <h4>
                  <a className="name" href="#">
                    {userDetails?.fullName}
                  </a>
                </h4>
                <p>
                  <a className="deg" href="#">
                    {userDetails?.vocation}
                  </a>
                </p>
                <ul className="social">
                  <li>
                    <a href={userDetails?.facebook}>
                      <i className="lni lni-facebook-original"></i>
                    </a>
                  </li>
                  <li>
                    <a href={userDetails?.twitter}>
                      <i className="lni lni-twitter-original"></i>
                    </a>
                  </li>
                  <li>
                    <a href={userDetails?.linkedin}>
                      <i className="lni lni-linkedin-original"></i>
                    </a>
                  </li>
                  <li>
                    <a href={userDetails?.dribble}>
                      <i className="lni lni-dribbble"></i>
                    </a>
                  </li>
                  <li>
                    <a href={userDetails?.pinterest}>
                      <i className="lni lni-pinterest"></i>
                    </a>
                  </li>
                </ul>
              </div>
            </div>
            <div className="col-lg-7 col-md-7 col-12">
              <div className="content-right">
                <div className="flex justify-between items-start title-main">
                  <h5 className="">Contact Info</h5>
                  <UserResumeEdit />
                </div>
                <div className="single-list">
                  <h5 className="title">Location</h5>
                  <p>{userDetails?.location}</p>
                </div>
                <div className="single-list">
                  <h5 className="title">E-mail</h5>
                  <p>{userDetails?.email}</p>
                </div>
                <div className="single-list">
                  <h5 className="title">Phone</h5>
                  <p>{userDetails?.phoneNumber}</p>
                </div>
                <div className="single-list">
                  <h5 className="title">Website Linked</h5>
                  <p>
                    <a href={userDetails?.portfolio}>
                      {userDetails?.portfolio}
                    </a>
                  </p>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div className="single-section">
          <h4>About</h4>
          <p className="font-size-4 mb-8">{userDetails?.about}</p>
        </div>
        <div className="single-section skill">
          <h4>Skills</h4>
          <ul className="list-unstyled d-flex align-items-center flex-wrap">
            {userDetails?.skills?.map((skill, index) => (
              <li>
                <a href="#" key={index}>
                  {skill.name}
                </a>
              </li>
            ))}
          </ul>
        </div>
        <div className="single-section exprerience">
          <h4>Work Exprerience</h4>
          {userDetails?.experiences?.map((exp, index) => (
            <ExperienceCard details={exp} key={index} />
          ))}
        </div>
        <div className="single-section education">
          <h4>Education</h4>
          {userDetails?.educations?.map((edu, index) => (
            <EducationCard details={edu} key={index} />
          ))}
        </div>
      </div>
    </div>
  );
};

export default AccountResume;
