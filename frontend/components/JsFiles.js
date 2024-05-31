"use client";

import { useEffect } from "react";

function BootstrapClient() {
  useEffect(() => {
    require("@/public/js/main");
  }, []);

  return null;
}

export { BootstrapClient };
