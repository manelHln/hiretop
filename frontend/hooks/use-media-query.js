import React from "react";

export default function useMediaQuery(query) {
    const [matches, setMatches] = React.useState(false);

    React.useEffect(() => {
        const matchQueryList = window.matchMedia(query);

        const handleChange = (e) => {
            setMatches(e.matches);
        };

        matchQueryList.addEventListener("change", handleChange);

        setMatches(matchQueryList.matches);

        return () => {
            matchQueryList.removeEventListener("change", handleChange);
        };
    }, [query]);

    return matches;
}
