import { Loader2 } from "lucide-react";

const Loading = () => {
  return (
    <div className="fixed top-0 bottom-0 right-0 left-0 z-50 bg-slate-100 opacity-80">
      <div className="absolute top-1/2 left-1/2 translate-y-0 scale-100">
        <Loader2 className="animate-spin h-12 w-12 text-slate-500" />
      </div>
    </div>
  );
};

export default Loading;
