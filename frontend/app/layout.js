"use client";

import { useState, useEffect, Suspense } from "react";
import Footer from "@/components/Footer";
import Navbar from "@/components/Navbar";
import BackToTopButton from "@/components/BackToTopButton";
import "../style/bootstrap.min.css";
import "../style/main.css";
import "../style/animate.css";
import "../style/LineIcons.2.0.css";
import "./globals.css";
import { BootstrapClient } from "@/components/JsFiles";
import { Toaster } from "@/components/ui/toaster";
import { SessionProvider } from "next-auth/react";

const Layout = ({ children }) => {
  const [showButton, setShowButton] = useState(false);

  if (typeof window !== "undefined") {
    window.addEventListener("scroll", () => {
      window.scrollY > 500 ? setShowButton(true) : setShowButton(false);
    });
  }

  return (
    <html lang="en">
      <body>
        <SessionProvider>
          <BackToTopButton showButton={showButton} />
          <Navbar />
          {children}
          <BootstrapClient />
          <Footer />
          <Toaster />
        </SessionProvider>
      </body>
    </html>
  );
};

export default Layout;
