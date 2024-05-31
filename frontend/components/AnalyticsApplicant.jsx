import React from "react";

const AnalyticsApplicant = () => {
  return (
    <div className="col-lg-8 change-password">
      <div className="password-content">
        <div className="flex gap-4 flex-wrap mb-4">
          <div className="block max-w-sm p-4 bg-white border border-gray-200 rounded-lg shadow-sm">
            <h5 className="mb-2 text-2xl font-semibold tracking-tight text-gray-900 dark:text-white">
              Jobs applied
            </h5>
            <p className="font-normal text-6xl text-gray-700 dark:text-gray-400">
              54
            </p>
          </div>

          <div className="block max-w-sm p-4 bg-white border border-gray-200 rounded-lg shadow-sm">
            <h5 className="mb-2 text-2xl font-semibold tracking-tight text-gray-900 dark:text-white">
              Jobs in review
            </h5>
            <p className="font-normal text-6xl text-gray-700 dark:text-gray-400">
              54
            </p>
          </div>

          <div className="block max-w-sm p-4 bg-white border border-gray-200 rounded-lg shadow-sm">
            <h5 className="mb-2 text-2xl font-semibold tracking-tight text-gray-900 dark:text-white">
              Jobs Rejected
            </h5>
            <p className="font-normal text-6xl text-gray-700 dark:text-gray-400">
              54
            </p>
          </div>
        </div>

        <h2 className="font-bold text-lg mb-4">Recents applications</h2>

        <div className="manage-applications">
          <div className="job-items p-0">
            <div className="manage-content p-3">
              <div className="row align-items-center justify-content-center">
                <div className="col-lg-5 col-md-5">
                  <div className="title-img">
                    <div className="can-img">
                      <img src="assets/images/jobs/manage-job1.png" alt="#" />
                    </div>
                    <h3>
                      Mechanical Engineer <span>Conzio construction</span>
                    </h3>
                  </div>
                </div>
                <div className="col-lg-2 col-md-2">
                  <p>
                    <span className="time">Full-Time</span>
                  </p>
                </div>
                <div className="col-lg-3 col-md-3">
                  <p>Nov 14th, 2023</p>
                </div>
                <div className="col-lg-2 col-md-2">
                  <p>Rejected</p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default AnalyticsApplicant;
