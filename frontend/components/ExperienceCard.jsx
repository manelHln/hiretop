import React from "react";

const ExperienceCard = ({details}) => {
  return (
    <div className="single-exp mb-30">
      <div className="d-flex align-items-center pr-11 mb-9 flex-wrap flex-sm-nowrap">
        <div className="w-100 mt-n2">
          <h3 className="mb-0">
            <a href="#">{details.role}</a>
          </h3>
          <a href="#">{details.company}</a>
          <div className="d-flex align-items-center justify-content-md-between flex-wrap">
            <a href="#">{details.from} - {details.to}</a>
            <a href="#" className="font-size-3 text-gray">
              <span className="mr-2" style={{ marginTop: "-2px" }}>
                <i className="lni lni-map-marker"></i>
              </span>
              {details.location}
            </a>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ExperienceCard;
