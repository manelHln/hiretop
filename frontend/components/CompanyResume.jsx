"use client";

import React, { useEffect, useState } from "react";
import UserResumeEdit from "@/components/UserResumeEdit";
import { Pencil } from "lucide-react";
import axios from "axios";
import { useToast } from "./ui/use-toast";
import { useSession } from "next-auth/react";

const CompanyResume = ({ details, setShouldUpdate }) => {
  const { data: session } = useSession();
  const { toast } = useToast();
  const [logoData, setLogoData] = useState(null);

  const handlePictureUpload = (e) => {
    const file = e.target.files[0];
    // const formData = new FormData();
    // formData.append("file", file);

    axios
      .post(
        `${process.env.NEXT_PUBLIC_BASE_API_URL}companies/add-logo`,
        {
          file: file,
        },
        {
          headers: {
            Authorization: `Bearer ${session.accessToken}`,
            "Content-Type": "multipart/form-data",
          },
        }
      )
      .then((res) => {})
      .catch((err) => {
        toast({
          title: "Error",
          description: err.response?.data?.error?.message || err.message,
          status: "destructive",
        });
      });
  };

  useEffect(() => {
    if (!details.logo) {
      return;
    }
    axios
      .get(`${process.env.NEXT_PUBLIC_BASE_API_URL}files/${details.logo}`, {
        headers: {
          Authorization: `Bearer ${session.accessToken}`,
        },
      })
      .then((res) => {
        setLogoData(res.data);
        setShouldUpdate(true)
      })
      .catch((err) => {
        toast({
          title: "Error",
          description: err.response?.data?.error?.message || err.message,
          status: "destructive",
        });
      });
  }, [details]);

  return (
    <div className="col-lg-8 col-12 resume">
      <div className="inner-content">
        <div className="personal-top-content">
          <div className="row">
            <div className="col-lg-5 col-md-5 col-12">
              <div className="name-head">
                <div className="mb-2 relative rounded-lg">
                  <img
                    className="circle-54"
                    src={logoData? `data:image/png;base64,${logoData}` : "/images/company.jpg"}
                    alt=""
                  />
                  <label
                    htmlFor="profile-pic"
                    className="absolute top-0 bottom-0 left-0 right-0 flex justify-center items-center hover:bg-slate-300 hover:opacity-50 duration-300  rounded-lg group cursor-pointer"
                  >
                    <Pencil
                      size={30}
                      className="hidden text-slate-600 group-hover:block"
                    />
                  </label>
                  <input
                    type="file"
                    className="hidden"
                    id="profile-pic"
                    onChange={handlePictureUpload}
                  />
                </div>
                <h4>
                  <a className="name" href="#">
                    {details?.name}
                  </a>
                </h4>
                <p>
                  <a className="deg" href="#">
                    {details?.founded}
                  </a>
                </p>
                <ul className="social">
                  <li>
                    <a href={details?.facebook}>
                      <i className="lni lni-facebook-original"></i>
                    </a>
                  </li>
                  <li>
                    <a href={details?.twitter}>
                      <i className="lni lni-twitter-original"></i>
                    </a>
                  </li>
                  <li>
                    <a href={details?.linkedin}>
                      <i className="lni lni-linkedin-original"></i>
                    </a>
                  </li>
                  <li>
                    <a href={details?.dribble}>
                      <i className="lni lni-dribbble"></i>
                    </a>
                  </li>
                  <li>
                    <a href={details?.pinterest}>
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
                  <p>{details?.location}</p>
                </div>
                <div className="single-list">
                  <h5 className="title">E-mail</h5>
                  <p>{details?.email}</p>
                </div>
                <div className="single-list">
                  <h5 className="title">Phone</h5>
                  <p>{details?.phoneNumber}</p>
                </div>
                <div className="single-list">
                  <h5 className="title">Website Linked</h5>
                  <p>
                    <a href={details?.companyWebsite}>
                      {details?.companyWebsite}
                    </a>
                  </p>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div className="single-section">
          <h4>About</h4>
          <p className="font-size-4 mb-8">{details?.history}</p>
        </div>
        <div className="single-section skill">
          <h4>Industries</h4>
          <ul className="list-unstyled d-flex align-items-center flex-wrap">
            {details?.industries?.map((industry, index) => (
              <li>
                <a href="#" key={index}>
                  {industry.name}
                </a>
              </li>
            ))}
          </ul>
        </div>
      </div>
    </div>
  );
};

export default CompanyResume;
