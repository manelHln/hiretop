"use client";

import Link from "next/link";
import Image from "next/image";
import { useEffect, useState } from "react";
import { useSession } from "next-auth/react";
import {
  DropdownMenu,
  DropdownMenuTrigger,
  DropdownMenuContent,
  DropdownMenuSeparator,
  DropdownMenuItem,
  DropdownMenuLabel,
} from "./ui/dropdown-menu";
import { Button } from "./ui/button";
import { signOut } from "next-auth/react";

const Navbar = () => {
  const [toggleMobileNav, setToggleMobileNav] = useState(false);
  const { data: session } = useSession();

  return (
    <header className="header other-page">
      <div className="navbar-area">
        <div className="container-sm">
          <div className="row align-items-center">
            <div className="col-lg-12">
              <nav className="navbar navbar-expand-lg">
                <Link className="navbar-brand logo" href="/">
                  <img
                    className="logo1"
                    src="/images/logo/logo.svg"
                    alt="Logo"
                  />
                </Link>

                <div
                  className={`collapse navbar-collapse sub-menu-bar visible ${
                    toggleMobileNav ? "show" : ""
                  }`}
                  id="navbarSupportedContent"
                >
                  <ul id="nav" className="navbar-nav ml-auto">
                    <li
                      className="nav-item"
                      onClick={() => setToggleMobileNav(false)}
                    >
                      <Link className="active" href="/">
                        Home
                      </Link>
                    </li>
                    <li
                      className="nav-item"
                      onClick={() => setToggleMobileNav(false)}
                    >
                      <Link href="/jobs">Jobs</Link>
                    </li>
                    <li
                      className="nav-item"
                      onClick={() => setToggleMobileNav(false)}
                    >
                      <Link href="/companies">Companies</Link>
                    </li>
                  </ul>
                </div>

                <div className="button">
                  {session?.user ? (
                    <DropdownMenu>
                      <DropdownMenuTrigger asChild>
                        <Button
                          variant="outline"
                          size="icon"
                          className="overflow-hidden rounded-full"
                        >
                          <Image
                            src="/images/placeholder-user.webp"
                            width={36}
                            height={36}
                            alt="Avatar"
                            className="overflow-hidden rounded-full"
                          />
                        </Button>
                      </DropdownMenuTrigger>
                      <DropdownMenuContent align="end" className="z-50">
                        <DropdownMenuSeparator />
                        <DropdownMenuItem>
                          <Link href="/account">Dashboard</Link>
                        </DropdownMenuItem>
                        <DropdownMenuSeparator />
                        <DropdownMenuItem onClick={() => signOut()}>
                          Logout
                        </DropdownMenuItem>
                      </DropdownMenuContent>
                    </DropdownMenu>
                  ) : (
                    <>
                      <Link href="/login" className="login">
                        <i className="lni lni-lock-alt"></i>
                        Login
                      </Link>
                      <Link href="/register" className="btn">
                        Sign Up
                      </Link>
                    </>
                  )}
                </div>
                <button
                  className="navbar-toggler"
                  type="button"
                  aria-controls="navbarSupportedContent"
                  aria-expanded="false"
                  aria-label="Toggle navigation"
                  onClick={() => setToggleMobileNav((prev) => !prev)}
                >
                  <span className="toggler-icon"></span>
                  <span className="toggler-icon"></span>
                  <span className="toggler-icon"></span>
                </button>
              </nav>
            </div>
          </div>
        </div>
      </div>
    </header>
  );
};

export default Navbar;

// ("use client");
// import React, { useState } from "react";
// import Button from "./Button";
// import Link from "next/link";
// import { MenuIcon } from "lucide-react";
// import { XIcon } from "lucide-react";
// import { motion } from "framer-motion";

// const navLinks = [
//   {
//     name: "Features",
//     href: "#features",
//   },
//   {
//     name: "About",
//     href: "#about",
//   },
//   {
//     name: "Chat",
//     href: "/chat",
//   },
//   {
//     name: "Posts",
//     href: "/posts",
//   },
// ];

// const Navbar = () => {
//   const [toggleMobileNav, setToggleMobileNav] = useState(false);
//   return (
//     <div className="sticky top-0 flex justify-between items-center px-8 py-4 sm:px-40 shadow-sm bg-white w-full z-10">
//       <div>edu.Exchange</div>
//       <ul className="hidden sm:flex items-center gap-8 text-sm">
//         {navLinks.map((e, i) => (
//           <li key={`${e.name}-${i}`}>
//             <Link href={e.href}>{e.name}</Link>
//           </li>
//         ))}
//       </ul>
//       <div className="hidden sm:flex gap-4 items-center">
//         <Button
//           text={"Login"}
//           className="cursor-pointer hover:underline text-sm"
//           to="/login"
//         />
//         <Button
//           text={"Get started Now"}
//           className="cursor-pointer py-2 px-4 rounded-md text-white bg-custom-orange text-sm"
//           to="register"
//         />
//       </div>

//       <div className="flex sm:hidden justify-center items-center w-9 h-9 rounded-[50%] relative">
//         <MenuIcon
//           className="cursor-pointer text-black w-[70%] height-[70%]"
//           onClick={() => setToggleMobileNav(true)}
//         />
//         {toggleMobileNav && (
//           <motion.div
//             whileInView={{ x: [300, 0] }}
//             transition={{ duration: 0.85, ease: "easeOut" }}
//             className="fixed bg-slate-100 top-0 bottom-0 right-0 z-20 p-4 w-4/5 h-screen flex flex-col justify-end items-end shadow-sm"
//           >
//             <XIcon
//               className="cursor-pointer w-9 h-9 my-2 mx-4"
//               onClick={() => setToggleMobileNav(false)}
//             />
//             <ul className="flex sm:hidden flex-col justify-start items-start w-full h-full gap-2 text-sm">
//               {["Demos", "About", "Blog", "Pages"].map((e, i) => (
//                 <li key={`${e}-${i}`}>
//                   <Link href={"/"}>{e}</Link>
//                 </li>
//               ))}
//             </ul>
//           </motion.div>
//         )}
//       </div>
//     </div>
//   );
// };

// export default Navbar;
