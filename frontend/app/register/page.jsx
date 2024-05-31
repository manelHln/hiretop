"use client";

import React, { useState } from "react";
import axios from "axios";
import { useRouter } from "next/navigation";
import { Button } from "@/components/ui/button";
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { Label } from "@/components/ui/label";
import { Tabs, TabsContent, TabsList, TabsTrigger } from "@/components/ui/tabs";
import { useToast } from "@/components/ui/use-toast";
import { z } from "zod";
import { format } from "date-fns";
import { CalendarIcon } from "lucide-react";
import InputWithIcon from "@/components/InputWithIcon";
import LoadingButton from "@/components/LoadingButton";
import { UserRound } from "lucide-react";
import { AtSign } from "lucide-react";
import { PersonStanding } from "lucide-react";
import {
  Select,
  SelectTrigger,
  SelectValue,
  SelectContent,
  SelectGroup,
  SelectItem,
} from "@/components/ui/select";
import { Calendar } from "@/components/CustomCalendar";
import {
  Popover,
  PopoverContent,
  PopoverTrigger,
} from "@/components/ui/popover";

const page = () => {
  const { toast } = useToast();
  const router = useRouter();
  const [companyFormData, setCompanyFormData] = useState({
    name: "",
    email: "",
    companyWebsite: "",
    password: "",
  });

  const [formData, setFormData] = useState({
    fullName: "",
    email: "",
    password: "",
    birthDate: "",
    gender: "",
  });

  const [isLoading, setIsLoading] = useState(false);
  const [isLoadingComp, setIsLoadingComp] = useState(false);

  const [errors, setErrors] = useState({});
  const [compnayFormErrors, setCompnayFormErrors] = useState({});

  const passwordValidation =
    /^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[#?!@$%^&*-]).{8,}$/;

  const companyFormSchema = z.object({
    name: z.string().min(3, { message: "Company name is required" }),
    email: z.string().email({ message: "Invalid email format" }),
    password: z
      .string()
      .min(8, { message: "Password must be at least 8 characters long" })
      .regex(passwordValidation, {
        message:
          "Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character",
      }),
    companyWebsite: z.string().nonempty({ message: "Birth date is required" }),
  });

  const formSchema = z.object({
    fullName: z.string().min(3, { message: "Fullname is required" }),
    email: z.string().email({ message: "Invalid email format" }),
    password: z
      .string()
      .min(8, { message: "Password must be at least 8 characters long" })
      .regex(passwordValidation, {
        message:
          "Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character",
      }),
    birthDate: z.string().date({ message: "Birth date is required" }),
    gender: z.string().min(4, { message: "Gender is required" }),
  });

  function checkZodErrors() {
    const validationResult = formSchema.safeParse(formData);
    if (!validationResult.success) {
      const fieldErrors = {};
      validationResult.error.errors.forEach((error) => {
        fieldErrors[error.path[0]] = error.message;
      });
      setErrors(fieldErrors);
      return false;
    }
    return true;
  }

  function checkZodCompanyFormErrors() {
    const validationResult = companyFormSchema.safeParse(companyFormData);
    if (!validationResult.success) {
      const fieldErrors = {};
      validationResult.error.errors.forEach((error) => {
        fieldErrors[error.path[0]] = error.message;
      });
      setCompnayFormErrors(fieldErrors);
      return false;
    }
    return true;
  }

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
    setErrors((prev) => ({ ...prev, [name]: null }));
  };

  const handleCompanyChange = (e) => {
    const { name, value } = e.target;
    setCompanyFormData({ ...companyFormData, [name]: value });
    setCompnayFormErrors((prev) => ({ ...prev, [name]: null }));
  };

  const handleBirthDateSelected = (date) => {
    setFormData((prev) => ({
      ...prev,
      birthDate: date.toISOString().split("T")[0],
    }));
  };

  const handleSubmitApplicant = (e) => {
    e.preventDefault();

    if (!checkZodErrors()) {
      return;
    }
    console.log("first");

    setErrors({});
    setIsLoading(true);

    axios
      .post(
        `${process.env.NEXT_PUBLIC_BASE_API_URL}auth/register/applicant`,
        formData
      )
      .then((result) => {
        if (result.data.status == "200") {
          toast({
            title: "Registration Successful",
            description:
              "You have successfully registered. Now, redirecting to login page",
            status: "success",
          });
          router.push("/login");
        }
      })
      .catch((error) => {
        toast({
          title: "Login Failed",
          description: error.response?.data?.error?.message || error.message,
          status: "error",
        });
      })
      .finally(() => setIsLoading(false));
  };

  const handleSubmitCompany = (e) => {
    e.preventDefault();

    if (!checkZodCompanyFormErrors()) {
      return;
    }

    setErrors({});

    setIsLoadingComp(true);

    axios
      .post(
        `${process.env.NEXT_PUBLIC_BASE_API_URL}auth/register/org`,
        companyFormData
      )
      .then((result) => {
        if (result.data.status == "200") {
          toast({
            title: "Registration Successful",
            description:
              "You have successfully registered. Now, redirecting to login page",
            status: "success",
          });
          router.push("/login");
        }
      })
      .catch((error) => {
        toast({
          title: "Login Failed",
          description: error.response?.data?.error?.message || error.message,
          status: "error",
        });
      })
      .finally(() => setIsLoadingComp(false));
  };

  return (
    <section className="py-24 md:py-32 bg-light flex items-center justify-center min-h-96">
      <Tabs defaultValue="applicant" className="w-[450px]">
        <TabsList className="grid w-full grid-cols-2">
          <TabsTrigger value="applicant">Applicant</TabsTrigger>
          <TabsTrigger value="company">Company</TabsTrigger>
        </TabsList>
        <TabsContent value="applicant">
          <Card>
            <CardHeader>
              <CardTitle>Register</CardTitle>
            </CardHeader>
            <CardContent className="space-y-2">
              <form onSubmit={handleSubmitApplicant}>
                <div className="mb-4">
                  <Label htmlFor="fullName" className="mb-2">
                    Enter your full name
                  </Label>
                  <InputWithIcon
                    type="text"
                    placeholder="John Doe"
                    name="fullName"
                    value={formData.fullName}
                    onChange={handleChange}
                    required
                    startIcon={UserRound}
                    error={errors.fullName}
                  />
                  {errors.fullName && (
                    <span className="text-red-500">{errors.fullName}</span>
                  )}
                </div>
                <div className="mb-4">
                  <Label htmlFor="fullName" className="mb-2">
                    Enter your email address
                  </Label>
                  <InputWithIcon
                    type="email"
                    placeholder="john@gmail.com"
                    name="email"
                    value={formData.email}
                    onChange={handleChange}
                    required
                    startIcon={AtSign}
                    error={errors.email}
                  />
                  {errors.email && (
                    <span className="text-red-500">{errors.email}</span>
                  )}
                </div>
                <div className="mb-4">
                  <Label htmlFor="fullName" className="mb-2">
                    Enter your password
                  </Label>
                  <InputWithIcon
                    type="password"
                    name="password"
                    value={formData.password}
                    onChange={handleChange}
                    placeholder="*************"
                    required
                    error={errors.password}
                  />
                  {errors.password && (
                    <span className="text-red-500">{errors.password}</span>
                  )}
                </div>
                <div className="mb-4">
                  <Label htmlFor="username" className="mb-2">
                    Gender
                  </Label>
                  <Select
                    onValueChange={(val) =>
                      setFormData((prev) => ({ ...prev, gender: val }))
                    }
                  >
                    <SelectTrigger className="p-1">
                      <SelectValue placeholder="Select your gender" />
                    </SelectTrigger>
                    <SelectContent>
                      <SelectGroup>
                        <SelectItem value="male">
                          <div className="flex items-center">
                            <PersonStanding className="text-muted-foreground mr-1" />
                            Male
                          </div>
                        </SelectItem>
                        <SelectItem value="female">
                          <div className="flex items-center">
                            <PersonStanding className="text-muted-foreground mr-1" />
                            Female
                          </div>
                        </SelectItem>
                      </SelectGroup>
                    </SelectContent>
                  </Select>
                  {errors.gender && (
                    <span className="text-red-500">{errors.gender}</span>
                  )}
                </div>
                <div className="mb-4">
                  <Label className="mb-2">Date of birth</Label>
                  <Popover>
                    <PopoverTrigger asChild>
                      <Button
                        variant={"outline"}
                        className="pl-1 text-left font-normal flex w-full justify-start gap-2 items-center"
                      >
                        <CalendarIcon
                          size={18}
                          className="text-muted-foreground"
                        />
                        {formData.birthDate ? (
                          format(formData.birthDate, "PPP")
                        ) : (
                          <span>Pick a date</span>
                        )}
                      </Button>
                    </PopoverTrigger>
                    <PopoverContent className="w-auto p-0" align="start">
                      <Calendar
                        mode="single"
                        selected={formData.birthDate}
                        onSelect={handleBirthDateSelected}
                        defaultMonth={new Date(2002, 0)}
                        toMonth={new Date(2006, 0)}
                        captionLayout="dropdown-buttons"
                        fromYear={1960}
                        toYear={2006}
                        disabled={(date) =>
                          date > new Date() || date < new Date("1900-01-01")
                        }
                        initialFocus
                      />
                    </PopoverContent>
                  </Popover>
                </div>
                <LoadingButton
                  className="py-3 px-7 flex gap-2 justify-center items-center transition-all duration-300 ease-in-out mb-6 w-full text-base text-white font-normal text-center leading-6 bg-blue-600 rounded-md hover:bg-black"
                  type="submit"
                  value="Register"
                  isLoading={isLoading}
                />
                <p className="text-center flex flex-wrap items-center justify-center gap-3">
                  <span className="text-sm text-deep font-normal">
                    Already a member?
                  </span>
                  <a
                    className="inline-block text-sm font-normal text-themePrimary hover:text-green-600 hover:underline"
                    href="/login/applicant"
                  >
                    Login
                  </a>
                </p>
              </form>
            </CardContent>
          </Card>
        </TabsContent>
        <TabsContent value="company">
          <Card>
            <CardHeader>
              <CardTitle>Register</CardTitle>
            </CardHeader>
            <CardContent className="space-y-2">
              <form onSubmit={handleSubmitCompany}>
                <div className="mb-4">
                  <Label htmlFor="name" className="mb-2">
                    Enter the name of the company
                  </Label>
                  <InputWithIcon
                    type="text"
                    placeholder="Stripe"
                    name="name"
                    value={companyFormData.name}
                    onChange={handleCompanyChange}
                    required
                    startIcon={UserRound}
                    error={compnayFormErrors.name}
                  />
                  {compnayFormErrors.name && (
                    <span className="text-red-500">
                      {compnayFormErrors.name}
                    </span>
                  )}
                </div>
                <div className="mb-4">
                  <Label htmlFor="fullName" className="mb-2">
                    Enter your email address
                  </Label>
                  <InputWithIcon
                    type="email"
                    placeholder="name@org.com"
                    name="email"
                    value={companyFormData.email}
                    onChange={handleCompanyChange}
                    required
                    startIcon={AtSign}
                    error={compnayFormErrors.email}
                  />
                  {compnayFormErrors.email && (
                    <span className="text-red-500">
                      {compnayFormErrors.email}
                    </span>
                  )}
                </div>
                <div className="mb-4">
                  <Label htmlFor="name" className="mb-2">
                    Enter company website
                  </Label>
                  <InputWithIcon
                    type="text"
                    placeholder="www.stripe.com"
                    name="companyWebsite"
                    value={companyFormData.companyWebsite}
                    onChange={handleCompanyChange}
                    required
                    startIcon={UserRound}
                    error={compnayFormErrors.companyWebsite}
                  />
                  {compnayFormErrors.companyWebsite && (
                    <span className="text-red-500">
                      {compnayFormErrors.companyWebsite}
                    </span>
                  )}
                </div>
                <div className="mb-4">
                  <Label htmlFor="fullName" className="mb-2">
                    Enter your password
                  </Label>
                  <InputWithIcon
                    type="password"
                    name="password"
                    value={companyFormData.password}
                    onChange={handleCompanyChange}
                    placeholder="*************"
                    required
                    error={compnayFormErrors.password}
                  />
                  {compnayFormErrors.password && (
                    <span className="text-red-500">
                      {compnayFormErrors.password}
                    </span>
                  )}
                </div>
                <LoadingButton
                  className="py-3 px-7 flex gap-2 justify-center items-center transition-all duration-300 ease-in-out mb-6 w-full text-base text-white font-normal text-center leading-6 bg-blue-600 rounded-md hover:bg-black"
                  type="submit"
                  value="Register"
                  isLoading={isLoadingComp}
                />
                <p className="text-center flex flex-wrap items-center justify-center gap-3">
                  <span className="text-sm text-deep font-normal">
                    Already a member?
                  </span>
                  <a
                    className="inline-block text-sm font-normal text-themePrimary hover:text-green-600 hover:underline"
                    href="/login/applicant"
                  >
                    Login
                  </a>
                </p>
              </form>
            </CardContent>
          </Card>
        </TabsContent>
      </Tabs>
    </section>
  );
};

export default page;
