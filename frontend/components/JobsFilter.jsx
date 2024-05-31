"use client";

import { useState, useEffect } from "react";

import {
  Accordion,
  AccordionContent,
  AccordionItem,
  AccordionTrigger,
} from "@/components/ui/accordion";
import { useToast } from "./ui/use-toast";
import Select from "react-select";
import makeAnimated from "react-select/animated";
import { RadioGroup, RadioGroupItem } from "@/components/ui/radio-group";
import { Label } from "@/components/ui/label";
import { Input } from "./ui/input";
import { Button } from "./ui/button";
import Icon from "./Icon";

const jobTypes = [
  "FULL TIME",
  "PART TIME",
  "FREELANCE",
  "INTERNSHIP",
  "CONTRACT",
];

const jobLevels = ["ENTRY", "MID", "SENIOR", "EXPERT"];

const animatedComponents = makeAnimated();

const JobsFilter = ({ onFilter }) => {
  const [categories, setCategories] = useState([]);
  const [skills, setSkills] = useState([]);

  const [toFilterValues, setToFilterValues] = useState({
    categories: [],
    skills: [],
    jobTitle: "",
    jobLevel: "",
    salaryFrom: null,
    salaryTo: null,
    createdDate: null
  })

  const { toast } = useToast();

  useEffect(() => {
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

    // Fetch skills
    fetch(`${process.env.NEXT_PUBLIC_BASE_API_URL}skills`)
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
        setSkills(data.content);
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
    <ul>
      <li className="heading flex text-blue-600 font-semibold gap-2">
        <Icon name="arrow-down-0-1" /> Filter Jobs
      </li>
      <li>Job title</li>
      <Input type="search" placeholder="developer" onChange={(e)=> setToFilterValues(prev => ({...prev, jobTitle: e.target.value}))} />
      <li className="mt-4">Categories</li>
      <Select
        closeMenuOnSelect={false}
        components={animatedComponents}
        isMulti
        options={categories}
        getOptionLabel={(option) => `${option.name}`}
        getOptionValue={(option) => `${option.name}`}
        onChange={(val)=> setToFilterValues(prev => ({...prev, categories: val.map(v => v.name)}))}
        className="z-20"
      />

      <li className="mt-4">Skills</li>
      <Select
        closeMenuOnSelect={false}
        components={animatedComponents}
        isMulti
        options={skills}
        getOptionLabel={(option) => `${option.name}`}
        getOptionValue={(option) => `${option.name}`}
        onChange={(val)=> setToFilterValues(prev => ({...prev, skills: val.map(v => v.name)}))}
        className="z-10"
      />
      <Accordion type="multiple" collapsible className="w-full">
        {/* <AccordionItem value="item-1">
          <AccordionTrigger>Job type</AccordionTrigger>
          <AccordionContent>
            <RadioGroup onValueChange={(val)=> setToFilterValues(prev => ({...prev, jobType: val}))}>
              {jobTypes.map((type, index) => {
                return (
                  <div className="flex items-center space-x-2" key={type}>
                    <RadioGroupItem value={type} id={type} />
                    <Label htmlFor={type}>{type}</Label>
                  </div>
                );
              })}
            </RadioGroup>
          </AccordionContent>
        </AccordionItem> */}
        <AccordionItem value="item-2">
          <AccordionTrigger>Job level</AccordionTrigger>
          <AccordionContent>
            <RadioGroup onValueChange={(val)=> setToFilterValues(prev => ({...prev, jobLevel: val}))}>
              {jobLevels.map((level, index) => {
                return (
                  <div className="flex items-center space-x-2" key={level}>
                    <RadioGroupItem value={level} id={level} />
                    <Label htmlFor={level}>{level}</Label>
                  </div>
                );
              })}
            </RadioGroup>
          </AccordionContent>
        </AccordionItem>
      </Accordion>

      <Button className="mt-4" onClick={()=> onFilter(toFilterValues)}>
        Apply
      </Button>
    </ul>
  );
};

export default JobsFilter;
