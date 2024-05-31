"use client";

import React, { useState, useEffect } from "react";
import AccountResume from "@/components/AccountResume";
import AnalyticsApplicant from "@/components/AnalyticsApplicant";
import Applications from "@/components/Applications";
import Bookmarks from "@/components/Bookmarks";
import ChangePassword from "@/components/ChangePassword";
import ManageJobs from "@/components/ManageJobs";
import AnalyticsComapany from "@/components/AnalyticsComapany";
import { useSession } from "next-auth/react";
import { signOut } from "next-auth/react";
import CompanyResume from "@/components/CompanyResume";
import axios from "axios";
import { useToast } from "@/components/ui/use-toast";

const tabsApplicant = [
  { id: "resume", label: "My Resume", icon: "lni lni-clipboard" },
  { id: "applications", label: "See Applications", icon: "lni lni-envelope" },
  { id: "analytics", label: "Analytics", icon: "lni lni-stats-up" },
  { id: "manageJobs", label: "Manage Jobs", icon: "lni lni-briefcase" },
];

const tabsCompany = [
  { id: "resume_company", label: "My Resume", icon: "lni lni-clipboard" },
  {
    id: "applications",
    label: "Manage Applications",
    icon: "lni lni-envelope",
  },
  { id: "analytics_company", label: "Analytics", icon: "lni lni-stats-up" },
  { id: "manageJobs", label: "Manage Jobs", icon: "lni lni-briefcase" },
  { id: "changePassword", label: "Change Password", icon: "lni lni-lock" },
];

const Page = () => {
  const { toast } = useToast();
  const [activeTab, setActiveTab] = useState("");
  const [userDetails, setUserDeatails] = useState({});
  const [shouldUpdate, setShouldUpdate] = useState(false)
  const { data: session, status } = useSession({
    required: true,
  });

  useEffect(() => {
    if (status !== "authenticated") {
      return;
    }
    if(!session){
      return
    }
    if (session.user.role === "COMPANY") {
      setActiveTab("resume_company");
    } else {
      setActiveTab("resume");
    }
    axios
      .get(`${process.env.NEXT_PUBLIC_BASE_API_URL}user`, {
        headers: {
          Authorization: `Bearer ${session.accessToken}`,
        },
      })
      .then((res) => {
        console.log(res)
        setUserDeatails(res.data.content);
      })
      .catch((error) => {
        toast({
          title: "Error",
          description: error.response?.data?.error?.message || error.message,
          status: "destructive",
        });
      })
      .finally();
  }, [status, session, shouldUpdate]);

  const renderContent = () => {
    switch (activeTab) {
      case "resume":
        return <AccountResume userDetails={userDetails} setShouldUpdate={setShouldUpdate} />;
      case "resume_company":
        return <CompanyResume details={userDetails} setShouldUpdate={setShouldUpdate} />;
      case "applications":
        return <Applications />;
      case "analytics":
        return <AnalyticsApplicant />;
      case "analytics_company":
        return <AnalyticsComapany />;
      case "manageJobs":
        return <ManageJobs />;
      case "changePassword":
        return <ChangePassword />;
    }
  };

  const tabs = activeTab === "COMPANY" ? tabsCompany : tabsApplicant;

  return (
    <>
      <div className="breadcrumbs overlay">
        <div className="container-sm">
          <div className="row">
            <div className="col-12">
              <div className="breadcrumbs-content">
                <h1 className="page-title">Resume</h1>
                <p>
                  Business plan draws on a wide range of knowledge from
                  different business
                  <br /> disciplines. Business draws on a wide range of
                  different business.
                </p>
              </div>
              <ul className="breadcrumb-nav">
                <li>
                  <a href="/">Home</a>
                </li>
                <li>Resume</li>
              </ul>
            </div>
          </div>
        </div>
      </div>

      <div className="section">
        <div className="container-sm">
          <div className="resume-inner">
            <div className="row">
              <div className="col-lg-4 col-12">
                <div className="dashbord-sidebar">
                  <ul>
                    <li className="heading">Manage Account</li>
                    {tabs.map((tab) => (
                      <li key={tab.id}>
                        <a
                          className={activeTab === tab.id ? "active" : ""}
                          onClick={() => setActiveTab(tab.id)}
                        >
                          <i className={tab.icon}></i> {tab.label}
                          {tab.notificationCount && (
                            <span className="notifi">
                              {tab.notificationCount}
                            </span>
                          )}
                        </a>
                      </li>
                    ))}
                    <li>
                      <a href="#" onClick={() => signOut()}>
                        <i className="lni lni-upload"></i>
                        Sign Out
                      </a>
                    </li>
                  </ul>
                </div>
              </div>
              {renderContent()}
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default Page;
