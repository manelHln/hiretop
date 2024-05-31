"use client";

import React, { useState } from "react";
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { Label } from "@/components/ui/label";
import { Tabs, TabsContent, TabsList, TabsTrigger } from "@/components/ui/tabs";
import { useToast } from "@/components/ui/use-toast";
import { z } from "zod";
import InputWithIcon from "@/components/InputWithIcon";
import LoadingButton from "@/components/LoadingButton";
import { AtSign } from "lucide-react";
import { signIn } from "next-auth/react";

const page = () => {
  const { toast } = useToast();
  const [companyFormData, setCompanyFormData] = useState({
    email: "",
    password: "",
  });

  const [formData, setFormData] = useState({
    email: "",
    password: "",
  });

  const [activeTab, setActiveTab] = useState("");
  const [isLoading, setIsLoading] = useState(false);
  const [isLoadingComp, setIsLoadingComp] = useState(false);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleCompanyChange = (e) => {
    const { name, value } = e.target;
    setCompanyFormData({ ...companyFormData, [name]: value });
  };

  const handleSubmitApplicant = async (e) => {
    e.preventDefault();

    setIsLoading(true)
    const res = await signIn("credentials", {
      redirect: true,
      email: formData.email,
      password: formData.password,
      userType: "applicant",
      callbackUrl: "/account",
    });
    console.log(res);
    if (res.status == "200") {
      toast({
        title: "Login Successful",
        description: "You have successfully logged in as an applicant.",
        status: "success",
      });
    } else {
      toast({
        title: "Login Failed",
        description: res.error || "An error occurred during login.",
        status: "error",
      });
    }
    setIsLoading(false)
  };

  const handleSubmitCompany = async (e) => {
    e.preventDefault();

    setIsLoadingComp(true)

    const res = await signIn("credentials", {
      redirect: true,
      email: companyFormData.email,
      password: companyFormData.password,
      userType: "org",
      callbackUrl: "/account",
    });

    if (res.status == "200") {
      toast({
        title: "Login Successful",
        description: "You have successfully logged in as a company.",
        status: "success",
      });
    } else {
      toast({
        title: "Login Failed",
        description: res.error || "An error occurred during login.",
        status: "error",
      });
    }
    setIsLoadingComp(false)
  };

  return (
    <section className="py-24 md:py-32 bg-light flex items-center justify-center min-h-96">
      <Tabs
        defaultValue="applicant"
        className="w-[450px]"
        onValueChange={(value) => setActiveTab(value)}
      >
        <TabsList className="grid w-full grid-cols-2">
          <TabsTrigger value="applicant">Applicant</TabsTrigger>
          <TabsTrigger value="company">Company</TabsTrigger>
        </TabsList>
        <TabsContent value="applicant">
          <Card>
            <CardHeader>
              <CardTitle>Login</CardTitle>
            </CardHeader>
            <CardContent className="space-y-2">
              <form onSubmit={handleSubmitApplicant}>
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
                  />
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
                  />
                </div>
                <LoadingButton
                  className="py-3 px-7 flex gap-2 justify-center items-center transition-all duration-300 ease-in-out mb-6 w-full text-base text-white font-normal text-center leading-6 bg-blue-600 rounded-md hover:bg-black"
                  type="submit"
                  value="Login"
                  isLoading={isLoading}
                />
                <p className="text-center flex flex-wrap items-center justify-center gap-3">
                  <span className="text-sm text-deep font-normal">
                    Not yet a member?
                  </span>
                  <a
                    className="inline-block text-sm font-normal text-themePrimary hover:text-green-600 hover:underline"
                    href="/register"
                  >
                    Register
                  </a>
                </p>
              </form>
            </CardContent>
          </Card>
        </TabsContent>
        <TabsContent value="company">
          <Card>
            <CardHeader>
              <CardTitle>Login</CardTitle>
            </CardHeader>
            <CardContent className="space-y-2">
              <form onSubmit={handleSubmitCompany}>
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
                  />
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
                  />
                </div>
                <LoadingButton
                  className="py-3 px-7 flex gap-2 justify-center items-center transition-all duration-300 ease-in-out mb-6 w-full text-base text-white font-normal text-center leading-6 bg-blue-600 rounded-md hover:bg-black"
                  type="submit"
                  value="Login"
                  isLoading={isLoadingComp}
                />
                <p className="text-center flex flex-wrap items-center justify-center gap-3">
                  <span className="text-sm text-deep font-normal">
                    Not yet a member?
                  </span>
                  <a
                    className="inline-block text-sm font-normal text-themePrimary hover:text-green-600 hover:underline"
                    href="/register"
                  >
                    Register
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
