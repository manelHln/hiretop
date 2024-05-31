"use client";

import Pagination from "@/components/Pagination";
import SingleJob from "@/components/SingleJob";
import Skeleton from "@/components/Skeleton";
import Link from "next/link";
import { useEffect, useState } from "react";
import Icon from "@/components/Icon";
import JobsFilter from "@/components/JobsFilter";
import axios from "axios";

const JobsPage = () => {
  const [jobs, setJobs] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const [currentPage, setCurrentPage] = useState(1);
  const [totalPages, setTotalPages] = useState(1);
  const [isFiltering, setIsFiltering] = useState(false);
  const [filterValues, setFilterValue] = useState(null);

  const fetchJobs = (page) => {
    setIsLoading(true);
    fetch(
      `${process.env.NEXT_PUBLIC_BASE_API_URL}jobs?page=${page - 1}&size=10`
    )
      .then((res) => res.json())
      .then((data) => {
        setJobs(data.content);
        setTotalPages(data.totalPages);
        setIsLoading(false);
      })
      .catch((e) => {
        console.log(e);
        setIsLoading(false);
      });
  };

  useEffect(() => {
    fetchJobs(currentPage);
  }, [currentPage]);

  const handlePageChange = (pageNumber) => {
    setCurrentPage(pageNumber);
  };

  const handleFilter = () => {
    let categories, skills;
    if (filterValues.categories) {
      categories = filterValues.categories.join(",");
    }
    if (filterValues.skills) {
      skills = filterValues.skills.join(",");
    }
    setIsLoading(true);

    const url = new URL(`${process.env.NEXT_PUBLIC_BASE_API_URL}jobs/filter`);

    if (categories) {
      url.searchParams.append("categories", categories);
    }

    if (skills) {
      url.searchParams.append("skills", skills);
    }

    if (filterValues.jobTitle) {
      url.searchParams.append("jobTitle", filterValues.jobTitle);
    }
    if (
      filterValues.salaryFrom !== null &&
      filterValues.salaryFrom !== undefined
    ) {
      url.searchParams.append("salaryFrom", filterValues.salaryFrom);
    }
    if (filterValues.salaryTo !== null && filterValues.salaryTo !== undefined) {
      url.searchParams.append("salaryTo", filterValues.salaryTo);
    }
    if (filterValues.createdDate) {
      url.searchParams.append("createdDate", filterValues.createdDate);
    }
    if (filterValues.jobLevel) {
      url.searchParams.append("jobLevel", filterValues.jobLevel);
    }

    fetch(url.toString())
      .then((res) => res.json())
      .then((data) => {
        setJobs(data.content);
        setTotalPages(data.totalPages);
      })
      .catch((e) => {
        console.log(e);
        setIsLoading(false);
      })
      .finally(() => {
        setIsLoading(false);
        setIsFiltering(false);
      });
  };

  useEffect(() => {
    if (!isFiltering) {
      return;
    }
    handleFilter();
  }, [isFiltering]);

  const onFilter = (values) => {
    setFilterValue(values);
    setIsFiltering(true);
  };

  return (
    <>
      <div className="breadcrumbs overlay">
        <div className="container-sm">
          <div className="row">
            <div className="col-12">
              <div className="breadcrumbs-content">
                <h1 className="page-title">Job List</h1>
                <p>
                  Business plan draws on a wide range of knowledge from
                  different business
                  <br /> disciplines. Business draws on a wide range of
                  different business.
                </p>
              </div>
              <ul className="breadcrumb-nav">
                <li>
                  <Link href="/">Home</Link>
                </li>
                <li>Job List</li>
              </ul>
            </div>
          </div>
        </div>
      </div>

      <section className="find-job job-list section">
        <div className="container">
          <div className="single-head">
            <div className="row">
              <div className="col-lg-4 col-12">
                <div className="dashbord-sidebar">
                  <JobsFilter onFilter={onFilter} />
                </div>
              </div>

              <div className="col-lg-8 col-12">
                {isLoading ? (
                  <div className="mt-4 grid grid-cols-1 gap-4">
                    {Array.apply(null, { length: 4 }).map((_, i) => (
                      <Skeleton key={i} />
                    ))}
                  </div>
                ) : (
                  jobs?.map((job) => <SingleJob job={job} key={job.id} />)
                )}
              </div>
              <Pagination
                currentPage={currentPage}
                onPageChange={handlePageChange}
                totalPages={totalPages}
              />
            </div>
          </div>
        </div>
      </section>
    </>
  );
};

export default JobsPage;
