import React from "react";
import { Button } from "./ui/button";
import { cn } from "@/lib/utils";
import { Loader2 } from "lucide-react";

const LoadingButton = ({ value, isLoading, className, ...props }) => {
  return (
    <Button className={cn("flex justify-center items-center", className)} props disabled={isLoading}>
      {isLoading ? <Loader2 className="animate-spin" size={20} /> : value}
    </Button>
  );
};

export default LoadingButton;
