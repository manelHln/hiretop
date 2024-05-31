import { ChevronUp } from "lucide-react";

const BackToTopButton = ({ showButton }) => {
  return (
    <button
      className={`fixed bottom-0 right-0 grid mb-4 mr-4 z-30 rounded-full shadow back-to-top-btn w-9 h-9 place-items-center bg-primary shadow-primary/60 text-white ${
        showButton && "active"
      }`}
      onClick={() => window.scrollTo(0, 0)}
    >
      <ChevronUp />
    </button>
  );
};

export default BackToTopButton;
